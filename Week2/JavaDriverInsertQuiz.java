/**
 *	puneeth_nn
 *  Jan 20, 2014
 *  12:56:11 PM
 */
package com.tengen;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class JavaDriverInsertQuiz {
	
	public static void main(String args[]) throws UnknownHostException{
	
	  MongoClient client = new MongoClient();
      DB db = client.getDB("school");
      DBCollection people = db.getCollection("people");

      DBObject doc = new BasicDBObject("name", "Andrew Erlichson").append("company", "10gen");

      try {
          people.insert(doc);      // first insert
          doc.removeField("_id");  // remove the "_id" field
          people.insert(doc);      // second insert
      } catch (Exception e) {
          e.printStackTrace();
      }

}
}