package labs.lab11;

public class NumbersThread implements Runnable {

	private static volatile boolean isFreeMonitor = true;
	private int id;
	
	public NumbersThread(int id) {
		this.id = id;
	}
	
	public void run() {
		
		boolean madeSearch = false;
		while(!madeSearch) {
			if (!isFreeMonitor) {
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				isFreeMonitor = false;
				System.out.println("Tread # "+id+" begins to search for a simple number");
				int randomNumber = 0;
				while(randomNumber < 2)
					randomNumber = new java.util.Random().nextInt(50);
				if ( isSimpleNumber(randomNumber) )
					System.out.println("Found simple number = " + randomNumber);
				System.out.println("Tread # "+id+" finished searching");
				isFreeMonitor = true;
				madeSearch = true;
			}

		}

		
	}
	
	private boolean isSimpleNumber(int number) {
		
		if (number < 2)
			throw new IllegalArgumentException("number parameter in isSimpleNumber method < 2!!!");
		
		for (int i=2; i<number-1; i++)
			if ( (number % i) == 0 )
				return false;
		return true;
	}
	
}
