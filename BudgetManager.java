package budgettracker;
import java.util.*;
import java.util.stream.Collectors;

public class BudgetManager {
	private List<Income>incomes=new ArrayList<>();
	private List<Expense> expenses=new ArrayList<>();
	private Map<String,Double> categoryLimits=new HashMap<>();
	
	public void addIncome(Income income)
	{
		incomes.add(income);
	}
	
	public void addExpense(Expense expense)
	{
		expenses.add(expense);
	}
	public double getTotalIncome()
	{
		return incomes.stream().mapToDouble(Income::getAmount).sum();
	}
	public double getTotalExpenses()
	{
		return expenses.stream().mapToDouble(Expense::getAmount).sum();
	}
	public double getBalance()
	{
		return getTotalIncome()-getTotalExpenses();
	}
    public Map<String, Double> getCategoryWiseExpenses() {
        Map<String, Double> map = new HashMap<>();
        for (Expense e : expenses) {
            map.put(e.getCategory(), map.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }
        return map;
    }

    public String getHighestExpenseCategory() {
        return getCategoryWiseExpenses().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");
    }

    public void setCategoryLimit(String category, double limit) {
        categoryLimits.put(category, limit);
    }

    public boolean isLimitExceeded(String category) {
        double spent = getCategoryWiseExpenses().getOrDefault(category, 0.0);
        double limit = categoryLimits.getOrDefault(category, Double.MAX_VALUE);
        return spent >= limit;
    }

    public List<Expense> getSortedExpensesByAmount() {
        return expenses.stream()
                .sorted(Comparator.comparingDouble(Expense::getAmount))
                .collect(Collectors.toList());
    }

    public List<Expense> getSortedExpensesByDate() {
        return expenses.stream()
                .sorted(Comparator.comparing(Expense::getDate))
                .collect(Collectors.toList());
    }

    public void resetData() {
        incomes.clear();
        expenses.clear();
        categoryLimits.clear();
    }

    public String getSessionSummary() {
        return "Total Income: " + getTotalIncome() +
                "\nTotal Expenses: " + getTotalExpenses() +
                "\nBalance: " + getBalance() +
                "\nHighest Expense Category: " + getHighestExpenseCategory();
    }
	

}
