/**
 *	puneeth_nn
 *  Jan 20, 2014
 *  12:42:43 PM
 */
package com.tengen;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class InsertTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		
		MongoClient client = new MongoClient();
		
		DB CourseDB = client.getDB("course");
		DBCollection collection = CourseDB.getCollection("insertTest");
		
		DBObject doc = new BasicDBObject("MUFC", -1);
		DBObject doc1 = new BasicDBObject("CFC", 0);
		
		System.out.println(doc);

		//Single document insert
		collection.insert(doc);
		
		//Inserting a bunch of documents as array with a single insert method
		collection.insert(Arrays.asList(doc,doc1));
		
		System.out.println(doc);
		
		

	}

}
