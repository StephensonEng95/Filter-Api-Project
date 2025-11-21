package com.stephenson.filter.filter_library.core;

import java.util.Map;

/** Filter interface defining contract for sub filters
  
 * each filter checks if a resource represented as (Map<String,String>resource)
 
 * matches a certain criteria
 
 * Strategy design pattern is chosen, each concrete filter (sub filter) 
 
 * will implement same filter for it's own filter logic
 */

public interface Filter { 
	
	 boolean isMatch(Map<String,String> user);
	 String asString();
	

}
