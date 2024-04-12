package model;

public class Book {
	private int productID;
	private String productName;
	private String Descript;
	private String author;
	private String publisher;
	private double price;
	private Category cate;
	
	public Book() {
		super();
	}
	
	public Book( String productName, String descript, String author, String publisher, double price,
			Category cate) {
		super();
		
		this.productName = productName;
		Descript = descript;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.cate = cate;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCate() {
		
		return cate;
	}

	public void setCate(Category cate) {
		this.cate = cate;
	}

	@Override
	public String toString() {
		return "Book [productID=" + productID + ", productName=" + productName + ", Descript=" + Descript + ", author="
				+ author + ", publisher=" + publisher + ", price=" + price + ", cate=" + cate + "]";
	}
	
	
	
	
}
