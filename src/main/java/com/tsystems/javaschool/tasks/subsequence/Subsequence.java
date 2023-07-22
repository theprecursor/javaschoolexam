package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
	
	//First there is a condition which if any
	//list is null show the exception 
	//IllegalArgumentException and then a 
	//condition which the objects in list x it 
	//is on the list y the boolean response changes 
	//to true and end return the response to defult 
	//is false if in the case the last condition false
	
    @SuppressWarnings("rawtypes")
    public boolean find(List<Object> x, List<Object> y) {
    	boolean response = false;
    	if(x == null || y == null) {
    		throw new IllegalArgumentException();
    	}
    	if(y.containsAll(x) == true) {
    		response = true;
    	}
	    return response;
    }
}
