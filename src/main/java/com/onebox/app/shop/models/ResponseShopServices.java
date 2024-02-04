/**
 * 
 */
package com.onebox.app.shop.models;

import java.util.List;

import jakarta.annotation.Nullable;

/**
 * 
 */
public class ResponseShopServices {

	String estat;
	String descripcio;
	Integer total;
	List resultat;
	
	
	public String getEstat() {
		return estat;
	}
	public void setEstat(String estat) {
		this.estat = estat;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public List getResultat() {
		return resultat;
	}
	public void setResultat(List resultat) {
		this.resultat = resultat;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
