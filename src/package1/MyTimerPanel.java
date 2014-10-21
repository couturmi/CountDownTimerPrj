package package1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/******************************************************************
 * Count Down Timer Project 1 GUI
 * @author Mitchell Couturier
 * @version 1/16/2014
 ******************************************************************/

public class MyTimerPanel extends JPanel{
	/** array of each CountDownTimer **/
	private CountDownTimer[] timers;
	
	/** array of JButtons for each timer **/
	private JButton[] starts, stops, sets;
	/** array of JLabels for each timer **/
	private JLabel[] hour, min, sec, time;
	/** array of JTextFields for each timer **/
	private JTextField[] eHour, eMin, eSec;
	/** array of JPanels for each timer **/
	private JPanel[] panels;
	/** array of booleans that decides if the timer should run **/
	private boolean[] on;
	
	/** array of Java Timers for each timer **/
	private Timer[] javaTimers;
	/** ActionListeners for the GUI **/
	TimerListenerOne m1;
	TimerListenerTwo m2;
	TimerListenerThree m3;
	
	/*************************************************
	 * GUI Constructor
	 *************************************************/
	public MyTimerPanel(){
		setPreferredSize(new Dimension(625,125));
		
		//instantiates all arrays of GUI components
		hour = new JLabel[3];
		min = new JLabel[3];
		sec = new JLabel[3];
		time = new JLabel[3];
		starts = new JButton[3];
		stops = new JButton[3];
		sets = new JButton[3];
		eHour = new JTextField[3];
		eMin = new JTextField[3];
		eSec = new JTextField[3];
		timers = new CountDownTimer[3];
		panels = new JPanel[3];
		javaTimers = new Timer[3];
		on = new boolean[3];

		m1 = new TimerListenerOne();
		m2 = new TimerListenerTwo();
		m3 = new TimerListenerThree();
		
		//creates components for each timer
		for (int i = 0; i<3; i++){
			timers[i] = new CountDownTimer();
			hour[i] = new JLabel("Hours: ");
			min[i] =  new JLabel("Minutes: ");
			sec[i] = new JLabel("Seconds: ");
			time[i] = new JLabel(timers[i].toString());
			sets[i] = new JButton("Set");
			starts[i] = new JButton("Start");
			stops[i] = new JButton("Stop");
			eHour[i] = new JTextField(5);
			eMin[i] = new JTextField(5);
			eSec[i] = new JTextField(5);
			panels[i] = new JPanel();
			on[i] = false;
		}
			javaTimers[0] = new Timer(1000, m1);
			javaTimers[1] = new Timer(1000, m2);
			javaTimers[2] = new Timer(1000, m3);

		//adds components to the GUI
		for (int i = 0; i<3;i++){
			panels[i].add(hour[i]);
			panels[i].add(eHour[i]);
			panels[i].add(min[i]);
			panels[i].add(eMin[i]);
			panels[i].add(sec[i]);
			panels[i].add(eSec[i]);
			panels[i].add(sets[i]);
			panels[i].add(starts[i]);
			panels[i].add(stops[i]);
			panels[i].add(time[i]);
			add(panels[i]);
		}
		
		//adds Listeners to JButtons
			starts[0].addActionListener(m1);
			starts[1].addActionListener(m2);
			starts[2].addActionListener(m3);
			stops[0].addActionListener(m1);
			stops[1].addActionListener(m2);
			stops[2].addActionListener(m3);
			sets[0].addActionListener(m1);
			sets[1].addActionListener(m2);
			sets[2].addActionListener(m3);
	
			
	}

	/***************************************************************
	 * Three ActionListener methods for each timer in the GUI
	 ***************************************************************/
	private class TimerListenerOne implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String enteredHours = "";
			String enteredMins = "";
			String enteredSecs = "";
			
				
				if(e.getSource()== sets[0]){
					enteredHours = eHour[0].getText();
					enteredMins = eMin[0].getText();
					enteredSecs = eSec[0].getText();
					
					if(enteredHours.equals(""))
						timers[0].hours = 0;
					else
						timers[0].hours = Integer.parseInt(enteredHours);
					if(enteredMins.equals(""))
						timers[0].mins = 0;
					else
						timers[0].mins = Integer.parseInt(enteredMins);
					if(enteredSecs.equals(""))
						timers[0].secs = 0;
					else
						timers[0].secs = Integer.parseInt(enteredSecs);
					
					time[0].setText(timers[0].toString());
				}
				
				if(e.getSource() == starts[0]){
					if(timers[0].toString().equals("0:00:00"))
						javaTimers[0].stop();
					else
						on[0] = true;
				}
				
				if(e.getSource() == stops[0]){
					on[0] = false;
				}
				
				//makes timer go
				if(on[0] == true){
					javaTimers[0].start();
					if(!timers[0].toString().equals("0:00:00")){
						timers[0].dec();
						time[0].setText(timers[0].toString());
					}else
						on[0] = false;
				}
				//makes timer stop
				if(on[0] == false)
					javaTimers[0].stop();
				
		}
	}
	
	private class TimerListenerTwo implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String enteredHours = "";
			String enteredMins = "";
			String enteredSecs = "";
				
				if(e.getSource()== sets[1]){
					enteredHours = eHour[1].getText();
					enteredMins = eMin[1].getText();
					enteredSecs = eSec[1].getText();
					
					if(enteredHours.equals(""))
						timers[1].hours = 0;
					else
						timers[1].hours = Integer.parseInt(enteredHours);
					if(enteredMins.equals(""))
						timers[1].mins = 0;
					else
						timers[1].mins = Integer.parseInt(enteredMins);
					if(enteredSecs.equals(""))
						timers[1].secs = 0;
					else
						timers[1].secs = Integer.parseInt(enteredSecs);
					
					time[1].setText(timers[1].toString());
				}
				
				if(e.getSource() == starts[1]){
					if(timers[1].toString().equals("0:00:00"))
						javaTimers[1].stop();
					else
						on[1] = true;
				}
				
				if(e.getSource() == stops[1]){
					on[1] = false;
				}
				
				//makes timer go
				if(on[1] == true){
					javaTimers[1].start();
					if(!timers[1].toString().equals("0:00:00")){
						timers[1].dec();
						time[1].setText(timers[1].toString());
					}else
						on[1] = false;
				}
				//makes timer stop
				if(on[1] == false)
					javaTimers[1].stop();
				
		}
	}
	
	private class TimerListenerThree implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String enteredHours = "";
			String enteredMins = "";
			String enteredSecs = "";
				
				if(e.getSource()== sets[2]){
					enteredHours = eHour[2].getText();
					enteredMins = eMin[2].getText();
					enteredSecs = eSec[2].getText();
					
					if(enteredHours.equals(""))
						timers[2].hours = 0;
					else
						timers[2].hours = Integer.parseInt(enteredHours);
					if(enteredMins.equals(""))
						timers[2].mins = 0;
					else
						timers[2].mins = Integer.parseInt(enteredMins);
					if(enteredSecs.equals(""))
						timers[2].secs = 0;
					else
						timers[2].secs = Integer.parseInt(enteredSecs);
					
					time[2].setText(timers[2].toString());
				}
				
				if(e.getSource() == starts[2]){
					if(timers[2].toString().equals("0:00:00"))
						javaTimers[2].stop();
					else
						on[2] = true;
				}
				
				if(e.getSource() == stops[2]){
					on[2] = false;
				}
				
				//makes timer go
				if(on[2] == true){
					javaTimers[2].start();
					if(!timers[2].toString().equals("0:00:00")){
						timers[2].dec();
						time[2].setText(timers[2].toString());
					}else
						on[2] = false;
				}
				//makes timer stop
				if(on[2] == false)
					javaTimers[2].stop();
				
		}
	}
}