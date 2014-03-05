package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends AbstractModel{

    private static String USERS_DATABASE = "users";
    private static String FRIENDS_DATABASE = "friends";

    /**
     * Access a user based off its username
     * @param theUsername the username associated with the user
     * @return User instance corresponding to username
     */
    public static User findByUsername(String theUsername){
        User user = (User) AbstractModel.getOneByValue(USERS_DATABASE, "name", (Object)theUsername);
        return user;
    }

    /**
     * Create a new User from an AbstractModel.
     * This is so findBy*() methods can return instances of User instead
     * of AbstractModel.
     * @param am the AbstractModel to create the User from
     */
    public User(AbstractModel am){	
    	super(am.getConnection(), USERS_DATABASE, am.getMap(), true);
    }
    
    
    /**
     * Access a user based off its id
     * @param id the id associated with the user
     * @return User instance corresponding to the user, or null if it doesn't exist
     */
    public static User findByID(int id){
        User user = (User) AbstractModel.getOneByValue(USERS_DATABASE, "id", (Object)id);
        return user;
    }


    /**
     * Creates a new user
     * @param username
     * @param password
     * @param administrator
     */
    public User(String username, String password, String fullname, boolean administrator){
        super(AbstractModel.getConnection(), USERS_DATABASE);
        if(!nameInUse(username)){
            setUserName(username);
            setPassword(password);
            setAdministrator(administrator);
            setFullname(fullname);
        }
    }

    /**
     * Sees everyone that the current user is friends with.
     * @return Returns a List<User> which is comprised of everyone the user's budsies.
     */
    public List<User> seeFriends(){
        List<User> friends = new ArrayList<User>();
        List<AbstractModel> modelsOfFriends = getByValue(FRIENDS_DATABASE, "FriendsWith", getUserName(), "=");
        for (int i = 0; i < modelsOfFriends.size(); i++){
            AbstractModel currFriends = modelsOfFriends.get(i);
            User toAdd = findByUsername((String)currFriends.getValue("MyName"));
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
        AbstractModel newFriend = new AbstractModel(AbstractModel.getConnection(), FRIENDS_DATABASE);
        newFriend.setValue("FriendsWith", username);
        newFriend.setValue("MyName", getUserName());
        AbstractModel reverseNewFriend = new AbstractModel(AbstractModel.getConnection(), "Friends");
        reverseNewFriend.setValue("FriendsWith", getUserName());
        reverseNewFriend.setValue("MyName", username);
        //newFriend.save();
        //reverseNewFriend.save;
    }

    /**
     * Sees a list of notes sent to this user.
     * @return returns an array of notes. Each note is a list of strings with two values-the person who sent it, and the message.
     */
    public List<ArrayList<String>> seeNotes(){
        List<ArrayList<String>> notes = new ArrayList<ArrayList<String>>();
        List<AbstractModel> notesSentToMe = getByValue("Notes", "SentTo", getUserName(), "=");
        for (int i = 0; i < notesSentToMe.size(); i++){
            AbstractModel currNote = notesSentToMe.get(i);
            String sentBy = (String) currNote.getValue("SentBy");
            String message = (String) currNote.getValue("Message");
            ArrayList<String> note = new ArrayList<String>();
            note.add(sentBy);
            note.add(message);
            notes.add(note);
        }
        return notes;
    }

    /**
     * Sends a note to someone
     * @param recipient Who the message is sent to
     * @param message What the message is
     */
    public void sendNote(String recipient, String message){
        AbstractModel newNote = new AbstractModel(AbstractModel.getConnection(), "Notes");
        newNote.setValue("SentTo", recipient);
        newNote.setValue("Message", message);
        newNote.setValue("SentBy", getUserName());
    }

    //Needs to be changed to a quiz once quiz functionality is made
    /**
     * Sees a list of challenges sent to this user.
     * @return returns an array of challenges. Each challenge is a list of strings with two values-the person who sent it, and the quiz.
     */
    public List<HashMap<String, String>> seeChallenges(){
        List<HashMap<String, String>> challenges = new ArrayList<HashMap<String, String>>();
        List<AbstractModel> challengesSentToMe = getByValue("Challenges", "SentTo", getUserName(), "=");
        for (int i = 0; i < challengesSentToMe.size(); i++){
            AbstractModel currChallenge = challengesSentToMe.get(i);
            String sentBy = (String) currChallenge.getValue("SentBy");
            String quiz = (String) currChallenge.getValue("Quiz");
            HashMap<String, String> challenge = new HashMap<String, String>();
            challenge.put(sentBy, quiz);
            challenges.add(challenge);
        }
        return challenges;
    }

    /**
     * Sends a challenge to someone
     * @param recipient Who the challenge is sent to
     * @param quiz What the quiz is
     */
    public void sendChallenge(String recipient, String quiz){
        AbstractModel newNote = new AbstractModel(AbstractModel.getConnection(), "Notes");
        newNote.setValue("SentTo", recipient);
        newNote.setValue("Message", quiz);
        newNote.setValue("SentBy", getUserName());
    }


    //Once the quiz class is made, change String quizName into a quiz type.
    /**
     * Returns the quizzes that this user has made.
     * @return A list of strings, where each string represents a quiz this user has made.
     */
    public List<String> seeQuizzesMade(){
        List<String> quizzesMadeByMe = new ArrayList<String>();
        List<AbstractModel> myQuizzes = getByValue("Quizzes", "MadeBy", getUserName(), "=");
        for (int i = 0; i < myQuizzes.size(); i++){
            AbstractModel currQuiz = myQuizzes.get(i);
            String quizName = (String) currQuiz.getValue("Name");
            quizzesMadeByMe.add(quizName);
        }
        return quizzesMadeByMe;
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
        return (findByUsername(username) != null);
    }

    /**
     * Sets the name of the user. If the name is already in use, does nothing.
     * @param username the desired username
     */
    public void setUserName(String username){
        if(nameInUse(username)){
            setValue("Username", username);
        }
    }

    /**
     * Gets the name of the user
     * @return the name of the user
     */
    public String getUserName(){
        return (String) getValue("Username");
    }

    /**
     * Sets the name of the user. If the name is already in use, does nothing.
     * @param username the desired username
     */
    public void setFullname(String fullname){
        setValue("Fullname", fullname);
    }

    /**
     * Gets the name of the user
     * @return the name of the user
     */
    public String getFullname(){
        return (String) getValue("Fullname");
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

    /**
     * Turns the user into a winner of the amateur author award
     * @param won whether the user has won it.
     */
    public void setAmateurAuthor(boolean won){
        setValue("AmateurAuthor", won);
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the Amateur Author award.
     */
    public boolean hasWonAmateurAuthor(){
        return (Boolean) getValue("AmateurAuthor");
    }

    /**
     * Turns the user into a winner of the prolific author award
     * @param won whether the user has won it.
     */
    public void setProlificAuthor(boolean won){
        setValue("ProlificAuthor", won);
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the prolific Author award.
     */
    public boolean hasWonProlificAuthor(){
        return (Boolean) getValue("ProlificAuthor");
    }

    /**
     * Turns the user into a winner of the prodigious author award
     * @param won whether the user has won it.
     */
    public void setProdigiousAuthor(boolean won){
        setValue("AmateurAuthor", won);
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the Prodigious Author award.
     */
    public boolean hasWonProdigiousAuthor(){
        return (Boolean) getValue("ProdigiousAuthor");
    }

    /**
     * Turns the user into a winner of the quiz machine award
     * @param won whether the user has won it.
     */
    public void setQuizMachine(boolean won){
        setValue("QuizMachine", won);
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the QuizMachine award.
     */
    public boolean hasWonQuizMachine(){
        return (Boolean) getValue("QuizMachine");
    }

    /**
     * Turns the user into a winner of the I am the greatest award
     * @param won whether the user has won it.
     */
    public void setIsGreatest(boolean won){
        setValue("IsGreatest", won);
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the I am the greatest award.
     */
    public boolean hasWonIsGreatest(){
        return (Boolean) getValue("IsGreatest");
    }

    /**
     * Turns the user into a winner of the practice makes perfect award
     * @param won whether the user has won it.
     */
    public void setPracticed(boolean won){
        setValue("PracticePerfect", won);
    }

    /**
     * Sees if the user has won the award
     * @return if the user has won the Practice Makes Perfect award.
     */
    public boolean hasWonPracticed(){
        return (Boolean) getValue("PracticePerfect");
    }

    public void commit(){
    //model.save();
    }

}
