package de.vogella.jersey.jaxb.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class Test {

	public static void main(String[] args) {
		try {
			ClientConfig clientConfig = new ClientConfig();
			Client client = ClientBuilder.newClient(clientConfig);
			WebTarget service = client.target(getBaseURI());
			// Get XML
			System.out.println(service.path("rest").path("todo").request()
					.accept(MediaType.TEXT_XML).get(String.class));
			// Get XML for application
			System.out.println(service.path("rest").path("todo").request()
					.accept(MediaType.APPLICATION_JSON).get(String.class));
			// Get JSON for application
			System.out.println(service.path("rest").path("todo").request()
					.accept(MediaType.APPLICATION_XML).get(String.class));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/com.vogella.jersey.jaxb").build();
	}

}
