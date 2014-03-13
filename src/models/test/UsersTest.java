package models.test;

import java.util.*;
import java.sql.*;

import static org.junit.Assert.*;
import models.AbstractModel;

import org.junit.*;
import models.User;
import models.Message;

public class UsersTest {
	
	
	static Connection con;

	@BeforeClass
	public static void setUp() throws Exception {
        con = AbstractModel.getConnection();
	}

    @Test
    public void testBasicFunctionality() {
        User am = new User("ben_test", "cheese", "Ben Ulmer", true);
        assertTrue(User.nameInUse("ben_test"));
        am.setPicURL("testing");
        assertTrue(am.isAdministrator());
        assertTrue(am.getUserName().equals("ben_test"));
        assertTrue(am.correctPassword("cheese"));
        assertTrue(am.getPicURL().equals("testing"));
        assertTrue(am.getFullname().equals("Ben Ulmer"));
        am.setAdminPriveledge(false);
        assertTrue(!am.isAdministrator());
        am.setAmateurAuthor(true);
        assertTrue(am.hasWonAmateurAuthor());
        List<String> awardsWon = am.seeAwardsWon();
        assertTrue(awardsWon.size() == 1);
        am.delete();
    }

    @Test
    public void testFriends() {
    	User am1 = new User("ben_test", "cheese", "Ben", true);
    	User am2 = new User("ben_test_friend", "cheese", "Ninja", true);
    	am1.addFriend("ben_test_friend");
    	List<User> benFriends = am1.getFriends();
    	assertTrue(benFriends.get(0).getUserName().equals("ben_test_friend"));
    	List<User> ninjaFriends = am2.getFriends();
    	assertTrue(ninjaFriends.get(0).getUserName().equals("ben_test"));
    }
    
    @Test
    public void testAdmin() {
    	User am1 = new User("ben_test", "cheese", "Ben", true);
    	User am2 = new User("ben_test_friend", "cheese", "Ninja", true);
    	List<User> admins = User.findAdministrators();
    	System.out.println(admins.size());
    	assertTrue(admins.size() == 4);
    	am1.setAdminPriveledge(false);
    	List<User> admins2 = User.findAdministrators();
    	assertTrue(admins2.size() == 3);
    }
    
    @Test
    public void testMessages(){
    	User am1 = new User("jed", "bleh", "Jed", false);
    	List<Message> notes = am1.seeMessages();
    	for(Message a:notes){
    		System.out.println(a.getText());
    	}
    }
    
    @Test
    public void testPassword(){
    	User am1 = new User("james", "johnson", "Jed", false);
    	assertTrue(User.validateLogin("james", "johnson") == true);
    	assertTrue(User.validateLogin("james", "jones") == false);
    }

}
