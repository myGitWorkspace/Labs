package labs.lab12;

public class Lab12Testing {

	public static void main(String[] args) {
		
		CircularBuffer buffer = new CircularBuffer();
		
		for (int i=0; i<20; i++) {
			Thread readThread = new Thread(new ReadingThread(i, buffer));
			readThread.start();
		}
		
		for (int i=0; i<25; i++) {
			Thread writeThread = new Thread(new WritingThread(i, buffer));
			writeThread.start();
		}
		
	}
	
}

class ReadingThread implements Runnable {
	
	private int id;
	private CircularBuffer buffer;
	
	public ReadingThread(int id, CircularBuffer buffer) {
		this.id = id;
		this.buffer = buffer;
	}
	
	public void run() {
		System.out.println("Reading thread # "+id+" trying to read from buffer");
		Object dataFromBuffer = buffer.get();
		System.out.println("Reading thread # "+id+" read data = "+dataFromBuffer);
	}
	
}


class WritingThread implements Runnable {
	
	private int id;
	private CircularBuffer buffer;
	
	public WritingThread(int id, CircularBuffer buffer) {
		this.id = id;
		this.buffer = buffer;
	}
	
	public void run() {
		System.out.println("Writing thread # "+id+" trying to write data into buffer");
		buffer.put("Data#"+id);
		System.out.println("Writing thread # "+id+" has written data to buffer" );
	}
	
}
