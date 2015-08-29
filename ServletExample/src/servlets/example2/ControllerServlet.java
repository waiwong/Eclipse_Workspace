package servlets.example2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/shopping")
// Define URL pattern (for servlet 3.0 only)
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger log = LogManager
			.getLogger(ControllerServlet.class);

	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("doGet called.");
		doPost(request, response); // Same as doPost()
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("doPost called.");
		// Retrieve the current session, or create a new session if no session
		// exists.
		HttpSession session = request.getSession(true);

		// Retrieve the shopping cart of the current session.
		List<CartItem> theCart = (ArrayList<CartItem>) session
				.getAttribute("cart");

		// For dispatching the next Page
		String nextPage = "";
		String todo = request.getParameter("todo");

		if (todo == null) {
			// First access - redirect to order.jsp
			nextPage = "/example2/order.jsp";
		} else if (todo.equals("add")) {
			// Submitted by order.jsp, with request parameters bookID and qty.
			// Create a CartItem for the selected book, and add it into the cart
			CartItem newCartItem = new CartItem(Integer.parseInt(request
					.getParameter("bookID")), Integer.parseInt(request
					.getParameter("qty")));
			if (theCart == null) { // shopping cart is empty
				theCart = new ArrayList<>();
				theCart.add(newCartItem);
				session.setAttribute("cart", theCart); // binds cart to session
			} else {
				// Check if this book is already in the cart.
				// If so, update the qty ordered. Otherwise, add it to the cart.
				boolean found = false;
				Iterator iter = theCart.iterator();
				while (!found && iter.hasNext()) {
					CartItem aCartItem = (CartItem) iter.next();
					if (aCartItem.getBookID() == newCartItem.getBookID()) {
						aCartItem.setQtyOrdered(aCartItem.getQtyOrdered()
								+ newCartItem.getQtyOrdered());
						found = true;
					}
				}
				if (!found) { // Add it to the cart
					theCart.add(newCartItem);
				}
			}
			// Back to order.jsp for more order
			nextPage = "/example2/order.jsp";
		} else if (todo.equals("remove")) {
			// Submitted by order.jsp, with request parameter cartIndex.
			// Remove the CartItem of cartIndex in the cart
			int cartIndex = Integer.parseInt(request.getParameter("cartIndex"));
			theCart.remove(cartIndex);
			// Back to order.jsp for more order
			nextPage = "/example2/order.jsp";
		} else if (todo.equals("checkout")) {
			// Submitted by order.jsp.
			// Compute the total price for all the items in the cart
			float totalPrice = 0;
			int totalQtyOrdered = 0;
			for (CartItem item : theCart) {
				float price = item.getPrice();
				int qtyOrdered = item.getQtyOrdered();
				totalPrice += price * qtyOrdered;
				totalQtyOrdered += qtyOrdered;
			}
			// Format the price in float to 2 decimal places
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb); // Send all output to sb
			formatter.format("%.2f", totalPrice); // 2 decimal places
			// Put the total price and qty in request header
			request.setAttribute("totalPrice", sb.toString());
			request.setAttribute("totalQtyOrdered", totalQtyOrdered + "");
			// Dispatch to checkout.jsp
			nextPage = "/example2/checkout.jsp";
		}

		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext
				.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}