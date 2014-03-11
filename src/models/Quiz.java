package models;

import java.util.List;

public class Quiz {

	private AbstractModel model;
	
	
	/**
	 * Constructs a new quiz
	 * @param givenName name of quiz
	 * @param givenTimed whether the quiz is timed or not
	 * @param givenMultiplePages whether the quiz consists of multiple pages
	 * @param givenDateMade the date the quiz was made
	 */
	public Quiz(String givenName, boolean givenTimed, boolean givenMultiplePages, long givenDateMade){
		model = new AbstractModel("quizzes");
		setTimed(givenTimed);
		setMultiplePages(givenMultiplePages);
		setDateMade(givenDateMade);
	}
	
	public int getID(){
		return (Integer) model.getValue("ID");
	}
	
	public boolean isTimed(){
		return (Boolean) model.getValue("Timed");
		
	}
	
	public boolean hasMultiplePages(){
		return (Boolean) model.getValue("MultiplePages");
	}
	
	public long getDateMade(){
		return (Long) model.getValue("Date");
	}
	
	public void setID(int id){
		model.setValue("ID", id);
	}
	
	public void setTimed(boolean timed){
		model.setValue("Timed", timed);
	}
	
	public void setMultiplePages(boolean multPages){
		model.setValue("MultiplePages", multPages);
	}
	
	public void setDateMade(long date){
		model.setValue("Date", date);
	}
	
	public void commit(){
	//model.save();
	}
	
	//How to get questions
	
}
