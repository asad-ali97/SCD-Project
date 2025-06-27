package budgettracker;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;


public class BudgetMenuGUI extends JFrame {
    private BudgetManager manager;

    public BudgetMenuGUI() {
        manager = new BudgetManager();

        setTitle("Budget Tracker - Main Menu");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addIncomeBtn = new JButton("Add Income");
        JButton addExpenseBtn = new JButton("Add Expense");
        JButton viewBalanceBtn = new JButton("View Balance");
        JButton categoryReportBtn = new JButton("Category Report");
        JButton sessionSummaryBtn = new JButton("Session Summary");
        JButton setLimitBtn = new JButton("Set Category Limit");
        JButton viewSortedAmountBtn = new JButton("Sort Expenses by Amount");
        JButton viewSortedDateBtn = new JButton("Sort Expenses by Date");
        JButton resetBtn = new JButton("Reset Data");
        JButton exitBtn = new JButton("Exit");

        JPanel panel = new JPanel(new GridLayout(10, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        panel.add(addIncomeBtn);
        panel.add(addExpenseBtn);
        panel.add(viewBalanceBtn);
        panel.add(categoryReportBtn);
        panel.add(setLimitBtn);
        panel.add(viewSortedAmountBtn);
        panel.add(viewSortedDateBtn);
        panel.add(sessionSummaryBtn);
        panel.add(resetBtn);
        panel.add(exitBtn);

        add(panel);
        setVisible(true);

        // Actions
        addIncomeBtn.addActionListener(e -> addIncome());
        addExpenseBtn.addActionListener(e -> addExpense());
        viewBalanceBtn.addActionListener(e -> viewBalance());
        categoryReportBtn.addActionListener(e -> viewCategoryReport());
        setLimitBtn.addActionListener(e -> setCategoryLimit());
        viewSortedAmountBtn.addActionListener(e -> showSortedByAmount());
        viewSortedDateBtn.addActionListener(e -> showSortedByDate());
        sessionSummaryBtn.addActionListener(e -> showSessionSummary());
        resetBtn.addActionListener(e -> {
            manager.resetData();
            JOptionPane.showMessageDialog(this, "Data has been reset.");
        });
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void addIncome() {
        try {
            String amtStr = JOptionPane.showInputDialog(this, "Enter income amount:");
            String source = JOptionPane.showInputDialog(this, "Enter income source:");
            String dateStr = JOptionPane.showInputDialog(this, "Enter income date (YYYY-MM-DD):");

            if (amtStr == null || source == null || dateStr == null) return;

            double amount = Double.parseDouble(amtStr.trim());
            LocalDate date = LocalDate.parse(dateStr.trim());

            Income income = new Income(amount, source, date);
            manager.addIncome(income);

            JOptionPane.showMessageDialog(this, "Income added successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please use YYYY-MM-DD for the date.");
        }
    }


    private void addExpense() {
        try {
            String amtStr = JOptionPane.showInputDialog(this, "Enter expense amount:");
            String category = JOptionPane.showInputDialog(this, "Enter category:");
            String dateStr = JOptionPane.showInputDialog(this, "Enter expense date (YYYY-MM-DD):");

            if (amtStr == null || category == null || dateStr == null) return;

            double amount = Double.parseDouble(amtStr.trim());
            LocalDate date = LocalDate.parse(dateStr.trim());

            Expense expense = new Expense(amount, category, date);
            manager.addExpense(expense);

            if (manager.isLimitExceeded(category)) {
                JOptionPane.showMessageDialog(this, "⚠ Warning: Limit exceeded for category: " + category);
            } else {
                JOptionPane.showMessageDialog(this, "Expense added successfully!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter date in YYYY-MM-DD format.");
        }
    }


    private void viewBalance() {
        double income = manager.getTotalIncome();
        double expense = manager.getTotalExpenses();
        double balance = manager.getBalance();
        String msg = "Total Income: " + income + "\nTotal Expenses: " + expense + "\nBalance: " + balance;
        JOptionPane.showMessageDialog(this, msg);
    }

    private void viewCategoryReport() {
        Map<String, Double> report = manager.getCategoryWiseExpenses();
        StringBuilder sb = new StringBuilder("Category-wise Expenses:\n");
        for (Map.Entry<String, Double> entry : report.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void setCategoryLimit() {
        String category = JOptionPane.showInputDialog(this, "Enter category to set limit:");
        String limitStr = JOptionPane.showInputDialog(this, "Enter limit for " + category + ":");
        if (category == null || limitStr == null) return;
        try {
            double limit = Double.parseDouble(limitStr.trim());
            manager.setCategoryLimit(category, limit);
            JOptionPane.showMessageDialog(this, "Limit set for " + category);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void showSortedByAmount() {
        List<Expense> sorted = manager.getSortedExpensesByAmount();
        StringBuilder sb = new StringBuilder("Expenses Sorted by Amount:\n");
        for (Expense e : sorted) {
            sb.append(e.getCategory()).append(" - ").append(e.getAmount()).append(" on ").append(e.getDate()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void showSortedByDate() {
        List<Expense> sorted = manager.getSortedExpensesByDate();
        StringBuilder sb = new StringBuilder("Expenses Sorted by Date:\n");
        for (Expense e : sorted) {
            sb.append(e.getDate()).append(" - ").append(e.getCategory()).append(": ").append(e.getAmount()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void showSessionSummary() {
        String summary = manager.getSessionSummary();
        JOptionPane.showMessageDialog(this, summary);
    }
}
