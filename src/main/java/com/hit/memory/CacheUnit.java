package com.hit.memory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.hit.algorithm.IAlgoCache;

import com.hit.dao.IDao;
import com.hit.dm.DataModel;

public class CacheUnit<T> extends java.lang.Object
{
	private IAlgoCache<java.lang.Long, DataModel<T>> algo;
	private IDao<java.lang.Long, DataModel<T>> dao;
	
	CacheUnit(com.hit.algorithm.IAlgoCache<java.lang.Long, DataModel<T>> algo,
			IDao<java.lang.Long, DataModel<T>> dao)
	{
		this.algo = algo;
		this.dao = dao;
	}
	
	public DataModel<T>[] getDataModels(java.lang.Long[] ids)
	{
		List<DataModel<T>> list = new LinkedList<DataModel<T>>();
		
		// getDataModels iterates across all given datamodel IDs and checks
		// whether their datamodels exist in cache memory. If the cache  
		// algorithm returns NULL then the datamodel does not exist,
		// therefore it searches the 2nd memory for the requested datamodel and 
		// adds the necessary datamodel to the cache.
		// If the datamodel exists in cache then it is added to the list.

		for (java.lang.Long id : ids)
		{
			DataModel<T> value = algo.getElement(id);
			
			if (value != null)
				// Cache contains datamodel of ID.
				list.add(value);
			else
			{
				// Cache does not contain datamodel.				
				try
				{
					// Searches the 2nd Mem.	
					value = dao.find(id);
					
					if (value != null)
					{
						// If datamodel was found in 2nd memory then store it
						// in cache and delete from 2nd memory
						dao.delete(value);
						
						DataModel<T> prev = algo.putElement(value.getDataModelId(), value);
						
						if (prev != null)
							dao.save(prev);
					}

					list.add(value);
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return list.toArray(new DataModel[list.size()]);
	}
	
	public DataModel<T>[] putDataModels(DataModel<T>[] datamodels) 
	{
		List<DataModel<T>> list = new LinkedList<>();
		
		// putDataModels iterates across all given datamodels
		// and inserts them to the cache memory. If the cache capacity
		// has reached maximum then putElement will return the replaced
		// values. All returned elements from putElement will be sent
		// back to disk via the dao object.
		
		for (DataModel<T> datamodel : datamodels)
		{
			// 'value' is the element that was replaced by the cache
			// algorithm. If Null then no value was replaced.
			DataModel<T> value = algo.putElement(datamodel.getDataModelId(), datamodel);
			
			// If the value is not null then it adds it to the list and send it
			// back to the 2nd Mem.
			if (value != null)
			{
				list.add(value);
				
				try
				{
					dao.save(value);
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
				
		}
		
		// Return replaced datamodels
		return list.toArray(new DataModel[list.size()]);
	}
	
	void removeDataModels(java.lang.Long[] ids)
	{
		for (java.lang.Long id : ids)
		{
			algo.removeElement(id);
			//dao.delete(entity);
		}
	}
}
