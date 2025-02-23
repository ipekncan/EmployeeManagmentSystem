package application;

import application.Employee;

public class HourlyEmployee extends Employee {
	
	private double wage;
	private int hours;
	
	public HourlyEmployee(String firstName,String lastName,String socialSecurityNumber,double wage,int hours) {
		
		super(firstName,lastName,socialSecurityNumber);
		
		if(wage>=0 && hours>=0 && hours<168) {
			
			this.wage=wage;
			this.hours=hours;
			
		}
		
		else throw new IllegalArgumentException("Wage cannot be negative and hours must be between 0 and 168");
		
	}

	public double getWage() {
		
		return wage;
	}
	
	public void setWage(double wage) {
		
		if(wage>=0)
		this.wage=wage;
		
		else throw new IllegalArgumentException("Wage cannot be negative!");
		
	}
	
	public int getHours() {
		
		return hours;
		
	}
	
	public void setHours(int hours) {
		
		if(hours>=0 && hours<168)
		this.hours=hours;
		
		else throw new IllegalArgumentException("Hours must be between 0 and 168!");
	}
	
	public double getPaymentAmount() {
		
		double result=0;
		
		if(hours<=40)
		   result=wage*hours;
		else if(hours>40)
			result=40*wage+(hours-40)*wage*1.5;
		
	    return result;
		
	}
	
	
	public String toString() {
		
		String str=super.toString();
		str += ","  + wage + "," +  hours + "," + getPaymentAmount();
		return str;
		
	}
	
}
