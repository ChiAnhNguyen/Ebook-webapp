package Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.DatabaseUtil;
import DAO.ProductDAO;
import DAO.ProductDAOIpm;
import model.Book;
import model.BookImage;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/update-page")
@MultipartConfig(fileSizeThreshold = 1024 *1024,
maxFileSize = 1024*1024*10,
maxRequestSize = 1024*1024*11)
public class UpdateViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseUtil util;
	private ProductDAO productDAO;
	private BookImage image;
    private Book book;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		util = new DatabaseUtil();
//		productDAO = new ProductDAOIpm(util);
//		
//		String str_productID = request.getParameter("productIdToUpdate");
//		if(str_productID==null) {
//			System.out.println("khong lay dc data");
//		}
//		int productID = Integer.parseInt(str_productID);
//		System.out.println("id lay tu index la: "+productID);
//		try {
//			List<Map<String,Object>> productList = productDAO.getProductsByID(productID);
//			request.setAttribute("productList", productList);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		RequestDispatcher rd =request.getRequestDispatcher("./View/update.jsp");
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		util = new DatabaseUtil();
		productDAO = new ProductDAOIpm(util);
		
		String str_productID = request.getParameter("productIdToUpdate");
		if(str_productID==null) {
			System.out.println("khong lay dc data");
		}
		int productID = Integer.parseInt(str_productID);
		System.out.println("id lay tu index la: "+productID);
		try {
			List<Map<String,Object>> productList = productDAO.getProductsByID(productID);
			request.setAttribute("productList", productList);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd =request.getRequestDispatcher("./View/update.jsp");
		rd.forward(request, response);
		doGet(request, response);
		
	
	}
	 

}
