package banks.com.banks.classes.account;

import banks.com.banks.classes.client.Client;
import banks.com.banks.classes.bank.Bank;

public class AccountCreditFactory extends AccountFactory {
    @Override
    public Account CreateAccount(Bank bank, Client client) {
        var account = new AccountCredit(client, bank.CreditCommission, bank.CreditLimit);
        return account;
    }
}
