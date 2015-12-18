/**
 * 
 */
package de.hrw.wi.persistence.dbunit;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 * @author andriesc
 *
 */
public class DBUnitExporter {
	private final static String dbURL = "jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db";
	private final static String user = "sa";
	private final static String password = "";

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// database connection
		Connection jdbcConnection = DriverManager.getConnection(dbURL, user,
				password);
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

		// DBUnit's DatabaseSequenceFilter automatically determines table order
		// using foreign keys information. Without, IDatabaseTester.onSetup()
		// would produce constraint violation exceptions on foreign keys.
		ITableFilter filter = new DatabaseSequenceFilter(connection);

		// full database export
		IDataSet fullDataSet = new FilteredDataSet(filter, connection.createDataSet());

		FlatXmlDataSet.write(fullDataSet, new FileOutputStream(
				"db_full_export.xml"));
		connection.close();
		jdbcConnection.close();
	}

}
