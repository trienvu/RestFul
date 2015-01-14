package com.vogella.jersey.jaxb;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.process.internal.RequestScoped;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
	// This method is called if XMLis request
	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public ToDo getXML() {
		ToDo todo = new ToDo();
		todo.setSummary("This is my first todo");
		todo.setDescription("This is my first todo");
		return todo;
	}

	// This method is called if JSON is request
	
	@GET
	@RequestScoped
	@Produces({ MediaType.APPLICATION_JSON })
	public ToDo getJson() {
		ToDo todo = new ToDo();
		todo.setSummary("This is my first todo");
		todo.setDescription("This is my first todo");
		return todo;
	}

	// This can be used to test the integration with the browser
	@GET
	@Produces({ MediaType.TEXT_XML })
	public ToDo getHTML() {
		ToDo todo = new ToDo();
		todo.setSummary("This is my first todo");
		todo.setDescription("This is my first todo");
		return todo;
	}
}
