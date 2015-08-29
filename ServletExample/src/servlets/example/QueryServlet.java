package servlets.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// The doGet() runs once per HTTP GET request to this servlet.
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Set the MIME type for the response message
		response.setContentType("text/html");
		// Get a output writer to write the response message into the network
		// socket
		PrintWriter out = response.getWriter();

		try {
			database.manager.sqlite.DBConnect dbconn = new database.manager.sqlite.DBConnect();
			// Step 3: Execute a SQL SELECT query
			String sqlStr = "SELECT * FROM books WHERE author = " + "'"
					+ request.getParameter("author") + "'"
					+ " AND qty > 0 ORDER BY author ASC, title ASC";

			// Print an HTML page as output of query
			out.println("<html><head><title>Query Results</title></head><body>");
			out.println("<h2>Thank you for your query.</h2>");
			out.println("<p>You query is: " + sqlStr + "</p>"); // Echo for
			// debugging
			ResultSet rset = dbconn.executeQuery(sqlStr); // Send the query to
			// the
			// server

			// Step 4: Process the query result
			int count = 0;
			while (rset.next()) {
				// Print a paragraph <p>...</p> for each row
				out.println("<p>" + rset.getString("author") + ", "
						+ rset.getString("title") + ", $"
						+ rset.getDouble("price") + "</p>");
				++count;
			}
			out.println("<p>==== " + count + " records found ====</p>");
			out.println("</body></html>");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
			// try {
			// // Step 5: Close the Statement and Connection
			// // if (stmt != null)
			// // stmt.close();
			// // if (conn != null)
			// // conn.close();
			// } catch (SQLException ex) {
			// ex.printStackTrace();
			// }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
