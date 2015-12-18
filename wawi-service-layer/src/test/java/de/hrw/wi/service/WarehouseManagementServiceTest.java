package de.hrw.wi.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.hrw.wi.business.Product;
import de.hrw.wi.business.StorageBin;
import de.hrw.wi.persistence.DatabaseReadInterface;

public class WarehouseManagementServiceTest {
	
	private WarehouseManagementServiceInterface wmService;
	private DatabaseReadInterface dbReadMock;
	
	
	@Before
	public void setup() {
		// Create mock object
		 dbReadMock = Mockito.mock(DatabaseReadInterface.class);
		
		Set<Integer> numbers = new HashSet<Integer>(Arrays.asList(123, 456));
		Set<String> productCodes = new HashSet<String>(Arrays.asList("0799637096608", "1234567895608"));
		
		when(dbReadMock.getAllWarehouses()).thenReturn(numbers);
		when(dbReadMock.getAmountForBin(123, 2)).thenReturn(10);
		when(dbReadMock.getAmountForBin(456, 1)).thenReturn(2);
		when(dbReadMock.getMaxAmountOfBins(123)).thenReturn(3);
		when(dbReadMock.getMaxAmountOfBins(456)).thenReturn(2);
		
		when(dbReadMock.getArticleCodeForBin(123, 2)).thenReturn("0799637096608");		
		when(dbReadMock.getArticleCodeForBin(456, 1)).thenReturn("0799637096608");
		when(dbReadMock.getArticleCodeForBin(123, 1)).thenReturn("1234567895608");
		
		when(dbReadMock.getAllProducts()).thenReturn(productCodes);
		when(dbReadMock.getNameOfProduct("0799637096608")).thenReturn("Nutella");
		when(dbReadMock.getNameOfProduct("1234567895608")).thenReturn("Marmelade");
				
		wmService = new WarehouseManagementService(dbReadMock);
	}
	

	@Test
	public void testGetWarehouseNumbers() {
		Set<Integer> numbers = wmService.getWarehouseNumbers();
		assertTrue(numbers != null);
    	assertEquals(2, numbers.size());
		for (Integer n : numbers) {
			if (n.intValue() == 456) 
				return;
		}	
		fail("Aktives Lager mit Nummer 456 nicht gefunden!");
	}

	@Test
	public void testAvailableStockOfProduct() {
		Product p = new Product("Nutella", "0799637096608");
		int anzahl = wmService.availableStock(p);
		assertEquals(12, anzahl);
	}
	
	@Test
	public void testAvailableStock() {
		Set<Product> availableStock = wmService.availableStock();
		String nutella   = "Nutella";
		String marmelade = "Marmelade";
		assertTrue(availableStock != null);
		assertTrue(availableStock.size() == 2);

		boolean nutellaFound = false;
		boolean marmeladeFound = false;
		for (Product product : availableStock) {
			if (product.getName().equals(nutella)) {
				nutellaFound = true;
			}
			else if (product.getName().equals(marmelade)) {
				marmeladeFound = true;
			}
		}	
		assertTrue(nutellaFound && marmeladeFound);

		verify(dbReadMock, times(1)).getNameOfProduct("0799637096608");
		verify(dbReadMock, times(1)).getNameOfProduct("1234567895608");

	}
	
	@Test
	public void testStorageBinsFor() {
		Product product = new Product("Marmelade", "1234567895608");
		Set<StorageBin> bins = wmService.storageBinsFor(product);
		assertTrue(bins != null);
		assertTrue(bins.size() == 1);

		for (StorageBin bin : bins) {
			assertEquals(1, bin.getBin());
			assertEquals(123, bin.getWareHouseNumber());
		}	

		verify(dbReadMock).getAllWarehouses();
		verify(dbReadMock, times(2)).getMaxAmountOfBins(anyInt());
		verify(dbReadMock, times(5)).getArticleCodeForBin(anyInt(), anyInt());
	}
	
}
