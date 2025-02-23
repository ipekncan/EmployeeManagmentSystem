package application;

import application.Employee;

public class CommissionEmployee extends Employee {
	
 	private double grossSales;
	private double commissionRate;

	public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber,double grossSales,double commissionRate) {
		
		super(firstName,lastName,socialSecurityNumber);
		
		if(grossSales>=0 && commissionRate>0 && commissionRate<1) {
			
			this.grossSales=grossSales;
			this.commissionRate=commissionRate;
			
		}
		
		else throw new IllegalArgumentException("Commission rate must be between 0 and 1! And gross sales cannot be negative!");
		
	}
	
	public double getGrossSales() {
		
		return grossSales;
	}
	
	public void setGrossSales(double grossSales) {
		
		if(grossSales>=0)
		this.grossSales=grossSales;
		
		else throw new IllegalArgumentException("Gross sales cannot be negative!");
		
	}
	
	public double getCommissionRate() {
		
		return commissionRate;
	}
	
	public void setCommissionRate(double commissionRate) {
		
		if(commissionRate>0 && commissionRate<1)
		this.commissionRate=commissionRate;
		
		else throw new IllegalArgumentException("Commission rate must be between 0 and 1!");
		
	}
	
	public double getPaymentAmount() {
		
		double result=grossSales*commissionRate;
		return result;
		
	}
	
	public String toString() {
		
		String str=super.toString();
		str += "," +  grossSales + "," +  commissionRate + "," + getPaymentAmount();
		return str;
		
	}
	
}

