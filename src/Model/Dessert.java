package Model;

public class Dessert extends Product {
    public Dessert(String name, double purchasePrice, double sellingPrice, double utilityCost) {
        super(name, purchasePrice, sellingPrice, utilityCost);
    }

    @Override
    public double calculateExpense() {
        return getPurchasePrice() + getUtilityCost();
    }
}
