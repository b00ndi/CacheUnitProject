package com.hit.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CLI implements Runnable
{
	private PrintWriter printWriter;
	private Scanner scanner;
	private StatusCodes state;

		private PropertyChangeSupport propertyChanger;

	//Constructor
	public CLI(InputStream in, OutputStream out)
	{
		this.printWriter = new PrintWriter(out);
		this.scanner = new Scanner(in);
		this.propertyChanger = new PropertyChangeSupport(this);
		StatusCodes state = StatusCodes.STANDBY;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl)
	{
		propertyChanger.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		propertyChanger.removePropertyChangeListener(pcl);
	}

	@Override
	public void run()
	{
		String command, algo = "LRU";
		int capacity = 20;

		write("Default parameters\nAlgorithm: " + algo + " Capacity: " + capacity);

		command = scanner.next().toLowerCase();
		
		while (!command.equals("shutdown"))
		{
			switch (command)
			{
				case "start":
					write("Starting server...");
					propertyChanger.firePropertyChange("stateChange", this.state, StatusCodes.START);
					
					break;
					
				case "cache_unit_config":
					algo = scanner.next();
					capacity = scanner.nextInt();
					write("Algorithm:" +algo + " Capacity: " +capacity);
					
					break;
					
				default:
					write("Not a valid command");
					
					break;
			}
			
			command = scanner.next().toLowerCase();
		}
		
		write("Shutting Down");
	}

	//Write status for the user via OUT
	public void write(String string)
	{
		printWriter.write(string);
	}
}
