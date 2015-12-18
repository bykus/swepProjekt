/**
 * 
 */
package de.hrw.wi.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * @author andriesc
 *
 */
public class DatabaseReadInterfaceTest {

	RealDatabase db;
	
	private static final Set<Integer> ALLWAREHOUSES = new HashSet<Integer>(Arrays.asList(0, 1));
	private static final Set<String> ALLPRODUCTS = new HashSet<String>(Arrays.asList("8806085948587", "0885909560462", "0636926062442", "8806084893826", "4250366833286", "0799637096608"));
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		db = new RealDatabase();
	}
	
	private <E> void  assertEqualSet(Set<E> expected, Set<E> actual) {
		assertNotNull(actual);
		assertEquals(expected.size(), actual.size());
		for (E s : actual)
			assertTrue(expected.contains(s));
	}

	@Test
	public void testGetAllWarehouses() {
		assertEqualSet(ALLWAREHOUSES, db.getAllWarehouses());	
	}
	
	@Test
	public void testGetAllProducts() {
		assertEqualSet(ALLPRODUCTS, db.getAllProducts());
	}

	@Test
	public void testAmountForBin() {
		assertEquals(1, db.getAmountForBin(0,0));
		assertEquals(1, db.getAmountForBin(0,1));
		assertEquals(1, db.getAmountForBin(0,2));
		assertEquals(2, db.getAmountForBin(0,3));
		assertEquals(2, db.getAmountForBin(0,4));
		assertEquals(2, db.getAmountForBin(0,5));
		assertEquals(4, db.getAmountForBin(0,6));
		assertEquals(1, db.getAmountForBin(1,0));
		assertEquals(1, db.getAmountForBin(1,1));
		assertEquals(5, db.getAmountForBin(1,2));
	}

	@Test
	public void testArticleCodeForBin() {
		assertEquals("8806085948587", db.getArticleCodeForBin(0,0));
		assertEquals("8806085948587", db.getArticleCodeForBin(0,1));
		assertEquals("8806085948587", db.getArticleCodeForBin(0,2));
		assertEquals("0885909560462", db.getArticleCodeForBin(0,3));
		assertEquals("0636926062442", db.getArticleCodeForBin(0,4));
		assertEquals("4250366833286", db.getArticleCodeForBin(0,5));
		assertEquals("0799637096608", db.getArticleCodeForBin(0,6));
		assertEquals("8806084893826", db.getArticleCodeForBin(1,0));
		assertEquals("8806084893826", db.getArticleCodeForBin(1,1));
		assertEquals("0636926062442", db.getArticleCodeForBin(1,2));
		assertEquals(null, db.getArticleCodeForBin(3,4));
	}
	
	@Test
	public void testMaxAmountOfBins() {
		assertEquals(30, db.getMaxAmountOfBins(0));
		assertEquals(30, db.getMaxAmountOfBins(1));
		assertEquals(-1, db.getMaxAmountOfBins(99));
	}
	
	@Test
	public void testMaxSizeOfBins() {
		assertEquals(5, db.getMaxSizeOfBins(0));
		assertEquals(10, db.getMaxSizeOfBins(1));
		assertEquals(-1, db.getMaxSizeOfBins(99));
	}
	
	@Test
	public void testGetNameOfProduct() {
		assertEquals("Samsung BD-H5500 3D Blu-ray-Player",db.getNameOfProduct("8806085948587"));
		assertEquals("Apple TV MD199FD/A",db.getNameOfProduct("0885909560462"));
		assertEquals("TomTom Start 25 M Europe Traffic",db.getNameOfProduct("0636926062442"));
		assertEquals("LG 40UB800V 101 cm (40 Zoll) LED-Backlight-Fernseher",db.getNameOfProduct("8806084893826"));
		assertEquals("Gigaset C430 A Duo Dect-Schnurlostelefon mit Anrufbeantworter",db.getNameOfProduct("4250366833286"));
		assertEquals("Ipow schwarz Selfie Stange",db.getNameOfProduct("0799637096608"));
		assertEquals(null,db.getNameOfProduct("0123456789123"));
	}
	
	@Test
	public void testSizeOfProduct() {
		assertEquals(3,db.getSizeOfProduct("8806085948587"));
		assertEquals(2,db.getSizeOfProduct("0885909560462"));
		assertEquals(2,db.getSizeOfProduct("0636926062442"));
		assertEquals(8,db.getSizeOfProduct("8806084893826"));
		assertEquals(2,db.getSizeOfProduct("4250366833286"));
		assertEquals(1,db.getSizeOfProduct("0799637096608"));
		assertEquals(-1, db.getSizeOfProduct("0123456789123"));
	}
	
}
