package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class User extends AbstractModel{
	
	
	
	/**
	 * Access a user based off its username
	 * @param theUsername the username associated with the user
	 */
	public static User findUser(String theUsername){
		User user = (User) AbstractModel.getOneByValue("users", "Name", (Object)theUsername);
		return user;
	}
	
	/**
	 * Access a user based off its id
	 * @param id the id associated with the user
	 */
	public static User findUser(int id){
		User user = (User) AbstractModel.getOneByValue("users", "ID", (Object)id);
		return user;
	}
	
	
	/**
	 * Creates a new user
	 * @param username
	 * @param password
	 * @param administrator
	 */
	public User(String username, String password, boolean administrator){
		super(AbstractModel.getConnection(), "users");
		setName(username);
		setPassword(password);
	}
	
	public void setName(String username){
		setValue("Username", username);
	}
	
	public String getName(){
		return (String) getValue("Username");
	}
	
	public void setPassword(String password){
		setValue("Password", generateHash(password));
	}
	
	public byte[] getPasswordHash(){
		return (byte[]) getValue("Password");
	}
	
	public boolean correctPassword(String passwordAttempt){
		return (getPasswordHash() == generateHash(passwordAttempt));
	}
	
	public byte[] generateHash(String password){
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("SHA");
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(password.getBytes());
		return md.digest();
	}
	
	public void commit(){
	//model.save();
	}

}
