package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyManager {
	static Connection conn;
	
	static String CREATE_PERSON_TABLE = "CREATE TABLE Person "
			+ "(FIRSTNAME VARCHAR (50) NOT NULL, "
			+ "LASTNAME VARCHAR (50) NOT NULL, "
			+ "STREET VARCHAR(50) NOT NULL,"
			+ "CITY VARCHAR(50) NOT NULL,"
			+ "STATE VARCHAR(2) NOT NULL,"
			+ "ZIP VARCHAR(10) NOT NULL,"
			+ "GENDER BOOLEAN NOT NULL DEFAULT TRUE,"
			+ "ISCUSTOMER BOOLEAN NOT NULL DEFAULT TRUE"
			+ ")";
	
	public DerbyManager(){
		
		//initialize data base
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	    String dbName = "RetailSystemDB";
	    String connectionURL = "jdbc:derby:" + dbName + ";create=true";

	    
	    try{
		    Class.forName(driver);
	
		    conn = DriverManager.getConnection(connectionURL);
	
		    dropTables();
		    
		    createTables();
	
		    insertTestData();
		    
		    printData();
	

	    } catch (Exception e){
	    	System.out.println("Exception thrown while initializing Derby Manager: "
	    			+ e.getMessage());
	    	e.printStackTrace();
	    	
	    } 
		
	}
	
	private void dropTables() throws Exception {
		dropTable("Customer");
		dropTable("Person");	
	}
	
	public void dropTable( String table ) 
    { 
        try { 
        	//silently drop exceptions on table deletes
            PreparedStatement    ps = conn.prepareStatement 
                ( "DROP TABLE " + table ); 
            ps.execute(); 
            ps.close(); 
        } 
        catch (SQLException e) {} 
    } 
	
	private void createTables() throws Exception {
	    Statement stmt = conn.createStatement();
	    stmt.executeUpdate(CREATE_PERSON_TABLE);
		
	}
	
	public void insertPerson(Person person) throws Exception {
		if(person.personType == PersonType.Customer){
		    PreparedStatement psInsert = conn
			        .prepareStatement("insert into Person values (?,?,?,?,?,?,?,true)");
		
		    psInsert.setString(1, person.firstName);
		    psInsert.setString(2, person.lastName);
		    psInsert.setString(3, person.address);
		    psInsert.setString(4, person.city);
		    psInsert.setString(5, person.state);
		    psInsert.setString(6, person.zip);
		    psInsert.setBoolean(7, person.gender);
	
		    psInsert.executeUpdate();		
		} else {
		    PreparedStatement psInsert = conn
			        .prepareStatement("insert into Person values (?,?,?,?,?,?,?,false)");
		
		    psInsert.setString(1, person.firstName);
		    psInsert.setString(2, person.lastName);
		    psInsert.setString(3, person.address);
		    psInsert.setString(4, person.city);
		    psInsert.setString(5, person.state);
		    psInsert.setString(6, person.zip);
		    psInsert.setBoolean(7, person.gender);
	
		    psInsert.executeUpdate();	
		}
	}
	
	private void insertTestData() throws Exception {
		Person person = new Person();
		person.personType = PersonType.Customer;
		person.firstName = "Brian";
		person.lastName = "Grey";
		person.address = "123 Main St";
		person.city = "Springfield";
		person.state = "GA";
		person.zip = "12345";
		person.gender = true;
		
		insertPerson(person);
		
		person = new Person();
		person.personType = PersonType.Employee;
		person.firstName = "Lynn";
		person.lastName = "Grey";
		person.address = "123 Main St";
		person.city = "Springfield";
		person.state = "VA";
		person.zip = "12345-6789";
		person.gender = false;
		
		insertPerson(person);
	}
	
	private void printData() throws Exception {
	    Statement stmt2 = conn.createStatement();
	    ResultSet rs = stmt2.executeQuery("select * from Person where ISCUSTOMER = true");
	    System.out.println("Customers present in Person Table\n\n");
	    int num = 0;

	    while (rs.next()) {
	      System.out.println(++num + ": First Name: " + rs.getString(1) + "\n   "
	      		+ "Last Name: " + rs.getString(2) + "\n   "
	      		+ "Address: " + rs.getString(3) + "\n   "
	      		+ "City: " + rs.getString(4) + "\n   "
	      		+ "State: " + rs.getString(5) + "\n   "
	      		+ "Zip: " + rs.getString(6) + "\n   "
	      		+ "Gender: " + rs.getString(7) + "\n   "
	    	    + "Is Customer Type: " + rs.getString(8) + "\n"
	      		);
	    }
	    rs.close();	
	    
	    rs = stmt2.executeQuery("select * from Person where ISCUSTOMER = false");
	    System.out.println("Employees present in Person Table\n\n");
	    num = 0;

	    while (rs.next()) {
	      System.out.println(++num + ": First Name: " + rs.getString(1) + "\n   "
	      		+ "Last Name: " + rs.getString(2) + "\n   "
	      		+ "Address: " + rs.getString(3) + "\n   "
	      		+ "City: " + rs.getString(4) + "\n   "
	      		+ "State: " + rs.getString(5) + "\n   "
	      		+ "Zip: " + rs.getString(6) + "\n   "
	      		+ "Gender: " + rs.getString(7) + "\n   "
	    	    + "Is Customer Type: " + rs.getString(8) + "\n"
	      		);
	    }
	    rs.close();	
	}
	
	public static void main(String[] args) throws Exception {
		
		DerbyManager manager = new DerbyManager();
		
		System.exit(0);
	}

}
