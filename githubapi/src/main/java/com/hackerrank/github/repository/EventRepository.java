package com.hackerrank.github.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.github.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

    @Override
    Iterable<Event> findAll();

	List<Event> findByActorIdOrderByIdAsc(long actorID);
	List<Event> findByRepoIdOrderByIdAsc(long repoID);
	List<Event> findByActorId(int actorID);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Event")
	void deleteAll();
}
