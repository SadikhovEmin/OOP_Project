package Model;

import java.util.ArrayList;

public class Waiter extends Employee {
    private double orderRate;
    private ArrayList<Order> ordersReceived;

    public Waiter(int id, String name) {
        super(id, name);
        this.ordersReceived = new ArrayList<Order>();
        this.orderRate = 0.1;
    }

    @Override
    public double calculateExpense() {
        double totalExpense = 0;

        for (Order order : ordersReceived) {
            totalExpense += order.calculateTotalPrice();
        }

        return totalExpense * orderRate;
    }

    public void createOrder(Order order) {
        ordersReceived.add(order);
    }

    public ArrayList<Order> getOrdersReceived() {
        return this.ordersReceived;
    }
}
