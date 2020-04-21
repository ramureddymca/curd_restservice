package com.hackerrank.github.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hackerrank.github.model.Actor;

public interface ActorRepository extends CrudRepository<Actor, Long> {
	
	/*
	 * @Query("SELECT  act, (select count(evt.id) from Event evt where evt.actor.id = act.id) as act.totalEvents FROM Actor act order by totalEvents desc"
	 * ) List<Actor> findActorsOrderByNumberEventsDesc();
	 * 
	 * @Query("SELECT act FROM Actor act") List<Actor>
	 * findActorsOrderByMaximumStreakDesc();
	 */
}



