

/** Class Person for holding person information
*
*@author Andrew
*@version 3.0
*/
public class Person {
	private String name;
	private String surname;
	private String birthDate;

	/** Costructor for class Person
	 * 
	 * @param name	  	Name of the person
	 * @param surname	Surname of the person
	 * @param birthDate	Date of birth of the person
	 */
	public Person (String name, String surname, String birthDate) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	public void showPersonInfo () {
		System.out.println("Name="+name+"; Surname="+surname+"; Date of birth="+birthDate);
	}

}