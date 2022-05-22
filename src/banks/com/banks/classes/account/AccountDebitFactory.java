package banks.com.banks.classes.account;

import banks.com.banks.classes.client.Client;
import banks.com.banks.classes.bank.Bank;

public class AccountDebitFactory extends AccountFactory {
    @Override
    public Account CreateAccount(Bank bank, Client client) {
        var account = new AccountDebit(client, bank.DebitInterest);
        return account;
    }
}
