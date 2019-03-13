package com.chan.keepme.model;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.chan.keepme.entity.keepme;
import com.chan.keepme.repo.keepmeRepository;

@Named
@RequestScoped
public class KeepListBean implements Serializable {
	private List<keepme> KEEPME;
	
	private String title;
	
	@Inject
	private keepmeRepository repo;
	
	@PostConstruct
	private void init() {
		KEEPME = repo.search(title);
	}
	
	@NotEmpty(message = "Can't search")
	public String search() {
		KEEPME = repo.search(title);
		return"/index";
	}
	
	public String delete(int id) {
		repo.delete(id);
		return "/index?faces-redirect=true";
	}

	public List<keepme> getKEEPME() {
		return KEEPME;
	}

	public void setKEEPME(List<keepme> kEEPME) {
		KEEPME = kEEPME;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}
