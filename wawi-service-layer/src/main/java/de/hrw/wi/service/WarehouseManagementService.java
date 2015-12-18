package de.hrw.wi.service;

import java.util.HashSet;
import java.util.Set;

import de.hrw.wi.business.Product;
import de.hrw.wi.business.StorageBin;
import de.hrw.wi.persistence.DatabaseReadInterface;

public class WarehouseManagementService implements
		WarehouseManagementServiceInterface {

	private DatabaseReadInterface dbRead;

	public WarehouseManagementService(DatabaseReadInterface dbRead) {
		this.dbRead = dbRead;
	}

	@Override
	public void createWarehouses(int amount, int numberOfBins, int sizeOfEachBin) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Integer> getWarehouseNumbers() {
		return dbRead.getAllWarehouses();
	}

	@Override
	public boolean removeWarehouse(int number) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StorageBin proposeStorageBinFor(Product p, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean placeIntoStock(Product p, int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int availableStock(Product p) {
		String code = p.getProductCode();
		int stock = 0;
		Set<Integer> allWarehouses = dbRead.getAllWarehouses();
		for (Integer w : allWarehouses) {
			int binCount = dbRead.getMaxAmountOfBins(w.intValue());
			for (int i = 0; i < binCount; i++) {
				String product = dbRead.getArticleCodeForBin(w.intValue(), i);
				if (product != null && product.equals(code)) {
					stock += dbRead.getAmountForBin(w.intValue(), i);
				}
			}
		}
		return stock;
	}

	@Override
	public Set<StorageBin> storageBinsFor(Product p) {
		String code = p.getProductCode();
		Set<StorageBin> bins = new HashSet<StorageBin>();
		Set<Integer> allWarehouses = dbRead.getAllWarehouses();
		for (Integer w : allWarehouses) {
			int binCount = dbRead.getMaxAmountOfBins(w.intValue());
			for (int i = 0; i < binCount; i++) {
				String product = dbRead.getArticleCodeForBin(w.intValue(), i);
				if (product != null && product.equals(code)) {
					bins.add(new StorageBin(w.intValue(), i));
				}
			}
		}
		return bins;
	}

	@Override
	public Set<Product> availableStock() {
		Set<Product> stock = new HashSet<Product>();
		Set<String> codes = dbRead.getAllProducts();
		for (String code : codes) {
			// TODO Schleife fertig implementieren, so dass die Methode wie
			// gefordert die Liste der Produkte ausgibt, die in allen Lägern
			// grundsätzlich verfügbar sind.
			String name = dbRead.getNameOfProduct(code);
			Product product = new Product(name, code);
			product.setSize(dbRead.getSizeOfProduct(code));
			stock.add(product);
		}
		return stock;
	}

	@Override
	public Set<StorageBin> removeFromStock(Product p, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

}
