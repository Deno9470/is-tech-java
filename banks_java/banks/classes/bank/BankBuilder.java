package banks.classes.bank;

public class BankBuilder
{
    private Bank _bank = new Bank();

    public void setBankName(String name)
    {
        _bank.BankName = name;
    }

    public void setDebitInterest(double percent)
    {
        _bank.DebitInterest = percent;
    }

    public void setDepositInterest(double percent)
    {
        _bank.DepositInterest = percent;
    }

    public void setCreditCommission(double comm)
    {
        _bank.CreditCommission = comm;
    }

    public void setUntrustedTransactionLimit(double limit)
    {
        _bank.UntrustedTransactionLimit = limit;
    }

    public void setCreditLimit(double limit)
    {
        _bank.CreditLimit = limit;
    }

    public void setDepositTerm(int term)
    {
        _bank.DepositTerm = term;
    }

    public void reset()
    {
        _bank = new Bank();
    }

    public Bank getBank()
    {
        Bank result = _bank;
        this.reset();
        return result;
    }
}