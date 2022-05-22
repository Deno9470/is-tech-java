package banks.classes.account;

import banks.classes.client.Client;
import banks.classes.bank.Bank;

public class AccountDepositFactory extends AccountFactory {
    @Override
    public Account CreateAccount(Bank bank, Client client) {
        var account = new AccountDeposit(client, bank.DepositTerm, bank.DepositInterest);
        return account;
    }
}
