package com.chan.keepme.repo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.chan.keepme.entity.keepme;

@ApplicationScoped
public class keepmeRepository {
	private List<keepme> keepME;
	
	@PostConstruct
	private void init() {
		keepME = new ArrayList<>();
	}
	
	public void add(keepme KEEPME) {
		KEEPME.setPostTime(LocalDateTime.now());
		if(keepME.isEmpty()) {
			KEEPME.setId(1);
		}else {
			KEEPME.setId(keepME.get(keepME.size() - 1 ).getId()+1);
		}
		keepME.add(KEEPME);
	}
	
	public void update(keepme KEEPME) {
		
	}
	
	public keepme findByID(int id) {
		return keepME.stream().filter(keepME -> keepME.getId() == id).findFirst().orElse(null);
	}
	
	public void delete(int id) {
		keepme KeepMe = findByID(id);
		keepME.remove(KeepMe);
	}
	
	public List<keepme> search(String title){
		Predicate<keepme> filter = a -> true;
		if(null != title && !title.isEmpty()) {
			filter = filter.and(keepME -> keepME.getTitle().toLowerCase().contains(title.toLowerCase()));
		}
		return keepME.stream().filter(filter).collect(Collectors.toList());
	}
	

}
