/**
 *	puneeth_nn
 *  Jan 13, 2014
 *  5:14:25 PM
 */
package com.tengen;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldMongoSparkFreeMarkerStyle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(
				HelloWorldFreeMarkerStyle.class,"/");
		
		Spark.get(new Route("/") {
		
			public Object handle(final Request request,final Response response) {
				
				StringWriter writer = new StringWriter();
				
				try {
					Template helloTemplate = configuration.getTemplate("hello.ftl");
					
					Map<String, Object> helloMap = new HashMap<String, Object>();
					helloMap.put("name","Puneeth");
					//System.out.println(helloMap.get("name"));
					helloTemplate.process(helloMap, writer);
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
