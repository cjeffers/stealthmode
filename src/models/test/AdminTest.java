package models.test;

import models.*;

import java.sql.Connection;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.*;


public class AdminTest {

	static Connection con;

	@BeforeClass
	public static void setUp() throws Exception {
        con = AbstractModel.getConnection();
	}

    @Test
    public void testBasicFunctionality() {
        User am = new User("ben_test_admin", "cheese", "Ben Ulmer", true);
        assertTrue(User.nameInUse("ben_test_admin"));
       User.deleteUser(am.getID());
       assertTrue(!User.nameInUse("ben_test_admin"));
    }

    @Test
    public void testFriends() {
    	System.out.println("There are this many users: "+ User.getAmountOfUsers());
    	System.out.println("People have taken this many quizzes: " + Quiz.getTotalQuizzesTaken());
    }
    
    @Test
    public void testAdmin() {
    	Message.makeAnnouncement("Ben", "Testing announcement");
    	List<Message> announcements = Message.seeAnnouncements();
    	for (int i = 0; i < announcements.size(); i++){
    		System.out.println(announcements.get(i).getText());
    	}
    }
    


}
