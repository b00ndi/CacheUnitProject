package com.hit.memory;

import java.io.IOException;

import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;

public class main {

	public static void main(String[] args) {
		
		DaoFileImpl x = new DaoFileImpl("datamodel.txt");
		
		DataModel<Integer> y = new DataModel<>((long)1,1);
		DataModel<Integer> w = new DataModel<>((long)2,2);
		
		
		try {
			x.save(y);
			x.save(w);
			
			System.out.println(x.find((long)1).getDataModelId());
			System.out.println(x.find((long)2).getDataModelId());	
		}
		
		catch (IOException e)
		{	
			e.printStackTrace();
		}
	}
}