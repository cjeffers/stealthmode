package models.test;

import java.util.*;
import java.sql.*;

import static org.junit.Assert.*;
import models.AbstractModel;

import org.junit.*;
import models.User;
import models.Message;

public class FriendsTest {
	
	
	static Connection con;

	@BeforeClass
	public static void setUp() throws Exception {
        con = AbstractModel.getConnection();
	}

    
    
    @Test
    public void findAll(){
    	User jed = User.findByUsername("jed");
    	jed.addFriend("james");
    	jed.addFriend("ben_test");
    }

}
