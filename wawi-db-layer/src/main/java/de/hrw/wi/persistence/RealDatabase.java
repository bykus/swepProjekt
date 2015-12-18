/**
 * 
 */
package de.hrw.wi.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author andriesc
 *
 */
public class RealDatabase implements DatabaseReadInterface,
		DatabaseWriteInterface {

	private final String dbURL = "jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db";
	private final String user = "sa";
	private final String password = "";

	private ResultSet executeQuery(String sql) throws SQLException {
		Connection c = null;
		try {
			c = DriverManager.getConnection(dbURL, user, password);
			ResultSet rs = c.createStatement().executeQuery(sql);
			c.commit();
			return rs;
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private int executeUpdate(String sql) throws SQLException {
		Connection c = null;
		try {
			c = DriverManager.getConnection(dbURL, user, password);
			int result = c.createStatement().executeUpdate(sql);
			c.commit();
			return result;
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private List<String> getResultAsString(String sql) {
		List<String> list = new ArrayList<String>();
		try {
			ResultSet result = executeQuery(sql);
			while (result.next())
				list.add(result.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private List<Integer> getResultAsInt(String sql) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			ResultSet result = executeQuery(sql);
			while (result.next())
				list.add(result.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private int getInt(String sql) {
		try {
			ResultSet result = executeQuery(sql);
			if (result.next())
				return result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	private String getString(String sql) {
		try {
			ResultSet result = executeQuery(sql);
			if (result.next())
				return result.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Set<Integer> getAllWarehouses() {
		return new HashSet<Integer>(
				getResultAsInt("SELECT number FROM WAREHOUSES"));
	}

	public int getMaxAmountOfBins(int numberOfWarehouse) {
		return getInt("SELECT maxBin FROM WAREHOUSES WHERE number ="
				+ numberOfWarehouse);
	}

	public int getMaxSizeOfBins(int numberOfWarehouse) {
		return getInt("SELECT maxSize FROM WAREHOUSES WHERE number ="
				+ numberOfWarehouse);
	}

	public String getArticleCodeForBin(int numberOfWarehouse, int numberOfBin) {
		return getString("SELECT articleCode FROM STOCK WHERE number="
				+ numberOfWarehouse + " AND bin=" + numberOfBin);
	}

	public int getAmountForBin(int numberOfWarehouse, int numberOfBin) {
		return getInt("SELECT amount FROM STOCK WHERE number="
				+ numberOfWarehouse + " AND bin=" + numberOfBin);
	}

	public Set<String> getAllProducts() {
		return new HashSet<String>(
				getResultAsString("SELECT articleCode FROM PRODUCTS"));
	}

	public String getNameOfProduct(String articleCode) {
		return getString("SELECT name FROM PRODUCTS WHERE articleCode=\'"
				+ articleCode + "\'");
	}

	public int getSizeOfProduct(String articleCode) {
		return getInt("SELECT size FROM PRODUCTS WHERE articleCode=\'"
				+ articleCode + "\'");
	}

	public void addProduct(String articleCode, String name, int size)
			throws PersistenceException {

		if (articleCode.length() != 13)
			throw new PersistenceException(
					"Article code does not have 13 digits.");

		try {
			String s = "INSERT INTO PRODUCTS VALUES(\'" + articleCode
					+ "\', \'" + name + "\', " + size + ")";
			int res = executeUpdate(s);
			if (res == 0)
				throw new PersistenceException("Product could not be added.");
		} catch (SQLException e) {
			throw new PersistenceException("Product could not be added.", e);
		}
	}

	public void deleteProduct(String articleCode) throws PersistenceException {
		try {
			executeUpdate("DELETE FROM STOCK WHERE articleCode=\'"
					+ articleCode + "\'");
			int res = executeUpdate("DELETE FROM PRODUCTS WHERE articleCode=\'"
					+ articleCode + "\'");
			if (res == 0)
				throw new PersistenceException("Product could not be deleted.");
		} catch (SQLException e) {
			throw new PersistenceException("Product could not be deleted.");
		}
	}

	public void setStock(int numberOfWarehouse, int bin, String articleCode,
			int amount) throws PersistenceException {
		HashSet<Integer> warehouses = new HashSet<Integer>(getAllWarehouses());

		// Nur abspeichern, wenn es das Lager auch gibt
		if (warehouses.contains(numberOfWarehouse)) {
			HashSet<String> products = new HashSet<String>(getAllProducts());

			// Nur abspeichern, wenn es das Produkt bereits in der Datenbank
			// gibt
			if (products.contains(articleCode)) {
				int maxSize = getMaxSizeOfBins(numberOfWarehouse);

				// Lagerplatz leer?
				if (getArticleCodeForBin(numberOfWarehouse, bin) == null) {
					// Nur abspeichern, wenn die gewünschte Menge auch paßt
					if (maxSize >= amount * getSizeOfProduct(articleCode)) {
						try {
							executeUpdate("INSERT INTO STOCK VALUES("
									+ numberOfWarehouse + ", " + bin + ", "
									+ "\'" + articleCode + "\', " + amount
									+ ")");
						} catch (SQLException e) {
							throw new PersistenceException(
									"Stock could not be set.", e);
						}
					} else
						throw new PersistenceException("Bin is too small.");
				} else {
					// Lagerplatz nicht leer
					// Es sind keine zwei verschiedenen Produkte pro Platz
					// möglich,
					// liegt bereits das richtige Produkt auf dem Platz?
					String exArtCode = getArticleCodeForBin(numberOfWarehouse,
							bin);
					if (exArtCode.equals(articleCode)) {
						if (maxSize >= amount * getSizeOfProduct(articleCode)) {
							// Genügend Platz, dann überschreiben
							try {
								executeUpdate("UPDATE STOCK SET("
										+ numberOfWarehouse + ", " + bin + ", "
										+ "\'" + articleCode + "\', " + amount
										+ ")");
							} catch (SQLException e) {
								throw new PersistenceException(
										"Stock could not be set.", e);
							}
						} else
							throw new PersistenceException("Bin is too small.");
					} else
						throw new PersistenceException(
								"Bin is already taken for different product.");
				}
			} else
				throw new PersistenceException("Product does not exist.");
		} else
			throw new PersistenceException("Warehouse does not exist.");

	}

	public void addWarehouse(int number, int maxNumberOfBins, int maxSizeOfBins)
			throws PersistenceException {
		if (number >= 0 && maxNumberOfBins >= 1 && maxSizeOfBins >= 1) {
			try {
				int res = executeUpdate("INSERT INTO WAREHOUSES VALUES("
						+ number + ", " + maxNumberOfBins + ", "
						+ maxSizeOfBins + ")");
				if (res == 0)
					throw new PersistenceException(
							"Warehouse could not be added.");
			} catch (SQLException e) {
				throw new PersistenceException("Warehouse could not be added.",
						e);
			}
		} else
			throw new PersistenceException("Warehouse could not be added.");
	}

}
