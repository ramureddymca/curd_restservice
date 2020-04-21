package com.hackerrank.github.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.github.service.EventService;

@RestController
@RequestMapping
public class ResourcesController {

	@Autowired
	private EventService eventService;
	
	/**
	 * Erasing all the events: The service should be able to erase all the events by the DELETE request at /erase. 
	 * The HTTP response code should be 200.
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/erase", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllEvents(HttpServletRequest request) {

		eventService.deleteAll();
	}

}
