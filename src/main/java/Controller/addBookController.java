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
 * Servlet implementation class addBookController
 */
@WebServlet("/addbook")
@MultipartConfig(fileSizeThreshold = 1024 *1024,
				maxFileSize = 1024*1024*10,
				maxRequestSize = 1024*1024*11)
public class addBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private DatabaseUtil util = new DatabaseUtil();
    private Category category = new Category();
    private BookImage image = new BookImage();
    private int categoryID;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addBookController() {
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
		 // Nhận dữ liệu từ form
        String productName = request.getParameter("productname");
        System.out.println(productName);
        String categoryName = request.getParameter("categoryname");
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
        Part imagePart = request.getPart("image");
        System.out.println(imagePart.getSubmittedFileName());

        // Lưu trữ dữ liệu hình ảnh
        byte[] imageData = getImageData(imagePart);

        // Tạo đối tượng Book từ dữ liệu nhận được
        category = new Category();
        category.setCategoryName(categoryName);
        Book book = new Book(productName,descript,author, publisher,price,category);
        book.setProductName(productName);
        // Cài đặt các trường khác của book
        
        // Tạo đối tượng BookImage từ dữ liệu nhận được
       
        image.setImageName(imagePart.getSubmittedFileName());
        String imageName = image.getImageName();
        System.out.println("image name :"+imageName);
        image.setImageType(imagePart.getContentType());
        String imageType = image.getImageType();
        System.out.println("type: "+imageType);
        image.setImageData(imageData);
        byte[] imagedata = image.getImageData();
        

        // Gọi phương thức addProduct của DAO để thêm sách vào cơ sở dữ liệu
        try {
        	productDAO = new ProductDAOIpm(util);
        	categoryID= productDAO.findCategory(category);
        	System.out.println("CategoryID: "+categoryID);
            productDAO.addProduct(book,categoryID);
            
            image = new BookImage(book.getProductID(),imageName,imageType,imagedata);
            productDAO.addProductImage(image);
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi
        }

        // Redirect hoặc forward đến trang cần thiết
        response.sendRedirect("home");
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


