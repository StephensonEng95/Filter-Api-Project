package com.stephenson.filter.filter_library.propertyfilters;

import java.util.Map;

import java.util.regex.Pattern;

import com.stephenson.filter.filter_library.core.Filter;

/**

 * RegexFilter checks if the value of a property matches a given regular expression
 * 
 * throw error if user resource or regex are empty or null
 
 */

public record RegexFilter(String property, String regex) implements Filter{
    
	public RegexFilter{
		if(property == null || property.isEmpty()) {
			throw new IllegalArgumentException("property can't be null nor empty");
		}
		if(regex == null || regex.isEmpty()) {
			throw new IllegalArgumentException("regex can't be null nor empty");
		}
	}
	/* 
	 * checks if given resource's property macthes the regex
	    
	 * return false if property is missing or value doesn't match
	 
	 */  
	
	@Override
	public boolean isMatch(Map<String, String> user) 
	{
		if(user == null || user.isEmpty()) {
			throw new IllegalArgumentException(" user resource can't be null nor empty");
		} 
		String value = user.get(property);
		if(value == null || value.isEmpty()) {
			return false;
		}
		return Pattern.compile(regex).matcher(value).matches(); //value matches regex  
		 
	}   

	@Override   
	public String asString() 
	{
		
		return "RegexFilter(" + property + "matches \"" + regex + "\")";
	}

}
