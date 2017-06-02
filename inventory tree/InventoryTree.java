import java.util.Scanner;
import java.io.*;

public class InventoryTree {
	protected class TreeNode {
		TreeNode left, right;
		Item data;

		public TreeNode() {
			left = null;
			right = null;
		}

		public TreeNode(Item item) {
			data = item;
			left = null;
			right = null;
		}
	}

	private TreeNode root;

	public InventoryTree(InventoryTree t) {
		if (t.root == null)
		    root = null;
		else {
			root = new TreeNode();
			TreeNode temp = t.root;
			fillValues(root, temp);
		}
	}

	public InventoryTree() {
		root = null;
	}

	private void fillValues(TreeNode parent, TreeNode temp) {
		parent.data = new Item( temp.data );
		if (temp.left != null) {
			TreeNode current = new TreeNode();
			parent.left = current;
			fillValues(current, temp.left);
		}
		else parent.left = null;
		if (temp.right != null) {
			TreeNode current = new TreeNode();
			parent.right = current;
			fillValues(current, temp.right);
		}
		else parent.right = null;
	}

	private TreeNode search(String itemNumber) {
			TreeNode present = root, parent = null;
			while(present != null && !present.data.getItemNumber().equalsIgnoreCase(itemNumber)) {
				parent = present;
				present = returnChild(present, itemNumber);
			}
			return parent;
	}

	private TreeNode returnChild(TreeNode node, String string) {
		if(node == null) return root;
		else if(node.data.getItemNumber().compareTo(string) < 0) return node.right;
		else return node.left;
	}

	public boolean addItem(Item item) {
		TreeNode getNode = search(item.getItemNumber());
		if(findItem(item.getItemNumber())) return false;
		else {
			TreeNode add = new TreeNode(item);
			if(getNode == null) root = add;
			else {
				if(getNode.data.getItemNumber().compareTo(item.getItemNumber()) < 0) getNode.right = add;
				else getNode.left = add;
			}
			return true;
		}
	}

	public boolean deleteItem(String itemNumber) {
		TreeNode getNode = search(itemNumber), child = returnChild(getNode, itemNumber);
		if(child == null || child.data.getQuantity() != 0) return false;
		boolean smaller = child.data.getItemNumber().compareTo(getNode.data.getItemNumber()) > 0? false: true;
		if(child.right == null && child.left == null) {
			if(!smaller) getNode.right = null;
			else getNode.left = null;
		}
		else if(child.left == null && child.right != null) {
			if(smaller) getNode.left = child.right;
			else getNode.right = child.right;
		}
		else if(child.left != null && child.left.right == null) {
			child.left.right = child.right;
			if(!smaller) getNode.right = child.left;
			else getNode.left = child.left;
		}
		else if(child.left != null && child.left.right != null) {
			TreeNode node = child.left.right, parent = child.left;
			while(node.right != null) {
				parent = node;
				node = node.right;
			}
			parent.right = node.left;
			child.data = node.data;
		}
		return true;
	}

	public boolean findItem(String itemNumber) {
		TreeNode getNode = search(itemNumber), node = returnChild(getNode, itemNumber);
		if(node == null || !node.data.getItemNumber().equalsIgnoreCase(itemNumber)) return false;
		else return true;
	}

	public boolean changeUnitPrice(String itemNumber, double price) {
		TreeNode child = returnChild(search(itemNumber), itemNumber);
		if(child == null || child.data.getUnitPrice() + price < 0) return false;
		child.data.setUnitPrice(price);
		return true;
	}

	public boolean adjustQuantity(String itemNumber, int quantity) {
		TreeNode child = returnChild(search(itemNumber), itemNumber);
		if(child == null || child.data.getQuantity() + quantity < 0) return false;
		child.data.setQuantity(child.data.getQuantity() + quantity);
		return true;
	}

	public String toString() {
		return recursivePrint(root);
	}

	private String recursivePrint(TreeNode node) {
		if(node == null) return "";
		else return recursivePrint(node.left) + node.data + recursivePrint(node.right);
	}

	public static void main(String[] args) throws IOException {
		InventoryTree tree = new InventoryTree();
		Scanner input = new Scanner(new File("items.txt"));
		while(input.hasNext())
			tree.addItem(new Item(input.next(), input.next(), input.nextInt(), input.nextDouble()));
		input.close();
		System.out.println(tree);
		tree.deleteItem("54325");
		tree.deleteItem("21987");
		tree.deleteItem("10123");
		tree.deleteItem("21900");
		tree.deleteItem("35984");
		tree.deleteItem("13249");
		System.out.println(tree);
	}
}