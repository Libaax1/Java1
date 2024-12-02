package CST8132A2.amazonsystem;

public class AmazonProductCategory {
    private String categoryName;

    public AmazonProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
