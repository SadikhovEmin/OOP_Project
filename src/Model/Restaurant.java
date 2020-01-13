package Model;

import java.util.ArrayList;
import java.util.Random;

public class Restaurant {
    private static int id = 0;
    private ArrayList<Employee> employees = new ArrayList<>();
    public ArrayList<Product> products = new ArrayList<>();


    public Restaurant() {
        initEmployees();
        initProducts();
    }

    private void initEmployees() {

        addCook("Monica", 100);

        addWaiter("Ross");
        addWaiter("Phobe");
        addWaiter("Rachel");
    }

    private void initProducts() {
        products.add(new MainDish("Pizza", 2, 6, 2));
        products.add(new MainDish("Burger", 1.5, 5, 2));

        products.add(new Beverage("Coke", 0.5, 2));
        products.add(new Beverage("Lemonade", 0.3, 2));

        products.add(new Dessert("Tiramusu", 1, 4, 1));
        products.add(new Dessert("Cake", 0.5, 3, 1));
        products.add(new Dessert("Ice Cream", 0.5, 3, 0.5));

        ArrayList<Product> HGproducts = new ArrayList<>();
        HGproducts.add(new MainDish("Pizza", 2, 6, 2));
        HGproducts.add(new Beverage("Coke", 0.5, 2));
        HGproducts.add(new Dessert("Tiramusu", 1, 4, 1));
        products.add(new MenuProduct("Hunger Games Menu", HGproducts));

        ArrayList<Product> Kidsproducts = new ArrayList<>();
        Kidsproducts.add(new MainDish("Burger", 1.5, 5, 2));
        Kidsproducts.add(new Beverage("Lemonade", 0.3, 2));
        Kidsproducts.add(new Dessert("Ice Cream", 0.5, 3, 0.5));
        products.add(new MenuProduct("Kids Menu", Kidsproducts));
    }

    public void listEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public void addCook(String name, double salary) {
        employees.add(new Cook(++id, name,salary));
    }

    public void addWaiter(String name) {
        employees.add(new Waiter(++id,name));
    }

    /**
     * Waiter is assigned if the generated random number is the id of a Waiter
     * @return Model.Waiter
     */
    public Waiter assignWaiter() {
        Random random = new Random();

        while (true) {
            int rand = random.nextInt(employees.size());

            for (Employee employee : employees) {
                if (employee instanceof Waiter) {

                    if (employee.getId() == rand) {
                        return (Waiter)employee;
                    }
                }
            }
        }
    }

    /**
     * Calculates employee and order expenses of the restaurant
     * @return Total expense
     */
    public double calculateExpenses() {
        double totalExpense = 0;

        for (Employee employee : employees) {
            totalExpense += employee.calculateExpense();

            if (employee instanceof Waiter) {
                for (Order order : ((Waiter)employee).getOrdersReceived()) {                        // Gets all orders
                    for (Product product : order.getOrderedProducts()) {
                        totalExpense += product.calculateExpense();
                    }
                }
            }
        }

        return totalExpense;
    }

    public double calculateRevenue() {
        double totalRevenue = 0;

        for (Employee employee : employees) {
            if (employee instanceof Waiter) {

                for (Order order : ((Waiter)employee).getOrdersReceived()) {
                    totalRevenue += order.calculateTotalPrice();
                }
            }
        }

        return totalRevenue;
    }


    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
