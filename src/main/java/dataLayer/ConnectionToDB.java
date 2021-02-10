package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * The pourpose of this class is to provide a fast connection to the Data Base for 
 * other classes which can require it.
 */

public class ConnectionToDB {

	/*
	 * Create strings with your DB data (not necessary the same as the ones below).
	 * >variables named in uppercase are CONSTANTS, name parts are separated by underscores (_).
	 * 
	 * >words following "?" IN AN URL ADRESS are called queries. They provide aditional information to servers.
	 * 
	 * >just write this: "jdbc:mysql://{YOUR HOST NAME}:{PORT}//{YOUR DATABASE NAME}?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true"
	 *  you'll get those details when you create your database in the SQL Server installation.
	 */
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	private static final String JDBC_USER = "root"; 
	private static final String JDBC_PASSWORD = "1234";
	
	/*
	 * Create the methods to:
	 * > Establish the connection and close it;
	 * > Close ResultSetm Statement and PreparedStatement,
	 * 	 We'll see in the CarJDBC class what all these mean.
	 */
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(JDBC_URL, JDBC_USER,JDBC_PASSWORD);
		//throws an exception if something goes wrong, we will capture it in the CarJDBC class.
	}
	
	public static void close(ResultSet rs) throws SQLException {
		rs.close();
	}
	
	public static void close(Statement smtm) throws SQLException {
		smtm.close();
	}
	
	public static void close(PreparedStatement smtm) throws SQLException {
		smtm.close();
	}
	
	public static void close(Connection conn) throws SQLException {
		conn.close();
	}
}

