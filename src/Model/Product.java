package Model;

public abstract class Product implements Expense {
    private String name;
    private double purchasePrice;                    // Cost of the ingredients
    private double sellingPrice;
    private double utilityCost;                      // Cost of cooking

    public Product(String name, double purchasePrice, double sellingPrice, double utilityCost) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.utilityCost = utilityCost;
    }

    public Product(String name) {
        this(name,0,0,0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getUtilityCost() {
        return utilityCost;
    }

    @Override
    public String toString() {
        return name + " : " + this.sellingPrice;
    }
}
