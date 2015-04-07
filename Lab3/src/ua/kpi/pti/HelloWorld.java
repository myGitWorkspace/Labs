package ua.kpi.pti;
import ua.kpi.pti.pkg.PrintHello;


/** Class HelloWorld for testing java
*
*@author Andrew
*@version 3.1
*/
public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("HelloWorld");
		PrintHello printHello = new PrintHello();
		printHello.printHelloMethod();
	}
}