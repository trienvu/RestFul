package com.springrestservicesample.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springrestservicesample.domain.User;
import com.springrestservicesample.reposity.UserService;

@RestController
@RequestMapping("/service/user/")
public class SpringServiceController {
	Logger log = Logger.getGlobal();

	// UserService userService = new UserService();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User getUser(@PathVariable int id) {
		// User user = userService.getUserById(id);
		log.info("One user");
		User user = new User();
		user.setEmail("xx");
		user.setFirstName("22");
		user.setLastName("33");
		user.setUserid(111);
		return user;
	}

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getAllUsers() {
		log.info("All user");
		// List<User> users = userService.getAllUsers();
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setEmail("xx");
		user.setFirstName("22");
		user.setLastName("33");
		user.setUserid(111);
		users.add(user);
		return users;
	}

}