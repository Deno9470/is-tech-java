package tests;
import banks.classes.account.Account;
import banks.classes.account.AccountCreditFactory;
import banks.classes.account.AccountDebitFactory;
import banks.classes.bank.Bank;
import banks.classes.bank.BankBuilder;
import banks.classes.bank.CentralBank;
import banks.classes.client.Client;
import banks.classes.client.ClientBuilder;
import banks.tools.BanksException;
import org.junit.jupiter.api.*;

class BankTest {
    private CentralBank _central;
    private Bank _firstBank;
    private Bank _secondBank;
    private Client _client1;
    private Client _client2;
    private Account _accountDebit;
    private Account _accountCredit;

    @BeforeEach
    public void Setup() throws BanksException
    {
        _central = new CentralBank();
        BankBuilder bankBuilder = new BankBuilder();
        bankBuilder.setBankName("FirstBank");
        bankBuilder.setDebitInterest(10);
        bankBuilder.setCreditCommission(15);
        bankBuilder.setCreditLimit(10000);
        bankBuilder.setDepositInterest(25);
        bankBuilder.setDepositTerm(12);
        bankBuilder.setUntrustedTransactionLimit(5000);
        _firstBank = bankBuilder.getBank();
        _central.addBank(_firstBank);
        bankBuilder.setBankName("SecondBank");
        bankBuilder.setDebitInterest(50);
        bankBuilder.setCreditCommission(1);
        bankBuilder.setCreditLimit(500);
        bankBuilder.setDepositInterest(10);
        bankBuilder.setDepositTerm(60);
        bankBuilder.setUntrustedTransactionLimit(500);
        _secondBank = bankBuilder.getBank();
        _central.addBank(_secondBank);


        var clientBuilder = new ClientBuilder();

        clientBuilder.toName("Ivan");
        clientBuilder.toSurname("Petrov");
        clientBuilder.toAddress("SPB, Nevsky 51");
        clientBuilder.toPassportNumber("544687");
        _client1 = clientBuilder.getClient();
        _firstBank.addClient(_client1);
        _accountDebit = new AccountDebitFactory().CreateAccount(_firstBank, _client1);
        _firstBank.addAccount(_accountDebit);

        clientBuilder.toName("Dima");
        clientBuilder.toSurname("Lizkoff");
        clientBuilder.toAddress("SPB, Nevsky 60");
        clientBuilder.toPassportNumber("456423");
        _client2 = clientBuilder.getClient();
        _secondBank.addClient(_client2);
        _accountCredit = new AccountCreditFactory().CreateAccount(_secondBank, _client2);
        _secondBank.addAccount(_accountCredit);
    }
    @Test
    public void DepositToBalance_BalanceIsCorrect()
    {
        double depositMoney = 500.5;
        _accountDebit.deposit(depositMoney);
        Assertions.assertEquals(depositMoney, _accountDebit.getBalance());
    }
    @Test
    public void TransferMoneyToAnotherBank_SecondAccountHasMoney()
    {
        double depositMoney = 500;
        _accountDebit.deposit(depositMoney);
        try { _central.makeTransaction(_accountDebit, _accountCredit, 500);}
        catch (Exception e) {}
        Assertions.assertEquals(0, _accountDebit.getBalance());
        Assertions.assertEquals(depositMoney, _accountCredit.getBalance());
    }
    @Test
    public void ClientSubscribe_ClientGetNotification()
    {
        String notifications = "Credit Commission is decreased";
        _firstBank.subscribeToNotifications(_client1);
        _secondBank.subscribeToNotifications(_client2);
        _firstBank.notifySubscribers(notifications);
        Assertions.assertEquals(notifications, _client1.getBankAdvert());
        Assertions.assertNotEquals( notifications, _client2.getPassportInfo());
    }

    @Test
    public void TransferTimeByOneMonth_BalanceRaised()
    {
        double firstMonthMoney = 1000;
        _accountDebit.deposit(firstMonthMoney);
        _central.ChangeDate(1);
        Assertions.assertTrue(_accountDebit.getBalance() > firstMonthMoney);
    }
     @Test
    public void CancelTransactionTwice_TransactionCanceledOnceAndExceptionThrown() throws Exception
    {
        int transactionId = 0;
        double depositMoney = 500;
        _accountDebit.deposit(depositMoney);
        _central.makeTransaction(_accountDebit,_accountCredit, 500);
        _central.cancelTransaction(transactionId);
        Assertions.assertEquals(_accountDebit.getBalance(), depositMoney);
        Assertions.assertThrows(BanksException.class, ()-> _central.cancelTransaction(transactionId));
    }


}
