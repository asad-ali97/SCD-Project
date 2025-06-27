package budgettracker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddExpenseWindow extends JFrame {
    public AddExpenseWindow(BudgetManager budgetManager) {
        setTitle("Add Expense");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextField amountField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField dateField = new JTextField();

        JButton submitBtn = new JButton("Submit");

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("Category:"));
        panel.add(categoryField);
        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(dateField);
        panel.add(new JLabel(""));
        panel.add(submitBtn);

        add(panel);
        setVisible(true);

        submitBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                String category = categoryField.getText().trim();
                LocalDate date = LocalDate.parse(dateField.getText().trim());

                Expense expense = new Expense(amount, category, date);
                budgetManager.addExpense(expense);

                if (budgetManager.isLimitExceeded(category)) {
                    JOptionPane.showMessageDialog(this, "⚠ Limit exceeded for category: " + category);
                } else {
                    JOptionPane.showMessageDialog(this, "Expense added successfully.");
                }
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Please enter date in YYYY-MM-DD format.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding expense.");
            }
        });
    }
}
