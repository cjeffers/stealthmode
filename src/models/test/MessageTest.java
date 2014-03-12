package models.test;
import java.util.*;
import java.sql.*;

import static org.junit.Assert.*;
import models.AbstractModel;

import org.junit.*;
import models.User;
import models.Message;

public class MessageTest {
	static Connection con;

	@BeforeClass
	public static void setUp() throws Exception {
		con = AbstractModel.getConnection();
	}

	@Test
	public void testBasicFunctionality() {
		Message test = new Message("jed", "ben", "hellothisisjed", 'm');
		assertTrue(test.getSender().equals("jed"));
		assertTrue(test.getReceiver().equals("ben"));
		assertTrue(test.getText().equals("hellothisisjed"));
	}

	@Test
	public void testGetMessages() {
		/*Message test1 = new Message("jed", "ben", "hellothisisjed", 'm');
		Message test2 = new Message("jed", "ben", "blebleh", 'm');
		Message test3 = new Message("ben", "ben", "bongbong", 'm');
		Message test4 = new Message("ben", "jed", "hellothisisjed", 'm');*/
		List<Message> allM = Message.getAllMessages();
		System.out.println(allM.size());
		List<Message> jedGets = Message.findMessagesSentByUser("ben");
		System.out.println(jedGets.size());
		for(Message i:jedGets){
			System.out.println(i.getText());
		}
		List<Message> benGets = Message.findMessagesSentByUser("jed");
		System.out.println(benGets.size());
		for(Message i:benGets){
			System.out.println(i.getText());
		}
	}
	





}
