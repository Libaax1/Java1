package CST8132A2.amazonsystem;

public class AmazonProductSubCategory {
    private String subCategoryName;
    private AmazonProductCategory category;

    public AmazonProductSubCategory(String subCategoryName, AmazonProductCategory category) {
        this.subCategoryName = subCategoryName;
        this.category = category;
    }

    @Override
    public String toString() {
        return subCategoryName;
    }
}
