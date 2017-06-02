/**
 *
 * This is the Item class.
 * The specified object has four data members: the item's number, description, quantity and price.
 * 
 * @author Andre Parsons-Legault
 */
public class Item {
	private String itemNumber;
	private String description;
	private int quantity;
	private double price;
	
	/**
	 * the Zero-parameter constructor. Sets all values to null and 0, respectively.
	 */
	public Item() {
		setItem("", "", 0, 0.0);
	}
	
	/**
	 * Parameter constructor
	 * @param itemNumber the item's number
	 * @param description the item's description
	 * @param quantity the item's quantity
	 * @param price the item's price
	 */
	public Item(String itemNumber, String description, int quantity, double price) {
		setItem(itemNumber, description, quantity, price);
	}
	
	/**
	 * Copy constructor
	 * @param toCopy the other Item that has to be copied
	 */
	public Item(Item toCopy) {
		setItem(toCopy.getItemNumber(), toCopy.getDescription(), toCopy.getQuantity(), toCopy.getUnitPrice());
	}
	
	/**
	 * 
	 * @param itemNumber the item number
	 * @param description the description of the item
	 * @param quantity the quantity of the item
	 * @param price the price of the item
	 */
	public void setItem(String itemNumber, String description, int quantity, double price) {
		this.itemNumber = itemNumber;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	
	/**
	 * Gets the item number
	 * @return item number
	 */
	public String getItemNumber() {
		return itemNumber;
	}
	
	/**
	 * Gets the item's description
	 * @return item description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Gets the quantity of the item
	 * @return item quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Gets the unit price of the item
	 * @return unit price
	 */
	public double getUnitPrice() {
		return price;
	}
	
	/**
	 * Sets the quantity of the item
	 * @param quantity Quantity to set it to
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Sets the unit price of the item
	 * @param price unit price to set it to
	 */
	public void setUnitPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return the string representation of the item
	 */
	public String toString() {
		return "this item's Item ID is: " + itemNumber + ", the description is: " + description + ", the quanity is: " + quantity + ", the price is: " + price;
	}
	
	/**
	 * Checks if the item number is equal to the other item number
	 * @param otherItem the item to be compared to
	 * @return true if the item numbers are equal, false otherwise
	 */
	public boolean equals(Item otherItem) {
		return this.itemNumber.equals(otherItem.itemNumber);
	}
	
	/**
	 * Returns respective values based on the item numbers
	 * @param other Other item to compare to
	 * @return -1 if this item's number is smaller than the other's item number
	 * @return 1 if this item's number is bigger than the other item's number
	 * @return 0 if this item's number is equal to the other item's number
	 */
	public int compareTo(Item otherItem) {
		if(itemNumber.compareTo(otherItem.itemNumber) < 0) return -1;
		else if(itemNumber.compareTo(otherItem.itemNumber) > 0) return 1;
		else return 0;
	}
}
