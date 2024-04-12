package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;

import model.Book;
import model.BookImage;
import model.Category;

public class ProductDAOIpm implements ProductDAO{
//	private DatabaseUtil connection;
	private Connection connect;
	public ProductDAOIpm(DatabaseUtil util) {
		try {
			this.connect = util.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public List<Map<String, Object>> getAllProducts() throws SQLException {
		List<Map<String, Object>> productList = new ArrayList<>();
        String query = "SELECT product.productID,product.categoryID, product.productName, product.Descript, product.author, product.publisher,categoryname, product.price,  productimage.imageData "
        		+ "FROM product  INNER JOIN category ON product.categoryID = category.categoryID "
        		+ "INNER JOIN productImage ON product.productID = productimage.productID ";
        		
        try (PreparedStatement statement = connect.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Map<String, Object> productMap = new HashMap<>();
                Book product = new Book();
                Category category = new Category();
                if(resultSet.getString("categoryID") != null){
                category.setCategoryID(resultSet.getInt("categoryID"));}
                if (resultSet.getString("categoryname") != null) {
                    category.setCategoryName(resultSet.getString("categoryname"));
                }
                product.setProductID(resultSet.getInt("productID"));
                product.setProductName(resultSet.getString("productName"));
                product.setDescript(resultSet.getString("Descript"));
                product.setAuthor(resultSet.getString("author"));
                product.setPublisher(resultSet.getString("publisher"));
                
                product.setPrice(resultSet.getDouble("price"));
                
                // Lấy dữ liệu hình ảnh từ kết quả truy vấn
                byte[] imageData = resultSet.getBytes("imageData");
                String base64String = Base64.encodeBase64String(imageData);
                // Tạo đường dẫn tới hình ảnh
               
                // Thêm thông tin sản phẩm, đường dẫn hình ảnh và dữ liệu hình ảnh vào Map
                productMap.put("product", product);
                productMap.put("category", category);
                productMap.put("imageData",base64String );
                // Thêm Map này vào danh sách
                productList.add(productMap);
            }
        }
        return productList;
	}

	@Override
	public void addProduct(Book product, int categoryID) throws SQLException {
		String query = "INSERT INTO product (productName, Descript, author, publisher,categoryID, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connect.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getDescript());
            statement.setString(3, product.getAuthor());
            statement.setString(4, product.getPublisher());
            statement.setInt(5, categoryID);
            statement.setDouble(6, product.getPrice());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Inserting product failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int productId = generatedKeys.getInt(1);
                    product.setProductID(productId);
                } else {
                    throw new SQLException("Inserting product failed, no ID obtained.");
                }
            
            }
            
    }
	}
	

	@Override
	public boolean updateProduct(Book product,int categoryID) throws SQLException {
String query = "UPDATE product SET productName = ?, Descript = ?, author = ?, publisher = ?, price = ?, categoryID = ? WHERE productID = ?";
        
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getDescript());
            statement.setString(3, product.getAuthor());
            statement.setString(4, product.getPublisher());
            statement.setDouble(5, product.getPrice());
            statement.setInt(6, categoryID);
            statement.setInt(7, product.getProductID());
            
            int rowsUpdated = statement.executeUpdate();
            
            return rowsUpdated > 0;
            
            
        }
    }
	

	@Override
	public boolean deleteProduct(int productId) throws SQLException {
		String query = "DELETE FROM product where productID =?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setInt(1, productId);
			int rowUpdate = statement.executeUpdate();
			
			
			return rowUpdate >0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public void addProductImage(BookImage image) {
		String query = "INSERT INTO productImage (productID, imageName, imageType, imageData) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setInt(1, image.getProductID());
            statement.setString(2,image.getImageName());
            statement.setString(3, image.getImageType());
            statement.setBytes(4, image.getImageData());
            statement.executeUpdate();
            statement.close();
            connect.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
    }

	@Override
	public boolean updateProdcutImage(int productID, BookImage image) {
		String query = "UPDATE productImage set imageName =?, imageType=?, imageData =? where productID =?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, image.getImageName());
            statement.setString(2,image.getImageType());
            statement.setBytes(3, image.getImageData());
            statement.setInt(4, productID);
            int rowupdated = statement.executeUpdate();
            statement.close();
            return rowupdated>0;
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}

	@Override
	public boolean deleteImage(int productID) {
		String query = "DELETE FROM productimage where productID =?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setInt(1, productID);
			int rowUpdate = statement.executeUpdate();
			
			
			return rowUpdate >0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
//	public static void main(String[] args) {
//		DatabaseUtil util = new DatabaseUtil();
//		ProductDAOIpm DAO = new ProductDAOIpm(util);
//	}


	@Override
	public int findCategory(Category category) {
		String query = "SELECT categoryID , categoryname FROM category WHERE categoryname = ? ";
	    int categoryId = 0;
	    try (PreparedStatement statement = connect.prepareStatement(query)) {
	        statement.setString(1, category.getCategoryName());
	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            categoryId = rs.getInt("categoryID");
	           
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return categoryId;
	}


	@Override
	public String findCategoryName(Category category) throws SQLException {
		String query = "SELECT  categoryname FROM category WHERE categoryID = ? ";
		String categoryname ="";
	    try (PreparedStatement statement = connect.prepareStatement(query)) {
	        statement.setInt(1, category.getCategoryID());
	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            categoryname = rs.getString("categoryname");
	           
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return categoryname;
	}


	@Override
	public List<Map<String, Object>> getProductsByID(int productId) throws SQLException {
		List<Map<String, Object>> productList = new ArrayList<>();
		Map<String, Object> productMap = new HashMap<>();
	    String query = "SELECT productName, product.categoryID, Descript, author, publisher, category.categoryName, price, productimage.imageData, product.productID " +
	                   "FROM product " +
	                   "INNER JOIN category ON product.categoryID = category.categoryID " +
	                   "INNER JOIN productimage ON product.productID = productimage.productID " +
	                   "WHERE product.productID = ?";

	    try (PreparedStatement statement = connect.prepareStatement(query)) {
	        statement.setInt(1, productId);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	            	Book book = new Book();
	            	Category category = new Category();
	            	book.setProductName(resultSet.getString("productName"));
	            	category.setCategoryID(resultSet.getInt("categoryID"));
	            	book.setDescript(resultSet.getString("Descript"));
	            	book.setAuthor(resultSet.getString("author"));
	            	book.setPrice(resultSet.getDouble("price"));
	            	book.setPublisher(resultSet.getString("publisher"));
	            	category.setCategoryName(resultSet.getString("categoryName"));
	              

	                // Lấy dữ liệu hình ảnh từ kết quả truy vấn và encode sang base64
	                byte[] imageData = resultSet.getBytes("imageData");
	                book.setProductID(resultSet.getInt("productID"));
	                String base64String = Base64.encodeBase64String(imageData);
	                productMap.put("imageData", imageData);
	                productMap.put("product",book);
	                productMap.put("category", category);
	                productList.add(productMap);
	            }
	        }
	    }

	    return productList;
	}
		
	}


