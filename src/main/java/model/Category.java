package model;

public class Category {
	int categoryID;
	
	String categoryName;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Category(int categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
	
		this.categoryName = categoryName;
	}

	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
