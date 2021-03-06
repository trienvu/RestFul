package com.springservicesample;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/greeting")
public class SpringServiceController {
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getGreeting(@PathVariable String name) {
		String result = "Hello " + name;
		return result;
	}

	@RequestMapping("/trien")
	public String getTrien(@RequestParam(value = "name") String name) {
		return name;
	}
}