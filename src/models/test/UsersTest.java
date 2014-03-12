package models.test;

import java.util.*;
import java.sql.*;

import static org.junit.Assert.*;
import models.AbstractModel;

import org.junit.*;
import models.User;

public class UsersTest {
	
	
	static Connection con;

	@BeforeClass
	public static void setUp() throws Exception {
        con = AbstractModel.getConnection();
	}

    @Test
    public void testBasicFunctionality() {
        User am = new User("ben_test", "cheese", "Ben Ulmer", true);
      //  assertTrue(User.nameInUse("ben_test"));
        am.setPicURL("testing");
        assertTrue(am.isAdministrator());
        assertTrue(am.getUserName().equals("ben_test"));
        assertTrue(am.correctPassword("cheese"));
        assertTrue(am.getPicURL().equals("testing"));
        assertTrue(am.getFullname().equals("Ben Ulmer"));
        am.setAdminPriveledge(false);
        assertTrue(!am.isAdministrator());
        am.setUserName("Bob");
        assertTrue(am.getUserName().equals("Bob"));
        am.setAmateurAuthor(true);
        assertTrue(am.hasWonAmateurAuthor());
        List<String> awardsWon = am.seeAwardsWon();
        assertTrue(awardsWon.size() == 1);
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
    	assertTrue(admins.size() == 2);
    	am1.setAdminPriveledge(false);
    	List<User> admins2 = User.findAdministrators();
    	assertTrue(admins2.size() == 1);
    }

}
