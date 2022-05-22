package banks;

import banks.classes.bank.BankBuilder;
import banks.classes.bank.CentralBank;
import banks.classes.client.Client;
import banks.classes.client.ClientBuilder;
import banks.tools.BanksException;
import banks.classes.bank.Bank;

import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception{
        var centralBank = new CentralBank();
        var bankBuilder = new BankBuilder();
        var clientBuilder = new ClientBuilder();
        var bank = new Bank();
        String input;
        String choose;
        while (true) {
            System.out.println();
            System.out.println("1. Add new bank");
            System.out.println("2. Add new client");
            System.out.println("3. Exit");
            Scanner console = new Scanner(System.in);
            choose = console.nextLine();

            switch (choose) {
                case "1":
                    System.out.println("Enter the bank name: ");
                    bankBuilder.setBankName(console.nextLine());

                    System.out.println("Enter the debit interest: ");
                    bankBuilder.setDebitInterest(Double.parseDouble(console.nextLine()));

                    System.out.println("Enter the credit commission: ");
                    bankBuilder.setCreditCommission(Double.parseDouble(console.nextLine()));

                    System.out.println("Enter the credit limit: ");
                    bankBuilder.setCreditLimit(Double.parseDouble(console.nextLine()));

                    System.out.println("Enter the deposit interest: ");
                    bankBuilder.setDepositInterest(Double.parseDouble(console.nextLine()));

                    System.out.println(
                            "Enter the transaction limit for client without ID or address data: ");
                    bankBuilder.setUntrustedTransactionLimit(Double.parseDouble(console.nextLine()));

                    bank = bankBuilder.getBank();
                    System.out.println("Bank data ");
                    System.out.println("Bank name: " + bank.BankName);
                    System.out.println("Debit percent: " + bank.DebitInterest);
                    System.out.println("Credit commission: " + bank.CreditCommission);
                    System.out.println("Credit limit: " + bank.CreditLimit);
                    System.out.println("Deposit percent : "+ bank.DebitInterest);
                    System.out.println(
                            "Money transfer limit for untrusted account: " + bank.UntrustedTransactionLimit);
                    centralBank.addBank(bank);

                    console.nextLine();
                    break;
                case "2":
                    System.out.println("Enter name of the bank of new client: ");
                    try {
                        bank = centralBank.getBank(console.nextLine());
                    } catch (BanksException e) {
                        System.out.println(e.getMessage());
                        return;
                    }

                    System.out.println("Enter client's name:");
                    clientBuilder.toName(console.nextLine());

                    System.out.println("Enter client's surname:");
                    clientBuilder.toSurname(console.nextLine());

                    System.out.println("Enter client's address:(type \"Skip\" to skip)");
                    input = console.nextLine();
                    if (input.equals("Skip")) {
                        clientBuilder.toAddress(null);
                    } else {
                        clientBuilder.toAddress(input);
                    }

                    System.out.println("Enter client's passport number:(type \"Skip\" to skip)");
                    input = console.nextLine();
                    if (input.equals("Skip")) {
                        clientBuilder.toPassportNumber(null);
                    } else {
                        clientBuilder.toPassportNumber(input);
                    }

                    Client client = clientBuilder.getClient();
                    bank.addClient(client);
                    System.out.println("Client data ");
                    System.out.println("Client name: " + client.getName());
                    System.out.println("Client Surname: " + client.getSurname());
                    System.out.println("Client Address: " + client.getAddress());
                    System.out.println("Client ID: " + client.getPassportInfo());
                    if (client.getAddress() == null || client.getPassportInfo() == null)
                        System.out.println("The client is untrusted, fill in the address and ID data to remove restrictions");
                    console.nextLine();
                    break;
                case "3":
                    System.out.println("Exit!");
                    return;
                default:
                    throw new Exception("Incorrect argument!");
            }
        }
    }
}
