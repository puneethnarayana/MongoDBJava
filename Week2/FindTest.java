/**
 *	puneeth_nn
 *  Jan 20, 2014
 *  1:01:03 PM
 */
package com.tengen;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class FindTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub

		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		DBCollection collection = db.getCollection("findTest");
		collection.drop();


		for(int i=0;i<1000;i++){
			collection.insert(new BasicDBObject("x",new Random().nextInt(100)));	    	  
		}


		System.out.println("FindOne");

		DBObject doc = collection.findOne();

		System.out.println(doc);

		System.out.println("Find All");

		DBCursor cursor = collection.find();

		try {
		while(cursor.hasNext()){
			
			DBObject cur = cursor.next();
			System.out.println(cur);
		}
		} finally {
			// TODO: handle exception
			cursor.close();
		}
		
		
		System.out.println("count");
		
		long count = collection.count();
		
		System.out.println(count);
	}	

}
