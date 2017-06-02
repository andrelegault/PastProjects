/*
 * This programs makes an inventory list of items.
 * It uses the ItemNode class to support its structure.
 * 
 * @author Andre Parsons-Legault
 */
public class InventoryList {
	/*
	 * ItemNode class that is used to relate the data entries (Items) together
	 */
	protected class ItemNode {
		Item data;
		ItemNode link;
		
		/*
		 * Default constructor that initializes the data members of this ItemNode instance to null.
		 */
		public ItemNode() {
			data = null;
			link = null;
		}
		
		/*
		 * This sets the data variable of the ItemNode to the parameter that is passed. The link is set to null.
		 * 
		 * @param item Item that is to be copied into the node.
		 */
		public ItemNode(Item item) {
			data = item;
			link = null;
		}
	}
	
	/*
	 * The initial ItemNode of the InventoryList.
	 */
	ItemNode first;
	
	/*
	 * Iterates through the InventoryList's ItemNodes by using the compareTo method to find the preceding node of the searched string (str). It checks if the itemNumber of
	 * the current ItemNode is smaller than the searched string. By doing so, it ensures to return the correct ItemNode.
	 * 
	 * @param str string to find
	 * @return The ItemNode preceding the actual value searched.
	 */
	private ItemNode search(String str) {
		ItemNode present = first, previous = null;
		
		while(present != null && (present.data.getItemNumber().compareTo(str) < 0)) {
			previous = present;
			present = present.link;
		}
		
		return previous;
	}
	
	/*
	 * Provides the calling ItemNode with the link of it. Thus, giving the next node in the list. If this node isn't null of course.
	 * 
	 * @param node Preceding ItemNode of the returned result (if not null).
	 * @return ItemNode that is next in the list.
	 */
	private ItemNode nextNode(ItemNode node) {
		if(node == null) return null;
		else
			return node.link;
	}
	
	/*
	 * Default constructor. Initializes the class' data member to null.
	 */
	public InventoryList() {
		first = null;
	}
	
	/*
	 * Copy Constructor. Copies all the ItemNode of the parametized InventoryList after making sure it isn't empty.
	 * 
	 * @param other InventoryList that is to be copied.
	 */
	public InventoryList(InventoryList other) {
		if(other.isEmpty()) first = null;
		else {
			first = new ItemNode(new Item(other.first.data));
			ItemNode old = other.first, present = first;
			while(old.link != null) {
				present = present.link = new ItemNode(new Item(old.link.data));
				old = old.link;
			}
		}
	}
	
	/*
	 * Checks whether or not the calling list is empty.
	 * 
	 * @return true is the list is empty.
	 * @return false if the list is not empty.
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/*
	 * Adds an item to the list in ascending order.
	 * 
	 * @param item Item that is to be added to the list.
	 * @return true if the item was successfully added to the list.
	 * @return false if the item was not added to the list.
	 */
	public boolean addItem(Item item) {
		ItemNode getNode = search(item.getItemNumber()), newItem = new ItemNode(item);
		if(getNode == null) {
			if(first != null && first.data.getItemNumber().equals(item.getItemNumber())) return false;
			newItem.link = first;
			first = newItem;
			return true;
		}
		else {
			if(nextNode(getNode) != null && nextNode(getNode).data.getItemNumber().equals(item.getItemNumber())) return false;
			newItem.link = nextNode(getNode);
			getNode.link = newItem;
			return true;
		}
	}
	
	/*
	 * Deletes an Item in the list.
	 * 
	 * @param item Item that is to be deleted if found.
	 * 
	 * @return true if the item was successfully deleted.
	 * @return false it the item was not deleted.
	 */
	public boolean deleteItem(String item) {
		if(findItem(item) == null || !findItem(item).data.getItemNumber().toUpperCase().equals(item)) return false;
		else {
			ItemNode getNode = search(item);
			if(getNode == null) first = nextNode(first);
			else getNode.link = nextNode(getNode).link;
			return true;
		}
	}
	
	/*
	 * Checks whether or not a specific Item with a specific item number is in the list.
	 * 
	 * @param itemNumber item number that is to be compared in the list.
	 * @return true if found in the list.
	 * @return false if not found in the list.
	 */
	public ItemNode findItem(String itemNumber) {
		ItemNode getNode = nextNode(search(itemNumber));
		if(getNode == null) {
			if(first != null && first.data.getItemNumber().equals(itemNumber)) return first;
			else return null;
		}
		else {
			if(getNode.data.getItemNumber().equals(itemNumber)) return getNode;
			else return null;
		}
	}
	
	/*
	 * Adjusts the quantity of the specific item in the list.
	 * 
	 * @param item item number to look for in the list.
	 * @param quantity quantity to be added.
	 * @return true if the quantity was changed.
	 * @return false if the quantity was not changed.
	 */
	public boolean adjustQuantity(String itemNumber, int quantity) {
		ItemNode getNode = nextNode(search(itemNumber));
		if(quantity == 0) return true;
		else if(getNode == null) {
			if(first != null && first.data.getItemNumber() == itemNumber && first.data.getQuantity() + quantity >= 0) {
				first.data.setQuantity(first.data.getQuantity() + quantity);
				return true;
			}
			else return false;
		}
		else {
			if(getNode.data.getItemNumber() == itemNumber && getNode.data.getQuantity() + quantity >= 0) {
				getNode.data.setQuantity(getNode.data.getQuantity() + quantity);
				return true;
			}
			else return false;
		}
	}
	
	/*
	 * Changes the unit price of the specific item.
	 * 
	 * @param itemNumber item number to look for in the list.
	 * @param price value to set the new price to.
	 * 
	 * @return true if the price was changed.
	 * @return false if the price was not changed.
	 */
	public boolean changeUnitPrice(String item, double price) {
		ItemNode itemNode = nextNode(search(item));
		if(itemNode == null) {
			if(first != null && first.data.getItemNumber() == item && first.data.getUnitPrice() != price) {
				first.data.setUnitPrice(price);
				return true;
			}
			else return false;
		}
		else {
			if(itemNode.data.getItemNumber() == item && itemNode.data.getUnitPrice() != price) {
				itemNode.data.setUnitPrice(price);
				return true;
			}
			else return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = "";
		ItemNode present = first, previous = null;
		while(present != null) {
			previous = present;
			str += previous.data.toString() + "\n";
			present = present.link;
		}
		return str;
	}
	
	/*
	 * Obtains the string representation of the specific object in decreasing order.
	 * 
	 * @return string containing the reversed toString() method.
	 */
	public String decreasingOrder() {
		return reversePrint(first);
	}
	
	/*
	 * @param node specific node that is called by itself.
	 * @return Reversed representation of the toString() method.
	 */
	private String reversePrint(ItemNode node) {
		if(node == null) return "";
		return reversePrint(nextNode(node)) + "\n" + node.data.toString();
	}
}