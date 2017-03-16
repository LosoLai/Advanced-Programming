package Tute03;

public class Account {
	private String _name; // a different style to define instance
	private String _ID; // variables, so keyword ¡§this¡¨ is not
	private double _balance; // necessary
	public Account(String name, String ID, double balance) {
	 _name = name;
	 _ID = ID;
	 _balance = balance;
	}
	
	public Account(String name, String ID) {
		this(name, ID, 0.0);
	 //_balance = 0;
	}
	public double getBalance() {
		return _balance;
	}
	public boolean withdraw(double amt) {
	 if (amt > _balance) 
		 return false;
	 else
		 _balance -= amt;
	 return true;
	}
	public boolean deposit(double amt) {
	 _balance += amt;
	 return true;
	 }
	public double getBlance()
	{
		return _balance;
	}
	public void setBalance(double balance)
	{
		this._balance = balance;
	}
}
