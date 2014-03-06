package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
    //private Connection connection;   // needed for save and delete
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
		//instance_connection = theConnection;
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

	/**
	 * ResultSet constructor - constructs using a SQL result set
	 * Assumes the result set has already been moved to the correct row
	 * Defaults isInDatabase to true
	 * @param theConnection connection to the database server
	 * @param theTableName name of the table in the database to use
	 * @param theRS result set from which to construct the value map
	 */
	public AbstractModel(Connection theConnection, String theTableName, ResultSet theRS) {
		this(theConnection, theTableName, new HashMap<String, Object>(), true);
		try {
			java.sql.ResultSetMetaData rsmd = theRS.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				this.setValue(rsmd.getColumnName(i), theRS.getObject(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// Getter and Setter

	/**
	 * Returns the value associated with the given column name
	 * Returns null if the column name is not in the map
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

	// save and delete

	/**
	 * Saves this AbstractModel's data in the database.
	 * If it is not already in the table, it is added.
	 * Otherwise, the values are simply updated.  Sets
	 * isInDatabase to be true.
	 */
	public void save() {
		int id = (Integer) valueMap.get("id");
		if(!isInDatabase) insert(id);
		update(id);
	}

	/**
	 * Inserts this AbstractModel's id into the database.
	 * Assumes this model does not already exist in the table.
	 * Only inserts the id field.  Other fields can then be
	 * inserted via an update.
	 */
	private void insert(int id) {
		try {
			state.executeUpdate("INSERT INTO " + instance_tableName + " SET id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Updates this AbstractModel's data in the database.
	 * Uses the model's id to identify the row to update.
	 * Therefore, an AbstractModel's id can not change or
	 * be updated.
	 */
	private void update(int id) {
		Iterator<Map.Entry<String, Object>> it = valueMap.entrySet().iterator();
		Map.Entry<String, Object> entry;
		if (it.hasNext()) {
			entry = it.next();

			String query = ("UPDATE " + instance_tableName + "SET " +
				entry.getKey() + AbstractModel.EQ + entry.getValue());

			while(it.hasNext()) {
				entry = it.next();
				query += (", " + entry.getKey() + AbstractModel.EQ + entry.getValue());
			}

			query += AbstractModel.WHERE + "id = " + valueMap.get("id");

			try{
				state.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deletes this AbstractModel from the database if it
	 * is already in it.  Otherwise, does nothing.  Sets
	 * isInDatabase to be false.
	 */
	public void delete() {

	}



	/*
	 * Static variables and methods for querying the database
	 *
	 * Methods:
	 * getByValue
	 * getOneByValue
	 * getByID
	 * getAll
	 * getWhere
	 */

	private static Statement state;
	private static String tableName;

	// SQL query strings
	private static String QUERY_BEGIN = "SELECT * FROM ";
	private static String WHERE = " WHERE ";

	private static String EQ = " = ";
	private static String GR = " > ";
	private static String LE = " < ";
	private static String GR_EQ = " >= ";
	private static String LE_EQ = " <= ";


	/*
	 * getByID()
	 * String tableName (overloaded to default this to the static variable)
	 * int id
	 */

	/**
	 * getByID - tableName and id needed
	 * Returns null if exception thrown or nothing found
	 * @param table name
	 * @param id
	 * @return the row as an Abstract Model
	 */
	public static AbstractModel getByID(String tableName, int id) {
		return(getOneByValue(tableName, "id", id));
	}

	/**
	 * getByID - table name defaulted
	 * Returns null if exception thrown or nothing found
	 * @param id
	 * @return the row as an Abstract Model
	 */

	/**
	 * getAll
	 * Returns all of the rows in the given table as a list
	 * of Abstract Models.  Exceptions result in returning
	 * null.  Empty tables return empty lists.
	 * @param table name
	 * @return all rows as a list of Abstract Models
	 */
	public static List<AbstractModel> getAll(String theTableName) {
		try {
			ResultSet rs = state.executeQuery(QUERY_BEGIN + theTableName);
			return getFromRS(rs, theTableName);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * getAll - defaulted table name
	 * table name defaulted to the static variable
	 * Returns all of the rows in the given table as a list
	 * of Abstract Models.  Exceptions result in returning
	 * null.  Empty tables return empty lists.
	 * @return all rows as a list of Abstract Models
	 */
	public static List<AbstractModel> getAll() {
		return getAll(tableName);
	}

	/*
	 * getOneByValue/getByValue
	 * All Parameters:
	 * String column name
	 * Object value
	 * String table name
	 * String comparator
	 *
	 * Versions:
	 * all parameters
	 * default table name to static variable
	 * default comparator to equals
	 * default both table name and comparator
	 *
	 * NOTE: because both table name and comparator are Strings,
	 * the two overloaded methods where these are individually
	 * defaulted are only distinguishable by the order of the
	 * parameters.  Ensure you don't mix this up.
	 *
	 * getByValue returns a list of Abstract Models
	 * getOneByValue returns a single Abstract Model
	 */

	/**
	 * getOneByValue - all parameters
	 * Returns the first row returned by the search parameters as an Abstract Model
	 * Returns null if an exception is thrown or the search returns zero results
	 * @param the table name
	 * @param the column name
	 * @param value
	 * @param comparator
	 * @return row as an Abstract Model
	 */
	public static AbstractModel getOneByValue(String theTableName, String colName, Object value, String comparator) {
		ResultSet rs = getResultSet(theTableName, colName, value, comparator);

		try {
			if(rs.next()) {
				return(new AbstractModel(connection, theTableName, rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * getOneByValue - default table name to static variable
	 * Returns the first row returned by the search parameters as an Abstract Model
	 * Returns null if an exception is thrown or the search returns zero results
	 * @param column name
	 * @param value
	 * @param comparator
	 * @return row as an Abstract Model
	 */
	public static AbstractModel getOneByValue(String colName, Object value, String comparator) {
		return getOneByValue(tableName, colName, value, comparator);
	}

	/**
	 * getOneByValue - default comparator to equals
	 * Returns the first row returned by the search parameters as an Abstract Model
	 * Returns null if an exception is thrown or the search returns zero results
	 * @param table name
	 * @param column name
	 * @param value
	 * @return row as an Abstract Model
	 */
	public static AbstractModel getOneByValue(String theTableName, String colName, Object value) {
		return getOneByValue(theTableName, colName, value, EQ);
	}

	/**
	 * getOneByValue - default comparator to equals
	 * Returns the first row returned by the search parameters as an Abstract Model
	 * Returns null if an exception is thrown or the search returns zero results
	 * @param column name
	 * @param value
	 * @return row as an Abstract Model
	 */
	public static AbstractModel getOneByValue(String colName, Object value) {
		return getOneByValue(tableName, colName, value, EQ);
	}

	/**
	 * Returns the rows returned by the search parameters as a list of Abstract Models
	 * Returns null if an exception is thrown
	 * @param the table name
	 * @param the column name
	 * @param value
	 * @param comparator
	 * @return list of rows returned by search as Abstract Models
	 */
	public static List<AbstractModel> getByValue(String theTableName, String colName, Object value, String comparator) {
		ResultSet rs = getResultSet(theTableName, colName, value, comparator);
		return getFromRS(rs, theTableName);
	}

	/**
	 * getByValue - default table name to static variable
	 * Returns the first row returned by the search parameters as an Abstract Model
	 * Returns null if an exception is thrown
	 * @param the column name
	 * @param value
	 * @param comparator
	 * @return list of rows returned by search as Abstract Models
	 */
	public static List<AbstractModel> getByValue(String colName, Object value, String comparator) {
		return getByValue(tableName, colName, value, comparator);
	}

	/**
	 * getByValue - default comparator to equals
	 * Returns the first row returned by the search parameters as an Abstract Model
	 * Returns null if an exception is thrown
	 * @param the table name
	 * @param the column name
	 * @param value
	 * @return list of rows returned by search as Abstract Models
	 */
	public static List<AbstractModel> getByValue(String theTableName, String colName, Object value) {
		return getByValue(theTableName, colName, value, EQ);
	}

	/**
	 * getByValue - default comparator to equals
	 * Returns the first row returned by the search parameters as an Abstract Model
	 * Returns null if an exception is thrown
	 * @param the column name
	 * @param value
	 * @return list of rows returned by search as Abstract Models
	 */
	public static List<AbstractModel> getByValue(String colName, Object value) {
		return getByValue(tableName, colName, value, EQ);
	}

	/**
	 * Allows a client to access the map
	 * @return the map associated with an abstract model.
	 */
	public Map<String, Object> getMap(){
		return valueMap;
    }

    /**
	 * Returns a list of AbstractModels given a Result Set
	 * and the table name
	 * @param a result set
	 * @param the table name
	 * @return list of rows as Abstract Models
	 */
	private static List<AbstractModel> getFromRS(ResultSet rs, String theTableName) {
		try {
			List<AbstractModel> list = new ArrayList<AbstractModel>();
			while(rs.next()) {
				list.add(new AbstractModel(connection, theTableName, rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Given search parameters, returns a Result Set using a prepared statement
	 * Returns null if an exception is thrown
	 * @param the table name
	 * @param the column name
	 * @param value
	 * @param comparator
	 * @return ResultSet
	 */
	protected static ResultSet getResultSet(String theTableName, String colName, Object value, String comparator) {
		String prepared = QUERY_BEGIN + theTableName + WHERE + colName + comparator + "?";
		try {
			java.sql.PreparedStatement prepState = connection.prepareStatement(prepared);
			prepState.setObject(1, value);
			return(prepState.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Returns the list of objects returned by executing the given
	 * query on the table in the database.  The string passed in
	 * is appended to "SELECT * FROM ", so that part is not needed.
	 * Note:
	 * The parameter is "theTableName", not to be confused with the
	 * static variable "tableName".
	 * IMPORTANT:
	 * Prepared statements are not used so do not make this method
	 * available to website users in any way.
	 */
	public static List<AbstractModel> getWhere(String sqlQuery, String theTableName) {
		String query = QUERY_BEGIN + theTableName + WHERE + sqlQuery;
		List<AbstractModel> list = new ArrayList<AbstractModel>();

		try {
			ResultSet rs = state.executeQuery(query);


			while (rs.next()) {
				list.add(new AbstractModel(connection, theTableName, rs));
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	/*
	 * Static variables and methods for managing a server connection
	 */

	// Database info
	private static final String username = "ccs108jvangogh";
	private static final String password = "ahweeyoi";
	private static final String dbName = "c_cs108_jvangogh";
	private static final String server = "mysql-user.stanford.edu";

	// connection
	private static Connection connection = null;


	/**
	 * Initializes the connection to the MySQL server.
	 */
	private static void initConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://" + server, username, password);

			state = connection.createStatement();
			state.executeQuery("USE " + dbName);

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
