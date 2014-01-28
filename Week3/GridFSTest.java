/**
 *	puneeth_nn
 *  Jan 28, 2014
 *  11:11:33 AM
 */
package com.tengen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.*;

public class GridFSTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MongoClient client = new MongoClient();
		DB db = client.getDB("course");
		FileInputStream inputStream = null;
		
		GridFS videos = new GridFS(db,"videos");
		
		
		try {
			inputStream = new FileInputStream("C:/Users/puneeth_nn/Downloads/Resources/Ontology.wmv");
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("Cant Open File");
			System.exit(1);
		}
		
		GridFSInputFile video = videos.createFile(inputStream,"Ontology.wmv");
		
		//Creating some metadata for the object
		BasicDBObject meta = new BasicDBObject("Description","Ontology lecture");
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("DB");
		tags.add("Education");
		meta.append("tags", tags);
		
		video.setMetaData(meta);
		video.save();
		
		System.out.println("Object ID in files collection"+video.get("_id"));
		
		System.out.println("Retrieving the saved file");
		
		GridFSDBFile gridfile = videos.findOne(new BasicDBObject("filename","Ontology.wmv"));
		
		FileOutputStream outputStream = new FileOutputStream("C:/Users/puneeth_nn/Downloads/Resources/Onto_copy.wmv");
		
		gridfile.writeTo(outputStream);
		
	}

}
