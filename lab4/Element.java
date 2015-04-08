package labs.lab4;

public class Element {
	private Integer value;
	private Element next;
	
	public Element(Integer value) {
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
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public void remove(){
			
	}
	
}
