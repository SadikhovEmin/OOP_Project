package Model;

public class Beverage extends Product {
    public Beverage(String name, double purchasePrice, double sellingPrice) {
        super(name, purchasePrice, sellingPrice,0);
    }

    @Override
    public double calculateExpense() {
        return super.getPurchasePrice();
    }
}
