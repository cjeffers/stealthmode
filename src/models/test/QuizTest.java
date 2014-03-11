package models.test;

import models.Quiz;
import models.AbstractModel;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class QuizTest {

	Quiz q;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Quiz.getConnection();
	}
	
}
