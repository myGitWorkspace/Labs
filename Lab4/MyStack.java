package labs.lab4;

public class MyStack extends MyLinkedList {
	public MyStack() {
		super();
	}
	
	public void push(Element element) {
		super.addLast(element.getValue());
	}
	
	public Element pop() {
		Element elem = new Element(super.getLast());
		super.removeLast();
		return elem;
	}
}
