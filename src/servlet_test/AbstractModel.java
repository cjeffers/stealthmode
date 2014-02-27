package servlet_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import servlet_test.MyDBInfo;


/*
 * This class abstracts the accessing of the MySQL database
 * used to store data for the quiz website
 */


public class AbstractModel {
	
	// Database info
	private static final String username = MyDBInfo.MYSQL_USERNAME;
	private static final String password = MyDBInfo.MYSQL_PASSWORD;
	private static final String dbName = MyDBInfo.MYSQL_DATABASE_NAME;
	private static final String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	
	
	private static Connection connection = null;
	
	
	/**
	 * Initializes the connection to the MySQL server.
	 */
	private static void initConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://" + server, username, password);
			
			Statement statement = connection.createStatement();
			statement.executeQuery("USE " + dbName);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if (connection == null) initConnection();
		return connection;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*      Jacob's Database class created for HW 5.  Used for reference.
	
	// column names
	private static String[] colNames = {"productid", "name", "imagefile", "price"};
	
	// name of the table in the SQL database
	private static String tableName = "products";
	
	// SQL login info
	private static String password = MyDBInfo.MYSQL_PASSWORD;
	private static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	private static String dbName = MyDBInfo.MYSQL_DATABASE_NAME;
	private static String username = MyDBInfo.MYSQL_USERNAME;
	
	// SQL query strings
	private static String QUERY_BEGIN = ("SELECT * FROM " + tableName);
	private static String WHERE = " WHERE ";

	// instance variables
	private Connection connection;
	private Statement statement;
	private boolean open;         // true if the connection is open
	
	
	
	 * Sets up the connection to the SQL database and
	 * creates a statement that is used throughout
	 * the class to execute queries and updates
	
	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://" + server, username, password);
			open = true;
			
			statement = connection.createStatement();
			statement.executeQuery("USE " + dbName);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a list of all of the products in the database
	 * @return product list
	 
	public List<Product> getAll() {
		if (!open) throw new RuntimeException("connection is closed");
		
		String query = QUERY_BEGIN;
		List<Product> list = new ArrayList<Product>();
		Product product;

		try {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				product = makeProduct(rs);
				list.add(product);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns a product based on the given product id
	 * @param product id
	 * @return product
	 
	public Product getOne(String id) {
		if (!open) throw new RuntimeException("connection is closed");
		
		String query = QUERY_BEGIN + WHERE + colNames[0] + " = \"" + id + "\"";
		try {
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				return makeProduct(rs);
			}
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Makes a product from the result set
	 * @param result set
	 * @return product
	
	private Product makeProduct(ResultSet rs) {
		try{
			Product product = new Product(rs.getString(1), rs.getString(2), 
					rs.getString(3), Double.parseDouble(rs.getString(4)));
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * closes the connection to the SQL database
	 
	public void closeConnection() {
		try {
			connection.close();
			open = false;
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * returns a list of the column names
	 * @return list
	 
	public static List<String> getColumnNames() {
		return Arrays.asList(colNames);
	}
	*/
}
