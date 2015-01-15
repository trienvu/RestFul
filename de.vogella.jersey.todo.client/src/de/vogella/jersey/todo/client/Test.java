package de.vogella.jersey.todo.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class Test {
	public static void main(String[] args) {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget service = client.target(getBaseUri());
		// create one todo
		Todo todo = new Todo("6", "Blabla");
		Response response = service
				.path("rest")
				.path("todos")
				.path(todo.getId())
				.request()
				.accept(MediaType.APPLICATION_XML)
				.put(Entity.entity(todo, MediaType.APPLICATION_XML),
						Response.class);
		// Return code should be 201 == created resource
		if (response != null)
			System.out.println(response.getStatus());

		// Get the Todos
		System.out.println(service.path("rest").path("todos").request()
				.accept(MediaType.TEXT_XML).get(String.class));
		// Get JSON for application
		/*
		 * System.out.println(service.path("rest").path("todos").request()
		 * .accept(MediaType.APPLICATION_JSON).get(String.class));
		 */
		 // Get XML for application
	    System.out.println(service.path("rest").path("todos").request()
	        .accept(MediaType.APPLICATION_XML).get(String.class));

	    // Get the Todo with id 1
	    System.out.println(service.path("rest").path("todos/1").request()
	        .accept(MediaType.APPLICATION_XML).get(String.class));
	    // get Todo with id 1
	    service.path("rest").path("todos/1").request().delete();
	    // Get the all todos, id 1 should be deleted
	    System.out.println(service.path("rest").path("todos").request()
	        .accept(MediaType.APPLICATION_XML).get(String.class));

	    // create a Todo
	    Form form = new Form();
	    form.param("id", "4");
	    form.param("summary", "Demonstration of the client lib for forms");
	    response = service.path("rest").path("todos").request()
	        .accept(MediaType.APPLICATION_FORM_URLENCODED)
	        .post(Entity.entity(form, MediaType.APPLICATION_XML),
					Response.class);
	    System.out.println("Form response " + response.getEntity());
	    // Get the all todos, id 4 should be created
	    System.out.println(service.path("rest").path("todos").request()
	        .accept(MediaType.APPLICATION_XML).get(String.class));

	}

	public static URI getBaseUri() {
		return UriBuilder.fromUri(
				"http://localhost:8080/de.vogella.jersey.todo").build();
	}
}
