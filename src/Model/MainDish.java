package Model;

public class MainDish extends Product {
    public MainDish(String name, double purchasePrice, double sellingPrice, double utilityCost) {
        super(name, purchasePrice, sellingPrice, utilityCost);
    }

    @Override
    public double calculateExpense() {
        return getPurchasePrice() + getUtilityCost();
    }
}
