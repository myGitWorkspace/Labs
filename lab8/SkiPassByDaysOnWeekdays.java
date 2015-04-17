package labs.lab8;


import java.util.ArrayList;
import java.util.Calendar;

/** Daughter class of SkiPass that counts days of SkiPass card usage only on Weekdays
 * Controls correct hours of usage when half of day is selected
 * 
 *
 */
public class SkiPassByDaysOnWeekdays extends SkiPass {
	
	private ArrayList<Integer> daysAllowed = new ArrayList<Integer>();

	private int[] daysNumb = new int[] {1,1,2,5};
	private int daysNumbId;
	private Calendar lastDateLoginSkiPass;
	
	public SkiPassByDaysOnWeekdays(int id, Calendar expireDate, int daysNumbId) {
			super(id, expireDate);
			this.daysNumbId = daysNumbId;
			this.counterUse = daysNumb[this.daysNumbId];
			this.lastDateLoginSkiPass = null;
			
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
		
		if ( isCardEmptyOrExpired(currDate) ) {			
			return false;			
		}
					
		if ( !daysAllowed.contains(currDate.get(Calendar.DAY_OF_WEEK)) ) {
			System.out.println("Access forbiden!!! You can't use you card on Saturday or Sunday");
			return false;
		}
				
		if ( !isCorrectDayHours( getCurrentDateTime() ) ) {
			System.out.println("Access forbiden!!! You can ski only from 9 till 13 or from 13 till 17");
			return false;
		}
				
		return true;
			
	}
	
	private boolean isCorrectDayHours(Calendar currDateTime) {
		
		if (currDateTime == null) {
			System.out.println("Calendar parameter in function is null!!!");
			return false;
		}
		
		if (this.daysNumbId == 0)
			return isCorrectHalfDayHours(currDateTime);
		return true;
	}
	
	private boolean isCorrectHalfDayHours(Calendar currDateTime) {
		
		if (currDateTime == null) {
			System.out.println("Calendar parameter in function is null!!!");
			return false;
		}
		
		int currDayHour = currDateTime.get(Calendar.HOUR_OF_DAY);
		int currDayMinutes = currDateTime.get(Calendar.MINUTE);
		int currDate = currDateTime.get(Calendar.DATE);
		int currMonth = currDateTime.get(Calendar.MONTH);
		int currYear = currDateTime.get(Calendar.YEAR);
		int lastVisitHour = 0;
		int lastVisitDate = 0;
		int lastVisitMonth = 0;
		int lastVisitYear = 0;
		if (this.lastDateLoginSkiPass != null) {
			lastVisitHour = this.lastDateLoginSkiPass.get(Calendar.HOUR_OF_DAY);
			lastVisitDate = this.lastDateLoginSkiPass.get(Calendar.DATE);
			lastVisitMonth = this.lastDateLoginSkiPass.get(Calendar.MONTH);
			lastVisitYear = this.lastDateLoginSkiPass.get(Calendar.YEAR);
		}
				
		if ( currDayHour >= 9 && currDayHour < 13 ) {
			
			if ( (currDate!=lastVisitDate) || (currMonth!=lastVisitMonth) || (currYear!=lastVisitYear) ) {
				minusDayFromCard();
				this.lastDateLoginSkiPass = Calendar.getInstance();
				this.lastDateLoginSkiPass.set(currYear,currMonth,currDate,currDayHour,currDayMinutes,0);
			}		
			return true;
		}
		if ( currDayHour >= 13 && currDayHour < 17 ) {
			if ( (currDate!=lastVisitDate) || (currMonth!=lastVisitMonth) || (currYear!=lastVisitYear) ) {
				minusDayFromCard();
				this.lastDateLoginSkiPass = Calendar.getInstance();
				this.lastDateLoginSkiPass.set(currYear,currMonth,currDate,currDayHour,currDayMinutes,0);
				return true;
			} else {
				if ( lastVisitHour < 13 )
					return false;
				else return true;
			}
		}
		return false;
	}
	
	protected boolean isCardEmptyOrExpired(Calendar currDateTime) {
		
		if (currDateTime == null) {
			System.out.println("Calendar parameter in function is null!!!");
			return true;
		}
		
		if ( isCardDateExpired(currDateTime) ) {			
			System.out.println("Access forbiden!!! Date for using card has expired");
			return true;
		}
		
		if ( this.counterUse == 0 && !isStardedSkiToday(currDateTime) ) {
			System.out.println("Access forbiden!!! Your card is empty");
			return true;
		}
	
		return false;
	}
	
	private boolean isStardedSkiToday(Calendar currDateTime) {
		
		if (currDateTime == null) {
			System.out.println("Calendar parameter in function is null!!!");
			return false;
		}
		
		int lastVisitDate = 0;
		int lastVisitMonth = 0;
		int lastVisitYear = 0;
		if (this.lastDateLoginSkiPass != null) {			
			lastVisitDate = this.lastDateLoginSkiPass.get(Calendar.DATE);
			lastVisitMonth = this.lastDateLoginSkiPass.get(Calendar.MONTH);
			lastVisitYear = this.lastDateLoginSkiPass.get(Calendar.YEAR);
		}
		int currDate = currDateTime.get(Calendar.DATE);
		int currMonth = currDateTime.get(Calendar.MONTH);
		int currYear = currDateTime.get(Calendar.YEAR);
		
		boolean isStardedToday = false;
		if ( (currDate == lastVisitDate) && (currMonth == lastVisitMonth) && (currYear==lastVisitYear) )
			isStardedToday = true;
		
		return isStardedToday;
		
	}
	
	private void minusDayFromCard() {
		if (this.counterUse > 0)
			this.counterUse --;
	}
	
	public void minusTripOrDayFromCard() {
		
	}
	

	
}
