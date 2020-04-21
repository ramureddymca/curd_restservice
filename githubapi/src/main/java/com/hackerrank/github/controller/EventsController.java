package com.hackerrank.github.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.github.exceptions.ActorNotFoundException;
import com.hackerrank.github.exceptions.ActorUpdateFieldsOtherThanAvatarException;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.model.Repo;
import com.hackerrank.github.service.ActorService;
import com.hackerrank.github.service.EventService;

@RestController
@RequestMapping(value = "/")
public class EventsController {

	@Autowired
	private ActorService actorService;

	@Autowired
	private EventService eventService;

	/**
	 * Adding new events: The service should be able to add a new event by the POST
	 * request at /events. The event JSON is sent in the request body. If an event
	 * with the same id already exists then the HTTP response code should be 400,
	 * otherwise, the response code should be 201.
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public ResponseEntity<Long> addNewEvent(@RequestBody Event event, HttpServletRequest request) {

		if(eventService.getEventByEventID(event.getId()).isPresent()){

			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
		Long eventId = eventService.addNewEvent(event);

		return new ResponseEntity<Long>(eventId, HttpStatus.CREATED);
	}

	/**
	 * Returning all the events: The service should be able to return the JSON array
	 * of all the events by the GET request at /events. The HTTP response code
	 * should be 200. The JSON array should be sorted in ascending order by event
	 * ID.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getAllEvents(HttpServletRequest request) {
		List<Event> events = null;
		try {
			events = eventService.getAllEvents();
		} catch (Exception e) {

		}
		if (null != events) {
			return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Event>>(events, HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Returning the event records filtered by the actor ID: The service should be
	 * able to return the JSON array of all the events which are performed by the
	 * actor ID by the GET request at /events/actors/{actorID}. If the requested
	 * actor does not exist then HTTP response code should be 404, otherwise, the
	 * response code should be 200. The JSON array should be sorted in ascending
	 * order by event ID. Updating the avatar URL of the actor: The service should
	 * be able to update the avatar URL of the actor by the PUT request at /actors.
	 * The actor JSON is sent in the request body. If the actor with the id does not
	 * exist then the response code should be 404, or if there are other fields
	 * being updated for the actor then the HTTP response code should be 400,
	 * otherwise, the response code should be 200.
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/events/{eventID}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Event> getEventByEventId(@PathVariable("eventID") long eventID, HttpServletRequest request) {
		Optional<Event> events = null;
		try {
			events = eventService.getEventByEventID(eventID);
		} catch (Exception e) {
		}
		if (events.isPresent()) {
			return new ResponseEntity<Event>(events.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);		}

	}

	@RequestMapping(value = "/events/repos/{repoID}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Event>> getAllEventsByReposId(@PathVariable("repoID") long repoID,
			HttpServletRequest request) {

		List<Event> events = eventService.getEventsByRepoID(repoID);
		if (!events.isEmpty()) {
			return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Event>>(events, HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/events/actors/{actorID}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Event>> getAllEventsByActorId(@PathVariable("actorID") long actorID,
			HttpServletRequest request) {

		List<Event> events = eventService.getEventsByActorID(actorID);
		if (!events.isEmpty()) {
			return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Event>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Updating the avatar URL of the actor: The service should be able to update
	 * the avatar URL of the actor by the PUT request at /actors. The actor JSON is
	 * sent in the request body. If the actor with the id does not exist then the
	 * response code should be 404, or if there are other fields being updated for
	 * the actor then the HTTP response code should be 400, otherwise, the response
	 * code should be 200.
	 * 
	 * @param actor
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/actors", method = RequestMethod.PUT)
	public ResponseEntity<Long> updateActorAvatar(@RequestBody Actor actor, HttpServletRequest request) {

		try {

			actorService.updateActorAvatar(actor);

			return new ResponseEntity<Long>(-1L, HttpStatus.OK);

		} catch (ActorNotFoundException e) {
			return new ResponseEntity<Long>(-1L, HttpStatus.NOT_FOUND);
		} catch (ActorUpdateFieldsOtherThanAvatarException e) {
			return new ResponseEntity<Long>(-1L, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/events/repos/{repoId}/actors/{actorId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Repo>> getAllReposById(@PathVariable Long repoId, @PathVariable Long actorId) {
		List<Repo> events = eventService.getAllReposByActorId(repoId,actorId);
		if(!events.isEmpty()) { 
			return new ResponseEntity<List<Repo>>(events, HttpStatus.OK);
		} else { 
			return new ResponseEntity<List<Repo>>(HttpStatus.NOT_FOUND);
		}
	}

	
	/*
	 * public ResponseEntity<List<Event>>
	 * getAllEventsByActorId(@PathVariable("actorID") long actorID,
	 * HttpServletRequest request) {
	 * 
	 * List<Event> events = eventService.getEventsByActorID(actorID); if
	 * (!events.isEmpty()) { return new ResponseEntity<List<Event>>(events,
	 * HttpStatus.OK); } else { return new
	 * ResponseEntity<List<Event>>(HttpStatus.NOT_FOUND); }
	 */
		
		
}
