/**
 *	puneeth_nn
 *  Jan 20, 2014
 *  3:52:43 PM
 */
package com.tengen;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class UpdateTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		DBCollection collection = createCollection();
		collection.drop();
		List<String> names = Arrays.asList("Harvard","MIT","CalTech","Stanford");
		for (String name : names) {
			collection.insert(new BasicDBObject("_id",name));
		}

		printCollection(collection);
		collection.update(new BasicDBObject("_id","Harvard"),new BasicDBObject("Age",225));
		printCollection(collection);

		collection.update(new BasicDBObject("_id","Harvard"),new BasicDBObject("$set",new BasicDBObject("Alma Mater",true)));
		collection.update(new BasicDBObject(),new BasicDBObject("$set",new BasicDBObject("Title","Ivy")),false,true);
		
		printCollection(collection);

	}

	/**
	 * @param collection
	 */
	private static void printCollection(DBCollection collection) {
		// TODO Auto-generated method stub
		DBCursor cursor = collection.find();
		try{
			while(cursor.hasNext()){
				DBObject cur = cursor.next();
				System.out.println(cur);
			}
		}
		finally{
			cursor.close();
		}

	}

	/**
	 * @return
	 * @throws UnknownHostException 
	 */
	private static DBCollection createCollection() throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");

		return db.getCollection("updateTest");
	}

}
