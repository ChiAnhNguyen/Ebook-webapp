package model;

public class BookImage {
	private int imageID;
	private int productID;
	private String imageName;
	private String imageType;
	private byte[] imageData;
	
	public BookImage(int productID, String imageName, String imageType, byte[] imageData) {
		super();
		this.productID = productID;
		this.imageName = imageName;
		this.imageType = imageType;
		this.imageData = imageData;
	}
	public BookImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
}
