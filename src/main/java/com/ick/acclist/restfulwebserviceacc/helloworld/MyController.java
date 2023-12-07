package com.ick.acclist.restfulwebserviceacc.helloworld;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class MyController {

    private final TestQuery myService;
    private final  JdbcTemplate jdbcTemplate;

    @Autowired
    public MyController(TestQuery myService,JdbcTemplate jdbcTemplate) {
        this.myService = myService;
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @GetMapping(path="/test222") 
	public  Map <String, Object>  helloWorld2() {
    	Map <String, String>  queryMap= new HashMap<>();
    	queryMap.put("website_tracking", "Select * from website_tracking t1 Limit 10" );
    	queryMap.put("website1", "Select * from website_tracking t1 Limit 10" );    	
		return myService.runQury(queryMap);
	}
    
    @GetMapping(path="/test333") 
   	public  Map <String, Object>  helloWorld3() {
       	Map <String, String>  queryMap= new HashMap<>();
       	queryMap.put("website_tracking", "Select * from website_tracking t1 Limit 10" );
       	queryMap.put("website1", "Select * from website_tracking t1 Limit 10" );    	
   		return myService.runQuryMT(queryMap);
   	}
    
    @GetMapping(path="/test444")
    public Map<String, Object> multiTasking(){
    	TestQuery  myService1  = new TestQuery(jdbcTemplate);
    	Map <String , String> queryMap =new HashMap<>(); 
    	queryMap.put("sleep_5_1", "SELECT 5 , SLEEP(0.5)");
    	queryMap.put("sleep_10", "SELECT 10 , SLEEP(01)");
    	queryMap.put("sleep_5_2", "SELECT 5 , SLEEP(0.5)");
    	queryMap.put("sleep_5_3", "SELECT 5 , SLEEP(0.5)"); 
    	return myService1.runQuryMT(queryMap);
    }
    // Your controller methods go here

}
