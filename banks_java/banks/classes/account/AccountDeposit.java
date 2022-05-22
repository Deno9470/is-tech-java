package banks.classes.account;

import banks.classes.client.Client;
import banks.tools.BanksException;

public class AccountDeposit extends Account {
        private double DepositInterest;
        private int DepositTerm;

        public AccountDeposit(Client owner, int depositTerm, double depositInterest) {
                Owner = owner;
                DepositTerm = depositTerm;
                DepositInterest = depositInterest;
        }

        @Override
        public void applyCommissionsAndInterest(int period) {
                if (DepositTerm < DateNow)
                        return;
                double percentPerDay = ((DepositInterest / 12) / 30) / 100;
                Balance += Math.pow(1 + percentPerDay, period) * Balance;
                DateNow += period;
        }

        @Override
        public void withdraw(double sum) throws BanksException {
                if (DateNow < DepositTerm)
                        throw new BanksException("The deposit period has not ended");
                if (Owner.getPassportInfo() == null || Owner.getAddress() == null)
                        throw new BanksException("Account is untrusted");
                if (sum > Balance)
                        throw new BanksException("There are not enough funds in the account");
                Balance -= sum;
        }
}