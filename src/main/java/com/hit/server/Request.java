package com.hit.server;

public class Request<T> extends java.lang.Object
implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	public Request(java.util.Map<java.lang.String, java.lang.String> header,
			T body)
	{
		
	}
	
	public T getBody()
	{
		return null;
	}
	
	public java.util.Map<java.lang.String, java.lang.String> getHeaders()
	{
		return null;
	}
	
	public void setBody(T body)
	{
		
	}
	
	public java.lang.String toString()
	{
		return null;
	}
}
