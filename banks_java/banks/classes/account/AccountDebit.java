package banks.classes.account;

import banks.classes.client.Client;
import banks.tools.BanksException;

public class AccountDebit extends Account {
    private double _interest;
    public AccountDebit(Client owner, double interest)
    {
        Owner = owner;
        _interest = interest;
        Balance = 0;
    }
    @Override
    public void applyCommissionsAndInterest(int period)
    {
        double percentPerDay = ((_interest / 12) / 30) / 100;
        Balance += Math.pow(1 + percentPerDay, period) * Balance;
        DateNow += period;
    }

    @Override
    public void withdraw(double sum) throws BanksException
    {
        if (Owner.getPassportInfo() == null || Owner.getAddress() == null)
            throw new BanksException("Account is untrusted");
        if (sum > Balance)
            throw new BanksException("There are not enough funds in the account");
        Balance -= sum;
    }

    public void deposit(double sum)
    {
        Balance += sum;
    }
}
