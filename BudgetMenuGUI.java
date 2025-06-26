package budgettracker;

import javax.swing.*;
import java.awt.*;

public class BudgetMenuGUI extends JFrame {
    private BudgetManager budgetManager;

    public BudgetMenuGUI() {
        budgetManager = new BudgetManager();

        setTitle("Budget Tracker - Main Menu");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create buttons
        JButton addIncomeBtn = new JButton("Add Income");
        JButton addExpenseBtn = new JButton("Add Expense");
        JButton viewBalanceBtn = new JButton("View Balance");
        JButton categoryReportBtn = new JButton("Category Report");
        JButton resetBtn = new JButton("Reset Data");
        JButton exitBtn = new JButton("Exit");

        // Layout
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(addIncomeBtn);
        panel.add(addExpenseBtn);
        panel.add(viewBalanceBtn);
        panel.add(categoryReportBtn);
        panel.add(resetBtn);
        panel.add(exitBtn);

        add(panel);
        setVisible(true);

        // === Event Handling ===

        addIncomeBtn.addActionListener(e -> new AddIncomeWindow(budgetManager));
        addExpenseBtn.addActionListener(e -> new AddExpenseWindow(budgetManager));

        viewBalanceBtn.addActionListener(e -> showBalance());
        categoryReportBtn.addActionListener(e -> showReport());
        resetBtn.addActionListener(e -> resetData());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void addIncome() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter Income Amount:");
        String source = JOptionPane.showInputDialog(this, "Enter Income Source:");

        try {
            double amount = Double.parseDouble(amountStr.trim());
            budgetManager.addIncome(amount, source);
            JOptionPane.showMessageDialog(this, "Income added successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void addExpense() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter Expense Amount:");
        String category = JOptionPane.showInputDialog(this, "Enter Expense Category:");
        String date = JOptionPane.showInputDialog(this, "Enter Date (YYYY-MM-DD):");

        try {
            double amount = Double.parseDouble(amountStr.trim());
            budgetManager.addExpense(amount, category, date);
            JOptionPane.showMessageDialog(this, "Expense added successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void showBalance() {
        double income = budgetManager.getTotalIncome();
        double expenses = budgetManager.getTotalExpense();
        double balance = budgetManager.getBalance();

        String msg = "Total Income: Rs. " + income + "\n" +
                     "Total Expenses: Rs. " + expenses + "\n" +
                     "Balance: Rs. " + balance;

        JOptionPane.showMessageDialog(this, msg);
    }

    private void showReport() {
        String report = budgetManager.getCategoryWiseReport();
        JOptionPane.showMessageDialog(this, report);
    }

    private void resetData() {
        budgetManager = new BudgetManager(); // reset object
        JOptionPane.showMessageDialog(this, "All data has been reset.");
    }
}
