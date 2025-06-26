package budgettracker;

import javax.swing.*;
import java.awt.*;

public class AddIncomeWindow extends JFrame {
    public AddIncomeWindow(BudgetManager budgetManager) {
        setTitle("Add Income");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextField amountField = new JTextField();
        JTextField sourceField = new JTextField();
        JButton submitBtn = new JButton("Submit");

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("Source:"));
        panel.add(sourceField);
        panel.add(new JLabel(""));
        panel.add(submitBtn);

        add(panel);
        setVisible(true);

        submitBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                String source = sourceField.getText().trim();
                budgetManager.addIncome(amount, source);
                JOptionPane.showMessageDialog(this, "Income added successfully!");
                dispose(); // close window
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });
    }
}

