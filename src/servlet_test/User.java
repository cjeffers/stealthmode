package servlet_test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	
	private AbstractModel model;
	
	public User(String theUsername, byte[] password, boolean administrator){
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
