package com.hackerrank.github.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.hackerrank.github.model.Event;



public class GithubService {

	public static <T> List<T> toList(final Iterable<T> iterable) {
		
	    return StreamSupport.stream(iterable.spliterator(), false)
	                        .collect(Collectors.toList());
	}

	public Optional<Event> getEvent(long eventID) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
