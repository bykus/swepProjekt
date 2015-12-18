package de.hrw.wi.service;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.hrw.wi.business.Product;
import de.hrw.wi.business.StorageBin;
import de.hrw.wi.persistence.DatabaseReadInterface;
import de.hrw.wi.persistence.RealDatabase;

public class WarehouseManagementServiceIntegrationTest {
	
	private WarehouseManagementServiceInterface wmService;
	
	
	@Before
	public void setup() {
		// Create real database
		DatabaseReadInterface db = new RealDatabase();
		wmService = new WarehouseManagementService(db);
	}
	

	@Test
	public void testGetWarehouseNumbers() {
		Set<Integer> numbers = wmService.getWarehouseNumbers();
		assertTrue(numbers != null);
    	assertEquals(2, numbers.size());
		for (Integer n : numbers) {
			if (n.intValue() == 1) 
				return;
		}	
		fail("Aktives Lager mit Nummer 1 nicht gefunden!");
	}

	@Test
	public void testAvailableStockOfProduct() {
		Product p = new Product("Samsung BD-H5500 3D Blu-ray-Player", "8806085948587");
		int anzahl = wmService.availableStock(p);
		assertEquals(3, anzahl);
	}
	
	@Test
	public void testAvailableStock() {
		Set<Product> availableStock = wmService.availableStock();
		
		String expected       = "0885909560462";
		String nonexisting    = "1234567890000";
		
		assertTrue(availableStock != null);
		assertTrue(availableStock.size() == 6);
		
		boolean expectedFound = false;
		boolean nonexistingFound = false;
		for (Product product : availableStock) {
			if (product.getProductCode().equals(expected)) {
				expectedFound = true;
			}
			else if (product.getName().equals(nonexisting)) {
				nonexistingFound = true;
			}
		}	
		assertTrue(expectedFound);
		assertFalse(nonexistingFound);
	}
	
	@Test
	public void testStorageBinsFor() {
		Product product = new Product("Gigaset C430 A Duo Dect-Schnurlostelefon mit Anrufbeantworter", "4250366833286");
		Set<StorageBin> bins = wmService.storageBinsFor(product);
		assertTrue(bins != null);
		assertTrue(bins.size() == 1);

		for (StorageBin bin : bins) {
			assertEquals(5, bin.getBin());
			assertEquals(0, bin.getWareHouseNumber());
		}
	}
	

	
}
