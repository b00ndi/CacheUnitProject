package com.hit.server;

import java.io.Serializable;
import java.util.Map;

public class Request<T> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public Request(Map<String, String> header,
			T body)
	{
		
	}
	
	public T getBody()
	{
		return null;
	}
	
	public Map<String, String> getHeaders()
	{
		return null;
	}
	
	public void setBody(T body)
	{
		
	}
	
	public String toString()
	{
		return null;
	}
}
