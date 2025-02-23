package application;

import application.Employee;

public class SalariedEmployee extends Employee {
	
	private double weeklySalary;
	
	public SalariedEmployee(String firstName,String lastName,String socialSecurityNumber,double weeklySalary) {
		
		super(firstName,lastName,socialSecurityNumber);
		
		if(weeklySalary>=0)
		this.weeklySalary=weeklySalary;
		
		else throw new IllegalArgumentException("Weekly salary cannot be negative!");
		
	}
	
	public double getWeeklySalary() {
		
		return weeklySalary;
		
	}
	
	public void setWeeklySalary(double weeklySalary) {
		
		if(weeklySalary>=0)
		this.weeklySalary=weeklySalary;
		
		else throw new IllegalArgumentException("Weekly salary cannot be negative!");
		
	}
	
	public double getPaymentAmount() {
		
		return weeklySalary*4;
	}
    
	public String toString() {
		
		String str=super.toString();
		str += ","  + weeklySalary + "," + getPaymentAmount() ;
		return str;
		
	}
}

