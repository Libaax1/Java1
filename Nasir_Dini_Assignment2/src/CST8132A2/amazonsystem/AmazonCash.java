package CST8132A2.amazonsystem;

public class AmazonCash extends AmazonCredit {
    public AmazonCash(float amount) {
        super(amount);
    }

     public static AmazonCash createCash(String[] data) {
        if (data.length != 1 || data[0].trim().isEmpty()) {
            return null;
        }
        
      try {
          float amount = Float.parseFloat(data[0].trim());
            return new AmazonCash(amount);
        } catch (NumberFormatException e) {
            return null;
         }
    }

     @Override
     public String getType(){
         return "Cash";
     }

    @Override
    public String toString() {
        return "Type: Cash, value: " + getAmount();
    }
}
