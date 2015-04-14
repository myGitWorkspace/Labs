package labs.lab7;

import labs.lab7.Element;

public class MyLinkedList implements MyList,Stack,Queue {
	private Element root;
	private Element lastElem;
	private int size;
	
	public MyLinkedList() {
		root = null;
		lastElem = null;
		size = 0;
	}
	
	public MyLinkedList(Element root) {		
		this.root = root;
		if (root != null) {
			lastElem = findLastElem(root);
			lastElem.setNext(null);
		}
	}
	
	private Element findLastElem( Element startElem ) {
		int counter = 1;
		Element currElem = startElem;
		while (currElem.hasNext()) {
			counter++;
			currElem = currElem.next();
		}
		size = counter;
		return currElem;
	}
	
	public void add(Object element) {
		if (element == null) {
			System.out.println("Trying to add null object");
			return;
		}
		
		Element newElement = new Element(element);
		newElement.setNext(null);
		if (root == null) {
			root = newElement;
			lastElem = newElement;
		} else {			
			lastElem.setNext(newElement);
			lastElem = newElement;		
		}
		
		size++;
		
	}
	
	public void add(int index, Object element) {
		if (index < 0 ) {
			System.out.println("Index param < 0 !!!");
			return;
		}			
		if (index >= size) {
			System.out.println("index value is out of the list's size");
			return;
		}
		if (element == null) {
			System.out.println("Trying to add null object");
			return;
		}
		Element newElement = new Element(element);
		if (root == null) {
			root = newElement;
			lastElem = newElement;
		} else {
			if (index == 0) {
				newElement.setNext(root);
				root = newElement;
			} else {
				Element currElement = root;
				for (int i=index; i>1; i--)
					currElement = currElement.next();
				newElement.setNext(currElement.next());
				currElement.setNext(newElement);
			}
		}
		size++;
	}
	
	public void addAll(Object[] elementList) {
		if (elementList == null) {
			System.out.println("Trying to add a null object list");
			return;
		}
		for (int i=0; i<elementList.length; i++)
			add(elementList[i]);
	}
	
	public void addAll(int index, Object[] elementList) {
		if (elementList == null) {
			System.out.println("Trying to add a null object list");
			return;
		}
		for (int i=0; i<elementList.length; i++)
			add(index+i,elementList[i]);
	}
	
	public Object get(int index) {
		if (index < 0 ) {
			System.out.println("Index param < 0 !!!");
			return -1;
		}
		if (root == null) {
			System.out.println("List is empty");
			return null;
		}
		if (index >= size) {
			System.out.println("index value is out of the list's size");
			return null;
		}
		Element currElement = root;
		for (int i=0; i<index; i++)
			currElement = currElement.next();
		return currElement.getValue();
	}
	
	public Object remove(int index) {
		if (index < 0 ) {
			System.out.println("Index param < 0 !!!");
			return -1;
		}
		if (root == null) {
			System.out.println("List is empty");
			return null;
		}
		if (index >= size) {
			System.out.println("index value is out of the list's size");
			return null;
		}
		if (root == lastElem && size == 1) {
			Object currValue = root.getValue();
			root = null;
			lastElem = null;
			size--;
			return currValue;
		}
		Object currValue = 0;
		if (index == 0) {
			currValue = root.getValue();
			root = root.next();
		} else {
			Element currElement = root;
			for (int i=index; i>1; i--)
				currElement = currElement.next();		
			currValue = currElement.next().getValue();
			currElement.setNext(currElement.next().next());
			if (index == size-1)
				lastElem = currElement;
		}
		size--;
		return currValue;
	}
	
	public void set(int index, Object element) {
		if (index < 0 ) {
			System.out.println("Index param < 0 !!!");
			return;
		}
		if (root == null) {
			System.out.println("List is empty");
			return;
		}
		if (index >= size) {
			System.out.println("index value is out of the list's size");
			return;
		}
		Element currElement = root;
		for (int i=0; i<index; i++)
			currElement = currElement.next();
		currElement.setValue(element);
		return;
	}
	
	public int indexOf(Object targetValue) {
		if (root == null) {
			System.out.println("List is empty");
			return -1;
		}
		Element currElement = root;
		int counter = 0;
		while (currElement.hasNext()) {
			if (currElement.getValue() == targetValue)
				return counter;
			currElement = currElement.next();
			counter++;
		}		
		return -1;	
	}
	
	public int size() {
		return size;
	}
	
	public Object[] toArray() {
		if (root == null) {
			System.out.println("List is empty");
			return null;
		}
		Object[] resultArray = new Object[size];		
		for (int i=0; i<size; i++)
			resultArray[i] = get(i);
		return resultArray;
	}
	
	public void offer(Object element) {
		if (element == null) {
			System.out.println("Input element is null");
			return;
		}	
		add(element);
	}
	
	public Object peek() {
		return get(0);
	}
	
	public Object poll() {
		return remove(0);
	}
	
	public void push(Object element) {
		if (element == null) {
			System.out.println("Input element is null");
			return;
		}
		add(element);
	}
	
	public Object pop() {
		return remove(size-1);
	}
	
}
