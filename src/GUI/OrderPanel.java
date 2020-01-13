package GUI;

import Model.Order;
import Model.Product;
import Model.Restaurant;
import Model.Waiter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Emin Sadikhov <emin.sadikhov@ozu.edu.tr>
 */

public class OrderPanel extends JPanel {
    private Restaurant restaurant;
    private Waiter waiter;
    private Order order;

    public OrderPanel(Restaurant restaurant) {
        orderHomePage();
        this.restaurant = restaurant;
        this.order =  new Order();
    }

    // Method is used to go back to initial window after completing the order
    private void orderHomePage() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton button = new JButton("New Order");
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    JButton button1 = (JButton)e.getSource();

                    if (button1.getText().equals(button.getText())) {
                        removeAll();                                     // Removes everything from the panel
                        message();
                        orderFood();
                    }
                }
            }
        });
    }

    private void message() {
        this.waiter = restaurant.assignWaiter();

        JOptionPane.showMessageDialog(null,"Hi, I am " + waiter.getName() +
                ".\nWhat would you like to order?","Info",JOptionPane.INFORMATION_MESSAGE);
    }

    private void orderFood() {
        setLayout(new GridLayout(2,1));

        JPanel addProduct = new JPanel();
        addProduct.setBorder(BorderFactory.createTitledBorder("Add Product"));
        addProduct.setLayout(new GridLayout(4,2));

        JLabel productLabel = new JLabel("Product : ");
        JLabel countLabel = new JLabel("Count : ");
        JLabel priceLabel = new JLabel("Price : ");
        JLabel displayPriceLabel = new JLabel("0.00 TL");
        JComboBox<String> comboBox = new JComboBox<>();
        JSpinner spinner = new JSpinner();
        JButton addButton = new JButton("Add");
        JButton finalize = new JButton("Finalize");

        // Modifications
        setComboBox(comboBox);
        comboBoxActionListener(comboBox,displayPriceLabel,addButton,finalize);

        displayPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        finalize.setHorizontalAlignment(SwingConstants.RIGHT);

        addButton.setEnabled(false);
        finalize.setEnabled(false);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel();
        spinnerNumberModel.setValue(1);
        spinnerNumberModel.setMinimum(1);
        spinnerNumberModel.setMaximum(10);
        spinner.setModel(spinnerNumberModel);


        /**
         * Adds Objects to the upper panel (addProduct panel)
         */
        addProduct.add(productLabel);
        addProduct.add(comboBox);
        addProduct.add(countLabel);
        addProduct.add(spinner);
        addProduct.add(priceLabel);
        addProduct.add(displayPriceLabel);
        addProduct.add(new JLabel());                         // Empty label to move addButton to right Border of frame
        addProduct.add(addButton);


        /**
         * Lower Part
         */
        JPanel currentOrder = new JPanel();
        currentOrder.setBorder(BorderFactory.createTitledBorder("Current Order"));
        currentOrder.setLayout(new BorderLayout());

        JPanel finalizePanel = new JPanel();                                                                // Panel for finalizeButton
        finalizePanel.setLayout(new BorderLayout());
        currentOrder.add(finalizePanel,BorderLayout.SOUTH);
        finalizePanel.add(finalize,BorderLayout.EAST);

        DefaultTableModel model = new DefaultTableModel();

        // Add Column method only works with JScrollPane. Without scrolling it will not be added to table
        model.addColumn("Product Name");
        model.addColumn("Count");
        model.addColumn("Price");
        JTable table = new JTable(model);
        currentOrder.add(table,BorderLayout.NORTH);



        ArrayList<Double> priceArrayList = new ArrayList<Double>();
        ArrayList<Integer> countArrayList = new ArrayList<Integer>();

        addButtonActionListener(addButton,model,comboBox,spinner,priceArrayList,countArrayList);           // Adds ordered product to order list
        finalizeActionListener(finalize,priceArrayList,countArrayList);

        /*
        Adds two panels
         */
        add(addProduct);
        add(currentOrder);
    }

    /**
     * Adds all products in restaurant to the comboBox
     * @param comboBox comboBox
     */
    private void setComboBox(JComboBox<String> comboBox) {
        comboBox.addItem("Select an item");
        for (Product product : restaurant.getProducts()) {
            comboBox.addItem(product.getName());
        }
    }

    /**
     * Sets the price of the selected product
     * @param comboBox comboBox
     * @param priceLabel priceLabel
     */
    private void comboBoxActionListener(JComboBox<String> comboBox, JLabel priceLabel,JButton addButton, JButton finalizeButton) throws NullPointerException {
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();

                // Find selectedItem in products
                for (Product product : restaurant.getProducts()) {
                    if (product.getName().equals(selectedItem)) {
                        priceLabel.setText(product.getSellingPrice() + " TL");
                    }
                }


                if (!selectedItem.equals("Select an item")) {
                    addButton.setEnabled(true);
                    finalizeButton.setEnabled(true);
                } else {
                    addButton.setEnabled(false);
                }
            }
        });
    }

    /**
     * @param myPrice Price of order is stored at @myPrice.
     *                It will be later used to calculate total price of the order.
     */
    private void addButtonActionListener(JButton button, DefaultTableModel model,JComboBox<String> comboBox, JSpinner spinner,ArrayList<Double> myPrice,
                                         ArrayList<Integer> myCount) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double price = 0;
                for (Product product : restaurant.getProducts()) {
                    if (product.getName().equals(comboBox.getSelectedItem())) {
                        price = product.getSellingPrice();

                        for (int i = 0; i < (int)spinner.getValue(); i++) {
                            order.addProduct(product);
                        }
                    }
                }

                myPrice.add(price);
                myCount.add((Integer) spinner.getValue());
                price *= (int)spinner.getValue();
                model.addRow(new Object[]{(String)comboBox.getSelectedItem(),spinner.getValue(),price});
            }
        });
    }

    private void finalizeActionListener(JButton button, ArrayList<Double> myPrice, ArrayList<Integer> myCount) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waiter.createOrder(order);
                double total = 0;
                // Size of the myPrice and myCount are same
                for (int i = 0; i < myPrice.size(); i++) {
                    total += (myPrice.get(i) * myCount.get(i));
                }

                JOptionPane.showMessageDialog(null,"Your order is completed.\nTotal price is " + total + " TL");
                removeAll();
                orderHomePage();
            }
        });
    }
}
