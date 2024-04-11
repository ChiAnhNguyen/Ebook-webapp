package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DatabaseUtil;
import DAO.ProductDAO;
import DAO.ProductDAOIpm;
import model.Book;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/deleteproduct")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private DatabaseUtil util;
	private Book book;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		util = new DatabaseUtil();
		productDAO = new ProductDAOIpm(util);
		book = new Book();
		int productID = Integer.parseInt(request.getParameter("productId"));
		System.out.println(productID);
		try {
			productDAO.deleteImage(productID);
			productDAO.deleteProduct(productID);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("home");
		
	}

}
