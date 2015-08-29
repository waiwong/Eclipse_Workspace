import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class MySqlTest {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Hello World.");
		Class.forName("com.mysql.jdbc.Driver");

		String CLASSFORNAME = "com.mysql.jdbc.Driver";
		String SERVANDDB = "jdbc:mysql://localhost:3306/test";
		String USER = "root";
		String PWD = "1234";

		Connection con = (Connection) DriverManager.getConnection(SERVANDDB,
				USER, PWD);

		Statement stmt = (Statement) con.createStatement();

		String sqlStr = "SELECT * FROM books WHERE author IN (";
		sqlStr += "'Kumar'";
		sqlStr += ") AND qty > 0 ORDER BY author ASC, title ASC";
		ResultSet rset = stmt.executeQuery(sqlStr);
		while (rset.next()) {
			System.out.println(rset.getInt("id") + " "
					+ rset.getString("author"));
		}

		PreparedStatement statement = (PreparedStatement) con
				.prepareStatement("SELECT * FROM books");
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			System.out.println(result.getString(1) + " " + result.getString(2));
		}

		System.out.println("Result get by another method.");
		Statement stmt1 = (Statement) con.createStatement();
		ResultSet myRS = stmt1.executeQuery("SELECT * FROM books");
		while (myRS.next()) {
			System.out.println(myRS.getString(1) + " " + myRS.getString(2));
		}
		// try {
		//
		// } catch (SQLException sqlex) {
		//
		// }

		System.out.println("create table");
		String sqlCreateTab = "create table if not exists test ( c_id int(11) not null auto_increment,testcol nvarchar(200) null);";
		stmt.execute(sqlCreateTab);

		System.out.println("Result get after intsert.");
		stmt.executeUpdate("insert into test(testcol) values('test');");
		ResultSet myRS1 = stmt.executeQuery("select * from test");
		while (myRS1.next()) {
			System.out.println(myRS1.getString(1) + " " + myRS1.getString(2));
		}
	}
}
