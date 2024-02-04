package com.onebox.app.shop.models;

import java.util.List;

/**
 * 
 */
public class Carret {
	
	public Long id;
	public String usuari;
	public List<Producte> productes;
	
	
	public Carret() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Producte> getProductes() {
		return productes;
	}
	public void setProductes(List<Producte> productes) {
		this.productes = productes;
	}
	
	public String getUsuari() {
		return usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}
	
	

}
