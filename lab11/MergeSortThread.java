package labs.lab11;

public class MergeSortThread implements Runnable {

	private int id;
	private int[] inputArray;
	private int first;
	private int last;
	
	public MergeSortThread(int id, int[] inputArray, int first, int last) {
		this.id = id;
		this.inputArray = inputArray;
		this.first = first;
		this.last = last;
	}
	
	public void run() {
		if( first < last ) {
			System.out.println("Tread # "+id+" sorting sub array from "+ first+ " to "+last+" position");
			Thread sortFirstPart = new Thread( new MergeSortThread(id+1, inputArray, first, (first+last)/2) );
			Thread sortSecondPart = new Thread( new MergeSortThread(id+1, inputArray, (first+last)/2+1, last) );
			sortFirstPart.start();
			sortSecondPart.start();
			try {
				sortFirstPart.join();
				sortSecondPart.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			merge(inputArray, first, last);
			System.out.println("Tread # "+id+" finished sorting sub array from "+ first+ " to "+last+" position");
		}
	}
	
	private void merge(int[] inputArray, int first, int last) {
		
		int[] sortedArray = new int[inputArray.length];
		int middle = (first+last)/2;
		int start = first;
		int finish = middle+1;
		for(int j=first; j<=last; j++)
			if ( (start<=middle) && ((finish>last) || (inputArray[start]<inputArray[finish]) ) ) {
				sortedArray[j] = inputArray[start];
				start++;
			} else {
				sortedArray[j] = inputArray[finish];
				finish++;
			}
		for(int j=first; j<=last; j++)
			inputArray[j] = sortedArray[j];
	}
	
	public int[] getSortedArray() {
		return inputArray;
	}	
	
}
