package com.hackerrank.github.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;
import com.hackerrank.github.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private RepoRepository repoRepository;

	@Override
	public void deleteAll() {
		eventRepository.deleteAll();
	}

	@Override
	public Long addNewEvent(Event event) {
		Event newEvent = eventRepository.save(event);
		System.out.println(newEvent.toString());
		return newEvent.getId();
	}

	@Override
	public List<Event> getAllEvents() {

		return GithubService.toList(eventRepository.findAll());
	}

	@Override
	public List<Event> getEventsByActorID(long ID) {

		return GithubService.toList(eventRepository.findByActorIdOrderByIdAsc(ID));

	}

	@Override
	public List<Event> getEventsByRepoID(long repoID) {

		return GithubService.toList(eventRepository.findByRepoIdOrderByIdAsc(repoID));

	}

	@Override
	public Optional<Event> getEventByEventID(long eventID) {

		return eventRepository.findById(eventID);

	}
}
