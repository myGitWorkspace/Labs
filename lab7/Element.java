package labs.lab7;


public class Element {
	private Object value;
	private Element next;
	
	public Element(Object value) {
		this.value = value;
		next = null;
	}
	
	public Element() {
		value = 0;
		next = null;
	}
	
	public boolean hasNext() {
		if (next != null)
			return true;
		else
			return false;
	}
	
	public Element next() {
		return next;
	}
	
	public void setNext(Element next) {
		this.next = next;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
}
