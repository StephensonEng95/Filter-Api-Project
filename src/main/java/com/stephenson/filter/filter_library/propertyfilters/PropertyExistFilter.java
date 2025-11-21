package com.stephenson.filter.filter_library.propertyfilters;

import java.util.Map;

import com.stephenson.filter.filter_library.core.Filter;

/**
 * 
 * PropertyExistFilter returns true if user's resource contains the property 
 * 
 * and property's value isn't null or empty
 * 
 * Throw error if user's resource or expected criteria are empty or null
 * 
 */  
public record PropertyExistFilter(String property) implements Filter{
    
	public PropertyExistFilter{
		if(property == null || property.isEmpty()) {
			throw new IllegalArgumentException("property can't be null nor empty");
		}
	}   
	
	@Override
	public boolean isMatch(Map<String, String> user)
	{
		
		if(user == null || user.isEmpty()) {
			throw new IllegalArgumentException(" user resource can't be null nor empty");
		} 
		return user.containsKey(property) && user.get(property)!=null && !user.get(property).isEmpty();
	}  

	@Override      
	public String asString() 
	{
		
		return "PropertyExistFilter(" + property +" " + "exist )";
	}

}
  