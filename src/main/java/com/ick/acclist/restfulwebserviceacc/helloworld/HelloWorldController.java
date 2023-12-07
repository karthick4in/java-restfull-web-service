package com.ick.acclist.restfulwebserviceacc.helloworld;
 
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController { 
	
	@RequestMapping(method=RequestMethod.GET,path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	} 

	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean( "Hello World Primary");
	}

	@GetMapping(path="/test1")
	public HelloWorldBean helloWorldBean11() {
		return new HelloWorldBean( "Hello World Primary");
	}

//	@GetMapping(path="/tes3")
//    public Map <String, Object>  tes3() {
//		
//		TestQuery exeQue = TestQuery.performCustomQuery();
//	}
	@GetMapping(path="/")
	public String helloWorld2() {
		return "Home page";
	} 
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(
				String.format("Hello World Primary %s", name));
	}
	

}
