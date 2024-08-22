package demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Defines this application as a Restful web service. This class will be the entry point when the request is received.
@RequestMapping("/rest") // Class level mapping. Every request on this controller should be mapped to class level mapping first
//This specifies all our endpoints in class have mapping -  http://localhost:8080/rest
public class Controller 
{
	// A endpoint to get the greeting. It is a get mapping and url will be : http://localhost:8080/rest/Msg
	@GetMapping("/Msg")
	public String greeting()
	{
		return "College Spring Security";
	}

	// A endpoint to get the public greeting. It is a get mapping and url will be : http://localhost:8080/rest/public/msg
	@GetMapping("/public/Msg")
	public String greetingPublic()
	{
		return "Public level message. Open to all";
	}
	
}
