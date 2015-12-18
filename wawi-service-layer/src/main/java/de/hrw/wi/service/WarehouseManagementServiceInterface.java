package de.hrw.wi.service;

import java.util.Set;

import de.hrw.wi.business.Product;
import de.hrw.wi.business.StorageBin;

/**
 * Ein WarehoseManagementService stellt Funktionen zum Einlagern von Produkten,
 * Auslagern von Produkten etc. bereit. Er erhält seine Daten von den einzelnen
 * Lägern (Warehouse), gibt aber nie direkt Zugriff auf Läger sondern maximal
 * deren jeweilige Nummer.
 * 
 * @author andriesc
 *
 */
public interface WarehouseManagementServiceInterface {
	/**
	 * Erzeugt die angegebene Anzahl von Lägern, alle mit der gleichen Menge an
	 * Fächern und Größe der einzelnen Fächer.
	 * 
	 * @param amount
	 *            Anzahl zu erzeugender Läger
	 * @param numberOfBins
	 *            Anzahl der Fächer jeweils in den Lägern
	 * @param sizeOfEachBin
	 *            Größe der Fächer jeweils
	 */
	public void createWarehouses(int amount, int numberOfBins, int sizeOfEachBin);

	/**
	 * @return Die Nummern der aktiven Läger, egal ob in ihnen Produkte lagern
	 *         oder nicht.
	 */
	public Set<Integer> getWarehouseNumbers();

	/**
	 * Legt das Lager mit der angegebenen Nummer still. Ein Lager kann nicht
	 * stillgelegt werden, wenn in ihm noch Gegenstände gelagert sind. Nur ein
	 * leeres Lager kann stillgelegt werden.
	 * 
	 * @param number
	 *            Nummer des stillzulegenden Lagers
	 * @return true, wenn das Lager erfolgreich stillgelegt werden konnte
	 */
	public boolean removeWarehouse(int number);

	/**
	 * Schlägt Lager und Fach vor:
	 * 
	 * <ul>
	 * <li>Existierendes Fach, wenn es das Produktschon gibt und der Platz im
	 * Fach reicht
	 * 
	 * <li>sonst Neues Fach im selben Lager, wenn das Lager noch Platz hat,
	 * 
	 * <li>sonst neues Fach in neuem Lager, wenn es noch ein Lager mit Platz
	 * gibt
	 * 
	 * <li>Sonst Fehler
	 * </ul>
	 * 
	 * @param p
	 * @param amount
	 * @return StorageBin der das angegebene Produkt in der angegebenen Anzahl
	 *         aufnehmen kann, sonst null.
	 */
	public StorageBin proposeStorageBinFor(Product p, int amount);

	/**
	 * Lagert angegebene Menge des angegebenen Produktes ein. Kann das Produkt
	 * in der angegebenen Anzahl nicht eingelagert werden, wird gar nichts
	 * eingelagert.
	 * 
	 * @param p
	 * @param amount
	 * @return true, wenn alles eingelagert werden konnte, sonst false.
	 */
	public boolean placeIntoStock(Product p, int amount);

	/**
	 * @param p
	 * @return Gibt zurück, welche Stückzahl vom Produkt p in allen Lägern
	 *         insgesamt vorhanden ist.
	 */
	public int availableStock(Product p);

	/**
	 * Liefert alle Lagerplätze für das angegebene Produkt zurück.
	 * 
	 * @param p
	 * @return Eine Menge aller Lagerplätze für das angegebene Produkt
	 */
	public Set<StorageBin> storageBinsFor(Product p);

	/**
	 * @return Gibt zurück, welche Produkte insgesamt in den Lägern vorhanden
	 *         sind, ohne die jeweilige Stückzahl zu berücksichtigen
	 */
	public Set<Product> availableStock();

	/**
	 * Vom angegebenen Produkt die angegebene Menge auslagern.
	 * 
	 * @param p
	 *            Das auszulagernde Produkt
	 * @param amount
	 *            Die auszulagernde Menge
	 * @return Die Lagerplätze, aus denen das Produkt ausgelagert wurde.
	 */
	public Set<StorageBin> removeFromStock(Product p, int amount);
}
