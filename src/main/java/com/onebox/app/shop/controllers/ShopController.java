/**
 * 
 */
package com.onebox.app.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onebox.app.shop.models.RequestAfegirProducte;
import com.onebox.app.shop.models.ResponseShopServices;
import com.onebox.app.shop.services.ShopService;


/**
 * 
 */
@RestController("/")
public class ShopController {
	
	@Autowired ShopService shopService;

	@PostMapping("nouCarret")	
	public ResponseEntity<ResponseShopServices> nouCarret() {
		try {			
			return ResponseEntity.ok(this.shopService.nouCarret());
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("eliminarCarret/{id}")	
	public ResponseEntity<ResponseShopServices> eliminarCarret(@PathVariable Long id) {
		try {			
			return ResponseEntity.ok(this.shopService.eliminarCarret(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@GetMapping("infoCarret/{id}")	
	public ResponseEntity<ResponseShopServices> consultarInfoCarret(@PathVariable Long id) {
		try {			
			return ResponseEntity.ok(this.shopService.obtindreCarret(id));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("afegirProductes")	
	public ResponseEntity<ResponseShopServices> afegirProductes(@RequestBody RequestAfegirProducte request) {
		try {			
			return ResponseEntity.ok(this.shopService.afegirProductes(request));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
