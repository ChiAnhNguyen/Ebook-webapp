package Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.tomcat.util.codec.binary.Base64;

import DAO.DatabaseUtil;
import DAO.ProductDAO;
import DAO.ProductDAOIpm;
import model.Order;
import model.User;

@Path("/products")
public class RestfullService {
	private ProductDAO productDAO;
	private DatabaseUtil util;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String,Object>> getAllProducts() throws SQLException {
		util = new DatabaseUtil();
		productDAO = new ProductDAOIpm(util);
        
            List<Map<String, Object>> productList = productDAO.getAllProducts();
            System.out.println(productList+"\n");
            return productList;
        
    }
	
	@GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getProductById(@PathParam("productId") int productId) throws SQLException {
		util = new DatabaseUtil();
		productDAO = new ProductDAOIpm(util);
        // Gọi phương thức DAO để lấy sản phẩm
        return productDAO.getProductsByID(productId);
    }
	
	@GET
	@Path("/image/{productID}")
	@Produces("image/jpeg")
	public Response getImageByID(@PathParam("productID") int productID)throws SQLException{
		util = new DatabaseUtil();
		productDAO = new ProductDAOIpm(util);
		try {
            // Lấy dữ liệu ảnh từ cơ sở dữ liệu
            byte[] imageData = productDAO.findImageByID(productID);
            String base64String = Base64.encodeBase64String(imageData);
            if (imageData != null) {
                // Trả về ảnh dưới dạng dữ liệu nhị phân
                return Response.ok(imageData, "image/jpeg").build();
            } else {
                // Trả về mã trạng thái 404 nếu không tìm thấy ảnh
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ nếu có lỗi khi truy vấn cơ sở dữ liệu
            e.printStackTrace();
            return Response.serverError().build();
        }
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertUser(User user) {
		util = new DatabaseUtil();
		productDAO = new ProductDAOIpm(util);
		int row =productDAO.insertUser(user);
		if(row >0) {
			return "thêm thành công";
		}
		return "thêm thất bại";
		
	}
	@GET
	@Path("/user/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByName(@PathParam("username") String name) {
		util = new DatabaseUtil();
		productDAO = new ProductDAOIpm(util);
		User user = new User();
		user= productDAO.findUserByname(name);
		return user;
	}
	
	@POST
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	public int addOrder(Order order) {
		util = new DatabaseUtil();
		productDAO = new ProductDAOIpm(util);
		int rows = productDAO.addOrder(order);
		return rows;
	}
	
}
