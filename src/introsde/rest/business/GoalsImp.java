package introsde.rest.business;

import java.util.List;


import introsde.rest.business.LifeStatusImp;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.xml.ws.Holder;

import introsde.rest.business.Goals;
import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response;

import java.io.PrintWriter;
import java.net.URI;
import java.util.List;

public class GoalsImp{

		// this class gets all goals or goal by id 
		// by connecting the storage layer
      
       static ClientConfig clientConfig = new ClientConfig();
       static Client client = ClientBuilder.newClient(clientConfig);
       static WebTarget service = client.target(getBaseURI());
       static javax.ws.rs.core.Response response;
           
       private static URI getBaseURI() {
                return UriBuilder.fromUri("http://10.218.206.53:5700/introsde/user/getGoals").build();
            }

       public static List<Goals> getGoalList() throws Exception {
            response = service
    				.request()
    				.accept(MediaType.APPLICATION_JSON)
    				.get();
                		
    		String body = response.readEntity(String.class);
    		JSONArray personArray = new JSONArray(body);
    		List<Goals> glist = new ArrayList<Goals>();
    		for(int i=0; i<personArray.length();i++)
    		{
    			Goals g= new Goals();
    			g.setGoalName(personArray.getJSONObject(i).getString("goalName"));
    			g.setGoalValue(personArray.getJSONObject(i).getString("goalValue"));
    		    g.setIdGoal(personArray.getJSONObject(i).getInt("idGoal"));
    		    g.setMeasureType(personArray.getJSONObject(i).getString("measureType"));
    		    glist.add(g);
    		}
    		return glist;
       }   
       
       public static Goals getGoalById(int gid) throws Exception {
    	   List<Goals> goalList = GoalsImp.getGoalList();
    	   Goals g = new Goals();
    	   for(int i=0; i<goalList.size();i++)
   				{
    	   if(goalList.get(i).getIdGoal()==gid)
    	   { 
    		   g = goalList.get(i);
    	   }
   				}
    	   return g;
       }
}
