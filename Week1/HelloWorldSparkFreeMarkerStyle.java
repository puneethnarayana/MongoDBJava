/**
 *	puneeth_nn
 *  Jan 13, 2014
 *  4:53:40 PM
 */
package com.tengen;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldSparkFreeMarkerStyle {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldFreeMarkerStyle.class,"/");
		
		MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
		//DB Class to DB Name which is to be accessed
		DB database = client.getDB("course");
		//DB Collection name from which to extract the document
		final DBCollection collection = database.getCollection("hello");
		
		Spark.get(new Route("/") {
		
			public Object handle(final Request request,final Response response) {
				
				StringWriter writer = new StringWriter();
				
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					//System.out.println(helloMap.get("name"));
					
					DBObject doc = collection.findOne();
					
					helloTemplate.process(doc, writer);
					//System.out.println(writer);
										
				} catch (Exception e) {
					// TODO: handle exception
					halt(500);
					e.printStackTrace();
				}
				return writer;
			}
		});

	}

}
