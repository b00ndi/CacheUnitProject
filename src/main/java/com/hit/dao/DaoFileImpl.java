package com.hit.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.hit.dm.DataModel;

public class DaoFileImpl<T> implements IDao<java.lang.Long, DataModel<T>>, Serializable
{
	private final java.lang.String filePath;
	private int capacity;
	private Map<Long, DataModel<T>> map;
	
	public DaoFileImpl(java.lang.String filepath, int capacity)
	{
		this.filePath = filepath;
		this.capacity = capacity;
		
		this.map = null;
	}
	
	public DaoFileImpl(java.lang.String filepath)
	{
		this(filepath, 10);
	}
	
	// Delete
	@Override
	public void delete(DataModel<T> entity)
	{
		
	}
	
	// Find
	@Override
	public DataModel<T> find(Long id) throws IOException
	{
		FileInputStream fileInput = null;
		ObjectInputStream objectInput = null;
		
		try
		{
			fileInput = new FileInputStream(filePath);
			objectInput = new ObjectInputStream(fileInput);
			
			getMap(objectInput);
			
			return map.get(id);
		}
		
		catch(IOException io)
		{
			io.printStackTrace();
		}
		finally
		{
			if (objectInput != null)
				objectInput.close();
			if (fileInput != null)
				fileInput.close();
			
			map = null;
		}
		
		return null;
	}
	
	// Save
	@Override
	public void save(DataModel<T> entity) throws IOException
	{
		FileOutputStream fileOutput = null;
		ObjectOutputStream objectOutput = null;
		
		FileInputStream fileInput = null;
		ObjectInputStream objectInput = null;
		
		try
		{
			fileOutput = new FileOutputStream(filePath, true);
			objectOutput = new ObjectOutputStream(fileOutput);
			
			fileInput = new FileInputStream(filePath);
			objectInput = new ObjectInputStream(fileInput);
			
			getMap(objectInput);
			
			map.put(entity.getDataModelId(), entity);
			
			objectOutput.writeObject(map);
			objectOutput.flush();
		}
		
		catch (IOException io)
		{
			io.printStackTrace();
		}
		finally
		{
			if (objectOutput != null)
				objectOutput.close();
			if (fileOutput != null)
				fileOutput.close();
			
			if (objectInput != null)
				objectInput.close();
			if (fileInput != null)
				fileInput.close();
		}
	}
	
	private void getMap(ObjectInputStream ois)
	{
		if (map == null)
			map =  new LinkedHashMap<>();
		
		else
		{
			try
			{
				map = null;
				map = (LinkedHashMap<Long, DataModel<T>>) ois.readObject();	
			}
			
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();				
			}
			
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
