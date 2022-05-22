package banks.classes.account;


import banks.classes.client.Client;
import banks.tools.BanksException;

public abstract class Account {
    protected Client Owner;
    protected double Balance;
    public int DateNow;
    private int id;

    public abstract void withdraw(double sum) throws BanksException;

    public abstract void applyCommissionsAndInterest(int period);

    public void forceWithdraw(double sum) {
        Balance -= sum;
    }

    public void deposit(double sum) {
        Balance += sum;
    }

    public Client getOwner() {
        return Owner;
    }
    public double getBalance() {
        return Balance;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
}

