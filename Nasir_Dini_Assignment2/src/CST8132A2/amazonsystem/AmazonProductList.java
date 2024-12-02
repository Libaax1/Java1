package CST8132A2.amazonsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmazonProductList {
    private List<AmazonProduct> products;

    public AmazonProductList() {
        products = new ArrayList<>();
    }

    public void createList(String csvFile) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            String line = br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) { // Read each line in the CSV
                String[] data = splitCSVLine(line);
                if (data.length >= 10) {
                    try {
                        AmazonProductCategory category = new AmazonProductCategory(data[2]);
                        AmazonProductSubCategory subcategory = new AmazonProductSubCategory(data[3], category);
                        AmazonProduct product = new AmazonProduct(
                            Integer.parseInt(data[0]), data[1], category, subcategory,
                            data[4], data[5],
                            Float.parseFloat(data[6]), Integer.parseInt(data[7].replace(",", "")),
                            Float.parseFloat(data[8].replace("₹", "").replace(",", "")),
                            Float.parseFloat(data[9].replace("₹", "").replace(",", ""))
                        );
                        products.add(product); // Add product to the list
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping line due to number format issue: " + line);
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading CSV file: " + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error closing BufferedReader: " + e.getMessage());
                }
            }
        }
    }

    private String[] splitCSVLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;
        for (char ch : line.toCharArray()) {
            if (ch == '"') {
                inQuotes = !inQuotes;
            } else if (ch == ',' && !inQuotes) {
                result.add(current.toString().replace("\"", ""));
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }
        result.add(current.toString().replace("\"", ""));
        return result.toArray(new String[0]);
    }

    public List<AmazonProduct> getProducts() {
        return products;
    }
}
