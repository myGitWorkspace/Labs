package labs.lab4;

public class MyQueue extends MyLinkedList{
	public MyQueue() {
		super();
	}
	
	public void offer(Element element) {
		if (element != null)
			super.addLast(element.getValue());
	}
	
	public Element peek() {
		Element elem = new Element(super.getFirst());
		return elem;
	}
	
	public Element poll() {
		Element elem = new Element(super.getFirst());
		super.removeFirst();
		return elem;
	}
}
