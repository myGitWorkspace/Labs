package labs.lab11;


public class Lab11Testing {

	public static void main(String[] args) {
		
		System.out.println("=== Simple numbers search testing ==========");
		
		for (int i=0; i<10; i++) {
			Thread numbersThread = new Thread(new NumbersThread(i));
			numbersThread.start();
			try {
				numbersThread.join();				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("\n=== Sinus evaluation testing ==========");
		
		int numberOfThreads = 10;
		int argumentMax = 120;
		int stepOfArgument = argumentMax/numberOfThreads;
		
		Thread[] sinusRaw = new Thread[numberOfThreads];
		Thread[] sinusRawMinus = new Thread[numberOfThreads];
		for (int i=1; i<numberOfThreads+1; i++) {
			sinusRaw[i-1] = new Thread(new SinusRawCount(i, i*stepOfArgument));			
			sinusRawMinus[i-1] = new Thread(new SinusRawCount(i,(-1)*i*stepOfArgument));
			sinusRaw[i-1].start();
			sinusRawMinus[i-1].start();
		}		
		
		for (int i=0; i<numberOfThreads; i++) {
			try {
				sinusRaw[i].join();
				sinusRawMinus[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("sinusSum="+SinusRawCount.getSinusSum());
		
		
		System.out.println("\n=== Merge sort testing ==========");
		int[] unsortedArray = new int[]{8,76,432,213,4,6,34,2,12,1,56,8,678,34};
		MergeSortThread mergeSortThread = new MergeSortThread( 0, unsortedArray, 0, unsortedArray.length-1 );
		Thread sortThread = new Thread( mergeSortThread );
		sortThread.start();		
		try {
			sortThread.join();			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int[] sortedArray  = mergeSortThread.getSortedArray();
		System.out.print("Sorted array = ");
		for(int i: sortedArray)
			System.out.print(i+"-");
		
	}
	
}
