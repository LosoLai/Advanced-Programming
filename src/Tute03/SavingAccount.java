package Tute03;

public class SavingAccount extends Account{
	private double _minAmount;
	public SavingAccount(String name, String ID, double balance,
	 double minAmount) 
	{
		super(name, ID, balance);
		_minAmount = 100;
	}
	public boolean withdraw(double amt) {
		if (amt > (super.getBalance() - _minAmount)) 
			return false;
		else
		{
			double balance = super.getBalance();
			balance -= amt;
			super.setBalance(balance);
			return true;
		}
	}

	public boolean deposit(double amt) {
		if (amt >= _minAmount - super.getBalance() )
		{
			super.deposit(amt);
			return true;
		}
		return false;
	}
}
