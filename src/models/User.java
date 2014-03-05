package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		if(!nameInUse(username)){
			setName(username);
			setPassword(password);
			setAdministrator(administrator);
		}
	}
	
	/**
	 * Sees everyone that the current user is friends with.
	 * @return Returns a List<User> which is comprised of everyone the user's budsies.
	 */
	public List<User> seeFriends(){
		List<User> friends = new ArrayList<User>();
		List<AbstractModel> modelsOfFriends = getByValue("Friends", "FriendsWith", getName(), "=");
		for (int i = 0; i < modelsOfFriends.size(); i++){
			AbstractModel currFriends = modelsOfFriends.get(i);
			User toAdd = findUser((String)currFriends.getValue("MyName"));
			friends.add(toAdd);
		}
		return friends;
	}
	
	/**
	 * Makes the current user friends with another user. Works both ways, if user1 becomes friends with user2, user2
	 * automatically becomes friends with user1.
	 * @param username The username of the new friend.
	 */
	public void addFriend(String username){
		AbstractModel newFriend = new AbstractModel(AbstractModel.getConnection(), "Friends");
		newFriend.setValue("FriendsWith", username);
		newFriend.setValue("MyName", getName());
		AbstractModel reverseNewFriend = new AbstractModel(AbstractModel.getConnection(), "Friends");
		reverseNewFriend.setValue("FriendsWith", getName());
		reverseNewFriend.setValue("MyName", username);
		//newFriend.save();
		//reverseNewFriend.save;
	}
	
	/**
	 * Sets administrator privilege
	 * @param isAdministrator whether the user is an administrator or not
	 */
	public void setAdministrator(boolean isAdministrator){
		setValue("Administrator", isAdministrator);
	}
	
	/**
	 * Sees if the current user is an administrator
	 * @return a boolean of whether the user is an administrator
	 */
	public boolean isAdministrator(){
		return (Boolean) getValue("Administrator");
	}
	
	/**
	 * Sees if a name is already associated with a user
	 * @param username the name that will be checked
	 * @return whether the name is in user
	 */
	public boolean nameInUse(String username){
		return (findUser(username) != null);
	}
	
	/**
	 * Sets the name of the user. If the name is already in use, does nothing.
	 * @param username the desired username
	 */
	public void setName(String username){
		if(nameInUse(username)){
			setValue("Username", username);
		}
	}
	
	/**
	 * Gets the name of the user
	 * @return the name of the user
	 */
	public String getName(){
		return (String) getValue("Username");
	}
	
	/**
	 * Turns the password into a hash and stores that hash
	 * @param password the password, in string form.
	 */
	public void setPassword(String password){
		setValue("Password", generateHash(password));
	}
	
	/**
	 * Returns the correct password in hash form
	 * @return the correct password in hash form
	 */
	public byte[] getPasswordHash(){
		return (byte[]) getValue("Password");
	}
	
	/**
	 * returns whether the user inputed the correct password or not
	 * @param passwordAttempt the user's attempt at a password
	 * @return whether the passwords match
	 */
	public boolean correctPassword(String passwordAttempt){
		return (getPasswordHash() == generateHash(passwordAttempt));
	}
	
	
	/**
	 * Encrypts a password
	 * @param password the string that needs to be encrypted
	 * @return the encrypted password, in bytes.
	 */
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
