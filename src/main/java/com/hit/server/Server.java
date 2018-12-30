package com.hit.server;

import com.hit.util.StatusCodes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements PropertyChangeListener, Runnable
{
	private ServerSocket serverSocket;
	private int port;
	private StatusCodes state;

	public Server(int port)
	{
		this.port = port;
		//Server server = new Server(port);
		//server.run();
	}
	
	@Override
	public void run()
	{
		while (true) {
			try {
				this.serverSocket = new ServerSocket(port);
				Socket clientSock = serverSocket.accept();
				manageConections(clientSock);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{	
		String property = evt.getPropertyName();

		if (property == "stateChange")
		{
			if (evt.getOldValue().equals(StatusCodes.STANDBY) &&
					evt.getNewValue().equals(StatusCodes.START))
			{
				run();
			}
			else
				System.out.println("not good");
		}


	}

	private void manageConections(Socket clientSock) throws IOException
	{
		//@TODO - Need to add functionality
	}
}
