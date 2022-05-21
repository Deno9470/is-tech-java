package banks.com.banks.classes.account;

import banks.com.banks.classes.client.Client;
import banks.com.banks.classes.bank.Bank;

public abstract class AccountFactory {

    public abstract Account CreateAccount(Bank bank, Client client);

}
