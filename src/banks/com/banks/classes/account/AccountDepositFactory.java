package banks.com.banks.classes.account;

import banks.com.banks.classes.client.Client;
import banks.com.banks.classes.bank.Bank;

public class AccountDepositFactory extends AccountFactory {
    @Override
    public Account CreateAccount(Bank bank, Client client) {
        var account = new AccountDeposit(client, bank.DepositTerm, bank.DepositInterest);
        return account;
    }
}
