package com.onebox.app.shop.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.onebox.app.shop.models.Carret;
import com.onebox.app.shop.models.Producte;
import com.onebox.app.shop.models.RequestAfegirProducte;
import com.onebox.app.shop.models.ResponseShopServices;
import com.onebox.app.shop.services.ShopService;

class ShopServiceTest {
	
	@Test
    void testEliminarCarret() {
        ShopService shopService = Mockito.mock(ShopService.class);
        Mockito.when(shopService.eliminarCarret(1L)).thenAnswer(invocation -> {
            ResponseShopServices responseShopServices = new ResponseShopServices();
            responseShopServices.setDescripcio("Carret 1 el·liminat!.");
            responseShopServices.setEstat("OK");
            return responseShopServices;
        });

        ResponseShopServices responseShopServices = shopService.eliminarCarret(1L);

        assertEquals("Carret 1 el·liminat!.", responseShopServices.getDescripcio());
        assertEquals("OK", responseShopServices.getEstat());
        
        Mockito.verify(shopService, Mockito.times(1)).eliminarCarret(1L);
    }

    @Test
    void testNouCarret() {
        ShopService shopService = Mockito.mock(ShopService.class);
        Mockito.when(shopService.nouCarret()).thenAnswer(invocation -> {
            Carret carret = new Carret();
            carret.setId(1L);
            carret.setUsuari("1@onebox.es");
            
            List<Carret> resultat = new ArrayList<>();
            resultat.add(carret);
            
            ResponseShopServices responseShopServices = new ResponseShopServices();
            responseShopServices.setDescripcio("Carret creat amb èxit!.");
            responseShopServices.setTotal(resultat.size());
            responseShopServices.setEstat("OK");
            responseShopServices.setResultat(resultat);
            
            return responseShopServices;
        });

        ResponseShopServices responseShopServices = shopService.nouCarret();

        assertEquals("Carret creat amb èxit!.", responseShopServices.getDescripcio());
        assertEquals(1, responseShopServices.getTotal());
        assertEquals("OK", responseShopServices.getEstat());
        assertEquals(1, responseShopServices.getResultat().size());
        
        Mockito.verify(shopService, Mockito.times(1)).nouCarret();
    }

    @Test
    void testAfegirProductes() {
        ShopService shopService = Mockito.mock(ShopService.class);
        Mockito.when(shopService.afegirProductes(Mockito.any(RequestAfegirProducte.class))).thenAnswer(invocation -> {
            RequestAfegirProducte request = invocation.getArgument(0);
            
            ResponseShopServices responseShopServices = new ResponseShopServices();
            responseShopServices.setDescripcio("Producte/s afegit/s al carret "+request.getIdCarret()+" amb èxit!.");
            return responseShopServices;
        });

        RequestAfegirProducte request = new RequestAfegirProducte();
        request.setIdCarret(1L);
        List<Producte> productes = new ArrayList<>();
        Producte producte = new Producte();
        producte.setId(1L);
        producte.setDescripcio("Producte 1");
        producte.setQuantitat(10.0);
        productes.add(producte);
        request.setProductes(productes);

        ResponseShopServices responseShopServices = shopService.afegirProductes(request);
        responseShopServices.setEstat("OK");
        
        assertEquals("Producte/s afegit/s al carret 1 amb èxit!.", responseShopServices.getDescripcio());
        assertEquals("OK", responseShopServices.getEstat());
        
        Mockito.verify(shopService, Mockito.times(1)).afegirProductes(request);
    }    
}