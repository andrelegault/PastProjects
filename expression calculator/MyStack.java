/**
 * The Generic MyStack class uses the ArrayList class to generate a Stack entity.
 * Basic features are implemented such as:
 * 	Copying another list.
 * 	Checking whether or not the list is empty.
 *  Pushing.
 * 	Popping.
 * 	Looking on top of the list.
 * 	Using the Stack class' toString method.
 *
 * This class is generic, it accepts any type of value.
 * 
 * @author André Parsons-Legault
 */

import java.util.ArrayList; // Needed to use the ArrayList.
import java.util.Scanner;

/**
 * Generic MyStack class.
 */
public class MyStack<T> {

	/**
	 * Global variable that holds all of the information.
	 */
	private ArrayList<T> stack;
	
	/**
	 * Default constructor.
	 * 
	 * Instantiates the, to be empty, global variable.
	 */
	public MyStack() {
		stack = new ArrayList<T>();
	}
	
	/**
	 * Copy constructor.
	 * 
	 * Copies the data from the other Stack by using a condensed for-loop.
	 * Each iteration of the loop adds data onto the global variable after checking if the list was empty.
	 * 
	 * @param other Stack that is to be copied.
	 */
	public MyStack(MyStack<T> other) {
		stack = new ArrayList<T>();
		if(!other.isEmpty()) {
			for(T data: other.stack)
				stack.add(data);
		}
	}
	
	/**
	 * Checks whether or not the global variable is empty.
	 *
	 * @return True if the list is empty.
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * Pushes data onto the top of the global variable.
	 * 
	 * @param data Data that is to be added.
	 */
	public void push(T data) {
		stack.add(data);
	}
	
	/**
	 * After checking if the list is empty, it returns the generic entity that is on top of the global variable and removes it afterwards.
	 *
	 * @return T The last data entry on the global variable that is never to be found again. If the list is empty null is returned.
	 */
	public T pop() {
		if(!isEmpty()) {
			int size = stack.size() - 1;
			T data = stack.get(size);
			stack.remove(size);
			return data;
		}
		else return null;
	}
	
	/**
	 * Similar to the pop method. This method returns but does not remove the last entry, if present.
	 * 
	 * @return T Entry that is on top of the global variable. Null if there is none.
	 */
	public T lookUp() {
		if(!isEmpty()) {
			return stack.get(stack.size() - 1);
		}
		else return null;
	}
	
	public String toString() {
		return stack.toString();
	}
}