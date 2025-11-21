package com.stephenson.filter.filter_library.filterstest;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.stephenson.filter.filter_library.booleanfilters.FalseFilter;
import com.stephenson.filter.filter_library.booleanfilters.TrueFilter;
import com.stephenson.filter.filter_library.core.Filter;
import com.stephenson.filter.filter_library.logicalfilters.AndFilter;
import com.stephenson.filter.filter_library.logicalfilters.NotFilter;
import com.stephenson.filter.filter_library.logicalfilters.OrFilter;
import com.stephenson.filter.filter_library.propertyfilters.EqualFilter;
import com.stephenson.filter.filter_library.propertyfilters.GreaterThanFilter;
import com.stephenson.filter.filter_library.propertyfilters.LessThanFilter;
import com.stephenson.filter.filter_library.propertyfilters.PropertyExistFilter;
import com.stephenson.filter.filter_library.propertyfilters.RegexFilter;
    
import java.util.*;

/**
 * Unit tests for the Filter Api
 * 
 * Verifies each filter correctly matches or rejects
 * 
 * resources based on defined criteria
 * 
 * Handles edge cases  
 */
public class FiltersTest {

	Map<String, String> user = new HashMap<>();

	@BeforeEach
	void userSetup() // Set up test user data before each test
	{ 
		user.put("firstname", "Joe");
		user.put("lastname", "Bloggs");
		user.put("role", "Administrator");
		user.put("age", "35");
	}

	@Test
	void testEqualFilter() 
	{
		assertThrows(IllegalArgumentException.class, ()-> new EqualFilter("role",null));
		assertThrows(IllegalArgumentException.class, ()-> new EqualFilter("role", ""));
		Filter equalFilter = new EqualFilter("role", "administrator");
		assertTrue(equalFilter.isMatch(user));
 
		Filter notEqualFilter = new EqualFilter("role", "user");
		assertFalse(notEqualFilter.isMatch(user));
		assertThrows(IllegalArgumentException.class, ()->equalFilter.isMatch(Map.of()));
	    assertThrows(IllegalArgumentException.class, ()->equalFilter.isMatch(null));
	}

	@Test 
	void testGreaterThanFilter() 
	{  
		assertThrows(IllegalArgumentException.class, ()-> new GreaterThanFilter("","40"));
		assertThrows(IllegalArgumentException.class, ()-> new GreaterThanFilter(null,"40"));
		
		assertThrows(IllegalArgumentException.class, () -> new GreaterThanFilter("age", null));
	    assertThrows(IllegalArgumentException.class, () -> new GreaterThanFilter("age", ""));
		Filter greaterFilter = new GreaterThanFilter("age", "30");
		assertTrue(greaterFilter.isMatch(user));

		user.put("age", "25");
		assertFalse(greaterFilter.isMatch(user));
		assertThrows(IllegalArgumentException.class, ()->greaterFilter.isMatch(Map.of()));
	    assertThrows(IllegalArgumentException.class, ()->greaterFilter.isMatch(null));

	    user.put("age", "non numeric");
	    assertFalse(greaterFilter.isMatch(user)); // Should handle NumberFormatException
	}
 
	@Test
	void testLessThanFilter() 
	{
		assertThrows(IllegalArgumentException.class, ()-> new LessThanFilter("","40"));
		assertThrows(IllegalArgumentException.class, ()-> new LessThanFilter(null,"40"));
		
		assertThrows(IllegalArgumentException.class, () -> new LessThanFilter("age", null));
	    assertThrows(IllegalArgumentException.class, () -> new LessThanFilter("age", ""));
		Filter lessFilter = new LessThanFilter("age", "40");
		assertTrue(lessFilter.isMatch(user));
		assertThrows(IllegalArgumentException.class, ()->lessFilter.isMatch(Map.of()));
	    assertThrows(IllegalArgumentException.class, ()->lessFilter.isMatch(null));
	    
	    user.put("age", "xyz");
	    assertFalse(lessFilter.isMatch(user)); // Should handle NumberFormatException
	}
	

	@Test 
	void testPropertyExistFilter()
	{   
		assertThrows(IllegalArgumentException.class, ()-> new PropertyExistFilter(null));
		assertThrows(IllegalArgumentException.class, ()-> new PropertyExistFilter(""));

		Filter propertyExistFilter = new PropertyExistFilter("role");
		assertTrue(propertyExistFilter.isMatch(user));

		Filter notPropertyExistFilter = new PropertyExistFilter("email");
		assertFalse(notPropertyExistFilter.isMatch(user));
		assertThrows(IllegalArgumentException.class, ()->notPropertyExistFilter.isMatch(Map.of()));
		assertThrows(IllegalArgumentException.class, ()->notPropertyExistFilter.isMatch(null));
	}

	@Test
	void testTrueFilter() 
	{
		Filter trueFilter = new TrueFilter();
		assertTrue(trueFilter.isMatch(user));
	}

	@Test
	void testFalseFilter()
	{
		Filter falseFilter = new FalseFilter();
		assertFalse(falseFilter.isMatch(user));
	}

	@Test
	void testAndFilter() 
	{
		// Test case where all are true
		Filter equalFilter = new EqualFilter("role", "administrator");
		Filter greaterFilter = new GreaterThanFilter("age", "30");
		Filter andFilter = new AndFilter(equalFilter, greaterFilter);
		assertTrue(andFilter.isMatch(user));
		user.put("age", "25");
		assertFalse(andFilter.isMatch(user));
	}

	@Test     
	void testOrFilter()  
	{
		// Test case where any is true 
		Filter notEqualFilter = new EqualFilter("role", "user");
		Filter greaterFilter = new GreaterThanFilter("age", "30");
		Filter orFilter = new OrFilter(notEqualFilter, greaterFilter);
		assertTrue(orFilter.isMatch(user));

		// Test case where both role and age values are false
		user.put("age", "25"); // with new value, both filters return false
		assertFalse(orFilter.isMatch(user));

		user.put("role", "user"); // Changed role to make first filter true
		user.put("age", "35"); // Changed age back to make second filter true
		assertTrue(orFilter.isMatch(user));
	}

	@Test
	void testNotFilter() 
	{
		// Test inversion of a true condition to false
		Filter trueFilter = new TrueFilter();
		Filter notFilter = new NotFilter(trueFilter);
		assertFalse(notFilter.isMatch(user));

		// Test inversion of a false condition to true
		Filter falseFilter = new FalseFilter();
		Filter notFilter2 = new NotFilter(falseFilter);
		assertTrue(notFilter2.isMatch(user));
	}   
    
	@Test
	void testOrFilterWithTrueFilter() {
		// Test TrueFilter OrFilter"
		Filter trueFilter = new TrueFilter();
		Filter notEqualFilter = new EqualFilter("role", "user"); // This returns false
		Filter trueOrFilter = new OrFilter(trueFilter, notEqualFilter);
		assertTrue(trueOrFilter.isMatch(user)); // true OR false = true
	}

	@Test
	void testOrFilterWithFalseFilter() 
	{
		// Test FalseFilter OrFilter
		Filter falseFilter = new FalseFilter();
		user.put("role", "Administrator");
		Filter equalFilter = new EqualFilter("role", "administrator"); // This returns true
		Filter falseOrFilter = new OrFilter(falseFilter, equalFilter);
		assertTrue(falseOrFilter.isMatch(user)); // true because false OR true = true
	}
 
	@Test
	void testORfilterWithTrueAndFalseFilter() {
		Filter trueFilter = new TrueFilter();
		Filter falseFilter = new FalseFilter();
		Filter trueFalseOrFilter = new OrFilter(trueFilter, falseFilter);
		assertTrue(trueFalseOrFilter.isMatch(user)); //returns true, true OR false = true
	}
	
	@Test
	void testORfilterWithFalseAndFalseFilter() {
		Filter falseFilter = new FalseFilter();
		Filter falseFilter2 = new FalseFilter(); 
		Filter falseAndFalseOrFilter = new OrFilter(falseFilter, falseFilter2);
		assertFalse(falseAndFalseOrFilter.isMatch(user)); //returns false, false OR false = false
	}
	@Test   
	void testRegexFilter()   
	{  // Test invalid criteria in the constructor
		assertThrows(IllegalArgumentException.class, () -> new RegexFilter("role", null));
	    assertThrows(IllegalArgumentException.class, () -> new RegexFilter("role", ""));
	    
		//Test filter matches regular expression   
		Filter filter = new RegexFilter("role", "^[A-Z][a-z]+$");
		assertTrue(filter.isMatch(user));   
		  
		// Test it rejects wrong format, value is all lowercase
	    user.put("role", "administrator");
	    assertFalse(filter.isMatch(user)); 
	     
	    //Test invalid resource
	    assertThrows(IllegalArgumentException.class, ()->filter.isMatch(Map.of()));
	    assertThrows(IllegalArgumentException.class, ()->filter.isMatch(null));
	}
}
