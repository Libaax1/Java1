package CST8132A2.amazonsystem;

import java.util.ArrayList;
import java.util.List;

public class AmazonCustomer {
    private int id;
    private String name;
    private String address;
    private List<AmazonCredit> credits = new ArrayList<>();
    private List<AmazonProduct> wishlist = new ArrayList<>();
    private List<AmazonCartItem> cart = new ArrayList<>();
    private List<AmazonComment> comments = new ArrayList<>();

    public AmazonCustomer(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void addCredit(AmazonCredit credit) {
        credits.add(credit);
    }

    public List<AmazonCredit> getCredits() {
        return credits;
    }

    public void addProductInWishList(AmazonProduct product) {
        wishlist.add(product);
    }

    public void removeProductFromWishList(AmazonProduct product) {
        wishlist.remove(product);
    }

    public List<AmazonProduct> getWishlist() {
        return wishlist;
    }

    public int getWishlistSize() {
        return wishlist.size();
    }

    public void addItemInCart(AmazonCartItem item) {
        cart.add(item);
    }

    public void removeProductFromCart(AmazonProduct product) {
        cart.removeIf(item -> item.getProduct().equals(product));
    }

    public List<AmazonCartItem> getCartItems() {
        return cart;
    }

    public int getCartSize() {
        return cart.size();
    }

    public float calculateCartTotal() {
        float total = 0;
        for (AmazonCartItem item : cart) {
            total += item.calculateSubTotal();
        }
        return total;
    }

    public boolean payCart(AmazonCredit credit) {
        float total = calculateCartTotal();
        if (credit.getAmount() >= total) {
            credit.deductAmount(total);
            cart.clear();
            return true;
        }
        return false;
    }

    public void addComment(AmazonComment comment) {
        comments.add(comment);
    }

    public List<AmazonComment> getComments() {
        return comments;
    }

    public int getNumberOfComments() {
        return comments.size();
    }

    public int getNumberOfCredits() {
        return credits.size();
    }

    @Override
    public String toString() {
        return "Customer: [Id: " + id + "], [Name: " + name + "], [Address: " + address + "]";
    }

    public static AmazonCustomer createAmazonCustomer(String[] data) {
        if (data.length != 3 || data[0].trim().isEmpty() || data[1].trim().isEmpty() || data[2].trim().isEmpty()) {
            return null;
        }
        try {
            return new AmazonCustomer(
                Integer.parseInt(data[0].trim()),
                data[1].trim(),
                data[2].trim()
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
