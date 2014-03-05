package servlet_test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	
	private AbstractModel model;
	
	/**
	 * Access a user based off its username
	 * @param theUsername the username associated with the user
	 */
	public User(String theUsername){
		model = AbstractModel.getOneByValue("users", "Name", (Object)theUsername);
	}
	
	/**
	 * Access a user based off its id
	 * @param id the id associated with the user
	 */
	public User(int id){
		model = AbstractModel.getOneByValue("users", "ID", (Object)id);
	}
	
	
	/**
	 * Creates a new user
	 * @param username
	 * @param password
	 * @param administrator
	 */
	public User(String username, String password, boolean administrator){
		model = new AbstractModel(AbstractModel.getConnection(), "users");
	}
	
	public void setPassword(String password){
		model.setValue("Password", generateHash(password));
	}
	
	public byte[] getPasswordHash(){
		return (byte[]) model.getValue("Password");
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
