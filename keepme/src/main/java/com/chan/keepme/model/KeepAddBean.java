package com.chan.keepme.model;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.chan.keepme.entity.keepme;
import com.chan.keepme.repo.keepmeRepository;

@Named
@ViewScoped
public class KeepAddBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private keepme KEEPME;
	
	@Inject
	private keepmeRepository repo;
	
	@PostConstruct
	private void init() {
		KEEPME = new keepme();
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		String str =  context.getRequestParameterMap().get("id");
		if(null != str) {
			KEEPME = repo.findByID(Integer.parseInt(str));
		}
	}
	public String save() {
		if(KEEPME.getId() > 0) {
			repo.update(KEEPME);
		}else {
			repo.add(KEEPME);
		}
		return "/index?faces-redirect=true";
	}
	public keepme getKEEPME() {
		return KEEPME;
	}
	public void setKEEPME(keepme kEEPME) {
		KEEPME = kEEPME;
	}
	

}
