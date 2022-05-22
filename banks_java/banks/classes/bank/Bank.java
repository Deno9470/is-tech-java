package banks.classes.bank;
import banks.classes.Transaction;
import banks.classes.account.Account;
import banks.classes.client.Client;
import banks.tools.BanksException;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Bank {

    private List<Client> _clients = new ArrayList<>();
    private List<Account> _accounts = new ArrayList<>();
    private List<Transaction> _internalTransactions = new ArrayList<>();
    private List<Client> _subs = new ArrayList<>();
    public String BankName;

    public double DebitInterest;

    public double DepositInterest;

    public double CreditCommission;

    public double UntrustedTransactionLimit;

    public double CreditLimit;

    public int DepositTerm;

    public void addClient(Client client) {
        _clients.add(client);
    }

    public void addAccount(Account account) {
        account.setId(_accounts.size());
        _accounts.add(account);
    }

    public void deposit(Account account, double sum) {
        account.deposit(sum);
    }

    public void withdraw(Account account, double sum) throws BanksException {
        try {
            account.withdraw(sum);
        } catch (BanksException e) {
            throw new BanksException(e.getMessage());
        }
    }

    public void makeTransactionInSameBank(Account sender, Account receiver, double sum) throws BanksException {
        if ((sender.getOwner().getPassportInfo() == null || sender.getOwner().getAddress() == null) && sum > UntrustedTransactionLimit)
            throw new BanksException("An untrusted account cannot exceed the transaction limit.");
        if (sender.getBalance() < sum)
            throw new BanksException("There are not enough funds in the account");
        sender.forceWithdraw(sum);
        receiver.deposit(sum);
        var transaction = new Transaction(_internalTransactions.size(), sum, sender, receiver, false);
        _internalTransactions.add(transaction);
    }

    public void cancelTransactionSameBank(int id) throws BanksException {
        Transaction transaction = _internalTransactions.stream().filter(tr -> tr.getId() == id).findFirst().orElse(null);
        if (transaction == null || transaction.getCancelledStatus()) {
            throw new BanksException("Transaction can not be canceled.");
        }

        transaction.getSender().deposit(transaction.getSum());
        transaction.getReceiver().forceWithdraw(transaction.getSum());
        transaction.setCancelledStatus();
        _internalTransactions.remove(transaction);
    }

    public Account getAccount(int id) throws BanksException {
        Account account = _accounts.stream().filter(acc -> acc.getId() == id).findFirst().orElse(null);
        if (account == null) {
            throw new BanksException("This account doesn't exists");
        }

        return account;
    }

    public Client getClient(String name) throws BanksException {
        Client client = _clients.stream().filter(cl -> cl.getName().equals(name)).findFirst().orElse(null);

        if (client == null) {
            throw new BanksException("This client doesn't exists");
        }

        return client;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(new ArrayList(_accounts));
    }

    public List<Transaction> getSendedTransactions(Account sender) {
        List<Transaction> tmp = _internalTransactions.stream().filter(transaction -> transaction.getSender().equals(sender)).collect(Collectors.toList());
        tmp = Collections.unmodifiableList(tmp);
        return tmp;
    }

    public List<Transaction> getReceivedTransactions(Account receiver) {
        List<Transaction> tmp = _internalTransactions.stream().filter(transaction -> transaction.getReceiver().equals(receiver)).collect(Collectors.toList());
        tmp = Collections.unmodifiableList(tmp);
        return tmp;
    }

    public void updateTime(int month) {
        for (Account ac : _accounts) {
            ac.applyCommissionsAndInterest(month);
        }
    }

    public void subscribeToNotifications(Client client) {
        _subs.add(client);
    }

    public void notifySubscribers(String info) {
        for (Client client : _subs) {
            client.changeNotifications(info);
        }
    }
}
