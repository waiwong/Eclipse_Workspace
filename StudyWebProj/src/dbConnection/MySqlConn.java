package dbConnection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MySqlConn {
	private Connection con;

	public MySqlConn() {
		String CLASSFORNAME = "com.mysql.jdbc.Driver";
		String SERVANDDB = "jdbc:mysql://MySQL102:3306/test";
		String USER = "root";
		String PWD = "1234";
		try {
			Class.forName(CLASSFORNAME);
			con = (Connection) DriverManager
					.getConnection(SERVANDDB, USER, PWD);
		} catch (SQLException sqlex) {
			System.out.println("Database access exception");
			System.out.println(sqlex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getRes(String sql) {
		try {
			Statement stmt = (Statement) con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int result = stmt.executeUpdate(sql);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ResultSet getRes2(String sql) {
		try {
			Statement stmt2 = (Statement) con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet result = stmt2.executeQuery(sql);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
