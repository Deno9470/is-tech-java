package banks.classes.client;

public class Client
{
    private String name;
    private String surname;
    private String address;
    private String passportInfo;
    private String currentBank;
    private String BankAdvert;

    public void updateInfo(String info) {
        currentBank = info;
    }

    public boolean isIncorrectClient() {
        return  (address == null || address.isEmpty() ||
                passportInfo == null || passportInfo.isEmpty() ) ;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCurrentBank(String currentBank) {
        this.currentBank = currentBank;
    }

    public void setPassportInfo(String passportInfo) {
        this.passportInfo = passportInfo;
    }

    public String getPassportInfo() { return passportInfo; }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() { return address; }

    public void changeNotifications(String info) { BankAdvert = info; }

    public String getBankAdvert() { return BankAdvert;}
}