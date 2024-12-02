package CST8132A2.amazonsystem;

public class AmazonComment {
    private AmazonProduct product;
    private String comment;
    private float rating;

    public AmazonComment(AmazonProduct product) {
        this.product = product;
        this.comment = "";
        this.rating = 0.0f;
    }

    public AmazonComment(AmazonProduct product, String comment, float rating) {
        this.product = product;
        this.comment = comment;
        this.rating = rating;
    }

    public AmazonProduct getProduct() {
        return product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getStars() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProdId: [" + product.getId() + ", " + product.getName() + "] - Comment: " + comment + " - rate: " + rating;
    }
}
