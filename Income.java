package budgettracker;

import java.time.LocalDate;
public class Income {
	private double amount;
	private String source;
	private LocalDate date;
	public Income(double amount,String source,LocalDate date)
	{
		this.amount=amount;
		this.source=source;
		this.date=date;
	}
	public double getAmount()
	{
		return amount;
	}
	public String getSource()
	{
		return source;
	}
	public LocalDate getDate()
	{
		return date;
	}
	
	@Override
	public String toString()
	{
		return date+" | "+source+" | "+amount;
	}

}
