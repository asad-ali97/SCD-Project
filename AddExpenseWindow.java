package budgettracker;

import javax.swing.*;
import java.awt.*;

public class AddExpenseWindow extends JFrame {
    public AddExpenseWindow(BudgetManager budgetManager) {
        setTitle("Add Expense");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Fields
        JTextField amountField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField dateField = new JTextField(); // YYYY-MM-DD
        JButton submitBtn = new JButton("Submit");

        // Layout
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Expense Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(dateField);
        panel.add(new JLabel(""));
        panel.add(submitBtn);

        add(panel);
        setVisible(true);

        // Submit Button Logic
        submitBtn.addActionListener(e -> {
            String amountText = amountField.getText().trim();
            String category = categoryField.getText().trim();
            String date = dateField.getText().trim();

            if (amountText.isEmpty() || category.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            try {
                double amount = Double.parseDouble(amountText);
                budgetManager.addExpense(amount, category, date);
                JOptionPane.showMessageDialog(this, "Expense added successfully.");
                dispose(); // Close the window
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number for amount.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding expense.");
                ex.printStackTrace(); // Optional: log to console
            }
        });
    }
}

