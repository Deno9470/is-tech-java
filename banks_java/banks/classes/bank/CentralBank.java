package banks.classes.bank;

import banks.classes.Transaction;
import banks.classes.account.Account;
import banks.tools.BanksException;

import java.util.ArrayList;
import java.util.List;

public class CentralBank {
    private List<Bank> _banks = new ArrayList<>();
    private List<Transaction> _transactions = new ArrayList<>();

    private int _transactionId = 0;

    public int DateNow;

    public void addBank(Bank bank) throws BanksException {
        if (_banks.contains(bank)) {
            throw new BanksException("This bank is already exists.");
        }

        _banks.add(bank);
    }

    public Bank getBank(String bankName) throws  BanksException{
        Bank bank = _banks.stream().filter(b -> b.BankName.equals(bankName)).findFirst().orElse(null);

        if (bank == null) {
            throw new BanksException("This bank does not exists.");
        }

        return bank;
    }

    public void makeTransaction(Account sender, Account receiver, double sum) throws BanksException {
        Bank bankSender = _banks.stream().filter(bn -> bn.getAccounts().contains(sender)).findFirst().orElse(null);
        if (bankSender == null) {
            throw new BanksException("Internal error");
        }

        if ((sender.getOwner().getAddress() == null || sender.getOwner().getPassportInfo() == null) &&
                sum > bankSender.UntrustedTransactionLimit) {
            throw new BanksException("An untrusted account cannot exceed the transaction limit.");
        }

        if (sender.getBalance() < sum) {
            throw new BanksException("There are not enough funds in the account");
        }

        sender.forceWithdraw(sum);
        receiver.deposit(sum);
        var transaction = new Transaction(_transactionId, sum, sender, receiver, false);
        _transactions.add(transaction);
        _transactionId++;
    }

    public void cancelTransaction(int transactionId) throws BanksException {
        var transaction = _transactions.stream().filter(tr -> tr.getId() == transactionId).findFirst().orElse(null);
        if (transaction == null || transaction.getCancelledStatus()) {
            throw new BanksException("Transaction can not be canceled.");
        }

        transaction.getReceiver().forceWithdraw(transaction.getSum());
        transaction.getSender().deposit(transaction.getSum());
        transaction.setCancelledStatus();
        _transactions.remove(transaction);
    }

    public void ChangeDate(int month) {
        DateNow += month;
        for(Bank bank : _banks)
        {
            bank.updateTime(DateNow);
        }
    }
}
