package app;

import java.util.ArrayList;
import common.Level;

/**
 * This class "is a" competitor since it implements the Competitor interface.
 * FeatherWeightCompetitors are entitled to crew members.
 * This helps Sets FeatherWC apart from other types of competitors.
 */

public class FeatherWeightCompetitor implements Competitor {

	private int age = 0;
	private int number = 0;
	private Name name = null;
	private Level level = null;
	private int crewCount = 0;
	private ArrayList<Integer> scores = new ArrayList<Integer>(5);
	

	public FeatherWeightCompetitor(int age, int number, Name name, Level level, int crewCount, ArrayList<Integer> scores) {
		this.age = age;
		this.number = number;
		this.name = name;
		this.level = level;
		this.crewCount = crewCount;
		this.scores = scores;
	}
	
	//get the number value
	public int getNumber() {return number;}
	//set the number value
	public void setNumber(int number) {this.number = number;}
	//get the name object
	public Name getName() {return name;}
	//set the name object
	public void setName(Name name) {this.name = name;}
	//get the enum object level
	public Level getLevel() {return level;}
	//set the enum object level
	public void setLevel(Level level) {this.level = level;}
	//get the competitors age
	public int getAge() {return age;}
	//set the competitors age
	public void setAge(int age) {this.age = age;}
	//get the crewCount, this is the uncommon attribute amongst other competitor classes.
	public int getCrewCount() {return crewCount;}
	//get the crewCount, this is the uncommon attribute amongst other competitor classes.
	public void setCrewCount(int crewCount) {this.crewCount = crewCount;}
	//helper method to calculate the mean of competitor's score
	public double average() {
		double accumulator = 0;
		for(Integer value : scores) { accumulator += value; }
			accumulator = accumulator/scores.size();
			return accumulator;
	}
	
	//helper method to turn an array of numbers to a string
	public String arrToString() {
		String str="";
		for(Integer value : scores) { str += "," + value.toString(); }
		//remove the first item of the string
		return str.substring(1);
	}
	
	//helper method that returns an array of integers
	public ArrayList<Integer> getScoreArray(){return scores;}

	public String getShortDetails() {
		String nameInitials, shortDetail;
		int overallScore = (int) getOverallScore();
		//capitalize the 1st letter of competitors first, middle & lastname
		nameInitials = new StringBuilder()
						.append(name.getFirstName().toUpperCase().charAt(0))
						.append(name.getLastName().toUpperCase().charAt(0))
						.append(name.getMiddleName().toUpperCase().charAt(0))
						.toString();
		shortDetail = "CN " + number + " (" + nameInitials + ") " + 
					  "has overall score " + overallScore + ".";
		return shortDetail;
	}
	
	@Override
	public double getOverallScore() {
		double mean, score=0;
		mean = average();
		if (mean <= 3) {
			switch (level) {
			case NOVICE:
			score = mean + 1;
			break;
			case INTERMEDIATE:
			score = (int) (mean + 0.5);
			break;
			case ADVANCED:
			score = (int) (mean - 0.5);
			break;
			case EXPERT:
			score = mean - 1;
			break;
			}
		}else if(crewCount > 0) {
			score = mean + 1;
		}else {
			score = mean;
		}return score; 
	}

	@Override
	public String getFullDetails() {
		 String fullDetail = "Competitor Number " + number + "," + " with name " + name.getFullName();
				fullDetail += " is a " + age + " year old " + level + " at this competition. ";
		   		fullDetail += "\r\n" + name.getFirstName() + " recieved these scores : " + arrToString();
		   		fullDetail += "\r\n" + "This gives him an overall score of " + getOverallScore() + ".";
		   		fullDetail += "\r\n" + "FeatherWeight competitor's " + "are entitled to a crew, there are " 
		   				   + crewCount + " members of my crew here.";	   
		return fullDetail;
	}
	
	@Override
	public void setScores(ArrayList<Integer> scores) {
		this.scores = scores;
	}

}
