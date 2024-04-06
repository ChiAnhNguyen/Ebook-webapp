package model;

public class Book {
	int productID;
	String productName;
	String Descript;
	String author;
	String publisher;
	int categoryID;
	
	public Book() {
		super();
	}
	
	public Book(int productID, String productName, String descript, String author, String publisher, int categoryID) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.Descript = descript;
		this.author = author;
		this.publisher = publisher;
		this.categoryID = categoryID;
	}

	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescript() {
		return Descript;
	}
	public void setDescript(String decript) {
		Descript = decript;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	
	@Override
	public String toString() {
		return "book [productID=" + productID + ", productName=" + productName + ", Descript=" + Descript + ", author="
				+ author + ", publisher=" + publisher + ", categoryID=" + categoryID + "]";
	}
	
	
}
