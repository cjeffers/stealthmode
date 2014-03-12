package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message extends AbstractModel{

	private static String MESSAGES_DATABASE = "messages";
	private static String USERS_DATABASE = "users";

	private char type;


	/**
	 * Create a new Message from an AbstractModel.
	 * @param am the AbstractModel to create the Message from
	 */
	public Message(AbstractModel am){
		super(MESSAGES_DATABASE, am.getMap(), true);
	}


	/**
	 * Access a message based off its id
	 * @param id the id associated with the user
	 * @return Message instance corresponding to the message, or null if it doesn't exist
	 */
	public static Message findByID(int id){
		Message message = new Message(AbstractModel.getOneByValue(MESSAGES_DATABASE, "id", (Object)id));
		return message;
	}

	/**
	 * Access all messages sent by a user
	 * @param string that is the username of the sender
	 * @return List of message instances corresponding to the message, or null if it doesn't exist
	 */
	public static List<Message> findMessagesSentByUser(String user){
		List<Message> returnL = new ArrayList<Message>();
		List<AbstractModel> result = AbstractModel.getWhere("sender='" + user + "' AND typem='m';", MESSAGES_DATABASE);
		for (AbstractModel am : result) {
			returnL.add(new Message(am));
		}
		return returnL;
	}

	/**
	 * Access all messages recieved by a user
	 * @param string that is the username of the sender
	 * @return List of message instances corresponding to the message, or null if it doesn't exist
	 */
	public static List<Message> findMessagesReceivedByUser(String user){
		List<Message> returnL = new ArrayList<Message>();
		List<AbstractModel> result = AbstractModel.getWhere("receiver='" + user + "' AND typem='m';", MESSAGES_DATABASE);
		for (AbstractModel am : result) {
			returnL.add(new Message(am));
		}
		return returnL;
	}

	/**
	 * Access all messages sent by a user
	 * @param string that is the username of the sender
	 * @return List of message instances corresponding to the message, or null if it doesn't exist
	 */
	public static List<Message> findRequestsSentByUser(String user){
		List<Message> returnL = new ArrayList<Message>();
		List<AbstractModel> result = AbstractModel.getWhere("sender='" + user + "' AND typem='f';", MESSAGES_DATABASE);
		for (AbstractModel am : result) {
			returnL.add(new Message(am));
		}
		return returnL;
	}

	/**
	 * Access all messages recieved by a user
	 * @param string that is the username of the sender
	 * @return List of message instances corresponding to the message, or null if it doesn't exist
	 */
	public static List<Message> findRequestsRecievedByUser(String user){
		List<Message> returnL = new ArrayList<Message>();
		List<AbstractModel> result = AbstractModel.getWhere("receiver='" + user + "' AND typem='f';", MESSAGES_DATABASE);
		for (AbstractModel am : result) {
			returnL.add(new Message(am));
		}
		return returnL;
	}

	/**
	 * Access all messages sent by a user
	 * @param string that is the username of the sender
	 * @return List of message instances corresponding to the message, or null if it doesn't exist
	 */
	public static List<Message> findChallengesSentByUser(String user){
		List<Message> returnL = new ArrayList<Message>();
		List<AbstractModel> result = AbstractModel.getWhere("sender='" + user + "' AND typem='c';", MESSAGES_DATABASE);
		for (AbstractModel am : result) {
			returnL.add(new Message(am));
		}
		return returnL;
	}

	/**
	 * Access all messages recieved by a user
	 * @param string that is the username of the sender
	 * @return List of message instances corresponding to the message, or null if it doesn't exist
	 */
	public static List<Message> findChallengesRecievedByUser(String user){
		List<Message> returnL = new ArrayList<Message>();
		List<AbstractModel> result = AbstractModel.getWhere("receiver='" + user + "' AND typem='c';", MESSAGES_DATABASE);
		for (AbstractModel am : result) {
			returnL.add(new Message(am));
		}
		return returnL;
	}


	/**
	 * Creates a new user (used typically for challenge)
	 * @param string sender
	 * @param string receiver
	 * @param string administrator
	 * @param char type (m=message, f=friend request, c=challenge)
	 */


	public Message(String sender, String receiver, String text, char type, int id){
		super(MESSAGES_DATABASE);
		if(type=='c') setQuiz(id);
		setType(type);
		setSender(sender);           
		setReceiver(receiver);
		setMessageText(text);
		save();

	}

	/**
	 * Creates a new user
	 * @param string sender
	 * @param string receiver
	 * @param string administrator
	 * @param char type (m=message, f=friend request, c=challenge)
	 */


	public Message(String sender, String receiver, String text, char type){
		super(MESSAGES_DATABASE);
		setSender(sender);    
		setType(type);
		setReceiver(receiver);
		setMessageText(text);
		save();

	}



	/**
	 * Sets the sender of the message.
	 * @param username the desired sender
	 */
	public void setSender(String username){
		setValue("sender", username);
		//save();
	}

	/**
	 * Sets the reciever of the message. 
	 * @param username the desired reciever
	 */
	public void setReceiver(String username){
		setValue("receiver", username);
		//save();
	}

	/**
	 * Sets the message text of a message.
	 * @param text the desired message text
	 */
	public void setMessageText(String text){
		setValue("text", text);
		//save();
	}
	
	/**
	 * Sets the type of a message.
	 * @param char the desired message type
	 */
	public void setType(char type){
		setValue("typem", type);
		//save();
	}
	
	/**
	 * Sets the quiz of a challenge message.
	 * @param char the desired message type
	 */
	public void setQuiz(int id){
		setValue("quiz", id);
		//save();
	}
	
	 /**
     * Gets the name of the sender
     * @return the name of the user
     */
    public String getSender(){
        return (String) getValue("sender");
    }
    
    /**
     * Gets the name of the receiver
     * @return the name of the receiver
     */
    
    public String getReceiver(){
        return (String) getValue("receiver");
    }
    
    /*
     * Gets all Messages
     *
     */
    
    public static List<Message> getAllMessages(){
    	List<Message> returnL = new ArrayList<Message>();
		List<AbstractModel> result = AbstractModel.getAll(MESSAGES_DATABASE);
		for (AbstractModel am : result) {
			returnL.add(new Message(am));
		}
		return returnL;
    }
    
    /**
     * Gets the name of the receiver
     * @return the name of the receiver
     */
    
    public String getText(){
        return (String) getValue("text");
    }
    




	private static String QUERY_BEGIN = "SELECT * FROM ";
	private static String WHERE = " WHERE ";
	private static Statement state;
}


