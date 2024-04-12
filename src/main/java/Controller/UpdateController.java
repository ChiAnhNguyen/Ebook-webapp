package Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

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
import model.Category;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/updateproduct")
@MultipartConfig(fileSizeThreshold = 1024 *1024,
maxFileSize = 1024*1024*10,
maxRequestSize = 1024*1024*11)
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseUtil util;
	private ProductDAO productDAO;
	private BookImage image;
    private Book book;
    private Category category;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
		util = new DatabaseUtil();
		productDAO = new ProductDAOIpm(util);
		image = new BookImage();
		String str_productID = request.getParameter("productIdToUpdateForm");
		
		int productID = Integer.parseInt(str_productID);
		System.out.println("product id tu update.jsp: "+productID);
		 
		  // Tạo đối tượng BookImage từ dữ liệu nhận được
		Part imagePart = request.getPart("imageData");
        byte[] imageData = getImageData(imagePart);
        String productName = request.getParameter("productName");
        System.out.println(productName);
        String categoryName = request.getParameter("categoryName");
        System.out.println(categoryName);
        String price_inUI = request.getParameter("price");
        System.out.println(price_inUI);
        double price = Double.parseDouble(price_inUI);
        String author = request.getParameter("author");
        System.out.println(author);
        String publisher = request.getParameter("publisher");
        System.err.println(publisher);
        String descript = request.getParameter("descript");
        System.out.println(descript);
	       
        image.setImageName(imagePart.getSubmittedFileName());
        String imageName = image.getImageName();
        System.out.println("image name :"+imageName);
        image.setImageType(imagePart.getContentType());
        String imageType = image.getImageType();
        System.out.println("type: "+imageType);
        image.setImageData(imageData);
        byte[] imagedata = image.getImageData();
        category = new Category();
		category.setCategoryName(categoryName);
		book = new Book();
		book.setProductName(productName);
		book.setProductID(productID);
		book.setDescript(descript);
		book.setPublisher(publisher);
		book.setAuthor(author);
		book.setCate(category);
		book.setPrice(price);
		
		
		
		
	
		try {
			productDAO.updateProdcutImage(productID, image);
			int categotyID = productDAO.findCategory(category);
			productDAO.updateProduct(book, categotyID);
			response.sendRedirect("home");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private byte[] getImageData(Part imagePart) throws IOException {
        InputStream inputStream = imagePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
    }

}
