/**
 *	puneeth_nn
 *  Jan 13, 2014
 *  2:31:01 PM
 */
package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HelloWorldSparkStyle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Default Port for spark is 4567
		//Calling a static method of spark class i.e get and giving it a route.
		//Route specifies the pattern that should match
		Spark.get(new Route("/") {
			//So when "/" is encountered, i.e. home. It calls the handle method
			//Handle method returns "Hello World from Spark" as the Response to the get request
			@Override
			public Object handle(final Request arg0,final Response arg1) {
				// TODO Auto-generated method stub
				return "Hello World from Spark";
			}
		});

	}

}
