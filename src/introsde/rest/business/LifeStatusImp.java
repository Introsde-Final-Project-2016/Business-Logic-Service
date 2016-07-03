package introsde.rest.business;

import java.util.List;
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

public class LifeStatusImp{

      // this class gets all Lifestatus or one by id
	  // by connecting to storage layer
       static ClientConfig clientConfig = new ClientConfig();
       static Client client = ClientBuilder.newClient(clientConfig);
       static WebTarget service = client.target(getBaseURI());
       static javax.ws.rs.core.Response response;
           
       
       private static URI getBaseURI() {
                return UriBuilder.fromUri("https://afternoon-bayou-12442.herokuapp.com/introsde/user/getLifeStatus").build();
            }

       public static List<LifeStatus> getAllLifes() throws Exception {
            response = service
    				.request()
    				.accept(MediaType.APPLICATION_JSON)
    				.get();
    		
            // this case list.class is working intrestingly
//            List<LifeStatus> list = response.readEntity(List.class);
//            return list;
    		String body = response.readEntity(String.class);
    		JSONArray lsArray = new JSONArray(body);
    		List<LifeStatus> lflist = new ArrayList<LifeStatus>();
    		for(int i=0; i<lsArray.length();i++)
    		{
    			LifeStatus l= new LifeStatus();
    			l.setValue(lsArray.getJSONObject(i).getString("value"));
    		    l.setIdMeasure(lsArray.getJSONObject(i).getInt("idMeasure"));
    		    l.setMeasureType(lsArray.getJSONObject(i).getString("measureName"));
    		    lflist.add(l);
    		}
    		return lflist;
            
       }   
       
       public static LifeStatus getLsById(int gid) throws Exception {
    	   List<LifeStatus> list = LifeStatusImp.getAllLifes();
    	   LifeStatus l = new LifeStatus();
    	   System.out.println("listsize"+list.size());
    	   for(int i=0; i<list.size();i++)
   				{
    	   if(list.get(i).getIdMeasure()==gid)
    	   { 
    		   l = list.get(i);
    	   }
   				}
    	   return l;
       }
}
