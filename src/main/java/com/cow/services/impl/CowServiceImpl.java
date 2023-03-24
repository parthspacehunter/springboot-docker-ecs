package com.cow.services.impl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cow.models.CollarStatusResponse;
import com.cow.models.Cow;
import com.cow.repositories.CowRepository;
import com.cow.services.CowService;

@Service
@CacheConfig(cacheNames = "cowCache")
public class CowServiceImpl implements CowService {

	@Autowired
	private CowRepository cowRepository;

	@Cacheable(cacheNames = "cows")
	@Override
	public List<Cow> getAll() {
		return this.cowRepository.findAll();
	}

	@CacheEvict(cacheNames = "cows", allEntries = true)
	@Override
	public Cow add(Cow cow) {
		this.setCollarStatus(cow);
		return this.cowRepository.save(cow);
	}

	@CacheEvict(cacheNames = "cows", allEntries = true)
	@Override
	public Cow update(Cow cow) {
		try {
			Cow fetchedCow = this.cowRepository.findById(cow.getId()).get();
			if (fetchedCow == null)
				return null;
			fetchedCow.setCollarId(cow.getCollarId());
			fetchedCow.setCowNumber(cow.getCowNumber());
			this.setCollarStatus(fetchedCow);
			return this.cowRepository.save(fetchedCow);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Cow record with given id " + cow.getId() + "does not exixst");
		}

	}

	@Caching(evict = {@CacheEvict(cacheNames = "cow", key = "#id"),
			@CacheEvict(cacheNames = "customers", allEntries = true)})
	@Override
	public void delete(String id) {
		this.cowRepository.deleteById(id);
	}

	@Cacheable(cacheNames = "cow", key = "#id", unless = "#result == null")
	@Override
	public Cow getCowById(String id) {
		return this.cowRepository.findById(id).orElse(null);
	}

	public void setCollarStatus(Cow cow) {
		cow.setCollarStatus("ACTIVE");
	}
}