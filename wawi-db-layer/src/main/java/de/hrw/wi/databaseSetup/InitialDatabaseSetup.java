/**
 * 
 */
package de.hrw.wi.databaseSetup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author andriesc
 *
 */
public class InitialDatabaseSetup {
	public static void main(String[] args) throws SQLException {
		Connection c = DriverManager.getConnection(
				"jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db", "sa", "");
		c.setAutoCommit(false);
		System.out.println("Autocommit " + (c.getAutoCommit() ? "on" : "off"));

		c.createStatement().executeQuery("DROP TABLE STOCK IF EXISTS");
		c.createStatement().executeQuery("DROP TABLE WAREHOUSES IF EXISTS");
		c.createStatement().executeQuery("DROP TABLE PRODUCTS IF EXISTS");
		c.createStatement().executeQuery("DROP TABLE CUSTOMERS IF EXISTS");

		c.createStatement()
				.executeQuery(
						"CREATE TABLE PRODUCTS (articleCode varchar(13) PRIMARY KEY, name varchar(255), size INTEGER) ");
				
		c.createStatement()
				.executeQuery(
						"CREATE TABLE WAREHOUSES (number INTEGER PRIMARY KEY, maxBin INTEGER, maxSize INTEGER) ");
		c.createStatement()
				.executeQuery(
						"CREATE TABLE STOCK (number INTEGER, bin INTEGER, articleCode varchar(13), amount INTEGER,"
								+ " constraint PK_STOCK PRIMARY KEY (number, bin),"
								+ " constraint FK_PRODUCTS FOREIGN KEY (articleCode) REFERENCES PRODUCTS(articleCode),"
								+ " constraint FK_WAREHOUSE FOREIGN KEY (number) REFERENCES WAREHOUSES(number))");
		
		
		c.createStatement()
		.executeQuery(
				"CREATE TABLE CUSTOMERS (id INTEGER PRIMARY KEY, phone varchar(20),email varchar(100), adress varchar(200), lastname varchar(20), name varchar(20)) ");

		c.createStatement().executeQuery("INSERT INTO PRODUCTS VALUES ('8806085948587','Samsung BD-H5500 3D Blu-ray-Player',3)");
		c.createStatement().executeQuery("INSERT INTO PRODUCTS VALUES ('0885909560462','Apple TV MD199FD/A',2)");
		c.createStatement().executeQuery("INSERT INTO PRODUCTS VALUES ('0636926062442','TomTom Start 25 M Europe Traffic',2)");
		c.createStatement().executeQuery("INSERT INTO PRODUCTS VALUES ('8806084893826','LG 40UB800V 101 cm (40 Zoll) LED-Backlight-Fernseher',8)");
		c.createStatement().executeQuery("INSERT INTO PRODUCTS VALUES ('4250366833286','Gigaset C430 A Duo Dect-Schnurlostelefon mit Anrufbeantworter',2)");
		c.createStatement().executeQuery("INSERT INTO PRODUCTS VALUES ('0799637096608','Ipow schwarz Selfie Stange',1)");

		c.createStatement().executeQuery("INSERT INTO WAREHOUSES VALUES (0,30,5)");
		c.createStatement().executeQuery("INSERT INTO WAREHOUSES VALUES (1,30,10)");
		
		c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,0,'8806085948587',1)");
		c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,1,'8806085948587',1)");
		c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,2,'8806085948587',1)");
		c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,3,'0885909560462',2)");
		c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,4,'0636926062442',2)");
		c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,5,'4250366833286',2)");
		c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,6,'0799637096608',4)");
		c.createStatement().executeQuery("INSERT INTO STOCK VALUES (1,0,'8806084893826',1)");
		c.createStatement().executeQuery("INSERT INTO STOCK VALUES (1,1,'8806084893826',1)");
		c.createStatement().executeQuery("INSERT INTO STOCK VALUES (1,2,'0636926062442',5)");
		
		c.createStatement().executeQuery("INSERT INTO CUSTOMERS VALUES (0, '0176 2435 2345','andriessens@hs-weingarten.de', 'G�thestra�e 31 76921 G�ttingen', 'Andriessens', 'Christoph')");
		c.createStatement().executeQuery("INSERT INTO CUSTOMERS VALUES (1, '0176 2533 6735','haag@hs-weingarten.de', 'Uranusstra�e 17 89231 Ulm', 'Haag', 'Denis')");
		
		
		c.commit();
		c.close();
		System.out.println("ready");

	}

}
