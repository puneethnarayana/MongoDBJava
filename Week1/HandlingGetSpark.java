/**
 *	puneeth_nn
 *  Jan 13, 2014
 *  5:27:43 PM
 */
package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class HandlingGetSpark {

	/**
	 * Handling Route Parameters
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Spark.get(new Route("/") {
			public Object handle(final Request request,final Response response) {
				return "Hello World from Spark";
			}
		});
		Spark.get(new Route("/test") {
			@Override
			public Object handle(final Request request,final Response response) {
				return "This is a test page";
			}
		});
		
		//Dynamic handling , Wild Carding
		Spark.get(new Route("/echo/:thing") {
			public Object handle(final Request request,final Response response) {
				return request.params(":thing");
			}
		});

	}

}
