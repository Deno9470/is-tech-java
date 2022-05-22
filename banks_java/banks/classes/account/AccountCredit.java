package banks.classes.account;

import banks.classes.client.Client;
import banks.tools.BanksException;

public class AccountCredit extends Account {
    private int _monthInDebt;
    public double _creditLimit;
    public double _debtCost;

    public AccountCredit(Client owner, double commission, double limit) {
        Owner = owner;
        _debtCost = commission;
        _creditLimit = limit;
    }

    @Override
    public void applyCommissionsAndInterest(int period) {
        if (Balance < 0) {
            Balance -= _debtCost * (period - _monthInDebt);
        }

        DateNow += period;
    }
    @Override
    public void withdraw(double sum) throws BanksException {
        if (Balance > 0) {
            _monthInDebt = 0;
        }

        if (sum - _creditLimit > Balance)
            throw new BanksException("There are not enough funds in the account");
        if (Owner.getPassportInfo() == null || Owner.getAddress() == null)
            throw new BanksException("Account is untrusted");
        Balance -= sum;
        if (Balance < 0)
            _monthInDebt = DateNow;
    }
}
