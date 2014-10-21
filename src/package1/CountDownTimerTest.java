package package1;

import org.junit.*;
import java.io.*;
import static junit.framework.Assert.*;

/******************************************************************
 * Count Down Timer Project 1 JUnit Test
 * @author Mitchell Couturier
 * @version 1/14/2014
 ******************************************************************/

public class CountDownTimerTest {

	/**************************************************************
	 * Tests if parameters are less than 10, than zeros will
	 * be added in the toString method where needed
	 **************************************************************/
	@Test
	public void testLessThanTen(){
		CountDownTimer s = new CountDownTimer(1, 30, 0);
		assertEquals(s.toString(), "1:30:00");
		
		s = new CountDownTimer(1, 30, 5);
		assertEquals(s.toString(), "1:30:05");
		
		s = new CountDownTimer(1, 0, 30);
		assertEquals(s.toString(), "1:00:30");
		
		s = new CountDownTimer(1, 5, 30);
		assertEquals(s.toString(), "1:05:30");
		
		s = new CountDownTimer(0, 0, 0);
		assertEquals(s.toString(), "0:00:00");
	}
	
	/****************************************************************
	 * Tests the 3 constructors that take the integer parameters
	 * to make sure they enter the correct values.
	 ***************************************************************/
	@Test
	public void testIntConstructors(){
        CountDownTimer s = new CountDownTimer(5, 10, 30);
        assertEquals(s.toString(), "5:10:30");
        
		s = new CountDownTimer(10, 30);
		assertEquals(s.toString(), "0:10:30");
		
		s = new CountDownTimer(30);
		assertEquals(s.toString(), "0:00:30");
	}
	
	/***************************************************************
	 * Tests the constructor that takes an object as a parameter
	 * to make sure it distributes the correct values.
	 ***************************************************************/
	@Test
	public void testObjectConstructor(){
		CountDownTimer s1 = new CountDownTimer(1, 30, 0);
		CountDownTimer s2 = new CountDownTimer(s1);
		assertEquals(s2.toString(), "1:30:00");
	}
	
	/****************************************************************
	 * Tests the constructor that takes a String as a parameter
	 * to make sure it converts it to values correctly
	 ****************************************************************/
    @Test
    public void testStringConstructor() {

        CountDownTimer s = new CountDownTimer("20:10:8");
        assertEquals(s.toString(), "20:10:08");

        s = new CountDownTimer("20:8");
        assertEquals(s.toString(), "0:20:08");

        s = new CountDownTimer("8");
        assertEquals(s.toString(), "0:00:08");
    }

    /*****************************************************************
     * Tests the constructors for errors of entering second values
     * greater than 60.
     *****************************************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadSecondsOne() {
        CountDownTimer s = new CountDownTimer(5, 5, 300);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadSecondsTwo() {
    	CountDownTimer s = new CountDownTimer("5:05:300");
    }

    /***************************************************************
     * Tests the constructors for errors of entering second values
     * less than 60.
     ***************************************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeSecondsOne(){
    	CountDownTimer s = new CountDownTimer(5, 5, -10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeSecondsTwo(){
    	CountDownTimer s = new CountDownTimer("5:05:-10");
    }

    /***************************************************************
     * Tests the constructors for errors of entering minute values
     * greater than 60.
     ***************************************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadMinutesOne() {
        CountDownTimer s = new CountDownTimer(4, 100, 30);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBadMinutesTwo(){
    	CountDownTimer s = new CountDownTimer("4:100:30");
    }
    
    /**************************************************************
     * Tests the constructors for errors of entering minute values
     * less than 60.
     **************************************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeMinutesOne(){
    	CountDownTimer s = new CountDownTimer(5, -5, 5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeMinutesTwo(){
    	CountDownTimer s = new CountDownTimer("5:-10:5");
    }
    
    /*************************************************************
     * Tests the constructors for errors of entering hour values
     * less than 60.
     *************************************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeHoursOne(){
    	CountDownTimer s = new CountDownTimer(-5, 5, 20);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeHoursTwo(){
    	CountDownTimer s = new CountDownTimer("-5:05:20");
    }
    
    /*************************************************************
     * Tests the totalSeconds method to see if it converts
     * to seconds correctly.
     *************************************************************/
    @Test
    public void testTotalSeconds(){
    	CountDownTimer s = new CountDownTimer(2, 45, 15);
    	assertEquals(CountDownTimer.totalSeconds(s), 9915);
    	
    	s = new CountDownTimer();
    	assertEquals(CountDownTimer.totalSeconds(s), 0);
    	
    	s = new CountDownTimer("1:00:30");
    	assertEquals(CountDownTimer.totalSeconds(s), 3630);
    }
    
    /*************************************************************
     * Tests the splitTime method to see if it converts
     * back correctly. 
     *************************************************************/
    @Test
    public void testSplitTime(){
    	CountDownTimer s = new CountDownTimer();
    	s.splitTime(3975);
    	assertEquals(s.toString(), "1:06:15");
    	
    	s.splitTime(45);
    	assertEquals(s.toString(), "0:00:45");
    	
    }
  
    /***************************************************************
     * Tests the subtract method to see if it subtracts
     * the correct amount.
     ***************************************************************/
    @Test
    public void testSub() {
        CountDownTimer s1 = new CountDownTimer(5, 01, 30);
        s1.subtract(2000);
        assertEquals(s1.toString(), "4:28:10");

        s1 = new CountDownTimer(5, 59, 30);
        CountDownTimer s2 = new CountDownTimer(1, 2, 35);
        s1.subtract(s2);
        assertEquals(s1.toString(), "4:56:55");
    }
    
    /*****************************************************************
     * Tests the subtract method for errors of the time becoming
     * negative
     *****************************************************************/
    @Test(expected = IllegalArgumentException.class)
    public void testBadSubOne(){
    	CountDownTimer s = new CountDownTimer(0, 0, 30);
    	s.subtract(60);
    	
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBadSubTwo(){
    	CountDownTimer s1 = new CountDownTimer("2:34:07");
    	CountDownTimer s2 = new CountDownTimer("5:24:57");
    	s1.subtract(s2);
    }

    /**************************************************************
     * Tests the add method to see if it adds
     * the correct amount.
     **************************************************************/
    @Test
    public void testAdd(){
    	CountDownTimer s1 = new CountDownTimer(1, 0, 0);
    	s1.add(1800);
    	assertEquals(s1.toString(), "1:30:00");
    	
    	CountDownTimer s2 = new CountDownTimer(0, 30, 0);
    	s1.add(s2);
    	assertEquals(s1.toString(), "2:00:00");
    }
    
    /**************************************************************
     * Tests the dec method to see if it decrements 
     * only 1 each time it is called.
     **************************************************************/
    @Test
    public void testDec () {

        CountDownTimer s1 = new CountDownTimer(5, 59, 30);
        for (int i = 0; i < 15000; i++) {
            s1.dec();
        }
        assertEquals(s1.toString(), "1:49:30");
    }
     
    /**************************************************************
     * Tests the inc method to see if it increments
     * only 1 each time it is called.
     **************************************************************/
    @Test
    public void testInc(){
    CountDownTimer s1 = new CountDownTimer(1, 49, 30);
     	for(int i = 0; i < 15000; i++){
     		s1.inc();
        }
        assertEquals(s1.toString(), "5:59:30");
    }
    
    /**************************************************************
     * Tests the equals method to see if it compares
     * the objects correctly.
     **************************************************************/
    @Test
    public void testEqual () {
        CountDownTimer s1 = new CountDownTimer(5, 59, 00);
        CountDownTimer s2 = new CountDownTimer(6, 01, 00);
        CountDownTimer s3 = new CountDownTimer(6, 01, 00);
        CountDownTimer s4 = new CountDownTimer("5:59:00");

        assertFalse(s1.equals(s2));
        assertTrue(s1.equals(s4));
        assertFalse(CountDownTimer.equals(s1,s3));
        assertTrue(CountDownTimer.equals(s2,  s3));
    }

    /**************************************************************
     * Tests the compareTo method to see if it returns the
     * correct number based on the comparison.
     **************************************************************/
    @Test
    public void testCompareTo () {
        CountDownTimer s1 = new CountDownTimer(5, 59, 00);
        CountDownTimer s2 = new CountDownTimer(6, 01, 00);
        CountDownTimer s3 = new CountDownTimer(5, 50, 20);
        CountDownTimer s4 = new CountDownTimer("5:59:00");

        assertTrue(s2.compareTo(s1) > 0);
        assertTrue(s3.compareTo(s1) < 0);
        assertTrue(s1.compareTo(s4) == 0);
        assertTrue(CountDownTimer.compareTo(s2, s4) > 0);
        assertTrue(CountDownTimer.compareTo(s3, s1) < 0);
        assertTrue(CountDownTimer.compareTo(s1, s4) == 0);
    }

    /*************************************************************
     * Tests the load and save methods to see if they correctly
     * save the object to a file and correctly load an object 
     * from a file.
     *************************************************************/
    @Test
    public void testLoadSave() {
        CountDownTimer s1 = new CountDownTimer(5, 59, 30);
        CountDownTimer s2 = new CountDownTimer("5:59:30");

        s1.save("file1");
        s1 = new CountDownTimer();  // resets to zero

        s1.load("file1");
        assertTrue(s1.equals(s2));
    }
    
    /**************************************************************
     * Tests the load method for an error of loading a
     * non-existent file.
     **************************************************************/
    @Test(expected = IllegalArgumentException.class)
    public void TestLoadErrorOne(){
    	CountDownTimer s1 = new CountDownTimer(5, 59, 30);
    	
    	s1.load("file2");	
    }
    
    /**************************************************************
     * Tests the toggleSuspend method to see if it turns
     * 'off' and 'on'  any add method in CountDownTimer.
     **************************************************************/
    @Test
    public void TestToggleSuspend(){
    	CountDownTimer s1 = new CountDownTimer(1, 20, 30);
    	CountDownTimer.toggleSuspend();
    	s1.add(1000);
    	assertEquals(s1.toString(), "1:20:30");
    	
    	CountDownTimer.toggleSuspend();
    	s1.add(1000);
    	assertEquals(s1.toString(), "1:37:10");
    }
}