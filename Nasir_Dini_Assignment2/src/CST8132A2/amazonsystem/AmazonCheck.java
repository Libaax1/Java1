package CST8132A2.amazonsystem;

public class AmazonCheck extends AmazonCredit {
    private String accountNumber;

    public AmazonCheck(float amount, String accountNumber) {
        super(amount);
        this.accountNumber = accountNumber;
    }

   
    public static AmazonCheck createCheck(String[] data) {
        if (data.length != 2 || data[0].trim().isEmpty() || data[1].trim().isEmpty()) {
            return null;
        }
        try {
            float amount = Float.parseFloat(data[0].trim());
            String accountNumber = data[1].trim();
            return new AmazonCheck(amount, accountNumber);
        } catch (NumberFormatException e) {
            return null;
        }
    }

   @Override
   public String getType() {
    return "Check";
    }

   @Override
   public String toString() {
     return "Type: Check, account: " + accountNumber + ", value: " + getAmount();
    }
}
