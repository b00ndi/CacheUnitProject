package com.hit.services;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

public class CacheUnitService<T>
{
	private CacheUnit<T> cacheUnit = null;


	//Constructor
	public CacheUnitService()
	{
		CacheUnit<T> cacheUnit = new CacheUnit<>(new LRUAlgoCacheImpl(3), new DaoFileImpl("datasource.txt"));
	}

	///Update cacheUnit by removing old data and insert updated data
	public boolean update(DataModel<T>[] dataModels)
	{
		if (dataModels.length > 0)
		{
			DataModel<T>[] refactorDataModels = get(dataModels);
			delete(refactorDataModels);
			cacheUnit.putDataModels(refactorDataModels);
			return true;
		}
		return false;
	}

	//Remove specified datamodels from cacheUnit
	public boolean delete(DataModel<T>[] dataModels)
	{
		if (dataModels.length > 0)
		{
			Long[] ids = new Long[dataModels.length];
			for (int i = 0; i < dataModels.length; i++)
				ids[i] = dataModels[i].getDataModelId();

			cacheUnit.removeDataModels(ids);
			return true;
		}
		return false;
	}

	//Get list of specified datamodels from cacheUnit
	public DataModel<T>[] get(DataModel<T>[] dataModels)
	{
		if (dataModels.length > 0) {
			Long[] ids = new Long[dataModels.length];
			for (int i = 0; i < dataModels.length; i++)
				ids[i] = dataModels[i].getDataModelId();

			return cacheUnit.getDataModels(ids);
		}
		return null;
	}
}
