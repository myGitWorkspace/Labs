package labs.lab8;


/** Class for testing SkiPass modeling system
 *  Creates factory, SkiPass of any type and uses it
 * 
 *
 */
public class Lab8Testing {
	
	public static void main(String[] args) {

		SkiPassFactory factory = SkiPassFactory.getInstance();
		SkiPass skiPass = factory.produceSkiPass(2, 0);
		SkiPassLoginSystem loginSystem = new SkiPassLoginSystem(factory);
		factory.blockSkiPass(5);
		factory.blockSkiPass(9);		
		loginSystem.useSkiPassForSomeTime(skiPass);
		factory.getStatistics();
		
	}
	
}
