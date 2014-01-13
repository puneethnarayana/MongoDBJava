/**
 *	puneeth_nn
 *  Jan 13, 2014
 *  12:57:15 PM
 *  Basic of Mongo Java Driver
 */
package com.tengen;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class HelloWorldMongoDBStyle {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 * Finding a single document from a collection already in the DB. 
	 * DBName: 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
			//Entry to Mongo Cluster, set the server to connect to 
			MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
			//DB Class to DB Name which is to be accessed
			DB database = client.getDB("test");
			//DB Collection name from which to extract the document
			DBCollection collection = database.getCollection("hello");
			//findOne returns the DBObject 
			DBObject doc = collection.findOne();
			System.out.println(doc);
	}

}
