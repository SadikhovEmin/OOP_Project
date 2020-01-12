package Model;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> products;

    public Order() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    // Displays ordered products
    public void listOrder() {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }

    public ArrayList<Product> getOrderedProducts() {
        return products;
    }

    /**
     * @return Total Selling Price of the order
     */
    public double calculateTotalPrice() {
        double totalSellingPrice = 0;

        for (int i = 0; i < products.size(); i++) {
            totalSellingPrice += products.get(i).getSellingPrice();
        }

        return totalSellingPrice;
    }
}