package introsde.rest.business;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import org.json.*;

import introsde.rest.business.ExternalBmi;

@Stateless // Used only if the the application is deployed in a Java EE container
@LocalBean // Used only if the the application is deployed in a Java EE container
@Path("/user")


public class Resources {
	
	//ask for bmi by weight and height
	@GET
    @Produces({ MediaType.APPLICATION_JSON})
    @Path("/getBmi/{weight}/{height}")
    public Response getBmi(@PathParam("weight") double weight, @PathParam("height") double height) throws Exception {
        String result = ExternalBmi.getBmi(weight,height);
        if (result != null) {
            System.out.println("Getting the info of the BMI...");
            return Response.ok(result).build();
        }
        else {
            return Response.status(404).build();
        }
    }
	
	//compare and return the bmi and extra weight 
	@GET
    @Produces({ MediaType.APPLICATION_JSON})
    @Path("/compare")
    public Response getInfo() throws Exception {
        String[] result = Compare.getCompare();
        if (result != null) {
            System.out.println("Getting the info of the BMI...");
//            System.out.println(result);
            return Response.ok(result).build();
        }
        else {
            return Response.status(404).build();
        }
    }
	
	// get quote
	@GET
    @Produces({ MediaType.APPLICATION_JSON})
    @Path("/getQuote")
    public Response getQuotes() throws Exception {
        String result = Quote.getQuote();
        if (result != null) {
            System.out.println("Getting the info of the BMI...");
            System.out.println(result);
            return Response.ok(result).build();
        }
        else {
            return Response.status(404).build();
        }
    }
	
		// get user details
	    @GET
	    @Produces({ MediaType.APPLICATION_JSON})
	    @Path("/getDetail")
	    public Response getPersonDetail() throws Exception {
	        
	    	Person user = PersonImp.getUserDetail();
	        if (user != null) {
	            System.out.println("Getting the info of the user...");
	            return Response.ok(user).build();
	        }
	        else {
	            return Response.status(404).build();
	        }
	    }
	  
	    // get goals
		@GET
	    @Produces({ MediaType.APPLICATION_JSON})
	    @Path("/getGoals")
	    public Response getAllGoals() throws Exception {
	        List<Goals> glist = GoalsImp.getGoalList();
	        if (glist != null) {
	            System.out.println("Getting the goalll of the user...");
	            return Response.ok(glist).build();
	        }
	        else {
	            return Response.status(404).build();
	        }

	    }
	    
		//get goal by id
	    @GET
	    @Produces({ MediaType.APPLICATION_JSON})
	    @Path("/goalId/{goalId}")
	    public Response getGoalById(@PathParam("goalId")int gid) throws Exception {
	    	Goals g = GoalsImp.getGoalById(gid);
	        if (g != null) {
	            System.out.println("Getting the info of the user...");
	            return Response.ok(g).build();
	        }
	        else {
	            return Response.status(404).build();
	        }

	    }
	    
	    // get life status by id
	    @GET
	    @Produces({ MediaType.APPLICATION_JSON})
	    @Path("/getLS/{lsId}")
	    public Response getLsById(@PathParam("lsId") int lsid) throws Exception {
	    	LifeStatus ls = LifeStatusImp.getLsById(lsid);
	        if (ls != null) {
	            System.out.println("Getting the info of the user...");
	            return Response.ok(ls).build();
	        }
	        else {
	            return Response.status(404).build();
	        }

	    }
	    
	    // get all lifestatus
	    @GET
	    @Produces({ MediaType.APPLICATION_JSON})
	    @Path("/getLifeStatus")
	    public Response getAllLifes() throws Exception {
	        List<LifeStatus> lslist = LifeStatusImp.getAllLifes();
	        if (lslist != null) {
	            System.out.println("Getting the info of the user...");
	            return Response.ok(lslist).build();
	        }
	        else {
	            return Response.status(404).build();
	        }

	    }
	
}
       
