package repositories.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

public class SQLDataSource extends DataSource {

	private String driver;

	private String host;

	private String port;

	private String user;

	private String password;

	private String database;

	private boolean status = false;

	public SQLDataSource dbName(String db) {
		this.database = db;
		return this;
	}

	public SQLDataSource driver(String driver) {
		this.driver = driver;
		return this;
	}

	public SQLDataSource port(String port) {
		this.port = port;
		return this;
	}

	public SQLDataSource pass(String pass) {
		this.password = pass;
		return this;
	}

	public SQLDataSource user(String user) {
		this.user = user;
		return this;
	}

	public SQLDataSource host(String host) {
		this.host = host;
		return this;
	}

	@Override
	protected Connection stablishConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
			conn.setAutoCommit(false);
			LogManager.getLogger(this.getClass().getName()).log(Level.INFO, "Database conexion status: OK");
			status = true;
		} catch (Exception e) {
			LogManager.getLogger(this.getClass().getName()).log(Level.INFO, "Database conexion status: FAILS");
		}
		return conn;
	}
}
