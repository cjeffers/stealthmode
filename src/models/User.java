package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class User extends AbstractModel{

    private static String USERS_DATABASE = "users";
    private static String FRIENDS_DATABASE = "friends";
    private static String ACHIEVEMENTS_DATABASE = "achievements";
    private AbstractModel achievements;

    /**
     * Access a user based off its username
     * @param theUsername the username associated with the user
     * @return User instance corresponding to username
     */
    public static User findByUsername(String theUsername){
        AbstractModel am = AbstractModel.getOneByValue(USERS_DATABASE, "username", (Object)theUsername, "=");
        if (am == null){
        	return null;
        }
        User user = new User(am);
        return user;
    }

    /**
     * Create a new User from an AbstractModel.
     * This is so findBy*() methods can return instances of User instead
     * of AbstractModel.
     * @param am the AbstractModel to create the User from
     */

    public User(AbstractModel am){	
    	super(USERS_DATABASE, am.getMap(), true);
    	achievements = AbstractModel.getOneByValue(ACHIEVEMENTS_DATABASE, "user_id", am.getValue("user_id"));
    }


    /**
     * Access a user based off its id
     * @param id the id associated with the user
     * @return User instance corresponding to the user, or null if it doesn't exist
     */
    public static User findByID(int id){
        AbstractModel am = AbstractModel.getOneByValue(USERS_DATABASE, "id", (Object)id, "=");
        if (am == null){
        	return null;
        }
        User user = new User(am);
        return user;
    }


    /**
     * Creates a new user
     * @param username
     * @param password
     * @param administrator
     */
    public User(String username, String password, String fullname, boolean administrator){
        super(USERS_DATABASE);
        	setFullname(fullname);
            setUserName(username);
            setPicURL("");
            achievements =  new AbstractModel(ACHIEVEMENTS_DATABASE);
            achievements.setValue("user_id", getID());
            setAdminPriveledge(administrator);
            setIsGreatest(false);
        	setPracticed(false);
        	setQuizMachine(false);
        	setProdigiousAuthor(false);
        	setAmateurAuthor(false);
        	setProlificAuthor(false);
            setPassword(password);
            save();
    }
    
    /**
     * Creates a new user
     * @param username
     * @param password
     * @param administrator
     */
    public User(String username, String password, String fullname, boolean administrator, Map<String, Object> theMap, boolean exists){
        super(USERS_DATABASE, theMap,  false);
        if(!nameInUse(username)){
            setUserName(username);
            setPassword(password);
            setAdminPriveledge(administrator);
            achievements =  new AbstractModel(ACHIEVEMENTS_DATABASE);
            achievements.setValue("user_id", getID());
            setFullname(fullname);
            setIsGreatest(false);
        	setPracticed(false);
        	setQuizMachine(false);
        	setProdigiousAuthor(false);
        	setAmateurAuthor(false);
        	setProlificAuthor(false);
            save();
        }
    }

    /**
     * Sees everyone that the current user is friends with.
     * @return Returns a List<User> which is comprised of everyone the user's budsies.
     */
    public List<User> getFriends(){
        List<User> friends = new ArrayList<User>();
        List<AbstractModel> modelsOfFriends = getByValue(FRIENDS_DATABASE, "friends_with", getUserName(), "=");
        for (int i = 0; i < modelsOfFriends.size(); i++){
            AbstractModel currFriend = modelsOfFriends.get(i);
            User toAdd = findByUsername((String)currFriend.getValue("my_name"));
            if(toAdd != null){
            	friends.add(toAdd);
            }
        }
        return friends;
    }


    /**
     * Makes the current user friends with another user. Works both ways, if user1 becomes friends with user2, user2
     * automatically becomes friends with user1. Need to check that they aren't already friends before doing this.
     * @param username The username of the new friend.
     */
    public void addFriend(String username){
        AbstractModel newFriend = new AbstractModel(FRIENDS_DATABASE);
        newFriend.setValue("friends_with", username);
        newFriend.setValue("my_name", getUserName());
        AbstractModel reverseNewFriend = new AbstractModel(FRIENDS_DATABASE);
        reverseNewFriend.setValue("friends_with", getUserName());
        reverseNewFriend.setValue("my_name", username);
        newFriend.save();
       reverseNewFriend.save();
    }

    public void removeFriend(String username){
    	List<AbstractModel> oldFriends = AbstractModel.getWhere("friends_with=\""+username+"\" AND my_name=\""+getUserName()+"\"", FRIENDS_DATABASE);
    	for (int i = 0; i < oldFriends.size(); i++){
    		oldFriends.get(i).delete();
    	}
    	List<AbstractModel> reverseFriends = AbstractModel.getWhere("friends_with=\""+getUserName()+"\" AND my_name=\""+username+"\"", FRIENDS_DATABASE);
    	for (int i = 0; i < reverseFriends.size(); i++){
    		reverseFriends.get(i).delete();
    	}
    }
    
    /**
     * Sees a list of messages sent to this user.
     * @return returns an array of messages. Each note is a message object
     */
    public List<Message> seeMessages(){
        List<Message> notes = Message.findMessagesReceivedByUser(getUserName());
        return notes;
    }

    /**
     * Sends a message to someone
     * @param recipient Who the message is sent to
     * @param message What the message is
     */
    public void sendNote(String recipient, String message){
        Message newNote = new Message(getUserName(), recipient, message, 'm');
    }


    /**
     * Sees a list of challenges sent to this user.
     * @return returns an array of challenges. Each challenge is a message object
     */
    public List<Message> seeChallenges(){
        List<Message> notes = Message.findChallengesReceivedByUser(getUserName());
        return notes;
    }

    /**
     * Sends a message to someone
     * @param recipient Who the message is sent to
     * @param message What the message is
     */
    public void sendChallenge(String recipient, String message, int id){
        Message newNote = new Message(getUserName(), recipient, message, 'c', id);
    }


    //Once the quiz class is made, change String quizName into a quiz type.

    /**
     * Sees a list of friend requests sent to this user.
     * @return returns an array of friend requests. Each friend request is a message object
     */
    public List<Message> seeRequests(){
        List<Message> notes = Message.findRequestsReceivedByUser(getUserName());
        return notes;
    }

    /**
     * Sends a message to someone
     * @param recipient Who the message is sent to
     * @param message What the message is
     */
    public void sendRequest(String recipient, String message){
        Message newNote = new Message(getUserName(), recipient, message, 'r');
    }


    /**
     * Sets administrator privilege
     * @param isAdministrator whether the user is an administrator or not
     */
    public void setAdminPriveledge(boolean isAdmin){
    	if (isAdmin){
    		setValue("administrator", 1);
    	} else{
    		setValue("administrator", 0);
    	}
        save();
    }

    /**
     * Sees if the current user is an administrator
     * @return a boolean of whether the user is an administrator
     */
    public boolean isAdministrator(){
    	int admin = (Integer) getValue("administrator");
        return admin == 1;
    }

    /**
     * Sees if a name is already associated with a user
     * @param username the name that will be checked
     * @return whether the name is in user
     */
    public static boolean nameInUse(String username){
        return (findByUsername(username) != null);
    }

    /**
     * Sets the name of the user. If the name is already in use, does nothing.
     * @param username the desired username
     */
    public void setUserName(String username){
      // if(!nameInUse(username)){
            setValue("username", username);
            save();
       // }
    }

    /**
     * Gets the name of the user
     * @return the name of the user
     */
    public String getUserName(){
        return (String) getValue("username");
    }

    /**
     * Sets the name of the user. If the name is already in use, does nothing.
     * @param username the desired username
     */
    public void setFullname(String fullname){
        setValue("fullname", fullname);
        save();
    }

    /**
     * Gets the name of the user
     * @return the name of the user
     */
    public String getFullname(){
        return (String) getValue("fullname");
    }


    /**
     * Find all users by the given full name.
     * @param fullname the full name to search for
     * @return a list of Users with the given full name, which is empty
     *         if none exist
     */
    public static List<User> findByName(String fullname){
    	List<User> result = new ArrayList<User>();
    	List<AbstractModel> named = AbstractModel.getByValue("users", "fullname", (Object) fullname);
    	for (AbstractModel n : named) {
    		result.add(new User(n));
    	}
    	return result;
    }


    /**
     * Turns the password into a hash and stores that hash
     * @param password the password, in string form.
     */
    public void setPassword(String password){
    	String Salt = makeSalt();
    	setValue("salt", Salt);
        setValue("password", hexToString(generateHash(Salt + password)));
        save();
    }
    
    public static boolean validateLogin(String user, String password){
    	User tryUser = findByUsername(user);
    	if(tryUser == null) return false;
    	if(tryUser.correctPassword(password)){
    		return true;
    	}
    	return false;
    }

    private static int SALT_LENGTH = 8;
    private static String SALT_VALUES = "abcdefghijklmnopqrstuvwxyz1234567890";
    
    private String makeSalt(){
        char[] text = new char[SALT_LENGTH];
        Random rng = new Random();
        for (int i = 0; i < SALT_LENGTH; i++)
        {
            text[i] = SALT_VALUES.charAt(rng.nextInt(SALT_VALUES.length()));
        }
        return new String(text);
        
    }
    
    public int getID(){
    	return (Integer) getValue("id");
    }

    /**
     * Set the URL for the user's profile picture.
     * @param url the url for the picture
     */
    public void setPicURL(String url){
    	setValue("pic_url", url);
    }


    /**
     * Returns the correct password in hash form
     * @return the correct password in hash form
     */
    public String getPasswordHash(){
        return (String) getValue("password");
    }
    

    /**
     * returns whether the user inputed the correct password or not
     * @param passwordAttempt the user's attempt at a password
     * @return whether the passwords match
     */
    public boolean correctPassword(String passwordAttempt){
    	String Salt = (String) getValue("salt");
        return (getPasswordHash().equals(hexToString(generateHash((String) getValue("salt") + passwordAttempt))));
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
    
    
    private static String hexToString(byte[] bytes) {
    	StringBuffer buff = new StringBuffer();
    	for (int i=0; i<bytes.length; i++) {
    		int val = bytes[i];
    		val = val & 0xff;  // remove higher bits, sign
    		if (val<16) buff.append('0'); // leading 0
    		buff.append(Integer.toString(val, 16));
    	}
    	return buff.toString();
    }
    
    
    public static int getAmountOfUsers(){
    	return findAll().size();
    }
    
    /**
     * Get the URL for the user's profile picture.
     * @return the URL as a String
     */
    public String getPicURL(){
    	return (String) getValue("pic_url");
    }
    

    public static void deleteUser(String username){
    	User toDelete = User.findByUsername(username);
    	toDelete.delete();
    }
    
    public static void deleteUser(int ID){
    	User toDelete = User.findByID(ID);
    	toDelete.delete();
    }

    /**
     * Turns the user into a winner of the amateur author award
     * @param won whether the user has won it.
     */
    public void setAmateurAuthor(boolean won){
    	if (won){
    		achievements.setValue("amateur_author", 1);
    		achievements.setValue("amateur_author_time", System.currentTimeMillis());
    	} else{
    		achievements.setValue("amateur_author", 0);
    	}
        achievements.save();
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the Amateur Author award.
     */
    public boolean hasWonAmateurAuthor(){
    	if((Integer)achievements.getValue("amateur_author") == 1) return true;
    	else return false;
    }

    /**
     * Turns the user into a winner of the prolific author award
     * @param won whether the user has won it.
     */
    public void setProlificAuthor(boolean won){
    	if (won){
    		achievements.setValue("prolific_author", 1);
    		achievements.setValue("prolific_author_time", System.currentTimeMillis());
    	} else{
    		achievements.setValue("prolific_author", 0);
    	}
        achievements.save();
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the prolific Author award.
     */
    public boolean hasWonProlificAuthor(){
    	if((Integer)achievements.getValue("prolific_author") == 1) return true;
    	else return false;
    }

    /**
     * Turns the user into a winner of the prodigious author award
     * @param won whether the user has won it.
     */
    public void setProdigiousAuthor(boolean won){
    	if (won){
    		achievements.setValue("prodigious_author", 1);
    		achievements.setValue("prodigious_author_time", System.currentTimeMillis());
    	} else{
    		achievements.setValue("prodigious_author", 0);
    	}
        achievements.save();
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the Prodigious Author award.
     */
    public boolean hasWonProdigiousAuthor(){
    	if((Integer)achievements.getValue("prodigious_author") == 1) return true;
    	else return false;
    }

    /**
     * Turns the user into a winner of the quiz machine award
     * @param won whether the user has won it.
     */
    public void setQuizMachine(boolean won){
    	if (won){
    		achievements.setValue("quiz_machine", 1);
    		achievements.setValue("quiz_machine_time", System.currentTimeMillis());
    	} else{
    		achievements.setValue("quiz_machine", 0);
    	}
        achievements.save();
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the QuizMachine award.
     */
    public boolean hasWonQuizMachine(){
    	if((Integer)achievements.getValue("quiz_machine") == 1) return true;
    	else return false;
    }

    /**
     * Turns the user into a winner of the I am the greatest award
     * @param won whether the user has won it.
     */
    public void setIsGreatest(boolean won){
    	if (won){
    		achievements.setValue("is_greatest", 1);
    		achievements.setValue("is_greatest_time", System.currentTimeMillis());
    	} else{
    		achievements.setValue("is_greatest", 0);
    	}
        achievements.save();
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the I am the greatest award.
     */
    public boolean hasWonIsGreatest(){
    	if((Integer)achievements.getValue("is_greatest") == 1) return true;
    	else return false;
    }

    /**
     * Turns the user into a winner of the practice makes perfect award
     * @param won whether the user has won it.
     */
    public void setPracticed(boolean won){
    	if (won){
    		achievements.setValue("practice_perfect", 1);
    		achievements.setValue("practice_perfect_time", System.currentTimeMillis());
    	} else{
    		achievements.setValue("practice_perfect", 0);
    	}
        achievements.save();
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the Practice Makes Perfect award.
     */
    public boolean hasWonPracticed(){
    	if((Integer)achievements.getValue("practice_perfect") == 1) return true;
    	else return false;
    }
    
    public List<String> seeAwardsWon(){
    	List<String> result = new ArrayList<String>();
    	if (hasWonIsGreatest()) result.add("I Am The Greatest");
    	if (hasWonPracticed()) result.add("Practice Makes Perfect");
    	if (hasWonQuizMachine()) result.add("Quiz Machine");
    	if (hasWonProdigiousAuthor()) result.add("Prodigious Author");
    	if (hasWonAmateurAuthor()) result.add("Amateur Author");
    	if (hasWonProlificAuthor()) result.add("Prolific Author");
    	return result;
    }

  
    
    /**
     * Find all users who are administrators.
     */
    public static List<User> findAdministrators() {
    	List<User> result = new ArrayList<User>();
    	List<AbstractModel> ams = AbstractModel.getByValue("users", "administrator", 1);
    	for (AbstractModel am : ams) {
    		result.add(new User(am));
    	}
    	return result;
    }
    
    
    /**
     * Get a list of all users.
     * @return a list containing all users in the database
     */
    public static List<User> findAll(){
    	List<User> result = new ArrayList<User>();
        List<AbstractModel> all = AbstractModel.getAll(USERS_DATABASE);
        for (AbstractModel am : all) {
            result.add(new User(am));
        }
    	return result;
    }

    static final Comparator<Result> TIME_SORT = 
            new Comparator<Result>() {
   	 		public int compare(Result e1, Result e2) {
   	 		return (int) (e2.getTakenAt() - e1.getTakenAt());
   	 		}
   };
   	
   	/**
   	 * Returns all results of a quiz, sorted by time taken last (latest results first in list).
   	 * @return a sorted list of results
   	 */
   	
   	public List<Result> getRecentResults(){
   		List<Result> result = Result.findByUser(getID());
   		Collections.sort(result, TIME_SORT);
   		return result;
   	}
   	
   	
    static final Comparator<Result> SCORE_SORT = 
            new Comparator<Result>() {
   	 		public int compare(Result e1, Result e2) {
   	 		return e2.getScore() - e1.getScore();
   	 		}
   };
   	
   	/**
   	 * Returns all results of a quiz, sorted by score (highest scores first).
   	 * @return a sorted list of results
   	 */
   	
   	public List<Result> getScoresForQuiz(int quizID){
   		List<Result> result = Result.findByQuizAndUser(quizID, getID());
   		Collections.sort(result, SCORE_SORT);
   		return result;
   	}
   	
    static final Comparator<Quiz> CREATION_TIME_SORT =
            new Comparator<Quiz>() {
   	 		public int compare(Quiz e1, Quiz e2) {
   	 		return (int) (e2.getDateMade() - e1.getDateMade());
   	 		}
   };
   	
   
   /**
    * Shows all the quizzes a user has made, in order sorted by creation time
    * @return the list of quizzes made
    */
   	public List<Quiz> recentlyCreatedQuizzes(){
   		List<Quiz> quizzes = Quiz.findByCreator(getID());
   		Collections.sort(quizzes, CREATION_TIME_SORT);
   		return quizzes;
   	}
   	

}
