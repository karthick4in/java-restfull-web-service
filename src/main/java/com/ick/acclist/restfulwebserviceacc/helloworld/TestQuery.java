package com.ick.acclist.restfulwebserviceacc.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

@RestController
@Repository
public class TestQuery {
	private Map<String, Object>  queryObMap = new HashMap<>();
    private final JdbcTemplate jdbcTemplate ;
//
    @Autowired
    public  TestQuery(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@GetMapping(path="/tes2")
    public Map <String, Object>  performCustomQuery() {
        String sql = "SELECT t1.*, t2.* " +
                     "FROM table1 t1 " +
                     "JOIN table2 t2 ON t1.id = t2.table1_id";
          sql = "SELECT t1.* " +
                "FROM website_tracking t1 Limit 10000 ";
         List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
         
         Map<String, Object> queryObj = new HashMap<>();
         queryObj.put("jbo_model_products", data);
         
         Map <String, Object> res = new HashMap<>();
         res.put("Result", "Success");
         res.put("status", "OK");
         res.put("data", queryObj);
         return res;
    }
	
	
	public Map <String, Object>  runQury(Map  <String, String>  queryMap ) {  
      
	      Map<String, Object> queryObj = new HashMap<>();
	      queryMap.entrySet().stream().forEach(e->{
	    	  
	    	  String string = e.getValue();
	    	  List<Map<String, Object>> data = jdbcTemplate.queryForList(string);  
	    	  queryObj.put(e.getKey(), data);
	      });
	     
	     Map <String, Object> res = new HashMap<>();
	     res.put("Result", "Success");
	     res.put("status", "OK");
	     res.put("data", queryObj);
	     return res;
	 }
	 
	 // Synchronized method to safely update the shared variable
    private synchronized   void addToFinalResult(String  key, Object data) { 
        this.queryObMap.put(key, data);
    }
    
	public Map <String, Object>  runQuryMT(Map  <String, String>  queryMap ) {   
//		Map<String, Object>  queryObMap = new HashMap<>();
		 queryObMap = new HashMap<>();
	      List<Thread> threadsList = new ArrayList<>();
	      
	      queryMap.entrySet().stream().forEach(e->{
	    	 Thread t1 = new Thread(new Runnable() {				
				@Override
				public void run() {
					// TODO Auto-generated method stub
				 String string = e.getValue();
		    	  List<Map<String, Object>> data = jdbcTemplate.queryForList(string);  
		    	  addToFinalResult(e.getKey(), data);
//		    	  queryObMap.put(e.getKey(), data);
		    	  System.out.println("-----------------------------");
		    	  System.out.println(e.getKey());
				}
			});
	    	 t1.start();
	    	 threadsList.add(t1); 
	      });
	       
	     threadsList.forEach((Thread e ) ->{
	    	 try {
	    	 e.join();
	    	 }catch(InterruptedException err) {
	    		 err.printStackTrace();
	    	 }
	     });
	     
	     Map <String, Object> res = new HashMap<>();
	     if(queryObMap.size() == queryMap.size())
	    	{
	    	 res.put("Result", "Success");
	    	 res.put("status", "OK");
		     	}else
	    	 {
	    		res.put("Result", "Failed");
	    		res.put("status", "NOK");
	   	     }
	     res.put("queryObMap", queryObMap.size()); 
	     res.put("queryMap", queryMap.size());
	     res.put("data", queryObMap);
	     return res;
	 }
	
	
	 public Map <String, Object>  runQury(String sql1) {
	        String sql = "SELECT t1.*, t2.* " +
	                     "FROM table1 t1 " +
	                     "JOIN table2 t2 ON t1.id = t2.table1_id";
	          sql = "SELECT t1.* " +
	                "FROM website_tracking t1 Limit 10000 ";
	          sql = sql1;
	         List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
	         
	         Map<String, Object> queryObj = new HashMap<>();
	         queryObj.put("jbo_model_products", data);
	         
	         Map <String, Object> res = new HashMap<>();
	         res.put("Result", "Success");
	         res.put("status", "OK");
	         res.put("data", queryObj);
	         return res;
	    }
	 
}
