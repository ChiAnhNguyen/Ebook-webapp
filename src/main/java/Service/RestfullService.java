package Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import DAO.DatabaseUtil;
import DAO.ProductDAO;
import DAO.ProductDAOIpm;

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
	
}
