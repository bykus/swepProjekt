package de.hrw.wi.persistence;

import java.util.Set;

/**
 * Das ist das Interface zur Datenbank. Warenhäuser können Ihre Daten nur aus
 * der Datenbank holen.
 * 
 * @author andriesc
 *
 *
 */
public interface DatabaseReadInterface {

	/**
	 * @return Eine Menge mit den Nummern aller Läger
	 */
	public Set<Integer> getAllWarehouses();

	/**
	 * @param numberOfWarehouse
	 *            Die Nummer des Lagers
	 * @return Die maximale Anzahl der Lagerplätze im Lager (frei und belegt)
	 */
	public int getMaxAmountOfBins(int numberOfWarehouse);

	/**
	 * @param numberOfWarehouse
	 *            Die Nummer des Lagers
	 * @return Die maximale Größe eines Lagerplatzes in dem Lager
	 */
	public int getMaxSizeOfBins(int numberOfWarehouse);

	/**
	 * @param numberOfWarehouse
	 *            Nummer des Lagers
	 * @param numberOfBin
	 *            Nummer des Lagerplatzes
	 * @return Der EAN-Code für das Produkt, das auf dem angegebenen Platz
	 *         gelagert ist, oder "", wenn der Platz leer ist
	 */
	public String getArticleCodeForBin(int numberOfWarehouse, int numberOfBin);

	/**
	 * @param numberOfWarehouse
	 *            Nummer des Lagers
	 * @param numberOfBin
	 *            Nummer des Lagerplatzes
	 * @return Gibt die Anzahl der Produkte, die auf dem angegebenen Platz
	 *         gelagert ist, zurück
	 */
	public int getAmountForBin(int numberOfWarehouse, int numberOfBin);

	/**
	 * @return Gibt eine Liste mit den EAN-Codes aller Produkte, die in einem
	 *         Lager gespeichert sind, zurück
	 */
	public Set<String> getAllProducts();

	/**
	 * @param articleCode
	 *            der EAN-Code des Produkts
	 * @return Gibt den Namen des Produktes zum EAN-Code zurück
	 */
	public String getNameOfProduct(String articleCode);

	/**
	 * @param articleCode
	 *            der EAN-Code des Produkts
	 * @return Gibt die Größe des Produktes zum EAN-Code zurück
	 */
	public int getSizeOfProduct(String articleCode);
}
