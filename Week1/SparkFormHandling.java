/**
 *	puneeth_nn
 *  Jan 13, 2014
 *  5:52:13 PM
 */
package com.tengen;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Route.*;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SparkFormHandling {

	public static void main(String[] args){
		//FreeMarker Configuration
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldFreeMarkerStyle.class,"/");

		//Configure Routes
		Spark.get(new Route("/") {
			public Object handle(final Request request,final Response response) {
				try {
					Map<String, Object> fruitsMap = new HashMap<String, Object>();
					fruitsMap.put("fruits", Arrays.asList("La Pomme","Oranje","Banana","Peach","Chikoo"));

					Template fruitpickerTemplate = configuration.getTemplate("fruit.ftl");
					StringWriter writer = new StringWriter();
					fruitpickerTemplate.process(fruitsMap, writer);

					return writer;

				} catch (Exception e) {
					// TODO: handle exception
					halt(500);
					return null;
				}
			}
		});


		//Handles Form when it is submitted
		Spark.post(new Route("/favorite_fruit"){

			@Override
			public Object handle(final Request request,final Response response) {
				// TODO Auto-generated method stub
				final String fruit = request.queryParams("fruit");
				if (fruit==null) {
					return "Pick one silt vous plait!";

				} else {
					return "Votre Favorite Fruit est "+fruit;
				}
			}});

	}

}
