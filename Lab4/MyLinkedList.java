package labs.lab4;

public class MyLinkedList {
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
	
	public void add(Integer element) {
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
	
	public void addFirst(Integer element) {		
		Element newElement = new Element(element);
		newElement.setNext(root);
		if (root == null)
			lastElem = newElement;
		root = newElement;
		size++;
	}
	
	public void addLast(Integer element) {
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
	
	public void add(int index, Integer element) {
		if (index >= size) {
			System.out.println("index value is out of the list's size");
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
	
	public Integer getFirst() {
		if (root == null) {
			System.out.println("List is empty");
			return null;
		}
		return root.getValue();
	}
	
	public Integer getLast() {
		if (root == null) {
			System.out.println("List is empty");
			return null;
		}
		return lastElem.getValue();
	}
	
	public Integer get(int index) {
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
	
	public Integer remove(int index) {
		if (root == null) {
			System.out.println("List is empty");
			return null;
		}
		if (index >= size) {
			System.out.println("index value is out of the list's size");
			return null;
		}
		if (root == lastElem && size == 1) {
			Integer currValue = root.getValue();
			root = null;
			lastElem = null;
			size--;
			return currValue;
		}
		Integer currValue = 0;
		if (index == 0) {
			currValue = root.getValue();
			root = root.next();
		} else {
			Element currElement = root;
			for (int i=index; i>1; i--)
				currElement = currElement.next();		
			currValue = currElement.next().getValue();
			currElement.setNext(currElement.next().next());
		}
		size--;
		return currValue;
	}
	
	public Integer removeFirst() {
		if (root == null) {
			System.out.println("List is empty");
			return null;
		}
		Integer currValue = root.getValue();
		if (root == lastElem && size == 1) {			
			root = null;
			lastElem = null;
		} else		
			root = root.next();
		size--;
		return currValue;
	}
	
	public Integer removeLast() {
		if (root == null) {
			System.out.println("List is empty");
			return null;
		}
		if (root == lastElem && size == 1) {
			Integer currValue = root.getValue();
			root = null;
			lastElem = null;
			size--;
			return currValue;
		}
		Integer currValue = lastElem.getValue();
		Element currElement = root;
		for (int i=0; i<size-1; i++)
			currElement = currElement.next();
		currElement.setNext(null);
		lastElem = currElement;
		size--;
		return currValue;
	}
	
	public void set(int index, Integer element) {
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
	
	public int size() {
		return size;
	}
	
	public int indexOf(Integer targetValue) {
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
	
}
