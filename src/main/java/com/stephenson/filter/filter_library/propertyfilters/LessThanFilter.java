package com.stephenson.filter.filter_library.propertyfilters;

import java.util.Map;



import com.stephenson.filter.filter_library.core.Filter;

/** 
 *LessThanFilter implemented as record for immutability
 *
 *Checks if numeric property in the resource is less than expected criteria
 *
 *Throw error for empty or null user resource and expected criteria
 */

public record LessThanFilter(String property, String expectedCriteria) implements Filter{
	public LessThanFilter{
		if(property == null || property.isEmpty()) {
			throw new IllegalArgumentException("property can't be null nor empty");
		}
		if(expectedCriteria == null || expectedCriteria.isEmpty()) {
			throw new IllegalArgumentException("expectedCriteria can't be null nor empty");
		}

	}
	  
	@Override
	public boolean isMatch(Map<String, String> user)
	{
		if(user == null || user.isEmpty()) {
			throw new IllegalArgumentException(" user resource can't be null nor empty");
		} 
		String val = user.get(property); 
		if ( val == null) {
			return false; 
		}   
		try{ //try-catch block handling parsing exception
			double actualValue = Double.parseDouble(val);  //using double to handle both integers and decimal values
			double expectedValue = Double.parseDouble(expectedCriteria);
			return actualValue < expectedValue;
			} catch(NumberFormatException e) {
				return false; ///returns false for non-numeric value
			}
	}
	
	@Override
	public String asString() 
	{
		return "LessThanFilter(" + property +" "+ "<" +" "+ expectedCriteria + ")";
	}

} 
