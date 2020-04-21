package com.hackerrank.github.service;

import java.util.List;

import com.hackerrank.github.enums.ActorsOrdering;
import com.hackerrank.github.exceptions.ActorNotFoundException;
import com.hackerrank.github.exceptions.ActorUpdateFieldsOtherThanAvatarException;
import com.hackerrank.github.model.Actor;

public interface ActorService  {

	//List<Actor> getAllActors(ActorsOrdering ordering);

	void updateActorAvatar(Actor actor) throws ActorNotFoundException, ActorUpdateFieldsOtherThanAvatarException;

}
