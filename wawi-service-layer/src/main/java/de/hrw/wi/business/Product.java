/**
 * 
 */
package de.hrw.wi.business;

import java.math.BigDecimal;

/**
 * @author andriesc
 *
 */
public class Product {
	/**
	 * name speichert die Bezeichnung des Produkts.
	 */
	private String name;

	/**
	 * productCode speichert die 13stellige EAN-Nummer. productCode muss immer
	 * eine 13stellige Zeichenkette aus den Ziffern 0-9 sein.
	 */
	private String productCode;

	/**
	 * size speichert die Größe des Produktes als Zahl. Ein Produkt kann
	 * beispielsweise die Größe 1, 5, 8 oder 24 haben. Die Größe darf nicht 0
	 * und nicht negativ sein.
	 */
	private int size;

	/**
	 * @param name
	 * @param productCode
	 */
	public Product(String name, String productCode) {
		super();
		this.name = name;
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
