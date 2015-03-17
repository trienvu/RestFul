package com.springrestservicesample.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springrestservicesample.domain.User;

@RestController
@RequestMapping("/my")
public class MyController {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET,headers="Accept: text/plain; q=0.5, text/html,text/x-dvi; q=0.8, text/x-c")
	public String getAbc() {
		User user = new User();
		user.setEmail("xx");
		user.setFirstName("xx");
		user.setLastName("xx");
		user.setUserid(11);
		return "xx";
	}

}