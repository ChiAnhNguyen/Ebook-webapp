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
        String query = "SELECT product.productID, product.productName, product.Descript, product.author, product.publisher, product.price, productimage.imageName, productimage.imageData "
        		+ "FROM product INNER JOIN productImage ON product.productID = productimage.productID";
        try (PreparedStatement statement = connect.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Map<String, Object> productMap = new HashMap<>();
                Book product = new Book();
                product.setProductID(resultSet.getInt("productID"));
                product.setProductName(resultSet.getString("productName"));
                product.setDescript(resultSet.getString("Descript"));
                product.setAuthor(resultSet.getString("author"));
                product.setPublisher(resultSet.getString("publisher"));
                product.setPrice(resultSet.getDouble("price"));
                // Lấy tên hình ảnh từ kết quả truy vấn
                String imageName = resultSet.getString("imageName");
                // Lấy dữ liệu hình ảnh từ kết quả truy vấn
                byte[] imageData = resultSet.getBytes("imageData");
                // Tạo đường dẫn tới hình ảnh
                String imagePath = "path/to/images/" + imageName; // Thay đổi đường dẫn tùy thuộc vào cách bạn lưu trữ hình ảnh
                // Thêm thông tin sản phẩm, đường dẫn hình ảnh và dữ liệu hình ảnh vào Map
                productMap.put("product", product);
                productMap.put("imagePath", imagePath);
                productMap.put("imageData", imageData);
                // Thêm Map này vào danh sách
                productList.add(productMap);
            }
        }
        return productList;
	}

	@Override
	public void addProduct(Book product) throws SQLException {
		String query = "INSERT INTO product (productName, Descript, author, publisher, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connect.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getDescript());
            statement.setString(3, product.getAuthor());
            statement.setString(4, product.getPublisher());
            statement.setDouble(5, product.getPrice());
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
	public boolean updateProduct(Book product) throws SQLException {
String query = "UPDATE product SET productName = ?, Descript = ?, author = ?, publisher = ?, price = ?, categoryID = ? WHERE productID = ?";
        
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setString(2, product.getDescript());
            statement.setString(3, product.getAuthor());
            statement.setString(4, product.getPublisher());
            statement.setDouble(5, product.getPrice());
            statement.setInt(6, product.getCate().getCategoryID());
            statement.setInt(7, product.getProductID());
            
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            connect.close();
            return rowsUpdated > 0;
            
            
        }
    }
	

	@Override
	public boolean deleteProduct(int productId) throws SQLException {
		String query = "DELETE product where productID =?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setInt(1, productId);
			int rowUpdate = statement.executeUpdate();
			statement.close();
			connect.close();
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
            int rowupdated = statement.executeUpdate();
            statement.close();
            connect.close();
            return rowupdated>0;
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
	}

	@Override
	public boolean deleteImage(int productID) {
		String query = "DELETE productimage where productID =?";
		try (PreparedStatement statement = connect.prepareStatement(query)) {
			statement.setInt(1, productID);
			int rowUpdate = statement.executeUpdate();
			statement.close();
			connect.close();
			return rowUpdate >0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public static void main(String[] args) {
		DatabaseUtil util = new DatabaseUtil();
		ProductDAOIpm DAO = new ProductDAOIpm(util);
	}


	@Override
	public int findCategory(Category category) {
		String query = "SELECT categoryID , categoryname From category WHERE categoryname = ? ";
		int categoryId = 0;
		try(PreparedStatement statement = connect.prepareStatement(query)){
			ResultSet rs = statement.executeQuery();
			statement.setString(1, category.getCategoryName());
			while(rs.next()) {
				category.setCategoryID(rs.getInt("categoryID"));
				categoryId = category.getCategoryID();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryId;
	}
		
	}


