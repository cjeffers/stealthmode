package models.test;

import java.sql.Connection;
import java.util.List;

import models.AbstractModel;
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
        assertTrue(User.nameInUse("ben_test"));
        am.setPicURL("testing");
        assertTrue(am.isAdministrator());
        assertTrue(am.getUserName().equals("ben_test"));
        assertTrue(am.getPicURL().equals("testing"));
        assertTrue(am.getFullname().equals("Ben Ulmer"));
        am.setAdminPriveledge(false);
        assertTrue(!am.isAdministrator());
        am.setUserName("Bob");
        assertTrue(am.getUserName().equals("Bob"));
        assertTrue(am.correctPassword("cheese"));
        am.setAmateurAuthor(true);
        assertTrue(am.hasWonAmateurAuthor());
        List<String> awardsWon = am.seeAwardsWon();
        assertTrue(awardsWon.size() = 1);
    }

    @Test
    public void testFriends() {
    	User am1 = new User("ben_test", "cheese", "Ben", true);
    	User am2 = new User("ben_test_friend", "cheese", "Ninja", true);
    	am1.addFriend("Ninja");
    	List<User> benFriends = am1.getFriends();
    	assertTrue(benFriends.get(0).getUserName().equals("Ninja"));
    	List<User> ninjaFriends = am2.getFriends();
    	assertTrue(ninjaFriends.get(0).getUserName().equals("Ben"));
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
