package model;

public class User {
	private int customerID;
	private String customerName;
	private String custommerpass;
	private String email;
	private String phone;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int customerID, String customerName, String custommerpass, String phone,String email) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.custommerpass = custommerpass;
		this.phone = phone;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustommerpass() {
		return custommerpass;
	}
	public void setCustommerpass(String custommerpass) {
		this.custommerpass = custommerpass;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [customerID=" + customerID + ", customerName=" + customerName + ", custommerpass=" + custommerpass
				+ ", email=" + email + ", phone=" + phone + "]";
	}
	public User(String customerName, String custommerpass, String email, String phone) {
		super();
		this.customerName = customerName;
		this.custommerpass = custommerpass;
		this.email = email;
		this.phone = phone;
	}

	
	
}
