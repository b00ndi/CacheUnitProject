package com.hit.dao;

import java.io.IOException;
import java.io.Serializable;

public interface IDao<ID extends Serializable, T>
{
	public void delete(T entity);
	
	public T find(ID id) throws IOException;
	
	public void save(T entity) throws IOException;
}
