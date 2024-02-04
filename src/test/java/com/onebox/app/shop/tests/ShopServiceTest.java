package com.onebox.app.shop.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.onebox.app.shop.models.Carret;
import com.onebox.app.shop.models.Producte;
import com.onebox.app.shop.services.ShopService;

@SpringBootTest
public class ShopServiceTest {

    @Mock
    private List<Carret> mockCarrets;

    @InjectMocks
    private ShopService shopService;

    @BeforeEach
    public void setUp() {
    	Carret carret = new Carret();
    	List<Producte> productes = new ArrayList();
    	for(int i = 1;i<=6; i++) {
			Producte producte = new Producte();
			producte.setDescripcio("Producte "+i);
			producte.setId(i*1l);
			producte.setQuantitat(i*200.0);
			productes.add(producte);
		}
    	carret.setProductes(productes);
    	mockCarrets.add(carret);
        when(mockCarrets.stream()).thenReturn(Stream.of());

    }

    @Test
    public void testAfegirCarret() {
        when(mockCarrets.add(any())).thenReturn(true);

        Long carretId = shopService.nouCarret();

        assertNotNull(carretId);
        assertTrue(carretId > 0);
        verify(mockCarrets, times(2)).add(any());
    }

    @Test
    public void testObtindreCarret() {
        Carret mockCarret = new Carret();
        mockCarret.setId(1L);
        when(mockCarrets.stream()).thenReturn(java.util.stream.Stream.of(mockCarret));
        shopService.nouCarret();
        Carret resultCarret = shopService.obtindreCarret(1L);
        assertNotNull(resultCarret);
        assertEquals(1L, resultCarret.getId());
        verify(mockCarrets.stream(), times(2)).filter(Mockito.any());
    }

    @Test
    public void testEliminarCarret() {
        shopService.eliminarCarret(1L);
        verify(mockCarrets, times(1)).removeIf(any());
    }
}
