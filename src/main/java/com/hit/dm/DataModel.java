package com.hit.dm;

import java.io.Serializable;

public class DataModel<T>implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private T content;
	
	public DataModel(Long id, T content)
	{
		this.id = id;
		this.content = content;
	}
	
	public boolean equals(Object obj) { return false; }
	
	public T getContent() { return content; }
	
	public void setContent(T content) { this.content = content; }
	
	public Long getDataModelId() { return id; }
	
	public int hashCode() { return this.hashCode(); }
	
	public void setDataModelId(Long id) { this.id = id; }
	
	public String toString()
	{ 
		return new String("ID: " + this.id + "Content: " + this.content.toString());
	}
}
