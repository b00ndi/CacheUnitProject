package com.hit.memory;

import java.util.LinkedList;
import java.util.List;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;

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
		
		for (java.lang.Long id : ids)
		{
			DataModel<T> value = algo.getElement(id);
			
			if (value != null)
				list.add(value);
		}
		
		//DataModel<T>[] x = new DataModel<T>[list.size()]; ???
		
		return list.toArray(new DataModel[list.size()]);
	}
	
	public DataModel<T>[] putDataModels(DataModel<T>[] datamodels) 
	{
		List<DataModel<T>> list = new LinkedList<>();
		
		for (DataModel<T> datamodel : datamodels)
		{
			DataModel<T> value = algo.putElement((long) 1 /*??*/, datamodel);
			
			if (value != null)
			{
				list.add(value);
				
				//dao.save(value);
			}
		}
		
		return list.toArray(new DataModel[list.size()]);
	}
	
	void removeDataModels(java.lang.Long[] ids)
	{
		for (java.lang.Long id : ids)
		{
			algo.removeElement(id);
		}
	}
}
