package com.hit.dm;

public class DataModel<T> extends java.lang.Object implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	private java.lang.Long id;
	private T content;
	
	public DataModel(java.lang.Long id, T content)
	{
		this.id = id;
		this.content = content;
	}
	
	public boolean equals(java.lang.Object obj) { return false; }
	
	public T getContent() { return content; }
	
	public java.lang.Long getDataModelId() { return id; }
	
	public int hashCode() { return 0; }
	
	public void setContent(T content) { this.content = content; }
	
	public void setDataModelId(java.lang.Long id) { this.id = id; }
	
	public java.lang.String toString()
	{ 
		return new String("ID: " + this.id + "Content: " + this.content.toString());
	}
}
