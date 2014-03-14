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

	@Before
	public void setUp() throws Exception {
        con = AbstractModel.getConnection();
	}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        AbstractModel.closeConnection();
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
    	System.out.println("Ben has this many friends: " + benFriends.size());
    	am1.removeFriend("ben_test_friend");
    	List<User> newBenFriends = am1.getFriends();
    	assertTrue(newBenFriends.size() == 0);
    }

    @Test
    public void testAdmin() {
    	User am1 = new User("ben_test", "cheese", "Ben", true);
    	User am2 = new User("ben_test_friend", "cheese", "Ninja", true);
    	List<User> admins = User.findAdministrators();
    	System.out.println("Amount of admins are: " + admins.size());
    	am1.setAdminPriveledge(false);
    	List<User> admins2 = User.findAdministrators();
    	System.out.println("Amount of admins are: " + admins2.size());
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

    @Test
    public void findAll(){
    	System.out.print(User.findAll().size());
    }

}
