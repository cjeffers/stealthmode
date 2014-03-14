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
        User am = new User("ben_test", "cheese", "Ben Ulmer", true);
        assertTrue(User.nameInUse("ben_test"));
       User.deleteUser(am.getID());
       assertTrue(!User.nameInUse("ben_test"));
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
    


}
