

public class OrderDetail {

	public String billingFirstName;
	public String billingLastName;
	public String billingStreet;
	public String billingCity;
	public String billingState;
	public String billingZip;
	public String bookSelected;
	public int quantity;
	public String balance;

	public OrderDetail(String billingFirstName, String billingLastName,
			String billingStreet, String billingCity, String billingState, String billingZip, String bookSelected, int quantity, String balance ) {
		this.billingFirstName = billingFirstName;
		this.billingLastName = billingLastName;
		this.billingStreet = billingStreet;
		this.billingCity = billingCity;
		this.billingState = billingState;
		this.billingZip = billingZip;
		this.bookSelected = bookSelected;
		this.quantity = quantity;
		this.balance = balance;
	}

	public String getBillingFirstName() {
		return billingFirstName;
	}

	public void setBillingFirstName(String billingFirstName) {
		this.billingFirstName = billingFirstName;
	}

	public String getBillingLastName() {
		return billingLastName;
	}

	public void setBillingLastName(String billingLastName) {
		this.billingLastName = billingLastName;
	}

	public String getBillingStreet() {
		return billingStreet;
	}

	public void setBillingStreet(String billingStreet) {
		this.billingStreet = billingStreet;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingState() {
		return billingState;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public String getBillingZip() {
		return billingZip;
	}

	public void setBillingZip(String billingZip) {
		this.billingZip = billingZip;
	}

	public String getBookSelected() {
		return bookSelected;
	}

	public void setBookSelected(String bookSelected) {
		this.bookSelected = bookSelected;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

}
