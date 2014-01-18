/**
 * puneeth
 * 9:00:30 AM
 * Jan 18, 2014
 */
package mongodb;

import java.util.Arrays;
import java.util.Date;

import com.mongodb.BasicDBObject;

/**
 * @author puneeth
 *
 */
public class DocumentRepresentationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creating Document-Order matters
		BasicDBObject doc = new BasicDBObject();
		doc.put("username", "NP");
		doc.put("birthdate", new Date(123123));
		doc.put("RnD", true);
		doc.put("age", 24);
		doc.put("languages", Arrays.asList("Java","C","C++"));
		doc.put("address",new BasicDBObject("house no.","116")
		.append("street","2DC").append("area","Banaswadi").append("City","Bengaluru").append("ZIP", "560100"));
			
		
	}

}
