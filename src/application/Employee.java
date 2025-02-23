package application;

import application.Payable;

public abstract class Employee implements Payable {

	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
	
	public Employee(String firstName, String lastName, String socialSecurityNumber) {
		
		this.firstName=firstName;
		this.lastName=lastName;
		this.socialSecurityNumber=socialSecurityNumber;
		
	}
	
	public String getFirstName() {
		
		return firstName;
		
	}
	
	public void setFirstName(String firstName) {
		
		this.firstName=firstName;
		
	}
	
	public String getLastName() {
		
		return lastName;
		
	}
	
	public void setLastName(String lastName) {
		
		this.lastName=lastName;
		
	}
	
	public String getSocialSecurityNumber() {
		
		return socialSecurityNumber;
		
	}
	
	
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		
		
		this.socialSecurityNumber=socialSecurityNumber;
		
	}
	
	
	public String toString() {
		
		String str="";
		
		str= firstName + "," + lastName + "," +  socialSecurityNumber  ;
		
		return str;
		
	}
	
}

