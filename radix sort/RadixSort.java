import java.util.Scanner;
import java.io.*;


public class RadixSort {

	private int[] array;
	private MyQueue<Integer>[] q;
	private int size;
	private final int MAX_SIZE = 500;

	public RadixSort() {
		size = 0;
		array = new int[MAX_SIZE];
		q = new MyQueue[10];
		for(int i = 0; i < 10; i++) q[i] = new MyQueue<Integer>();
	}

	public RadixSort(int size) {
		array = new int[size];
		this.size = 0;
		q = new MyQueue[10];
		for(int i = 0; i < 10; i++) q[i] = new MyQueue<Integer>();
	}

	public int fillValues(Scanner in) throws IOException {
		while(in.hasNextInt()) {
			array[size] = in.nextInt();
			size++;
		}
		return size;
	}

	private boolean isSorted() {
		boolean check = true;
		for(int i = 0; i < size-1; i++) {
			if(array[i] > array[i+1]) check = false;
		}
		return check;
	}

	public void sort() {
		int count = 0;
		while(!isSorted()) {
			for(int i = 0; i < size; i++) q[getNumber(array[i], count)].addToQueue(array[i]);
			unload();
			count++;
		}
	}

	public void unload() {
		int count = 0;
		for(int i = 0; i < 10; i++) {
			while(!q[i].isEmpty()) {
				array[count] = q[i].deleteFromQueue();
				count++;
			}
		}
	}

	private int getNumber(int num, int count) {
		return (int)(num % Math.pow(10, count+1) / Math.pow(10, count));
	}

	public String toString() {
		String str = "";
		for(int i = 0; i < size; i++) {
			str += i % 10 == 0? "\n":"";
			str += String.format("%6d ", array[i]);
		}
		return str;
	}

	public static void main(String[] args) throws IOException {
		RadixSort sort = new RadixSort();
		sort.fillValues(new Scanner(new File("numbers.txt")));
		sort.sort();
		System.out.println(sort);
	}
}