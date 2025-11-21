package com.stephenson.filter.filter_library.booleanfilters;

import java.util.Map;


import com.stephenson.filter.filter_library.core.Filter;

/**
 * FalseFilter always returns false
 */

public class FalseFilter implements Filter {

	@Override
	public boolean isMatch(Map<String, String> user)
	{
 
		return false; 
		 
	}

	@Override
	public String asString() 
	{ 

		return "FalseFilter";
	}

}
