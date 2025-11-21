package com.stephenson.filter.filter_library.booleanfilters;

import java.util.Map;


import com.stephenson.filter.filter_library.core.Filter;

/**
 * TrueFilter always returns true
 */
public class TrueFilter implements Filter {

	@Override
	public boolean isMatch(Map<String, String> user) 
	{
		
		return true;
	}
  
	@Override
	public String asString()
	{
		
		return "TrueFilter";
	}

}
 