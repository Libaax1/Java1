package CST8132A2.amazonsystem;

public class AmazonProduct {
    private int id;
    private String name;
    private String mainCategory;
    private String subCategory;
    private String image;
    private String link;
    private float rating;
    private int numberOfRatings;
    private float discountPrice;
    private float actualPrice;

    public AmazonProduct(int id, String name, AmazonProductCategory mainCategory, AmazonProductSubCategory subCategory, String image, String link, float rating, int numberOfRatings, float discountPrice, float actualPrice) {
        this.id = id;
        this.name = name;
        this.mainCategory = mainCategory.toString();
        this.subCategory = subCategory.toString();
        this.image = image;
        this.link = link;
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
        this.discountPrice = discountPrice;
        this.actualPrice = actualPrice;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public String toString() {
        return "[" + id + ", " + name + ", " + mainCategory + ", " + subCategory + ", " + image + ", " + link + ", " + rating + ", " + numberOfRatings + ", " + discountPrice + ", " + actualPrice + "]";
    }

    public static AmazonProduct createAmazonProduct(String[] data) {
        if (data.length != 10 || data[0].trim().isEmpty() || data[1].trim().isEmpty() || data[2].trim().isEmpty() || data[3].trim().isEmpty() || data[4].trim().isEmpty() || data[5].trim().isEmpty() || data[6].trim().isEmpty() || data[7].trim().isEmpty() || data[8].trim().isEmpty() || data[9].trim().isEmpty()) {
            return null;
        }
        try {
            AmazonProductCategory category = new AmazonProductCategory(data[2].trim());
            AmazonProductSubCategory subCategory = new AmazonProductSubCategory(data[3].trim(), category);
            return new AmazonProduct(
                Integer.parseInt(data[0].trim()),
                data[1].trim(),
                category,
                subCategory,
                data[4].trim(),
                data[5].trim(),
                Float.parseFloat(data[6].trim()),
                Integer.parseInt(data[7].trim()),
                Float.parseFloat(data[8].trim()),
                Float.parseFloat(data[9].trim())
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
