package servlet_test;

import java.sql.Connection;

/*
 * This class is used to to initial testing on the AbstractModel class.
 * It makes use of the Metropolis table used in the previous homework.
 * It simulates accessing the database using the AbstractModel class in
 * the same way a User or Question class might.
 */
public class Metropolis extends AbstractModel {
	
	// Constants
	private static final String tableName = "Metropolis";
	private static final String[] colNames = {"Metropolis", "Continent", "Population"};

	
	/*
	 * Instance variables and methods
	 */
	
	private String name;
	private String continent;
	private long population;
	
	
	// ---------Constructors---------
	
	// Default constructor
	public Metropolis(String theName, String theContinent, long thePopulation, Connection theConnection) {
		super(tableName, colNames, theConnection);
		name = theName;
		continent = theContinent;
		population = thePopulation;
	}
	
	// Constructor where population is represented as a String
	public Metropolis(String theName, String theContinent, String thePopulation, Connection theConnection) {
		this(theName, theContinent, Long.parseLong(thePopulation), theConnection);
	}
	
	// Override of toString
	@Override
	public String toString() {
		return (name + ", " + continent + ", " + population);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
