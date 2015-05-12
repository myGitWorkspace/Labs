package labs.lab11;

public class SinusRawCount implements Runnable {

	private static volatile double sinusSum = 0;
	private static volatile boolean isFreeMonitor = true;
	private int id;
	private int sinusArgument;
	
	public SinusRawCount(int id, int sinusArgument) {
		this.id = id;
		this.sinusArgument = sinusArgument;
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
				System.out.println("Tread # "+id+" begins to add sinus");				
				double precision = 100000.;				
				sinusSum += Math.round( Math.sin(sinusArgument*3.14/180)*precision )/precision;				
				System.out.println("Tread # "+id+" finished adding sinus");
				isFreeMonitor = true;
				madeSearch = true;				
			}

		}
		
	}
	
	public static double getSinusSum() {
		return sinusSum;
	}	
	
}
