package com.cow.services;

import java.util.List;

import com.cow.models.Cow;

public interface CowService {

	public List<Cow> getAll();

	public Cow add(Cow cow);

	public Cow update(Cow cow);

	void delete(String id);

	public Cow getCowById(String id);
}
