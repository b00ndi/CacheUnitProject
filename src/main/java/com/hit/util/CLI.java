package com.hit.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CLI implements Runnable
{
	private PrintWriter pw;
	private Scanner scanner;
	private StatusCodes state;

		private PropertyChangeSupport proprtyChanger;

	//Constructor
	public CLI(InputStream in, OutputStream out)
	{
		this.pw = new PrintWriter(out);
		this.scanner = new Scanner(in);
		this.proprtyChanger = new PropertyChangeSupport(this);
		StatusCodes state = StatusCodes.STANDBY;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl)
	{
		proprtyChanger.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		proprtyChanger.removePropertyChangeListener(pcl);
	}

	@Override
	public void run() {
		String command, algo="LRU";
		int amount=20;

		write("Default parameters are: algo=" +algo+ " ,amount=" +amount);

		command = scanner.next().toLowerCase();
		while (!command.equals("shutdown"))
		{
			switch (command){
				case "start":
					write("Starting server.......");
					proprtyChanger.firePropertyChange("stateChange", this.state, StatusCodes.START);
					break;
				case "cache_unit_config":
					algo = scanner.next();
					amount = scanner.nextInt();
					write("algo =" +algo + " amount=" +amount);
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
		pw.write(string);
	}
}
