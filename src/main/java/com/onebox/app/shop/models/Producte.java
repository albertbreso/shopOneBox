/**
 * 
 */
package com.onebox.app.shop.models;

import java.beans.JavaBean;

/**
 * 
 */
@JavaBean
public class Producte {

	public Long id;
	public String descripcio;
	public Double quantitat;
	
	public Producte() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public Double getQuantitat() {
		return quantitat;
	}
	public void setQuantitat(Double quantitat) {
		this.quantitat = quantitat;
	}	
}
