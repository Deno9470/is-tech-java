package banks.classes.account;

import banks.classes.client.Client;
import banks.classes.bank.Bank;

public class AccountCreditFactory extends AccountFactory {
    @Override
    public Account CreateAccount(Bank bank, Client client) {
        var account = new AccountCredit(client, bank.CreditCommission, bank.CreditLimit);
        return account;
    }
}
