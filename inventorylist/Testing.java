
public class Testing {
	public static void main(String[] args) {
		InventoryList newList = new InventoryList();
		newList.addItem(new Item("12", "test", 29, 20.0));
		newList.addItem(new Item("14", "test1", 29, 20.0));
		newList.addItem(new Item("15", "test2", 29, 20.0));
		newList.addItem(new Item("16", "test3", 29, 20.0));
		newList.addItem(new Item("17", "test4", 29, 20.0));
		newList.addItem(new Item("18", "test5", 29, 20.0));
		newList.addItem(new Item("11", "test6", 29, 20.0));
		System.out.println(newList.addItem(new Item("18", "test7", 29, 20.0)));
		System.out.println(newList.deleteItem("22"));
		System.out.println(newList);
	}

}