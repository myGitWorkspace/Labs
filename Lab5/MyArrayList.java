package labs.lab5;

public class MyArrayList {
	private Object[] array;
	private int filled;
	public MyArrayList() {
		array = new Object[10];
		filled = -1;
	}
	
	public MyArrayList(int initialCapacity) {
		array = new Object[initialCapacity];
		filled = -1;
	}
	
	public void add(Object element) {
		if (array.length == filled+1)
			ensureCapacity(array.length + 1);
		filled++;
		array[filled] = element;
	}
	
	public void add(int index, Object element) {
		if (index >= array.length)
			return;
		if (array.length == filled+1)
			ensureCapacity(array.length + 1);
		if ( index > filled ) {
			add(element);
			return;
		}
		for (int i=filled+1; i>index; i--)
			array[i] = array[i-1];
		array[index] = element;
		filled++;
	}
	
	public void addAll(Object[] elements) {
		if (array.length < filled+1+elements.length)
			ensureCapacity(filled+1+elements.length);
		filled++;
		for (int i=0; i<elements.length; i++)
			array[filled+i] = elements[i];
		filled += elements.length-1;
	}
	
	public void addAll(int index, Object[] elements) {
		if (index >= array.length)
			return;
		if (array.length < filled+1+elements.length)
			ensureCapacity(filled+1+elements.length);
		if ( index > filled ) {
			addAll(elements);
			return;
		}
		
		for (int i=filled+elements.length; i>=index+elements.length; i--)
			array[i] = array[i-elements.length];
		filled++;
		for (int i=0; i<elements.length; i++)
			array[index+i] = elements[i];
		filled += elements.length-1;
	}
	
	public void ensureCapacity(int minCapacity) {
		if (minCapacity > array.length) {
			Object[] newArray = new Object[minCapacity];
			for (int i=0; i<array.length; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
	}
	
	public Object get(int index) {
		if (index > filled)
			return null;
		else 
			return array[index];
	}
	
	public Object remove(int index) {
		if (index > filled)
			return null;
		if (index == filled) {
			Object lastElem = array[filled];
			array[filled] = null;
			filled--;
			return lastElem;
		}
		Object deletedElem = array[index];
		for (int i=index; i<filled; i++)
			array[i] = array[i+1];
		filled--;
		ensureCapacity(array.length-1);
		return deletedElem;
	}
	
	public void set(int index, Object element) {
		if (index > filled)
			return;
		else 
			array[index] = element;
	}
	
	public int size() {
		return filled+1;
	}
}
