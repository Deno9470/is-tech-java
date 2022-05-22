package banks.classes.account;

import banks.classes.client.Client;
import banks.classes.bank.Bank;

public class AccountDebitFactory extends AccountFactory {
    @Override
    public Account CreateAccount(Bank bank, Client client) {
        var account = new AccountDebit(client, bank.DebitInterest);
        return account;
    }
}
