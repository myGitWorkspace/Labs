package labs.lab8;

/** Login System performs connection between factory and SkiPass cards
 * Login System checks all SkiPass and runs ski process
 * 
 *
 */
public class SkiPassLoginSystem {
	
	private SkiPassFactory factory;
	
	public SkiPassLoginSystem(SkiPassFactory factory) {
		this.factory = factory;
	}
	
	public boolean skiPassLogin(SkiPass skiPass) {
		
		if (skiPass == null) {
			System.out.println("SkiPass parameter in function is null!!!");
			return false;
		}
		
		if ( skiPass.loginCheckCard( factory.getCurrentDateTime() ) ) {
			skiPass.minusTripOrDayFromCard();
			factory.writeSkiPassAccessLog(skiPass.getId(), factory.getSkiPassTypeId(skiPass), true);
			return true;
		}
		factory.writeSkiPassAccessLog(skiPass.getId(), factory.getSkiPassTypeId(skiPass), false);
		return false;
	}
	
	public void useSkiPassForSomeTime(SkiPass skiPass) {
		
		if (skiPass == null) {
			System.out.println("SkiPass parameter in function is null!!!");
			return;
		}
		
		//int maxLimit = new java.util.Random().nextInt(50);
		int maxLimit = 11;
		for (int i=0; i<maxLimit; i++) {
			if (skiPassLogin(skiPass)) {
				// ski for some time
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.err.print(e);
				}
			}
		}
	}
	
	
}
