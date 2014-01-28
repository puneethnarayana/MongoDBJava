/**
 *	puneeth_nn
 *  Jan 27, 2014
 *  4:10:58 PM
 */
package com.tengen;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class Hw31 {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		
		DBCollection collection = createCollection();
		
		//QueryBuilder query = QueryBuilder.start("scores.score").greaterThan(50);
		
		ArrayList<Double> avgs = new ArrayList<Double>();
		
		for(int stu_id=0;stu_id<200;stu_id++){
		
		DBCursor cursor = collection.find(new BasicDBObject("_id",stu_id));
		
		try{
			while(cursor.hasNext()){
				Scores score = new Scores();
				DBObject cur = cursor.next();
			//	System.out.println(cur);
				DBObject cur1= (DBObject) cur.get("scores");
				BasicDBList studentsList = (BasicDBList) cur.get("scores");
				//System.out.println(studentsList.size());
				//System.out.println(cur1);
				ArrayList<Scores> tempScores = new ArrayList<Scores>();
				ArrayList<Double> Scores_temp = new ArrayList<Double>();
				Map<String, Double> temppy = new HashMap<String, Double>();
				Double tempMin=0.0;
				String tempKey="";
				Double sum=0.0;
				for (int i = 0; i < studentsList.size(); i++) {
					DBObject a= (DBObject) studentsList.get(i);
					//System.out.println(a.get("type"));
					temppy.put((String) a.get("type"),(Double) a.get("score"));
					Scores_temp.add( (Double) a.get("score"));
					while(i==0){
						tempMin=(Double) a.get("score");
						tempKey = (String) a.get("type");
						break;
					
						}
					if((Double) a.get("score")<tempMin){
						tempMin=(Double) a.get("score");
						tempKey = (String) a.get("type");
						
					}
					
						
					sum+=(Double) a.get("score");
					}
				
				
				avgs.add(sum-tempMin);
				System.out.println(stu_id+" Avg:"+(sum-tempMin));	
				//System.out.println(stu_id);
				
			
				//System.out.println("Key :"+tempKey+" Value:"+tempMin);
				
				//System.out.println(cur.get("scores"));
			/*	String keyy="";
				for (Entry<String, Double> entry : temppy.entrySet()) {
		            if (entry.getValue().equals(Collections.min(Scores_temp))) {
		            	System.out.println(Collections.min(Scores_temp));
		                keyy = entry.getKey();
		            }
		        }
				*/
				
				
				/*String key_you_look_for="";
				
				Iterator<Entry<String, Double>> iter = temppy.entrySet().iterator();
				while (iter.hasNext()) {
				    Entry<String, Double> entry = iter.next();
				    if (entry.getValue().equals(Collections.min(Scores_temp))) {
				         key_you_look_for = entry.getKey();
				    }
				}*/
				
				//System.out.println(keyy+" :KEY");
				//BasicDBObject match = new BasicDBObject("_id", ); // to match your document
				BasicDBObject update = new BasicDBObject("scores",
						new BasicDBObject("type",tempKey).append("score",tempMin));
				collection.update(cur, new BasicDBObject("$pull", update));
				//System.out.println(cur);
			}			
			
		}finally{
			cursor.close();
		}	
		}
		
		System.out.println(Collections.max(avgs));
		
	}
	
	
	public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
	     Set<T> keys = new HashSet<T>();
	     for (Entry<T, E> entry : map.entrySet()) {
	         if (value.equals(entry.getValue())) {
	             keys.add(entry.getKey());
	         }
	     }
	     return keys;
	}

	/**
	 * @param collection
	 */
	private static void printCollection(DBCollection collection) {
		// TODO Auto-generated method stub
		//DBCursor cursor = collection.find();
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
		DB db = client.getDB("school");

		return db.getCollection("students");
	}
	
}
