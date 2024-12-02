package CST8132A2.amazonsystem;

public class AmazonCard extends AmazonCredit {
    private String number;
    private String expiration;

    public AmazonCard(float amount, String number, String expiration) {
        super(amount);
        this.number = number;
        this.expiration = expiration;
    }

    @Override
    public String getType() {
        return "Card";
    }

    @Override
    public String toString() {
        return "Type: Card, number: " + number + ", expiration: " + expiration + ", value: " + getAmount();
    }
}
