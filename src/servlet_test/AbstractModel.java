package servlet_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import servlet_test.MyDBInfo;


/*
 * This class abstracts the accessing of the MySQL database
 * used to store data for the quiz website.  There are two
 * main goals for this class:
 * 
 * 1) Provide static methods to ease querying the database's tables
 * 2) Provide a framework for storing info from the database, so
 *    that a row can be represented as a single object
 */
public class AbstractModel {

	/*
	 * AbstractModel instance variables and methods.
	 * 
	 * An instance of an abstract model is a representation of a row 
	 * in a database table.  All of its values are stored in a hash map, 
	 * where the keys correspond to the column names and these map to 
	 * their values.
	 * 
	 * getValue uses the map to get its value and setValue change the
	 * map.  Neither interact with the database.
	 * 
	 * An AbstractModel can be written to the database by calling the
	 * method "save()".  To remove the row from the database, call the
	 * method "delete()".  Each instance also has a boolean which 
	 * represents whether or not the instance is already in the database
	 * or not.
	 */
	
	private Map<String, Object> valueMap;
	private Connection instance_connection;   // needed for save and delete
	private String instance_tableName;        // needed for save and delete
	private boolean isInDatabase;
	
	
	// Constructors
	
	/**
	 * Full constructor
	 * @param theConnection connection to the database server
	 * @param theTableName name of the table in the database to use
	 * @param theValueMap map from column names to values
	 * @param theIsInDatabase boolean
	 */
	public AbstractModel(Connection theConnection, String theTableName, Map<String, Object> theValueMap, boolean theIsInDatabase) {
		instance_connection = theConnection;
		instance_tableName = theTableName;
		isInDatabase = theIsInDatabase;
		valueMap = theValueMap;
	}
	
	/**
	 * Not in database constructor - defaults isInDatabase to false
	 * @param theConnection connection to the database server
	 * @param theTableName name of the table in the database to use
	 * @param theValueMap map from column names to values
	 */
	public AbstractModel(Connection theConnection, String theTableName, Map<String, Object> theValueMap) {
		this(theConnection, theTableName, theValueMap, false);
	}
	
	/**
	 * Minimum constructor - requires a connection and a table name.
	 * Defaults isInDatabase to false.
	 * Creates a new map for valueMap using a hash map implementation
	 * @param theConnection connection to the database server
	 * @param theTableName name of the table in the database to use
	 */
	public AbstractModel(Connection theConnection, String theTableName) {
		this(theConnection, theTableName, new HashMap<String, Object>(), false);
	}
	
	
	// Getter and Setter
	
	/**
	 * Returns the value associated with the given column name
	 * Returns null of the column name is not in the map
	 * @param colName
	 * @return value
	 */
	public Object getValue(String colName) {
		return valueMap.get(colName);
	}
	
	/**
	 * Sets the column name - value pairing
	 * If this column name already existed in the map, overwrites
	 * it.  Otherwise, adds it to the map
	 * @param colName
	 * @param value
	 */
	public void setValue(String colName, Object value) {
		valueMap.put(colName, value);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * Instance variables and methods
	 */

	private static Statement state;
	private static String tableName;

	// SQL query strings
	private static String QUERY_BEGIN = "SELECT * FROM ";
	private static String TABLE_BEGIN;
	private static String WHERE = " WHERE ";

	/**
	 * Given an id, returns all of the column values for the row
	 * @param id
	 * @return List<String> of column contents
	 */
	public static List<String> getByID(int id) {
        return getByValue("id", Integer.toString(id));
	}

	/**
	 * Returns all of the objects in the table as a list.
	 * Technically, returns a List< List<String> >.
	 * @return list of objects
	 */
	public static List< List<String> > getAll() {
		List< List<String> > allObjects = new ArrayList< List<String> >();
		try {
			ResultSet rs = state.executeQuery(TABLE_BEGIN);

			List<String> object;

			while(rs.next()) {
				java.sql.ResultSetMetaData rsmd = rs.getMetaData();
				int colNum = rsmd.getColumnCount();
				object = new ArrayList<String>();

				for (int i = 1; i <= colNum; i++) {
					object.add(rs.getString(i));
				}

				allObjects.add(object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allObjects;
	}

	/**
	 * Returns an object given a column name index and entry value
	 */
	public static List<String> getByValue(String colName, String value) {
		String query = TABLE_BEGIN + WHERE + colName + " = \"" + value + "\"";
        System.out.println(query);
		try {
			ResultSet rs = state.executeQuery(query);

			List<String> list = new ArrayList<String>();

			if (rs.next()) {
				java.sql.ResultSetMetaData rsmd = rs.getMetaData();
				int colNum = rsmd.getColumnCount();

				for (int i = 1; i <= colNum; i++) {
					list.add(rs.getString(i));
				}

				return list;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	



	/*
	 * Static variables and methods for managing a server connection
	 */

	// Database info
	private static final String username = MyDBInfo.MYSQL_USERNAME;
	private static final String password = MyDBInfo.MYSQL_PASSWORD;
	private static final String dbName = MyDBInfo.MYSQL_DATABASE_NAME;
	private static final String server = MyDBInfo.MYSQL_DATABASE_SERVER;

	// connection
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

	/**
	 * Returns the connection to the MySQL server
	 * If connection is null, initializes it.
	 * @return connection
	 */
	public static Connection getConnection() {
		if (connection == null) initConnection();
		return connection;
	}

	/**
	 * Closes the connection to the MySQL server
	 */
	public static void closeConnection() {
		try {
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
