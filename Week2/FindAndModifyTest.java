/**
 *	puneeth_nn
 *  Jan 20, 2014
 *  4:47:55 PM
 */
package com.tengen;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class FindAndModifyTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		DBCollection collection = createCollection();
		collection.drop();
		
		final String counterID = "abc";
		int first;
		int numNeeded;
		
		numNeeded = 2;
		first = getRange(counterID,numNeeded,collection);
		System.out.println("Range:"+first+"-"+(first+numNeeded-1));
		
		numNeeded = 3;
		first = getRange(counterID,numNeeded,collection);
		System.out.println("Range:"+first+"-"+(first+numNeeded-1));
		
		numNeeded = 10;
		first = getRange(counterID,numNeeded,collection);
		System.out.println("Range:"+first+"-"+(first+numNeeded-1));
		
		System.out.println();
		
		printCollection(collection);
	}


/**
	 * @param counterID
	 * @param numNeeded
	 * @param collection
	 * @return
	 */
	private static int getRange(String id, int range,
			DBCollection collection) {
		// TODO Auto-generated method stub
		
		DBObject doc = collection.findAndModify(new BasicDBObject("_id",id), null, null, false, 
				new BasicDBObject("$inc", new BasicDBObject("counter",range)), true,true);
		return (Integer) doc.get("counter")-range+1;
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

	return db.getCollection("FindAndModify");
}

}