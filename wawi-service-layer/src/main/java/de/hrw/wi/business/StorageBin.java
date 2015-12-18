/**
 * 
 */
package de.hrw.wi.business;

/**
 * @author andriesc
 * 
 *         StorageBin speichert, in welchem Lager (Nummer) und dort in welchem
 *         Gang (row) und dort in welchem Fach (bin) etwas gespeichert ist.
 *         
 *         Ein StorageBin kann nach der Erzeugung nicht mehr verändert werden.
 *         
 */
public final class StorageBin {
	private int wareHouseNumber;
	private int bin;

	/**
	 * @param wareHouseNumber
	 * @param row
	 * @param bin
	 */
	public StorageBin(int wareHouseNumber, int bin) {
		this.wareHouseNumber = wareHouseNumber;
		this.bin = bin;
	}

	public int getWareHouseNumber() {
		return wareHouseNumber;
	}

	public int getBin() {
		return bin;
	}

}
