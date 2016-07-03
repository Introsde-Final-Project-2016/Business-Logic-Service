package introsde.rest.business;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONObject;

// this class takes current life status and sending it to take BMI value and
// suggested weights
public class Compare {
	

     public static String[] getCompare() throws Exception {
         
    	 List<LifeStatus> list = LifeStatusImp.getAllLifes();
    	 
    	 Double weight = Double.parseDouble(list.get(0).getValue());
    	 Double height = Double.parseDouble(list.get(1).getValue());
    	 // get bmi and related values
    	 String result = ExternalBmi.getBmi(weight,height);
    	 String check;

       	 JSONObject obj = new JSONObject(result);
       	 // take ideal weight object
		 String idealweight = obj.getString("ideal_weight");
		 
		  String x="";
		  String y="";
	    
		  // parsing the string because it is originally like this "66.9kg to 90.8kg" 
			String[] tokens = idealweight.split("[ to ]+");
	        String[] tokens2 = tokens[0].split("[kg]+");
	          x=tokens2[0];
	    	String[] tokens3 = tokens[1].split("[kg]+");
	          y=tokens3[0];
	 
	          System.out.println("first one"+x);
	          System.out.println("second one"+y);

	    double minValue = Double.parseDouble(x);
	    double maxValue = Double.parseDouble(y);
	    
	    //get bmi object value
	    String bmiValue = obj.getJSONObject("bmi").getString("value");
   	    String statue;
   	    
   	    //decide if user has extra weight or less 
   	 if (weight < minValue)
		 	{
			 check = String.format("%.0f", (minValue-weight));
		 	 statue = "L";
		 	}
		  
		 else if (weight > maxValue) 
		 {
			 check = String.format("%.0f", (weight-maxValue));
			 statue = "M";
		 }
		 else
		 {
		    check= "0";
		    statue = "O";
		 }
	    	
   	 		// to send the all necessary information to the next layer
   	      String[] body = new String[5];
		  body[0]= x;
		  body[1]= y;
		  body[2]= bmiValue;
		  body[3]= statue;
		  body[4]= check;
		  
		 
		 
		 return body;
	
}
}
