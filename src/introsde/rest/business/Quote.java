package introsde.rest.business;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class Quote {

	// this class gets quote from storage
	
	 private static URI getBaseURI() {
        return UriBuilder.fromUri("http://10.218.206.53:5700/introsde/user/getOne").build();
    }

public static String getQuote() throws Exception {
   
	  ClientConfig clientConfig = new ClientConfig();
    Client client = ClientBuilder.newClient(clientConfig);
    WebTarget service = client.target(getBaseURI());
    Response response;
	 
    response = service
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
        		
		  String body = response.readEntity(String.class);
		  //JSONArray personArray = new JSONArray(body);
		  
		  return body;
	
}
}

