package labs.lab10;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.lang.Comparable;

public class Lab10Testing {

	public static void main(String[] args) {
		
		System.out.println("======== fromArrayToCollection testing =============");
		Integer[] intArray = new Integer[]{2,3,5,6,67,4,32,12,21,12,21};
		List<Integer> listInteger = new ArrayList<>();
		fromArrayToCollection(intArray, listInteger);
		System.out.println(listInteger);
		
		String[] strArray = new String[]{"asd","sdf","dfgh","fgh","fgh","hgj","hjk","tyu","vbccv"};
		List<String> listString = new ArrayList<>();
		fromArrayToCollection(strArray, listString);
		System.out.println(listString);
		
		System.out.println("\n ========  copyAll testing =============");
		List<Fruit> fruit = new ArrayList<Fruit>();
		fruit.add(new Fruit(1));
		fruit.add(new Fruit(2));
		fruit.add(new Fruit(3));
		fruit.add(new Fruit(4));

		List<Apple> apple = new ArrayList<Apple>();
		apple.add(new Apple(1));
		apple.add(new Apple(2));
		apple.add(new Apple(3));
		apple.add(new Apple(4));

		List<Orange> orange = new ArrayList<Orange>();
		orange.add(new Orange(1));
		orange.add(new Orange(2));
		orange.add(new Orange(3));
		orange.add(new Orange(4));

		List<RedApple> redApple = new ArrayList<RedApple>();
		redApple.add(new RedApple(1));
		redApple.add(new RedApple(2));
		redApple.add(new RedApple(3));
		redApple.add(new RedApple(4));

		List<Melon> melon = new ArrayList<Melon>();
		melon.add(new Melon(1));
		melon.add(new Melon(2));
		melon.add(new Melon(3));
		melon.add(new Melon(4));

		copyAll(orange,fruit);
		System.out.println("copyAll(orange,fruit)="+orange);
		copyAll(redApple,fruit);
		System.out.println("copyAll(redApple,fruit)="+redApple);
		copyAll(redApple,apple);
		System.out.println("copyAll(redApple,apple)="+redApple);
		copyAll(redApple,redApple);
		System.out.println("copyAll(redApple,redApple)="+redApple);		
		
		System.out.println("\n =========== myFilterCollection testing =============");
		List<Integer> newIntegerCollection = (List<Integer>)myFilterCollection(listInteger,10);
		System.out.println(newIntegerCollection);
	}

	
	public static <T> void fromArrayToCollection(T[] inputArray, Collection<T> collection) {
		if (inputArray == null || collection == null)
			throw new IllegalArgumentException("inputArray or collection parameter in fromArrayToCollection method is null!!!");
		
		if (inputArray.length > 0) {
			for (int i=0; i<inputArray.length; i++)
				collection.add(inputArray[i]);		
		}
	
	}
	
	public static <T extends E, E> void copyAll(Collection<T> destCollection, Collection<E> sourceCollection) {
		if (destCollection == null || sourceCollection == null)
			throw new IllegalArgumentException("destCollection or sourceCollection parameter in copyAll method is null!!!");
		
		Collection<E> sourceCopy = new ArrayList<>();
		sourceCopy.addAll(sourceCollection);
		
		Iterator<E> iterator = sourceCopy.iterator();
		
		try {
			
			while (iterator.hasNext()) {
				destCollection.add((T)iterator.next());
			}
			
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	public static <T extends Comparable<T>> Collection<T> myFilterCollection(Collection<T> sourceCollection, T compareElem) {
		if (sourceCollection == null || compareElem == null)
			throw new IllegalArgumentException("sourceCollection or compareElem parameter in myFilterCollection method is null!!!");
		
		Iterator<T> iterator = sourceCollection.iterator();
		Collection<T> newCollection = new ArrayList<>();
		newCollection.addAll(sourceCollection);
		
		while (iterator.hasNext()) {
			T currElement = iterator.next();
			if ( currElement.compareTo(compareElem) < 0 )
				newCollection.remove(currElement);				
		}
		
		return newCollection;
	}
	
	
}



class Fruit {
	protected int id;
	public Fruit(int id) {
		this.id = id;
	}
	public String toString() {
		return "Fruit-"+Integer.toString(id);
	}
}

class Orange extends Fruit {		
	public Orange(int id) {
		super(id);
	}
	public String toString() {
		return "Orange-"+Integer.toString(id);
	}
}

class Apple extends Fruit {
	public Apple(int id) {
		super(id);
	}
	public String toString() {
		return "Apple-"+Integer.toString(id);
	}
}

class RedApple extends Apple {
	public RedApple(int id) {
		super(id);
	}
	public String toString() {
		return "RedApple-"+Integer.toString(id);
	}
}

class Melon {
	protected int id;
	public Melon (int id) {
		this.id = id;
	}
	public String toString() {
		return "Melon-"+Integer.toString(id);
	}
}
