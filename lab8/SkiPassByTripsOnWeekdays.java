package labs.lab8;

import java.util.ArrayList;
import java.util.Calendar;

/** Daughter class of SkiPass that counts trips of SkiPass card usage only on Weekdays
 * 
 * 
 *
 */
public class SkiPassByTripsOnWeekdays extends SkiPass {
	
	private ArrayList<Integer> daysAllowed = new ArrayList<Integer>();
	private int[] tripsNumb = new int[] {10,20,50,100};
	private int tripsNumbId;
	
	public SkiPassByTripsOnWeekdays(int id, Calendar expireDate, int tripsNumbId) {
			super(id, expireDate);
			this.tripsNumbId = tripsNumbId;
			this.counterUse = this.tripsNumb[this.tripsNumbId];
			
			daysAllowed.add(Calendar.MONDAY);
			daysAllowed.add(Calendar.TUESDAY);
			daysAllowed.add(Calendar.WEDNESDAY);
			daysAllowed.add(Calendar.THURSDAY);
			daysAllowed.add(Calendar.FRIDAY);
	}
	
	public boolean loginCheckCard(Calendar currDate) {
		
		if (currDate == null) {
			System.out.println("Calendar parameter in function is null!!!");
			return false;
		}
		
		if ( isCardEmptyOrExpired(currDate) )
			return false;
		
		if ( !daysAllowed.contains( currDate.get(Calendar.DAY_OF_WEEK) ) ) {
			System.out.println("Access forbiden!!! You can't use you card on Saturday or Sunday");
			return false;
		}
				
		return true;
			
	}

	
	public void minusTripOrDayFromCard() {
		if (this.counterUse > 0)
			this.counterUse --;
	}
	
	
}
