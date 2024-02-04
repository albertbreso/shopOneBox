package com.onebox.app.shop.models;

import java.util.List;

import jakarta.annotation.Nullable;

public class RequestAfegirProducte {
	
	@Nullable
	public Long idCarret;
	public List<Producte> productes;
	
	
	public RequestAfegirProducte() {
		super();
	}
	public Long getIdCarret() {
		return idCarret;
	}
	public void setIdCarret(Long idCarret) {
		this.idCarret = idCarret;
	}
	public List<Producte> getProductes() {
		return productes;
	}
	public void setProductes(List<Producte> productes) {
		this.productes = productes;
	}
	
	
}
