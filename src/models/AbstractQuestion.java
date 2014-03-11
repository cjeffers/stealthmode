package models;

import javax.servlet.ServletRequest;


/**
 * Question types should use this class as their superclass
 * This enforces that all question types implement the abstract
 * methods outlined here.
 */
public abstract class AbstractQuestion extends Question {
	
	/*
	 * Constructors do not change
	 */
	public AbstractQuestion(AbstractModel am) {
		super(am);
	}
	
	// These methods must be implemented by all questions
	
	/**
	 * Checks to see if the answer is correct based on a request
	 * @param ServletRequest
	 * @return boolean
	 */
	public abstract boolean checkAnswer(ServletRequest request);
	
	/**
	 * Returns the question's answer
	 */
	public abstract String getAnswer();
}
