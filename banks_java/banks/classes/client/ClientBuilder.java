package banks.classes.client;

public class ClientBuilder {
    private Client client = new Client();

    public ClientBuilder toName(String name)
    {
        client.setName(name);
        return this;
    }

    public ClientBuilder toSurname(String surname)
    {
        client.setSurname(surname);
        return this;
    }

    public ClientBuilder toAddress(String address)
    {
        client.setAddress(address);
        return this;
    }

    public ClientBuilder toPassportNumber(String number)
    {
        client.setPassportInfo(number);
        return this;
    }

    public Client getClient()
    {
        Client res = client;
        update();
        return res;
    }
    private void update()
    {
        client = new Client();
    }
}

