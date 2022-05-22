package banks.classes.account;

import banks.classes.client.Client;
import banks.classes.bank.Bank;

public abstract class AccountFactory {

    public abstract Account CreateAccount(Bank bank, Client client);

}
