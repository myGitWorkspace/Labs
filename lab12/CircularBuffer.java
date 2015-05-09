package labs.lab12;

public class CircularBuffer {

	private int size;
	private Object[] arrayBuffer;
	private int indexIn = 0;
	private int indexOut = 0;
	private boolean isEmpty = true;
	private boolean isFull = false;
	
	public CircularBuffer() {
		this.size = 10;
		arrayBuffer = new Object[size];
	}
	
	public synchronized Object get() {
		
		while(isEmpty) {
			try {
				wait();
			} catch(InterruptedException e) {}			
		}
		
		Object returnElement = arrayBuffer[indexOut++];
		indexOut %= this.size;
		if (isFull) {
			isFull = false;
			notifyAll();
		} else if ( indexOut == indexIn ) {
			isEmpty = true;						
		}
		System.out.println("indexIn="+indexIn+"; indexOut="+indexOut);
		return returnElement;		
	}
	
	public synchronized void put(Object inputObject) {
		
		while(isFull) {
			try {
				wait();
			} catch(InterruptedException e) {}			
		}
		
		arrayBuffer[indexIn++] = inputObject;
		indexIn %= this.size;
		
		if (isEmpty) {
			isEmpty = false;
			notifyAll();
		} else if ( indexOut == indexIn ) {
			isFull = true;
		}
		System.out.println("indexIn="+indexIn+"; indexOut="+indexOut);
		
	}
	
}
