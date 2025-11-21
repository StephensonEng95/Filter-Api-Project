package com.stephenson.filter.filter_library.logicalfilters;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.stephenson.filter.filter_library.core.Filter;
 
/**
 * OrFilter, implements the Composite Pattern (structural).
 * 
 * Combines multiple filters using OR logic.
 *  
 * Evaluates to false only if all contained filters return false.
 */

public class OrFilter implements Filter{
	private final List<Filter> filters;
	
	public OrFilter(Filter...filters) {
		/*
		 * Use of varargs (Filter...filters) to construct the instance 
		 * 
		 * of OrFilter with any filters
		 */
		this.filters = Arrays.asList(filters);
	}
	
	/*
	 
	 *isMatch method checks if any sub-filter matches resource
	 
	 *returns true immediately if any filter matches, otherwise it returns false.  
	 
	 */
	
	@Override
	public boolean isMatch(Map<String, String> user) 
	{
		for(Filter filter : filters) {
			if(filter.isMatch(user)) {
				return true;
			}
		}
		return false; // no filter matched
	
		
	}

	@Override 
	public String asString() 
	{
		
		StringBuilder sb = new StringBuilder("OrFilter(");
		
		for(int i=0; i<filters.size(); i++) {
			sb.append(filters.get(i).asString());
			if(i < filters.size()-1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
		
		 
	}

}
