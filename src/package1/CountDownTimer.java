package package1;

import java.io.*;
import java.util.Scanner;

/******************************************************************
 * Count Down Timer Project 1
 * @author Mitchell Couturier
 * @version 1/12/2014
 ******************************************************************/
public class CountDownTimer {
	/** hours of the timer **/
	int hours;
	/** minutes of the timer **/
	int mins;
	/** seconds of the timer **/
	int secs;
	
	/** boolean for the toggleSuspend() method **/
	static boolean on;
	
	/***************************************************************
	 * Getters and Setters for variables
	 ***************************************************************/
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getMins() {
		return mins;
	}
	public void setMins(int mins) {
		this.mins = mins;
	}
	public int getSecs() {
		return secs;
	}
	public void setSecs(int secs) {
		this.secs = secs;
	}
	
	/*****************************************************************
	 * Constructor methods
	 *****************************************************************/
	public CountDownTimer(){
		hours = 0;
		mins = 0;
		secs = 0;
		on = true;
	}
	public CountDownTimer(int hours, int minutes, int seconds){
		if(hours < 0)
			throw new IllegalArgumentException();
		if(minutes > 60 || minutes < 0)
			throw new IllegalArgumentException();
		if(seconds > 60 || seconds < 0)
			throw new IllegalArgumentException();
		this.hours = hours;
		mins = minutes;
		secs = seconds;
		on = true;
	}
	public CountDownTimer(int minutes, int seconds){
		if(minutes > 60 || minutes < 0)
			throw new IllegalArgumentException();
		if(seconds > 60 || seconds < 0)
			throw new IllegalArgumentException();
		hours = 0;
		mins = minutes;
		secs = seconds;
		on = true;
	}
	public CountDownTimer(int seconds){
		if(seconds > 60 || seconds < 0)
			throw new IllegalArgumentException();
		hours = 0;
		mins = 0;
		secs = seconds;
		on = true;
	}
	public CountDownTimer(CountDownTimer other){
		this.hours = other.hours;
		this.mins = other.mins;
		this.secs = other.secs;
		on = true;
	}
	public CountDownTimer(String startTime){
		String [] values = startTime.split(":");
		int phours = 0;
		int pmins = 0;
		int psecs = 0;
		
		if(values.length == 3){
			
			phours = Integer.parseInt(values[0]);
			pmins = Integer.parseInt(values[1]);
			psecs = Integer.parseInt(values[2]);
			if(phours < 0)
				throw new IllegalArgumentException();
			if(pmins > 60 || pmins < 0)
				throw new IllegalArgumentException();
			if(psecs > 60 || psecs < 0)
				throw new IllegalArgumentException();
		}else if(values.length == 2){
			
			pmins = Integer.parseInt(values[0]);
			psecs = Integer.parseInt(values[1]);
			if(pmins > 60 || pmins < 0)
				throw new IllegalArgumentException();
			if(psecs > 60 || psecs < 0)
				throw new IllegalArgumentException();
		}else if(values.length ==1){
			
			psecs = Integer.parseInt(values[0]);
			if(psecs > 60 || psecs < 0)
				throw new IllegalArgumentException();
		}
		hours = phours;
		mins = pmins;
		secs = psecs;
		on = true;
	}
	
	/*******************************************************************
	 *compares two objects and returns true if equal
	 *******************************************************************/
	public boolean equals(Object other){
		if(hours == ((CountDownTimer)other).hours){
			if(mins == ((CountDownTimer)other).mins){
				if(secs == ((CountDownTimer)other).secs)
					return true;
			}
		}
		return false;
	}
	
	public static boolean equals(CountDownTimer t1, CountDownTimer t2){
		if(t1.equals(t2)== true){
			return true;
		}return false;
	}
	
	/******************************************************************
	 *converts total time into seconds 
	 ******************************************************************/
	public static int totalSeconds(CountDownTimer c1){
		int totalMins =0;
		int totalSecs =0;
		totalMins = c1.mins +(c1.hours * 60);
		totalSecs = c1.secs + (totalMins * 60);
		return totalSecs;
	}
	/*****************************************************************
	 * splits time  into hours, mins, and seconds
	 *****************************************************************/
	public void splitTime(int seconds){
		secs = seconds%60;
		mins = (seconds/60)%60;
		hours = seconds/3600;
	}
	
	/*****************************************************************
	 * compares two objects and checks which is greater
	 *****************************************************************/
	public int compareTo(CountDownTimer other){
		if(equals(other))
			return 0;
		if(totalSeconds(this) > totalSeconds(other))
			return 1;
		else
			return -1;
	}
	
	public static int compareTo(CountDownTimer t1, CountDownTimer t2){
		if(equals(t1,t2))
			return 0;
		if(totalSeconds(t1) > totalSeconds(t2))
			return 1;
		else
			return -1;
	}
	
	/******************************************************************
	 * subtracts a certain number of seconds from this object
	 ******************************************************************/
	public void subtract(int seconds){
		
		int newTotal;
		newTotal = totalSeconds(this) - seconds;
		if(newTotal < 0){
			throw new IllegalArgumentException();
		}
		//creates new time for "this" CountDownTimer
		splitTime(newTotal);
	}
	public void subtract(CountDownTimer other){
		subtract(totalSeconds(other));
	}
	
	/******************************************************************
	 * decrements "this" CountDownTimer by 1 second.
	 ******************************************************************/
	public void dec(){
		int newTotal;
		newTotal = totalSeconds(this) - 1;
		//creates new time for "this" CountDownTimer
		splitTime(newTotal);
	}
	
	/******************************************************************
	 * adds seconds to this CountDownTimer
	 ******************************************************************/
	public void add (int seconds){
		if(on){
			int newTotal;
			newTotal = totalSeconds(this) + seconds;
			//creates new time for "this" CountDownTimer
			splitTime(newTotal);
		}
	}
	public void add(CountDownTimer other){
		if(on){
			add(totalSeconds(other));
		}
	}
	
	/*******************************************************************
	 * increments "this" CountDownTimer by 1 second.
	 *******************************************************************/
	public void inc(){
		int newTotal;
		newTotal = totalSeconds(this) + 1;
		//creates new time for "this" CountDownTimer
		splitTime(newTotal);
	}
	
	/*******************************************************************
	 * returns a string that represents the time
	 *******************************************************************/
	public String toString(){
		//adds hours
			String s = ""+hours+":";
		//adds minutes
			if(mins<10)
				s+="0"+mins+":";
			else
				s+= mins+":";
		//adds seconds
			if(secs==0)
				s+="00";
			else if(secs<10)
				s+="0"+secs;
			else
				s+= secs;
		return s;
	}
	
	/******************************************************************
	 * Loads the "this" CountDownTimer to a file
	 ******************************************************************/
	public void load (String fileName){
		
		try{
			Scanner fileReader = new Scanner(new File(fileName));
			
			this.hours = fileReader.nextInt();
			this.mins = fileReader.nextInt();
			this.secs = fileReader.nextInt();
		}
		
		catch(FileNotFoundException error){
			System.out.println("File not found.");
			throw new IllegalArgumentException();
		}
		
		catch(IOException error){
			System.out.println("Oops! Something went wrong.");
			throw new IllegalArgumentException();
		}
	
	}
	
	/****************************************************************
	 * Saves the "this" CountDownTimer to a file
	 ****************************************************************/
	public void save (String fileName){
		PrintWriter out = null;
		try{
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		}
		catch(IOException e){
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		
		out.println(this.hours);
		out.println(this.mins);
		out.println(this.secs);
		out.close();
	}
	
	/****************************************************************
	 * turns 'off' and 'on' any add method in CountDownTimer.
	 ****************************************************************/
	public static void toggleSuspend(){
		if(on){
			on = false;
		}else{
			on = true;
		}
	}
	
	/*****************************************************************
	 * Main Method for Software Testing
	 *****************************************************************/
    public static void main (String[] args){
    	//test the different constructors
    	CountDownTimer s = new CountDownTimer("1:30:7");
    	System.out.println("Time: "+ s);	//should print "1:30:07"
    	
    	s = new CountDownTimer("59:8");
    	System.out.println("Time: "+ s);	//should print "0:59:08"
    	
    	s = new CountDownTimer("5");
    	System.out.println("Time: "+ s);	//should print "0:00:05"
    	
    	s = new CountDownTimer(20, 4, 30);
    	System.out.println("Time: "+ s);	//should print "20:04:30"
    	
    	//test the subtract and add methods
    	s.subtract(1000);
    	System.out.println("Time: "+ s); 	//should print "19:47:30"
    	
    	s.add(1000);
    	System.out.println("Time: "+ s);	//should print "20:40:30"
    	
    	//test the dec and inc methods
    	s = new CountDownTimer(4, 20, 20);
    	for (int i = 0; i < 1000; i++){
    		s.dec();
    	}
    	System.out.println("Time: "+ s);	//should print "4:03:40"
    	
    	for(int i = 0; i < 1000; i++){
    		s.inc();
    	}
    	System.out.println("Time: "+ s);	//should print "4:20:20"
    	
    	//test the compareTo method
    	CountDownTimer s1 = new CountDownTimer();
    	int num = CountDownTimer.compareTo(s, s1);
    	System.out.println(num); //should be 1
    	
    	//tests the toString method
    	System.out.println(s.toString());	//should print "4:20:20"
    }
}
