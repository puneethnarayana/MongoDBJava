/**
 *	puneeth_nn
 *  Jan 20, 2014
 *  5:39:02 PM
 */
package com.tengen;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.Base64Codec;

public class Hw22 {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		DBCollection collection = createCollection();
		int count=0;
		int counter =0;
		int lastStudentID = 0,lastScore = 0;
	//	DBCursor doc = collection.find().sort(new BasicDBObject("student",1).append("score",1));
		//printCollection(collection);
		System.out.println(collection.count());
	//	for(int i=0;i<3;i++){
		DBCursor cursor = collection.find().sort(new BasicDBObject("student_id",1).append("score",-1));
		while(cursor.hasNext()){

			DBObject prev = new BasicDBObject();
		if(counter==0){
		 prev = cursor.next();}
		else{
			
			 prev = cursor.curr();
		}
	
		Integer prevStudentID = (Integer) prev.get("student_id");
		Double prevScore = (Double) prev.get("score");
		DBObject pres = cursor.next();
		Integer presStudentID = (Integer) pres.get("student_id");
		Double presScore = (Double) pres.get("score");
		
		System.out.println("Prev"+prevScore+":"+prevStudentID+"Pres"+presScore+":"+presStudentID);
		
		if(presStudentID > prevStudentID){
			collection.remove(new BasicDBObject("student_id",prevStudentID).append("score", prevScore));
			System.out.println("Deleting"+prevStudentID+":"+prevScore);
			count++;
		}
		
	
		counter++;
		}
		
		
		collection.remove(new BasicDBObject("student_id",lastStudentID).append("score", lastScore));
		
		//printCollection(collection);
	System.out.println(collection.count()+":"+count);
	//	}
		
/*
		Integer studentID = (Integer) collection.findOne().get("student_id");
		Double score = (Double) collection.findOne().get("score");
		System.out.println(collection.findOne());
		System.out.println(studentID);
		System.out.println(score);*/
	}

	
	/**
	 * @param collection
	 */
	private static void printCollection(DBCollection collection) {
		// TODO Auto-generated method stub
		//DBCursor cursor = collection.find();
		DBCursor cursor = collection.find().sort(new BasicDBObject("student_id",1).append("score",-1));
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
		DB db = client.getDB("students");

		return db.getCollection("grades");
	}
}
