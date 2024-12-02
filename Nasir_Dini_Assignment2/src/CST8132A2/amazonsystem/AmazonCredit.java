package CST8132A2.amazonsystem;

public abstract class AmazonCredit {
    private float amount;

    public AmazonCredit(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void deductAmount(float amount) {
        this.amount -= amount;
    }

    public abstract String getType();
}
