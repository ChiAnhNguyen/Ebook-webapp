package DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import model.Book;
import model.BookImage;
import model.Category;

public interface ProductDAO {
	// Lấy danh sách sản phẩm
   List<Map<String, Object>> getAllProducts() throws SQLException ;
   
   List<Map<String, Object>> getProductsByID(int productID) throws SQLException ;
		
    
    // Thêm sản phẩm mới
    void addProduct(Book book, int categoryID) throws SQLException;
    
    void addProductImage(BookImage image) ;
    
    // Sửa thông tin sản phẩm
    boolean updateProduct(Book product,int categoryID) throws SQLException;
    
    // Xóa sản phẩm
    boolean deleteProduct(int productId) throws SQLException;
    
    boolean updateProdcutImage(int productID, BookImage image);
    
    boolean deleteImage(int productID);
    
    int findCategory(Category category);
    String findCategoryName(Category categoty) throws SQLException;
}
