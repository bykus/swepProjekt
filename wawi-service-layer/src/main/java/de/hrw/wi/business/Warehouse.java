package de.hrw.wi.business;

import java.util.Set;

/**
 * @author andriesc
 *
 *         Warehouse ist ein Lager. Bei der Erzeugung wird seine Größe
 *         angegeben: Ein Warehouse kann n Fächer der jeweils gleichen Größe k
 *         enthalten.
 * 
 *         In einem Fach können jeweils nur Gegenstände eines Typs abgegelegt
 *         werden, von diesem Gegenstand aber dann soviele, solange die Summe der
 *         Größe der Gegenstände nicht größer ist als die Größe des Faches.
 * 
 *         In ein Lager können Gegenstände eingelagert und ausgelagert werden.
 * 
 *         Auf ein Warehouse soll niemals direkt zugegriffen werden, sondern nur
 *         über einen entsprechenden Service.
 * 
 */
public class Warehouse {
	private int binSize;
	private int maxBin;

	/**
	 * 
	 */
	public Warehouse(int numberOfBins, int sizeOfBins) {
		this.binSize = sizeOfBins;
		this.maxBin = numberOfBins;
	}

	/**
	 * Die Methode ermittelt, welches Fach der nächste freie Lagerplatz ist,
	 * wenn ich etwas der Größe size ablegen will.
	 * 
	 * @param size
	 *            Größe des oder der Gegenstände, die ich ablegen will
	 * 
	 * @return Nummer des nächsten freien Lagerplatzes passender Größe. Die
	 *         Lagerplätze werden bei 0 anfangend durchnummeriert. Ist kein
	 *         Platz mehr frei, wird -1 zurückgegeben.
	 */
	public int nextFreeBin(int size) {
		// TODO implement this method
		return 0;
	}

	/**
	 * Lagert von einem Produkt die angegebene Anzahl in einem angegebenen
	 * Lagerplatz ein. Wenn das Einlagern fehlschlägt, weil der Lagerplatz zu
	 * klein ist für das Produkt und seine Anzahl, dann wird das Produkt gar
	 * nicht eingelagert. Gegebenenfalls muss man also die Anzahl des Produktes
	 * anschließend in zwei oder mehrere neue Einlagerversuche aufteilen.
	 * 
	 * @param p
	 *            Das einzulagernde Produkt
	 * @param quantity
	 *            Angabe, in welcher Stückanzahl das Produkt eingelagert werden
	 *            soll
	 * @param bin
	 *            der Ziel-Lagerplatz für die Einlagerung
	 * @return gibt true zurück, wenn das Einlagern erfolgreich war oder false,
	 *         wenn es nicht erfolgreich war (etwa Lagerplatz nicht groß genug)
	 */
	public boolean placeIntoStock(Product p, int quantity, StorageBin bin) {
		// TODO implement this method
		return false;
	}

	/**
	 * Entnimmt von einem Produkt die angegebene Anzahl aus dem angegebenen
	 * Lagerplatz. Wenn das Auslagern fehlschlägt weil etwa nicht genügend Stück
	 * des Produktes vorhanden sind, wird gar nichts aus dem Lager entnommen.
	 * Gegebenenfalls muss also in einem weiteren Auslagerversuch versucht
	 * werden, eine reduzierte Anzahl von Produkten zu entnehmen.
	 * 
	 * @param p
	 *            Das auszulagernde Produkt
	 * @param quantity
	 *            Angabe, in welcher Stückzahl das Produkt entnommen werden soll
	 * @param bin
	 *            Der Lagerplatz, aus dem das Produkt in angegebener Stückzahl
	 *            entnommen werden soll
	 * @return gibt true zurück, wenn das Auslagern erfolgreich war oder false,
	 *         wenn es nicht erfolgreich war (Produkt nicht in ausreichender
	 *         Anzahl vorhanden)
	 */
	public boolean removeFromStock(Product p, int quantity, StorageBin bin) {
		// TODO implement this method
		return false;
	}

	/**
	 * 
	 * Gibt aus, wie groß der Bestand in diesem Lager von dem jeweiligen Produkt
	 * ist.
	 * 
	 * @param p
	 *            das angefragte Produkt
	 * @return Die Anzahl
	 */
	public int availableStock(Product p) {
		// TODO implement this method
		return 0;
	}

	/**
	 * Gibt aus, wo im Lager das Produkt gespeichert ist.
	 * 
	 * @param p
	 * @return Eine Menge von Lagerplätzen oder eine leere Menge, wenn das
	 *         Produkt gar nicht vorhanden ist.
	 */
	public Set<StorageBin> retrieveBinsOf(Product p) {
		// TODO implement this method
		return null;
	}

}
