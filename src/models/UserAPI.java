package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.*;

public class User extends AbstractModel{

    public static final String USER_TABLE = "users";

    // constructors

    /**
     * Create a new User instance that is not in the database.
     * Sets all the values of the new user to the values passed in. Call
     * save() on the new instance to write it to the database.
     * @param username the unique username
     * @param password user's password
     * @param fullname user's full name, not necessarily unique
     * @param administrator is this user an administrator?
     */
    public User(String username, String password, String fullname, boolean administrator) //check

    /**
     * Create a new User from an AbstractModel.
     * This is so findBy*() methods can return instances of User instead
     * of AbstractModel.
     * @param am the AbstractModel to create the User from
     */
    public User(AbstractModel am) //check


    // set/get/find by attributes

    /**
     * Find a user by the given id.
     * @param id the id to search for
     * @return the instance of User with the given id, or null if it
     *         doesn't exist
     */
    @Override
    public static User findByID(int id) //check

    // username

    /**
     * Get user's username.
     * @return the username
     */
    public String getUserName() //check

    /**
     * Set the user's username.
     * @param username the new username
     */
    public void setUserName(String username) //check

    /**
     * Find a user by the given username.
     * @param username the username to search for
     * @return the instance of User with the given username, or null if
     *         it doesn't exist
     */
    public static User findByUserName(String username) //check


    // actual name

    /**
     * Get user's full name.
     * @return the full name
     */
    public String getFullName() //check

    /**
     * Set the user's full name.
     * @param full name the new full name
     */
    public void setFullName(String name) //check

    /**
     * Find all users by the given full name.
     * @param fullname the full name to search for
     * @return a list of Users with the given full name, which is empty
     *         if none exist
     */
    public static List<User> findByName(String fullname)


    // password

    /**
     * Set the user's password.
     * Appends a random salt to the password, hashes it, and stores the
     * hash.
     * @param password the new password
     */
    public void setPassword(String password)

    /**
     * Check to see if the guess matches the password.
     * @param guess the string to check against the stored hash.
     * @return true if the guess matches, false otherwise.
     */
    public boolean isPasswordCorrect(String guess)


    // prof pic url
    /**
     * Set the URL for the user's profile picture.
     * @param url the url for the picture
     */
    public void setPicURL(String url)

    /**
     * Get the URL for the user's profile picture.
     * @return the URL as a String
     */
    public String getPicURL()

    // administrator
    /**
     * Set whether or not the user is an administrator.
     * @param isAdmin true if setting the user to admin, false if not.
     */
    public void setAdminPrivilege(boolean isAdmin)

    /**
     * Returns whether or not the user is an administrator.
     * @return true if the user is an administrator, false if not.
     */
    public boolean isAdministrator()

    /**
     * Returns a list of all administrators.
     * @return a list of all Users who are administrators.
     */
    public static List<User> findAdministrators()

    // add/get linked things
    // friends
    /**
     * Get a list of all friends.
     * @return a list of all users who are friends with this user.
     */
    public List<User> getFriends()

    /**
     * Make this user friends with someone else.
     * @param friendID the id of the user to make friends with.
     */
    public void addFriend(int friendID)

    // quizzes created
    /**
     * Get a list of all quizzes created by this user.
     * @return A List of quizzes that the user created.
     */
    public List<Quiz> getQuizzesCreated()

    /**
     * Add a quiz to the list of quizzes the user created.
     * @param quizID the id of the quiz to add
     */
    public void addQuizCreated(quizID)

    // quizzes taken
    /**
     * Get a list of all quizzes taken by this user.
     * @return A List of quizzes that the user has taken.
     */
    public List<Quiz> getQuizzesTaken()

    /**
     * Add a quiz to the list of quizzes the user has taken.
     * @param quizID the id of the quiz to add
     */
    public void addQuizTaken(quizID)

    // overrides
    /**
     * Return all users where colName matches value.
     * Overrides the AbstractModel version in order to return Users
     * instead of AbstractModels.
     * @param colName a String that matches the column to filter by
     * @param value the value to look for in the column
     * @return a list of users with values for column that match value
     */
    @Override
    public static List<User> getByValue(String colName, Object value)

    /**
     * Return all users where colName compares as specified to value.
     * Overrides the AbstractModel version in order to return Users
     * instead of AbstractModels.
     * @param colName a String that matches the column to filter by
     * @param value the value to look for in the column
     * @param comparator a String of the comparator (" = ", " > ", etc.) to use
     * @return a list of users with values for column that compare to value as
     *         specified.
     */
    @Override
    public static List<User> getByValue(String colName, Object value, String comparator)

    /**
     * Return the first user where colName matches value.
     * Overrides the AbstractModel version in order to return a User
     * instead of AbstractModels.
     * @param colName a String that matches the column to filter by
     * @param value the value to look for in the column
     * @return the first User where colName matches value.
     */
    @Override
    public static User getOneByValue(String colName, Object value)

    /**
     * Return the first user where colName compares as specified to value.
     * Overrides the AbstractModel version in order to return Users
     * instead of AbstractModels.
     * @param colName a String that matches the column to filter by
     * @param value the value to look for in the column
     * @param comparator a String of the comparator (" = ", " > ", etc.) to use
     * @return the first user with a value for column that compares to value as
     *         specified.
     */
    @Override
    public static User getOneByValue(String colName, Object value, String comparator)

    /**
     * Get a list of all users.
     * @return a list containing all users in the database
     */
    @Override
    public static List<User> getAll()

    /**
     * Get a list of users filtered by an SQL query.
     * Returns the equivalent of the SQL statemtent
     * <code>"SELECT * FROM users WHERE " + sqlQuery;</code>
     * IMPORTANT:
     * Prepared statements are not used so do not make this method
     * available to website users in any way.
     * @param sqlQuery a String with the filtering conditions formatted as SQL
     * @return a list of Users that match the specified query string
     */
    @Override
    public static List<User> getWhere(String sqlQuery)

    // Don't need the following for this part, and we should talk
    // about how exactly we want them to work.
    // achievements
    /**
     * Get a list of all the user's achievements.
     */
    //public List<Achievement> getAchievements()
    /**
     */
    //public void addAchievement(Achievement achievement)

    // messages sent
    /**
     */
    //public List<Message> getMessagesSent()
    /**
     */
    //public void sendMessage(Message msg)

    // messages received
    /**
     */
    //public List<Message> getMessagesReceived()

}
