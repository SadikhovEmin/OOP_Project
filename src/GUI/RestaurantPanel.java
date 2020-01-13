package GUI;

import Model.Cook;
import Model.Employee;
import Model.Restaurant;
import Model.Waiter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Emin Sadikhov <emin.sadikhov@ozu.edu.tr>
 */

public class RestaurantPanel extends JPanel {
    private Restaurant restaurant;
    private JButton listEmployees;
    private JButton addCook;
    private JButton addWaiter;
    private JButton calculateExpenses;

    public RestaurantPanel(Restaurant restaurant) {
        this.restaurant = restaurant;

        setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.listEmployees = new JButton("List Employees");
        this.addCook = new JButton("Add Cook");
        this.addWaiter = new JButton("Add Waiter");
        this.calculateExpenses = new JButton("Calculate Expenses");

        upperPanel.add(listEmployees);
        upperPanel.add(addCook);
        upperPanel.add(addWaiter);
        upperPanel.add(calculateExpenses);

        add(upperPanel,BorderLayout.NORTH);

        JPanel lowerPanel = new JPanel();
        add(lowerPanel,BorderLayout.CENTER);

        listEmployees(lowerPanel);
        addCook(lowerPanel);
        addWaiter(lowerPanel);
        calculateExpenses(lowerPanel);
    }

    /**
     * ActionListeners
     * @param lowerPanel Changes depending on which button is pressed
     */

    private void listEmployees(JPanel lowerPanel) {
        this.listEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerPanel.removeAll();
                lowerPanel.repaint();
                lowerPanel.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Job");

                JTable table = new JTable(model);
                panel.add(table,BorderLayout.NORTH);

                setListEmployees(model);

                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setVisible(true);
                panel.add(scrollPane);

                lowerPanel.add(panel);

                lowerPanel.revalidate();
            }
        });
    }

    private void addCook(JPanel lowerPanel) {

        this.addCook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerPanel.removeAll();
                lowerPanel.repaint();
                lowerPanel.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3,3));

                lowerPanel.add(panel,BorderLayout.NORTH);

                JLabel nameLabel = new JLabel("Name : ");
                JTextField nameTextField = new JTextField();
                JLabel salaryLabel = new JLabel("Salary : ");
                JTextField salaryTextField = new JTextField();
                JButton addButton = new JButton("Add");

                panel.add(nameLabel);
                panel.add(nameTextField);
                panel.add(salaryLabel);
                panel.add(salaryTextField);
                panel.add(new JLabel());                // Empty label to move add button to right border of the frame
                panel.add(addButton);

                addCookButton(addButton,nameTextField,salaryTextField);

                lowerPanel.revalidate();                                    // Redraws the layout
            }
        });
    }

    private void addWaiter(JPanel lowerPanel) {
        this.addWaiter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerPanel.removeAll();
                lowerPanel.repaint();

                lowerPanel.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(2,2));

                JLabel name = new JLabel("Name");
                JTextField nameTextField = new JTextField();
                JLabel label = new JLabel();                         // To move add button to right border of the frame
                JButton addButton = new JButton("Add");

                panel.add(name);
                panel.add(nameTextField);
                panel.add(label);
                panel.add(addButton);

                addWaiterButton(addButton,nameTextField);

                lowerPanel.add(panel,BorderLayout.NORTH);

                lowerPanel.revalidate();
            }
        });
    }

    private void calculateExpenses(JPanel lowerPanel) {
        this.calculateExpenses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerPanel.removeAll();
                lowerPanel.repaint();

                lowerPanel.setLayout(new BorderLayout());

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3,2));

                JLabel expenses = new JLabel("Expenses : ");
                double calculatedExpenses = restaurant.calculateExpenses();
                JLabel expenseAmount = new JLabel(String.valueOf(calculatedExpenses) + " TL");

                JLabel revenue = new JLabel("Revenue : ");
                double calculatedRevenue = restaurant.calculateRevenue();

                JLabel revenueAmount = new JLabel(String.valueOf(calculatedRevenue) + " TL");

                JLabel profit = new JLabel("Profit");
                JLabel profitAmount = new JLabel(String.valueOf(calculatedRevenue - calculatedExpenses) + " TL");

                panel.add(expenses);
                panel.add(expenseAmount);
                panel.add(revenue);
                panel.add(revenueAmount);
                panel.add(profit);
                panel.add(profitAmount);

                lowerPanel.add(panel,BorderLayout.NORTH);

                lowerPanel.revalidate();
            }
        });
    }

    private void setListEmployees(DefaultTableModel model) {
        for (Employee employee : restaurant.getEmployees()) {
            if (employee instanceof Cook) {
                model.addRow(new Object[]{employee.getId(),employee.getName(),"Cook"});

            } else if (employee instanceof Waiter) {
                model.addRow(new Object[]{employee.getId(),employee.getName(),"Waiter"});
            }
        }
    }

    /**
     * Adds Cook to the list of employees of the restaurant
     * @param button Add button
     * @param name Name of Cook (Employee)
     * @param salary Salary of Cook (Employee)
     */
    private void addCookButton(JButton button, JTextField name, JTextField salary) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkForError(name.getText())) {
                    JOptionPane.showMessageDialog(null, "Error : Enter a name.","Error",JOptionPane.ERROR_MESSAGE);

                } else if (!checkForError(salary.getText()) || Double.parseDouble(salary.getText()) < 0 || salary.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Error : Enter a salary.","Error",JOptionPane.ERROR_MESSAGE);

                } else {
                    restaurant.addCook(name.getText(), Double.parseDouble(salary.getText()));
                    JOptionPane.showMessageDialog(null, "Cook added successfully");
                }
            }
        });
    }

    /**
     * Adds Waiter to the list of employees of the restaurant
     * @param button Add Button
     * @param name Name of the Waiter (Employee)
     */
    private void addWaiterButton(JButton button, JTextField name) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkForError(name.getText())) {
                    JOptionPane.showMessageDialog(null, "Error : Enter a name.","Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    restaurant.addWaiter(name.getText());
                    JOptionPane.showMessageDialog(null, "Waiter added successfully");
                }
            }
        });
    }

    public boolean checkForError(String str) {
        try {
            double d = Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}