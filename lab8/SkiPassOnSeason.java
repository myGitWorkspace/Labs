package labs.lab8;

import java.util.Calendar;

/** Daughter class of SkiPass that allows free usage within 1 year
 * 
 * 
 *
 */
public class SkiPassOnSeason extends SkiPass {
	
	public SkiPassOnSeason(int id, Calendar expireDate) {
			super(id, expireDate);						
	}
	
	public boolean loginCheckCard(Calendar currDate) {
		
		if (currDate == null) {
			System.out.println("Calendar parameter in function is null!!!");
			return false;
		}
		
		if ( isCardDateExpired(currDate) )
			return false;		
				
		return true;			
	}
	
	public void minusTripOrDayFromCard() {
		
	}
	
}
