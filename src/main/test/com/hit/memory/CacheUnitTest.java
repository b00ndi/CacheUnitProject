package com.hit.memory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dm.DataModel;

class CacheUnitTest {

	@Test
	void test()
	{
//		CacheUnit<Integer> cache = new CacheUnit<>(new LRUAlgoCacheImpl(3), new DaoFileImpl<Integer>("datasource.txt"));
//		
//		/* Cache | 2nd Memory 
//		 *   -   |    -       */
//		
//		ArrayList<DataModel<Integer>> array = new ArrayList<>();
//		
//		array.add(new DataModel<Integer>((long) 1, 1));
//		array.add(new DataModel<Integer>((long) 2, 2));
//		array.add(new DataModel<Integer>((long) 3, 3));
//		
//		DataModel<Integer>[] returnedArray = cache.putDataModels(array.toArray(new DataModel[array.size()]));
//		
//		int[] output = new int[] { returnedArray[0].getContent(),
//				returnedArray[1].getContent(),
//				returnedArray[2].getContent() };
//		
//		Assert.assertArrayEquals(new int[] {1,2,3}, output);
//		
//		/* Cache | 2nd Memory 
//		 *   1   |    -    
//		 *   2   |        
//		 *   3   |            */
//		
//		array.clear();
//		
//		array.add(new DataModel<Integer>((long) 4, 4));
//		
//		returnedArray = cache.putDataModels(array.toArray(new DataModel[array.size()]));
//		
//		/* Cache | 2nd Memory 
//		 *   2   |    1    
//		 *   3   |       
//		 *   4   |            */
//		
//		array.clear();
//		
//		array.add(new DataModel<Integer>((long) 5, 5));
//		
//		returnedArray = cache.putDataModels(array.toArray(new DataModel[array.size()]));
//		
//		/* Cache | 2nd Memory 
//		 *   3   |    1    
//		 *   4   |    2  
//		 *   5   |            */
//		
//		/* Cache | 2nd Memory 
//		 *   4   |    2    
//		 *   5   |    3  
//		 *   1   |            */
//		
//		returnedArray = cache.getDataModels(new Long[] {(long)1, (long)4, (long)3});
	}
}
