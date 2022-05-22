package banks.classes;

import banks.classes.account.Account;

public class Transaction
{
    private int Id;
    private double Sum;
    private Account _sender;
    private Account _receiver;
    private boolean cancel;
    public Transaction(int id, double sum, Account sender, Account receiver, boolean cancelled)
    {
        Id = id;
        Sum = sum;
        _sender = sender;
        _receiver = receiver;
        cancel = cancelled;
    }
    public Transaction(int id, double sum, Account sender, Account receiver)
    {
        Id = id;
        Sum = sum;
        _sender = sender;
        _receiver = receiver;
        cancel = false;
    }

    public Account getSender() {
        return _sender;
    }

    public Account getReceiver() {
        return _receiver;
    }

    public double getSum() {
        return Sum;
    }

    public int getId() {
        return Id;
    }

    public void setCancelledStatus() { cancel = true;}
    public boolean getCancelledStatus() { return cancel;}
}
