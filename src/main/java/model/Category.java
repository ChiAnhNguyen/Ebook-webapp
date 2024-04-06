package model;

public class Category {
	int categoryID;
	int productID;
	String categoryName;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Category(int categoryID, int productID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.productID = productID;
		this.categoryName = categoryName;
	}

	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
