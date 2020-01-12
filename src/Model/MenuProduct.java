package Model;

import java.util.ArrayList;

public class MenuProduct extends Product {
    private ArrayList<Product> products;

    public MenuProduct(String name, ArrayList<Product> products) {
        super(name);
        this.products = products;
        double sellingPrice = calculateSellingPrice();
        setSellingPrice(sellingPrice);
    }

    @Override
    public double calculateExpense() {
        double menuProductExpense = 0;

        // Calculates expense from all products that are in menu
        for (int i = 0; i < products.size(); i++) {
            menuProductExpense += products.get(i).getPurchasePrice();
            menuProductExpense += products.get(i).getUtilityCost();
        }

        return menuProductExpense;
    }

    private double calculateSellingPrice() {
        double price = 0;

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i) instanceof MainDish) {
                double mainDishPrice = products.get(i).getSellingPrice();
                double discountedMainDish = mainDishPrice * 0.9;

                price += discountedMainDish;

            } else if (products.get(i) instanceof Dessert) {
                double dessertPrice = products.get(i).getSellingPrice();
                double discountedDessert = dessertPrice * 0.8;

                price += discountedDessert;

            } else if (products.get(i) instanceof Beverage) {
                double beveragePrice = products.get(i).getSellingPrice();
                double discountedBeverage = beveragePrice * 0.5;

                price += discountedBeverage;
            }
        }

        return price;
    }
}
