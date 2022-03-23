package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
	public Connection getConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(
				"jdbc:sqlserver://DUC;databaseName=ShoppingDB;encrypt=true;trustServerCertificate=true;", "sa", "sa");
	}
}
