package CST8132A2.amazonsystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AmazonManager {

    private List<AmazonProduct> products = new ArrayList<>();
    private List<AmazonCustomer> customers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            printMenu();
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().toUpperCase();

            switch (option) {
                case "A": loadProducts(); break;
                case "B": showProducts(); break;
                case "C": searchProducts(); break;
                case "D": addCustomer(); break;
                case "E": showCustomers(); break;
                case "F": addCreditToCustomer(); break;
                case "G": showCustomerCredits(); break;
                case "H": addProductToWishlist(); break;
                case "I": removeProductFromWishlist(); break;
                case "J": showWishlist(); break;
                case "K": addProductToCart(); break;
                case "L": removeProductFromCart(); break;
                case "M": showCart(); break;
                case "N": buyProductsFromCart(); break;
                case "O": commentOnProduct(); break;
                case "P": listProductComments(); break;
                case "Q": exit(); return;
                default: System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("||=====================================================================================||\n" +
                "|| **** **** **** **** ***** ***** ***** ***** ***** *****  ** ALGONQUIN COLLEGE       ||\n" +
                "|| **  **  **  **  **  **  **  **  **  **  **  **  ** ** ** ** COURSE: OOP/CST8152     ||\n" +
                "|| ******  **  **  **  **  **  **  **  ** ** ** ** ** ** ***** PROF: PAULO             ||\n" +
                "|| **  **  ****  ****  ****  **** **** **** **** **** **** **  TERM: FALL / 2024       ||\n" +
                "||=====================================================================================||\n" +
                "|| [Menu A2 - Amazon Manager]                                                          ||\n" +
                "||=====================================================================================||\n" +
                "|| USER OPTIONS                                                                        ||\n" +
                "||                                                                                     ||\n" +
                "|| ADMIN                                                                               ||\n" +
                "||                                                                                     ||\n" +
                "|| Product options ................           | Wishlist options  ....                 ||\n" +
                "||    [A] Load product list                   |   [H] Add product in wishlist          ||\n" +
                "||    [B] Show product list                   |   [I] Remove product from wishlist     ||\n" +
                "||    [C] Search products                     |   [J] Show products from wishlist      ||\n" +
                "||                                            |                                        ||\n" +
                "|| Customer options ...............           | Cart options .....................     ||\n" +
                "||    [D] Add customer                        |   [K] Add product in cart              ||\n" +
                "||    [E] Show customers                      |   [L] Remove product from cart         ||\n" +
                "||                                            |   [M] Show products from cart          ||\n" +
                "||                                            |   [N] Buy products from cart           ||\n" +
                "||                                            |                                        ||\n" +
                "|| Credit options ..........................  | Comment options ...................    ||\n" +
                "||    [F] Add credit to customer              |   [O] Comment products bought          ||\n" +
                "||    [G] Show credits from customer          |   [P] List comments from products      ||\n" +
                "||                                            |                                        ||\n" +
                "||    [Q] Exit                                |                                        ||\n" +
                "||=====================================================================================||\n");
    }


    private void loadProducts() {
        System.out.print("Name of file to load products [empty = default file]: ");
        String filename = scanner.nextLine();
        if (filename.isEmpty()) {
            filename = "src/Sample-Amazon-Products-v2.csv";
        }
        try {
            AmazonProductList productList = new AmazonProductList();
            productList.createList(filename);
            products.addAll(productList.getProducts());
            System.out.println("Result: Products loaded successfully!");
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void showProducts() {
        System.out.println("PRODUCT LIST:");
        for (AmazonProduct product : products) {
            System.out.println("[" + product.getId() + ", " + product.getName() + "]");
        }
    }

    private void searchProducts() {
        System.out.print("Enter one string for search in the list: ");
        String query = scanner.nextLine().toLowerCase();
        System.out.println("Results from search...");
        for (AmazonProduct product : products) {
            if (product.getName().toLowerCase().contains(query)) {
                System.out.println("[" + product.getId() + ", " + product.getName() + "]");
            }
        }
    }

    private void addCustomer() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        System.out.print("Enter the Customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the Customer address: ");
        String address = scanner.nextLine();
        customers.add(new AmazonCustomer(id, name, address));
        System.out.println("Result: Customer added successfully!");
    }

    private void showCustomers() {
        System.out.println("[Printing customers...]");
        for (AmazonCustomer customer : customers) {
            System.out.println(customer);
        }
    }

    private void addCreditToCustomer() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.print("Enter the Type of credit ([1]: Cash, [2]: Check, [3]: Card): ");
        input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Credit type cannot be empty.");
            return;
        }
        int creditType;
        try {
            creditType = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid credit type. Please enter a numeric value.");
            return;
        }

        System.out.print("Enter value: ");
        input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Credit value cannot be empty.");
            return;
        }
        double value;
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid value. Please enter a numeric value.");
            return;
        }

        AmazonCredit credit = null;
        switch (creditType) {
            case 1:
                credit = new AmazonCash((float) value);
                break;
            case 2:
                credit = new AmazonCheck((float) value, "123456");
                break;
            case 3:
                credit = new AmazonCard((float) value, "1234-5678-9876-5432", "12/24");
                break;
            default:
                System.out.println("Invalid credit type.");
                return;
        }
        customer.addCredit(credit);
        System.out.println("Result: Credit added successfully!");
    }

    private void showCustomerCredits() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.println("Customer credits:");
        for (AmazonCredit credit : customer.getCredits()) {
            System.out.println(credit);
        }
    }

    private void addProductToWishlist() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.print("Enter the Product ID to include in the Wishlist: ");
        input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Product ID cannot be empty.");
            return;
        }
        int productId;
        try {
            productId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Product ID. Please enter a numeric value.");
            return;
        }

        AmazonProduct product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        customer.addProductInWishList(product);
        System.out.println("Product added to wishlist.");
    }

    private void removeProductFromWishlist() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.print("Enter the Product ID to remove from the Wishlist: ");
        input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Product ID cannot be empty.");
            return;
        }
        int productId;
        try {
            productId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Product ID. Please enter a numeric value.");
            return;
        }

        AmazonProduct product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        customer.removeProductFromWishList(product);
        System.out.println("Product removed from wishlist.");
    }

    private void showWishlist() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        List<AmazonProduct> wishlist = customer.getWishlist();
        if (wishlist.isEmpty()) {
            System.out.println("No products in wishlist.");
        } else {
            System.out.println("Customer wishlist:");
            for (AmazonProduct product : wishlist) {
                System.out.println("[" + product.getId() + ", " + product.getName() + "]");
            }
        }
    }

    private void addProductToCart() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.print("Enter the Product ID to buy from your cart: ");
        input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Product ID cannot be empty.");
            return;
        }
        int productId;
        try {
            productId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Product ID. Please enter a numeric value.");
            return;
        }

        System.out.print("Enter the number of items to put in cart: ");
        input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Quantity cannot be empty.");
            return;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Please enter a numeric value.");
            return;
        }

        AmazonProduct product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        AmazonCartItem cartItem = new AmazonCartItem(product, quantity);
        customer.addItemInCart(cartItem);
        System.out.println("Cart updated: [" + quantity + " of " + productId + " added for customer " + id + "]");
    }

    private void removeProductFromCart() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.print("Enter the Product ID to remove from the cart: ");
        input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Product ID cannot be empty.");
            return;
        }
        int productId;
        try {
            productId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Product ID. Please enter a numeric value.");
            return;
        }

        AmazonProduct product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        customer.removeProductFromCart(product);
        System.out.println("Product removed from customer cart successfully!");
    }

    private void showCart() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.println("Customer cart:");
        for (AmazonCartItem item : customer.getCartItems()) {
            System.out.println("[" + item.getProduct().getId() + ", " + item.getProduct().getName() + "]");
        }
        System.out.println("* Total value: " + customer.calculateCartTotal());
    }

    private void buyProductsFromCart() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.print("Select the payment method [from 0 to " + (customer.getCredits().size() - 1) + "]: ");
        input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Payment method cannot be empty.");
            return;
        }
        int creditIndex;
        try {
            creditIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid payment method. Please enter a numeric value.");
            return;
        }

        if (creditIndex < 0 || creditIndex >= customer.getCredits().size()) {
            System.out.println("Invalid credit selection.");
            return;
        }
        AmazonCredit credit = customer.getCredits().get(creditIndex);
        boolean success = customer.payCart(credit);
        if (success) {
            System.out.println("Customer credit updated: Type: " + credit.getType() + ", value: " + credit.getAmount() + ".");
            System.out.println("Cart empty - you can comment on products now.");
        } else {
            System.out.println("Payment failed - not enough credit.");
        }
    }

    private void commentOnProduct() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.print("Enter the product Id to comment: ");
        input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Product ID cannot be empty.");
            return;
        }
        int productId;
        try {
            productId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Product ID. Please enter a numeric value.");
            return;
        }

        AmazonProduct product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        System.out.print("Enter the comment: ");
        String commentText = scanner.nextLine();
        System.out.print("Enter the stars: ");
        input = scanner.nextLine();
        float rating;
        try {
            rating = Float.parseFloat(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid rating. Please enter a numeric value.");
            return;
        }

        AmazonComment comment = new AmazonComment(product, commentText, rating);
        customer.addComment(comment);
        System.out.println("Comment added successfully!");
    }

    private void listProductComments() {
        System.out.print("Enter the Customer id: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            System.out.println("Customer id cannot be empty.");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Customer id. Please enter a numeric value.");
            return;
        }

        AmazonCustomer customer = findCustomerById(id);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        
        List<AmazonComment> comments = customer.getComments();
        if (comments.isEmpty()) {
            System.out.println("No comments found for the customer.");
        } else {
            System.out.println("Customer comments:");
            for (AmazonComment comment : comments) {
                AmazonProduct product = comment.getProduct();
                String productInfo = "ProdId: [" + product.getId() + ", " + product.getName() + "] - Comment: " + comment.getComment() + " - rate: " + comment.getStars();
                System.out.println(productInfo);
            }
        }
    }

    private void exit() {
        System.out.println("===========================================================================");
        System.out.println("|| [End of Application (Authors: Nasir & Salah - 777)] ||");
        System.out.println("===========================================================================");
    }

    private AmazonCustomer findCustomerById(int id) {
        for (AmazonCustomer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    private AmazonProduct findProductById(int id) {
        for (AmazonProduct product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new AmazonManager().start();
    }
}

