/**
 * 
 */
package com.onebox.app.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.onebox.app.shop.models.Carret;
import com.onebox.app.shop.models.Producte;
import com.onebox.app.shop.models.RequestAfegirProducte;
import com.onebox.app.shop.models.ResponseShopServices;

/**
 * 
 */
@Service
public class ShopService {
	
	private static final Logger log = LoggerFactory.getLogger(ShopService.class);
	
	public List<Carret> carrets = new ArrayList();
	
	public ResponseShopServices nouCarret() {
		ResponseShopServices responseShopServices = new ResponseShopServices();
		
		Carret carret = new Carret();
		carret.setId(Double.valueOf(Math.floor(Math.random()*1000+1)).longValue());
		carret.setUsuari(carret.id+"@onebox.es");
		carrets.add(carret);
		log.info("## Carret "+carret.getId()+" creat amb èxit!.");
		
		List<Carret> resultat = new ArrayList<>();
		resultat.add(carret);
		responseShopServices.setDescripcio("Carret creat amb èxit!.");
		responseShopServices.setTotal(resultat.size());
		responseShopServices.setEstat("OK");
		responseShopServices.setResultat(resultat);
		
		// lanzamos la task para "caducar" el carret
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	log.info("## Carret "+carret.getId()+" caducat!.");
            	eliminarCarret(carret.getId());
            }
        }, 1 * 60 * 1000);

		return responseShopServices;
	}

	public ResponseShopServices eliminarCarret(Long id) {
		ResponseShopServices responseShopServices = new ResponseShopServices();
		if(this.carrets.isEmpty()) {
			responseShopServices = obtindreCarret(id);
		} else {
			this.carrets.removeIf(c -> c.id.equals(id));
			log.info("## Carret "+id+" el·liminat!.");
			
			responseShopServices.setDescripcio("Carret "+id+" el·liminat!.");
			responseShopServices.setEstat("OK");
		}
		return responseShopServices;	
	}
	
	public ResponseShopServices afegirProductes(RequestAfegirProducte request) {
		ResponseShopServices responseShopServices = new ResponseShopServices();
		if(this.carrets.isEmpty()) {
			responseShopServices = obtindreCarret(request.idCarret);
		} else {
			Optional<Carret> carret = carrets.stream().filter(c -> c.id.equals(request.idCarret)).findFirst();
			carret.get().productes = new ArrayList<Producte>(request.getProductes());
			log.info("## Producte/s  afegit/s al carret "+request.idCarret+" amb èxit!");
			
			responseShopServices = obtindreCarret(request.idCarret);
			responseShopServices.setDescripcio("Producte/s  afegit/s al carret "+request.idCarret+" amb èxit!");
		}
		return responseShopServices;
	}
	
	public ResponseShopServices obtindreCarret(Long id) {
		ResponseShopServices responseShopServices = new ResponseShopServices();
		if(carrets.isEmpty()) {
			responseShopServices.setDescripcio("No n'hi ha cap carret!.");
			responseShopServices.setEstat("KO");
			responseShopServices.setTotal(Integer.valueOf(0));
			responseShopServices.setResultat(new ArrayList());
		} else {
			Optional<Carret> carret = carrets.stream().filter(c -> c.id.equals(id)).findFirst();
			if(carret == null) {
				responseShopServices.setDescripcio("Carret "+id+" no trobat!.");
				responseShopServices.setEstat("KO");
				responseShopServices.setTotal(Integer.valueOf(0));
				responseShopServices.setResultat(new ArrayList());
			}else {
				List<Carret> resultat = new ArrayList<>();
				resultat.add(carret.get());
				responseShopServices.setDescripcio("Carret "+id+" local·litzat!.");
				responseShopServices.setEstat("OK");
				responseShopServices.setTotal(Integer.valueOf(resultat.size()));
				responseShopServices.setResultat(resultat);
			}
		}
		return responseShopServices;
	}
	
//	@Scheduled(fixedDelay = 600000)
//	private void qwerty() {
//		eliminarCarret(id);
//		log.info("## Carret "+id+" caducat!.");
//	}
}
