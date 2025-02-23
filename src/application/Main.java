package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.*;

import application.BasePlusCommissionEmployee;
import application.CommissionEmployee;
import application.Employee;
import application.HourlyEmployee;
import application.SalariedEmployee;

import java.io.*;

public class Main extends Application {
	
	static Employee[] employee=new Employee[1000];
	static int employeeCounter=0;
	static String[] employeeType=new String[1000];
	
	//Buttons 
		Button btAdd=new Button("Add");
		Button btSearch=new Button("Search by SSN");
		Button btUpdate=new Button("Update by SSN");
		Button btClean=new Button("Clean TextFields");
		
	//Text Fields
	static TextField tfFirstName=new TextField();
	static TextField tfLastName=new TextField();
	static TextField tfSSN= new TextField();
	static TextField tfSearch=new TextField();
	static TextField tfGrossSales=new TextField();
	static TextField tfCommission=new TextField();
	static TextField tfBaseSalary=new TextField();
	static TextField tfWeeklySalary=new TextField();
	static TextField tfWage=new TextField();
	static TextField tfHours=new TextField();
		
	 Label empTypeLabel = new Label("Employee Type:");
	static ComboBox<String> empTypeComboBox = new ComboBox<>();
		
	
	static Label valueLabel = new Label("Value");

	public void start(Stage stage)throws Exception{
		stage.setTitle("EMPLOYEE SALARY CALCULATOR");
		GridPane root = new GridPane();
		
		root.setAlignment(Pos.CENTER);
		root.setHgap(5);
		root.setVgap(5);
		 
		empTypeComboBox.getItems().addAll("Salaried Employee", "Hourly Employee", "Commission Employee", "Base Plus Commission Employee","None");
		root.add(empTypeLabel, 1, 1); 
		root.add(empTypeComboBox, 2, 1);
		
		//Label
		root.add(new Label("First Name"),0,3);
		root.add(tfFirstName, 1, 3);
		
		root.add(new Label("Last Name"), 0, 4);
		root.add(tfLastName, 1, 4);
		
		root.add(new Label("SSN"), 0, 5); 
		root.add(tfSSN, 1, 5);
		
		root.add(new Label("Weekly Salary"), 0, 6);
		root.add(tfWeeklySalary, 1, 6);
		
		root.add(new Label("Salary"), 0, 7);
		
		//Label valueLabel = new Label("Value");
		root.add(valueLabel, 1, 7);
		
		root.add(new Label("Wage"), 2, 3);
		root.add(tfWage, 3, 3);
		
		root.add(new Label("Hours"), 2, 4);
		root.add(tfHours, 3, 4);
		
		root.add(new Label("Gross Sales"), 2, 5);
		root.add(tfGrossSales, 3, 5);
		
		root.add(new Label("Commission Rate"), 2, 6); 
		root.add(tfCommission, 3, 6);
		
		root.add(new Label("Base Salary"), 2, 7);
		root.add(tfBaseSalary, 3,7);
		
		
		root.add(btAdd, 1, 8);
		root.add(btSearch,2, 8);
		root.add(btUpdate, 3, 8);
		root.add(btClean, 4, 8);
		
		btAdd.setOnAction(this::handle);
		btSearch.setOnAction(this::handle);
		btUpdate.setOnAction(this::handle);
		btClean.setOnAction(this::handle);
		
		empTypeComboBox.setOnAction(this::handle);
		
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		stage.show();
	}
	public void handle(ActionEvent event) {
		if(event.getSource()==empTypeComboBox) {
			handleEmployeeTypeSelection();
		}
		
		if(event.getSource()==btAdd) {
			addEmployee();
			
			
		}
		else if(event.getSource()==btSearch) {
			readFromFile();
			Employee foundEmp=searchEmployee(tfSSN.getText());
			
			 if(foundEmp !=null) {    //if the employee with that ssn exists
                        
                       System.out.println(foundEmp);
       	 
			 }
			
			 else
				 System.out.println("Employee not found");
		}
		
		else if(event.getSource()==btUpdate) {
			update();
		}
		else if(event.getSource()==btClean) {
			cleanTextFields();
			valueLabel.setText("Value");
		}
	} 
	
	public void handleEmployeeTypeSelection() {
		String selectedEmp=empTypeComboBox.getValue().trim();
		tfFirstName.setDisable(false);
		tfLastName.setDisable(false);
		tfSSN.setDisable(true);
		valueLabel.setDisable(false);
		
		switch(selectedEmp) {
		case"Salaried Employee":
			tfWage.setDisable(true);
            tfHours.setDisable(true);
            tfGrossSales.setDisable(true);
            tfCommission.setDisable(true);
            tfBaseSalary.setDisable(true);
            tfWeeklySalary.setDisable(false);
			break;
		case"Commission Employee":
			tfWage.setDisable(true);
            tfHours.setDisable(true);
            tfGrossSales.setDisable(false);
            tfCommission.setDisable(false);
            tfBaseSalary.setDisable(true);
            tfWeeklySalary.setDisable(true);
			break;
		case"Base Plus Commission Employee":
			tfWage.setDisable(true);
            tfHours.setDisable(true);
            tfGrossSales.setDisable(false);
            tfCommission.setDisable(false);
            tfBaseSalary.setDisable(false);
            tfWeeklySalary.setDisable(true);
			break;
		case"Hourly Employee":
			tfWage.setDisable(false);
            tfHours.setDisable(false);
            tfGrossSales.setDisable(true);
            tfCommission.setDisable(true);
            tfBaseSalary.setDisable(true);
            tfWeeklySalary.setDisable(true);
			break;
			default:tfWage.setDisable(false);
            tfHours.setDisable(false);
            tfGrossSales.setDisable(false);
            tfCommission.setDisable(false);
            tfBaseSalary.setDisable(false);
            tfWeeklySalary.setDisable(false);
            tfSSN.setDisable(false);
				break;
		}
	}
	

	
	
private static void addEmployee() {

		
		
	//	Scanner scanner=new Scanner(System.in);
		
		System.out.println("\nPlease enter your employee type\n1.Salaried Employee\n2.Commission Employee\n3.Base plus commission employee\n4.Hourly Employee");
		
		String i=empTypeComboBox.getValue();
		
		
		switch(i) {
		
		case "Salaried Employee":
			
			
			String name=tfFirstName.getText();	
			
			String surname=tfLastName.getText();	
			
			String ssn= UUID.randomUUID().toString(); //randomly assigns a social security number
			
			double salary=Double.parseDouble(tfWeeklySalary.getText());
			
			
			Employee emp= new SalariedEmployee(name,surname,ssn,salary);
			employee[employeeCounter]=emp;
			employeeType[employeeCounter]="Salaried Employee,";
			employeeCounter++;
			
			
			addToFile();
			
			valueLabel.setText(String.valueOf(emp.getPaymentAmount()));
			System.out.println("Employee added successfully!\n");
					
			break;
			
		case "Commission Employee":
			
			String name2=tfFirstName.getText();	
			
			String surname2=tfLastName.getText();	
			
			String ssn2= UUID.randomUUID().toString();//randomly assigns a social security number
			
			double grossSales=Double.parseDouble(tfGrossSales.getText());	
		
		
			double commissionRate=Double.parseDouble(tfCommission.getText());	
			
			
			Employee emp2= new CommissionEmployee(name2,surname2,ssn2,grossSales,commissionRate);
			
			employee[employeeCounter]=emp2;
			employeeType[employeeCounter]="Commission Employee,";
			employeeCounter++;
			
			addToFile();
			valueLabel.setText(String.valueOf(grossSales*commissionRate));
			System.out.println("Employee added successfully!\n");
				
			break;
			
		case "Base Plus Commission Employee":
			
			String name3=tfFirstName.getText();	
			
			String surname3=tfLastName.getText();	
			
			String ssn3= UUID.randomUUID().toString();//randomly assigns a social security number
			
			double grossSales2=Double.parseDouble(tfGrossSales.getText());	
		
		
			double commissionRate2=Double.parseDouble(tfCommission.getText());	
	
			
			double baseSalary=Double.parseDouble(tfBaseSalary.getText());	
			
			
			Employee emp3= new BasePlusCommissionEmployee(name3,surname3,ssn3,grossSales2,commissionRate2,baseSalary);
			employee[employeeCounter]=emp3;
			employeeType[employeeCounter]="Base Plus Commission Employee,";
			employeeCounter++;
			
			addToFile();
			valueLabel.setText(String.valueOf(baseSalary+grossSales2*commissionRate2));
			System.out.println("Employee added successfully!\n");
			
			break;
			
		case"Hourly Employee":
			
			String name4=tfFirstName.getText();	
			
			String surname4=tfLastName.getText();	
			
			String ssn4= UUID.randomUUID().toString();//randomly assigns a social security number
			
			double wage=Double.parseDouble(tfWage.getText());	
			
			int hours=Integer.parseInt(tfHours.getText());	
			
			
			Employee emp4= new HourlyEmployee(name4,surname4,ssn4,wage,hours);
			employee[employeeCounter]=emp4;
			employeeType[employeeCounter]="Hourly Employee,";
			employeeCounter++;
			
			addToFile();
			valueLabel.setText(String.valueOf(wage*hours));
			System.out.println("Employee added successfully!\n");
			
			break;
		
		default:
			System.out.println("Invalid number!");
			
		}
		
	}public static void addToFile() {
		
		
	    try (BufferedWriter fWriter = new BufferedWriter(new FileWriter("records.txt", true))) {
	    	
	        if (employeeCounter > 0 && employee[employeeCounter - 1] != null) {
	        	
	        	fWriter.append(employeeType[employeeCounter-1]);//since employeeCounter increases after we assign(to the array) that employee we need to write employeeCounter-1 to represent that employee after the assigning
	            fWriter.append(employee[employeeCounter - 1].toString());//opening the file in append mode so it will not overwrite the file, instead it will add the new employee without overwriting
	            fWriter.newLine();
	            
	        } 
	        
	    } 
	    
	    catch (IOException e) {
	    	
	        e.printStackTrace();
	        
	    }
	}
public static void readFromFile() {
	    try (BufferedReader fReader = new BufferedReader(new FileReader("records.txt"))) {
	    	
	        String emp;

	        while ((emp = fReader.readLine()) != null) {
	        	
	            String[] columns = emp.split(",");

	            if (columns[0].equals("Salaried employee")) {//salaried Employee
	               
	            	String type=columns[0].trim();
	                String firstName = columns[1].trim();
	                String lastName = columns[2].trim();
	                String ssn = columns[3].trim();
	                double weeklySalary = Double.parseDouble(columns[4].trim());

	                Employee temp = new SalariedEmployee(firstName, lastName, ssn, weeklySalary);
	                employee[employeeCounter] = temp;
	                employeeType[employeeCounter]=type + ",";
	                employeeCounter++;

	            } 
	            
	            else if (columns[0].equals("Hourly Employee")) {//Hourly employee
	                
	            	String type=columns[0].trim();
	                String firstName = columns[1].trim();
	                String lastName = columns[2].trim();
	                String ssn = columns[3].trim();
	                double wage = Double.parseDouble(columns[4].trim());
	                int hours = Integer.parseInt(columns[5].trim()); 
	                
                    Employee temp = new HourlyEmployee(firstName, lastName, ssn, wage,  hours);
                    employee[employeeCounter] = temp;
                    employeeType[employeeCounter]=type + ",";
                    employeeCounter++;
                    
	            }
	            
	                else if (columns[0].equals("Commission Employee")) {//Commission employee
	                	
		            	String type=columns[0].trim();
		                String firstName = columns[1].trim();
		                String lastName = columns[2].trim();
		                String ssn = columns[3].trim();
		                double commissionRate = Double.parseDouble(columns[4].trim());
		                double grossSales = Double.parseDouble(columns[5].trim()); 
		                
	                    Employee temp = new CommissionEmployee(firstName, lastName, ssn, commissionRate,  grossSales);
	                    employee[employeeCounter] = temp;
	                    employeeType[employeeCounter]=type + ",";
	                    employeeCounter++;
	                 
	            } 
	            
	                else if (columns[0].equals("Base Plus Commission Employee")) {//base plus commission employee
	                
	            	String type=columns[0].trim();
	                String firstName = columns[1].trim();
	                String lastName = columns[2].trim();
	                String ssn = columns[3].trim();
	                double grossSales = Double.parseDouble(columns[4].trim());
	                double commissionRate = Double.parseDouble(columns[5].trim());
	                double baseSalary = Double.parseDouble(columns[6].trim());

	                Employee temp = new BasePlusCommissionEmployee(firstName, lastName, ssn, grossSales, commissionRate, baseSalary);
	                employee[employeeCounter] = temp;
	                employeeType[employeeCounter]=type + ",";
	                employeeCounter++;
	                
	            }
	        }
	        
	    } 
	    
	    catch (IOException e) {
	    	
	        e.printStackTrace();
	        
	    }
	}

public static Employee searchEmployee(String searchssn) {
		
		searchssn = searchssn.trim();
		
	    for (int i = 0; i < employeeCounter; i++) {
	    	
	        if (employee[i] != null && employee[i].getSocialSecurityNumber().equals(searchssn)) {//using equals() method because ssn is a string and we cannot directly compare strings
	        	
	            return employee[i];//employee with searchssn exists
	            
	        }
	    }
	    
	    return null; 
	    
	}

public static void updateFile() {
	
	
	try(BufferedWriter fWriter=new BufferedWriter(new FileWriter("records.txt"))) {
		
		for(int i=0;i<employeeCounter;i++) {
			
			if(employee[i]!=null && employeeType[i]!=null) {
				
			fWriter.write(employeeType[i]);
			fWriter.write(employee[i].toString()); //opening the file in write mode so it will overwrite the file with updated informations
			fWriter.newLine();
			
			}
		}
	}
	
	catch (IOException e) {
		
		e.printStackTrace();
		
	}
}

public void cleanTextFields() {
    //Clean all TextFields
    tfFirstName.clear();
    tfLastName.clear();
    tfSSN.clear();
    tfSearch.clear();
    tfGrossSales.clear();
    tfCommission.clear();
    tfBaseSalary.clear();
    tfWeeklySalary.clear();
    tfWage.clear();
    tfHours.clear();
}

public static void update() {
	
readFromFile();
Employee foundEmpp=searchEmployee(tfSSN.getText());

if(foundEmpp!=null) {//if the employee with that ssn exists
	if (tfFirstName.getText().trim().length() > 0) {
        foundEmpp.setFirstName(tfFirstName.getText().trim());
    }
    if (tfLastName.getText().trim().length() > 0) {
        foundEmpp.setLastName(tfLastName.getText().trim());
    }
	
		if(foundEmpp instanceof SalariedEmployee) {
			//1.Name\n2.Surname\n3.Weekly Salary
			
			if (tfWeeklySalary.getText().trim().length() > 0){
				double weeklysalary=Double.parseDouble(tfWeeklySalary.getText());
		    	((SalariedEmployee) foundEmpp).setWeeklySalary(weeklysalary);
			}
			valueLabel.setText(String.valueOf(((SalariedEmployee)foundEmpp).getPaymentAmount()));
			System.out.println("Salaried Employee updated.");
			
		}
		else if(foundEmpp instanceof BasePlusCommissionEmployee) {
			//1.Name\n2.Surname\n3.Gross sales\n4.Commission rate\n5.Base salary
			
			 if (tfGrossSales.getText().trim().length() > 0) {
	                double grossSales = Double.parseDouble(tfGrossSales.getText());
	                ((BasePlusCommissionEmployee) foundEmpp).setGrossSales(grossSales);
	            }
	            if (tfCommission.getText().trim().length() > 0) {
	                double commissionRate = Double.parseDouble(tfCommission.getText());
	                ((BasePlusCommissionEmployee) foundEmpp).setCommissionRate(commissionRate);
	            }
	            if (tfBaseSalary.getText().trim().length() > 0) {
	                double baseSalary = Double.parseDouble(tfBaseSalary.getText());
	                ((BasePlusCommissionEmployee) foundEmpp).setBaseSalary(baseSalary);
	            }
	            valueLabel.setText(String.valueOf(((BasePlusCommissionEmployee)foundEmpp).getPaymentAmount()));
	            System.out.println("Base Plus Commission Employee updated.");
			
		}
		else if(foundEmpp instanceof CommissionEmployee) {
			//1.Name\n2.Surname\n3.Gross sales\n4.Commission rate
			 if (tfGrossSales.getText().trim().length() > 0) {
	                double grossSales = Double.parseDouble(tfGrossSales.getText());
	                ((BasePlusCommissionEmployee) foundEmpp).setGrossSales(grossSales);
	            }
	            if (tfCommission.getText().trim().length() > 0) {
	                double commissionRate = Double.parseDouble(tfCommission.getText());
	                ((BasePlusCommissionEmployee) foundEmpp).setCommissionRate(commissionRate);}
	            valueLabel.setText(String.valueOf(((CommissionEmployee)foundEmpp).getPaymentAmount()));
	            System.out.println("Commission Employee updated.");
		}
		else if(foundEmpp instanceof HourlyEmployee) {
			//1.Name\n2.Surname\n3.Wage\n5.Hours
			
			if(tfWage.getText().trim().length()>0) {
			double wage=Double.parseDouble(tfWage.getText());
	    	((HourlyEmployee) foundEmpp).setWage(wage);
	    	}
			if(tfHours.getText().trim().length()>0) {
				int Hours=Integer.parseInt(tfHours.getText());
				((HourlyEmployee)foundEmpp).setHours(Hours);
			}
			valueLabel.setText(String.valueOf(((HourlyEmployee)foundEmpp).getPaymentAmount()));
			System.out.println("Hourly Employee updated.");
		}
updateFile();}
else System.out.println("Employee cannot be found!\n");
}
	
	
	
public static void main(String[] args) {
		launch(args);
		
	
		}
	}
	

