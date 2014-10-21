package package1;

import javax.swing.JFrame;

/******************************************************************
 * Count Down Timer Project 1 GUI main method
 * @author Mitchell Couturier
 * @version 1/16/2014
 ******************************************************************/

public class MyTimer {
	/*************************************************
	 * Main Method
	 *************************************************/
	public static void main(String [] args){
		JFrame frame = new JFrame("CountDown Timer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyTimerPanel panel = new MyTimerPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
}
