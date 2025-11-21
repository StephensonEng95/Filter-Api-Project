package com.stephenson.filter.filter_library.logicalfilters;

import java.util.Map;


import com.stephenson.filter.filter_library.core.Filter;

/**
 * NotFilter, implements the Composite Pattern (structural).
 * 
 * Inverts the result of a single filter using NOT logic.
 *  
 * Evaluates to the opposite of the contained filter.
 */

public class NotFilter implements Filter{
	
	private Filter filter;
	 
	public NotFilter(Filter filter) {
		this.filter = filter;
	}
      
	 /*
     * Here isMatch method inverts the result of the contained filter
     * returns true if the contained filter returns false, and vice versa
     */
	@Override 
	public boolean isMatch(Map<String, String> user) 
	{  
		   
		return !filter.isMatch(user);
	}

	@Override   
	public String asString()
	{
		
		return "NotFilter(" + filter.asString() + ")";
	}

}
