package application;

import application.CommissionEmployee;

public class BasePlusCommissionEmployee extends CommissionEmployee{
	
	private double baseSalary;
	
	public BasePlusCommissionEmployee(String firstName, String lastName,String socialSecurityNumber,double grossSales,double commissionRate,double baseSalary) {
		
		super(firstName,lastName,socialSecurityNumber,grossSales,commissionRate);
		
		if(baseSalary>=0)
		 this.baseSalary=baseSalary;
		
		else throw new IllegalArgumentException("Base salary must be more than 0!");
	}
	
	public double getBaseSalary() {
		
		return baseSalary;
		
	}
	
	public void setBaseSalary(double baseSalary) {
		
		if(baseSalary>0)
		this.baseSalary=baseSalary;
		
		else throw new IllegalArgumentException("Base salary must be more than 0!");
		
	}
	
	public double getPaymentAmount() {
		
		double result= baseSalary + (getGrossSales()*getCommissionRate());
		return result;
		
	}
	
	public String toString() {
		
		String str="";
		
		str+=getFirstName()+ "," +getLastName()+","+getSocialSecurityNumber()+","+getGrossSales()+","+getCommissionRate()+","+ getBaseSalary() + "," +  getPaymentAmount();
		
		return str;
		
	}
	
}

