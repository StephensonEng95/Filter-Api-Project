package com.stephenson.filter.filter_library.propertyfilters;

import java.util.Map;



import com.stephenson.filter.filter_library.core.Filter;

/**
 
 * EqualFilter is implemented as record for immutability
 * 
 * It checks if a given property in a resource matches expected criteria (case insensitive)
 * 
 * Throw error for empty or null user resource and expected criteria
  
 */ 

public record EqualFilter(String property, String expectedCriteria)implements Filter {
	
	public EqualFilter{ //compact constructor in records to handle constructor exceptions
		if(property == null || property.isEmpty()) {
			throw new IllegalArgumentException("property can't be null nor empty");
		}
		if(expectedCriteria == null || expectedCriteria.isEmpty()) {
			throw new IllegalArgumentException("expectedCriteria can't be null nor empty");
		}    
	}
	@Override
	public boolean isMatch(Map<String,String> user) 
	{     
		if(user == null || user.isEmpty()) {
			throw new IllegalArgumentException("user's resource can't be null nor empty");
		}  
		String value = user.get(property);
		return value == null?false: !value.isEmpty() && value.equalsIgnoreCase(expectedCriteria);
	} 

	@Override
	public String asString() 
	{
		return "Equal(" + property +" "+ "=" +" "+ expectedCriteria + ")";
	}

}
