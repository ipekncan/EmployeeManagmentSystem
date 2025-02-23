# Employee Management System  
📌 Developed in collaboration with **[Ezgi Şahin](https://github.com/EzgiSahin5)**.
This project is a **JavaFX-based** desktop application designed to manage employees. Users can perform operations such as adding, searching, and updating employees.
## 🚀Features  
- **Add Employee:** Kullanıcı, çalışan türünü seçerek yeni çalışan ekleyebilir.  
- **Search Employee:** SSN (Social Security Number) ile çalışan arayabilir.  
- **Update Employee:** SSN ile bulunan çalışanın bilgileri güncellenebilir.  
- **File Operations:** Çalışan verileri, bir **text dosyasında** saklanır ve güncellenir.  

## 🛠️ Technologies Used 
- **Java 11+**  
- **JavaFX**  

## 📁 Project Structure  
![Image](https://github.com/user-attachments/assets/eef52884-df3a-4038-add2-a79ac465e4ee)

This program is based on JavaFx Graphical User Interface(GUI) designed to manage employee data. There is an Employee abstract class with four types of subclasses.
The subclasses of Employee class:

- **Salaried Employee**
- **Hourly Employee**
- **Commission Employee**
- **Base Plus Commission Employee**

### ⚙️ Functional Components

- Employees’ data is stored in an **array**. 
- Employee counter holds the current employee count. 
- Employee type shows which subclass the employee belongs to.
- ![Image](https://github.com/user-attachments/assets/be61d41a-6a08-48c2-9c56-5a3d264f96fe)

### Graphical User Interface(GUI)

- **Layout**
Uses a GridPane for organizing GUI components.
Contains labels, text fields, combo boxes and buttons for input and interaction.
 
