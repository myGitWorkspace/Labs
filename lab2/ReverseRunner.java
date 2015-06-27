package labs.lab2;

public class ReverseRunner {
	
	public static void main(String[] args) {
		
		System.out.println("digitsNumberCount = " + digitsNumberCount(234234));
		System.out.println("reverseNumber = " + reverseNumber(123456));
		System.out.println("powNumber = " + powNumber(4, 5));
		System.out.println("factorial = " + factorial(4));
		System.out.println("fibonachi = " + fibonachi(7));
		System.out.println("mathFunc = " + mathFunc(10, 7));
		combinationsNumberWithoutArray(6,123456,0);
	}

	
	// 1st task
	static int digitsNumberCount(int number) {
		int count = 0;
		if (number < 0)
			number *= -1;
		int tempNumb = number;
		while (tempNumb > 0) {
			count ++;
			tempNumb = (int)(tempNumb/10);			
		}
		return count;
	}
	
	// 2nd task
	static int reverseNumber(int number) {
		int degreeNumber = 0;
		int tempNumb = number;
		while (tempNumb > 0) {
			degreeNumber ++;
			tempNumb = (int)(tempNumb/10);			
		}
		int reversedNumber = 0;
		int currentDigit = 0;
		tempNumb = number;
		for (int i=degreeNumber-1; i>-1; i--) {
			currentDigit = tempNumb % 10;
			tempNumb = (int)(tempNumb/10);
			reversedNumber += currentDigit*Math.pow(10, i);
		}
		return reversedNumber;
	}
	
	// 3rd task
	static int powNumber(int number, int degree) {
		if (degree < 0)
			throw new IllegalArgumentException("degree < 0!!!");
		
		if (degree == 1)
			return number;
		return number*powNumber(number,degree-1);
	}
	
	// 4th task
	static int factorial(int number) {
		
		if (number < 0)
			throw new IllegalArgumentException("number parameter in factorial method < 0!!!");		
		
		if (number == 1)
			return 1;
		return number*factorial(number-1);
	}
	
	// 5th task
	static int fibonachi(int number) {
		
		if (number < 0)
			throw new IllegalArgumentException("number parameter in fibonachi method < 0!!!");
		
		if ( number == 0 )
			return 0;
		if ( number == 1)
			return 1;
		return fibonachi(number-1)+fibonachi(number-2);
	}

	//6th task
	/** Calculation of the logarithmic function mathFunc(int a, int x)
	 * 
	 * @param a			Constant int value
	 * @param x			Value of the x variable of the function
	 * @return			Function returns result of math expression y=log2(x-4)+exp(2a-x)
	 */
	static double mathFunc(int a, int x) {
		if (x <=4)
			throw new IllegalArgumentException("Variable x is wrond (must be > 4)!!!");
		
		double y = Math.log(2*(x-4))+Math.exp(2*a-x);
		return y;
	}
	
	
	//7th task
	static void combinationsNumberWithoutArray(int number, int buffer, int currentNumberIndex) {
		
		if (currentNumberIndex < 0 || currentNumberIndex > number || number < 0 || buffer <= 0)
			throw new IllegalArgumentException("wrong parameters in method combinationsNumberWithoutArray!!!");
		
		if( currentNumberIndex == number-1 ) {
			int tempBuffer = buffer;
			int numberToShow = 0;
			for(int i=0; i<number; i++) {				
				int digit = tempBuffer % 10;
				tempBuffer = tempBuffer / 10;
				numberToShow += (int)digit*Math.pow(10, i);				
			}
			System.out.print(numberToShow);
			System.out.println();
			
			return;
		}
	
		for(int i=currentNumberIndex; i<number; i++) {

			int newBuffer = buffer;
			if (i > currentNumberIndex) {
				int tempBuffer = buffer;
				int digitInCurrentNumberIndex = 0;				
				for(int j=0; j<number-currentNumberIndex; j++) {
					digitInCurrentNumberIndex = tempBuffer % 10;
					tempBuffer = tempBuffer / 10;
				}
					
				int digitInIIndex = 0;
				tempBuffer = buffer;
				for(int j=0; j<number-i; j++) {
					digitInIIndex = tempBuffer % 10;
					tempBuffer = tempBuffer / 10;					
				}				
				
				int tempValue = digitInCurrentNumberIndex;
				tempBuffer = buffer;
				int multyplier = 0;
				newBuffer = 0;
				for(int j=0; j<number; j++) {
					multyplier = tempBuffer % 10;
					if (j == number - currentNumberIndex - 1)
						multyplier = digitInIIndex;
					newBuffer += (int)multyplier*Math.pow(10, j);					
					tempBuffer = tempBuffer / 10;
				}
				
				//newBuffer[currentNumberIndex] = digitInIIndex;
				
				for (int j=i; j>currentNumberIndex+1; j--) {
					int digitInJMinus1Index = 0;
					tempBuffer = buffer;
					for(int k=0; k<number-j+1; k++) {
						digitInJMinus1Index = tempBuffer % 10;
						tempBuffer = tempBuffer / 10;
					}
					
					//newBuffer[j] = buffer[j-1];
					tempBuffer = newBuffer;
					newBuffer = 0;
					multyplier = 0;
					for(int k=0; k<number; k++) {
						multyplier = tempBuffer % 10;
						if (k == number - j - 1)
							multyplier = digitInJMinus1Index;
						
						newBuffer += (int)multyplier*Math.pow(10, k);
						tempBuffer = tempBuffer / 10;
					}
					
					
				}
				//newBuffer[currentNumberIndex+1] = tempValue;
				
				tempBuffer = newBuffer;
				newBuffer = 0;
				multyplier = 0;
				for(int j=0; j<number; j++) {
					multyplier = tempBuffer % 10;
					if (j == number - currentNumberIndex-1-1)
						multyplier = tempValue;
					newBuffer += (int)multyplier*Math.pow(10, j);
					tempBuffer = tempBuffer / 10;
				}
				
			}
		
			combinationsNumberWithoutArray(number, newBuffer, currentNumberIndex+1);
		}
		
	}
	
	
	
	
}
