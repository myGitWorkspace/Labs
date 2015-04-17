package labs.lab8;

import java.util.Calendar;

/** Abstract class to describe basic SkiPass structure
 * 
 * 
 *
 */
abstract public class SkiPass {
	private int id;	
	private Calendar expireDate;
	protected int counterUse;
	
	public SkiPass(int id, Calendar expireDate ) {
		this.id = id;		
		this.expireDate = expireDate;		
	}

	public int getId() {
		return this.id;
	}
	
	protected boolean isCardDateExpired(Calendar currDate) {
		
		if (currDate == null) {
			System.out.println("Calendar parameter in function is null!!!");
			return true;
		}
		
		int checkResult = currDate.compareTo(this.expireDate);
		if (checkResult > 0)
			return true;
		else if (checkResult < 0)
			return false;
		return true;
	}
	
	private boolean isCardEmpty() {
		if (this.counterUse > 0)
			return false;
		else if (this.counterUse == 0)
			return true;
		return true;
	}
	
	protected boolean isCardEmptyOrExpired(Calendar currDate) {
		
		if (currDate == null) {
			System.out.println("Calendar parameter in function is null!!!");
			return true;
		}
		
		if ( isCardDateExpired(currDate) ) {			
			System.out.println("Access forbiden!!! Date for using card has expired");
			return true;
		}
		
		if ( isCardEmpty() ) {			
			System.out.println("Access forbiden!!! Your card is empty");
			return true;
		}
		return false;
	}	
	
	protected Calendar getCurrentDateTime() {
		
		Calendar calendar = Calendar.getInstance();
	    
	    return calendar;
	}
	
	abstract public boolean loginCheckCard(Calendar currDate);
	
	abstract public void minusTripOrDayFromCard();

	
}
