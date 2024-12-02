package CST8132A2.amazonsystem;

import java.util.ArrayList;
import java.util.List;

public class AmazonCart {
    private AmazonCustomer customer;
    private List<AmazonCartItem> items;
    private float total;

    public AmazonCart(AmazonCustomer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
        this.total = 0;
    }

    public float getItemsTotal() {
        float sum = 0;
        for (AmazonCartItem item : items) {
            sum += item.calculateSubTotal();
        }
        return sum;
    }

    public void calculateTotal() {
        total = getItemsTotal();
    }

    public void addItem(AmazonCartItem item) {
        items.add(item);
        calculateTotal();
    }

    public void removeItem(AmazonCartItem item) {
        items.remove(item);
        calculateTotal();
    }

    
    public boolean pay(AmazonCredit credit) {
        if (credit.getAmount() >= total) {
            credit.deduct(total);
            items.clear();
            calculateTotal();
            System.out.println("Payment successful!");
            return true;
        } else {
            System.out.println("Insufficient credit!");
            return false;
        }
    }

    public void clear() {
        items.clear();
        calculateTotal();
    }

    @Override
    public String toString() {
        String result = "Cart Items:\n";
        for (AmazonCartItem item : items) {
            result += item.toString() + "\n";
        }
        result += "Total: " + total;
        return result;
    }
}
