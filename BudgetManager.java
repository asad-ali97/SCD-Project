package budgettracker;

import java.util.*;

public class BudgetManager {
    private double totalIncome = 0;
    private double totalExpense = 0;

    private Map<String, Double> categoryExpenses = new HashMap<>();

    public void addIncome(double amount, String source) {
        totalIncome += amount;
    }

    public void addExpense(double amount, String category, String date) {
        totalExpense += amount;
        categoryExpenses.put(category, categoryExpenses.getOrDefault(category, 0.0) + amount);
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getBalance() {
        return totalIncome - totalExpense;
    }

    public String getCategoryWiseReport() {
        StringBuilder report = new StringBuilder("Category-wise Expense Report:\n");
        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            report.append(entry.getKey()).append(": Rs. ").append(entry.getValue()).append("\n");
        }
        return report.toString();
    }
}
