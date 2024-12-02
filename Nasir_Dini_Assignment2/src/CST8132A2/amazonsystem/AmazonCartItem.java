package CST8132A2.amazonsystem;

public class AmazonCartItem {
    private AmazonProduct product;
    private int quantity;

    public AmazonCartItem(AmazonProduct product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public AmazonProduct getProduct() {
        return product;
    }

    public float calculateSubTotal() {
        return product.getDiscountPrice() * quantity;
    }

    @Override
    public String toString() {
        return "Item: [" + product + "], quantity: " + quantity;
    }
}
