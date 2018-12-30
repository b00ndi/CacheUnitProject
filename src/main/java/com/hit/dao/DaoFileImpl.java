package com.hit.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

import com.hit.dm.DataModel;

public class DaoFileImpl<T> implements IDao<Long, DataModel<T>>
{
	private final String filePath;
	private final int capacity;
	private File file;
	
	private LinkedHashMap<Long, DataModel<T>> map;

	public DaoFileImpl(String filepath, int capacity)
	{
		this.filePath = filepath;
		this.capacity = capacity;
		this.file = new File(filePath);
		
		this.map = null;
	}
	
	public DaoFileImpl(String filepath)
	{
		this(filepath, 10);
	}
	
	// Delete
	@Override
	public void delete(DataModel<T> entity)
	{	
		FileOutputStream fileOutput = null;
		ObjectOutputStream objectOutput = null;
		
		// Private helper function - gets map from file.
		map = getMapFromFile();
		
		if (map != null)
		{
			// Remove datamodel from map.
			map.remove(entity.getDataModelId());
			
			try
			{
				// Write new map to file.
				fileOutput = new FileOutputStream(file);
				objectOutput = new ObjectOutputStream(fileOutput);
				
				objectOutput.writeObject(map);						
			} catch (IOException io) 
			{
				io.printStackTrace();
			} finally
			{	
				try
				{
					if (fileOutput != null)
						fileOutput.close();
					if (objectOutput != null)
						objectOutput.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				
				// Remove map from memory.
				map = null;
			}
		}
	}
	
	// Find
	@Override
	public DataModel<T> find(Long id)
	{
		// Private helper function - gets map from file.
		map = getMapFromFile();
		
		DataModel<T> dataModel = null;
		
		if (map != null)
		{
			// Get datamodel from map.
			dataModel = map.get(id);
			
			// Remove map from memory.
			map = null;
		}
		
		return dataModel;
	}
	
	// Save
	@Override
	public void save(DataModel<T> entity)
	{
		FileOutputStream fileOutput = null;
		ObjectOutputStream objectOutput = null;
		
		// Check if the file exists on the system.
		if (file.exists())
		{
			map = getMapFromFile();
		}
		
		else
		{
			// No file exists-> make new hash map.
			map = new LinkedHashMap<>(capacity);
		}
		
		if (map != null && map.size() < capacity)
		{
			try 
			{
				fileOutput = new FileOutputStream(file);
				objectOutput = new ObjectOutputStream(fileOutput);
				
				// Insert new datamodel to map.
				map.put(entity.getDataModelId(), entity);
				
				objectOutput.writeObject(map);
			}
			
			catch (IOException io) 
			{
				io.printStackTrace();
			} 
			
			finally
			{	
				try
				{
					if (fileOutput != null)
						fileOutput.close();
					if (objectOutput != null)
						objectOutput.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				
				// Remove map from memory.
				map = null;
			}
		}
	}
	
	private LinkedHashMap<Long, DataModel<T>> getMapFromFile()
	{
		// Helper function that retrieves the map from the file.		
		FileInputStream fileInput = null;
		ObjectInputStream objectInput = null;
		
		try
		{
			fileInput = new FileInputStream(filePath);
			objectInput = new ObjectInputStream(fileInput);
			
			return (LinkedHashMap<Long, DataModel<T>>) objectInput.readObject();
		}
		
		// Exception handling
		catch (FileNotFoundException e)
		{	
			e.printStackTrace();
		} catch (IOException e)
		{	
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			try
			{
				if (objectInput != null)
					objectInput.close();
				if (fileInput != null)
					fileInput.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
