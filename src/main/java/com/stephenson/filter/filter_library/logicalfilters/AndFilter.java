package com.stephenson.filter.filter_library.logicalfilters;
import java.util.*;


import com.stephenson.filter.filter_library.core.Filter;


/** 
 * AndFilter, implements the Composite Pattern (structural).
 * 
 * Combines multiple filters using AND logic.  
 * 
 * Evaluates to true only if all contained filters return true.
 */
public class AndFilter implements Filter{
    
	private final List<Filter> filters;
	
	public AndFilter(Filter...filters) {  
		/*
		 * Use of varargs (Filter...filters) to construct the instance 
		 * 
		 * of AndFilter with any filters
		 */
		this.filters = Arrays.asList(filters); 
	} 
	
	/*
	 
	 *isMatch method checks if all sub-filters matches resource
	 
	 *returns false immediately if any filter fails, otherwise true.*/
	
	@Override
	public boolean isMatch(Map<String, String> user)
	{
		for(Filter filter : filters) {
			if(!filter.isMatch(user)) {
				return false;
			} 
		}
		return true; // all filters matched
	}
	

	@Override 
	public String asString() 
	{   
		StringBuilder sb = new StringBuilder("AndFilter(");
		
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
