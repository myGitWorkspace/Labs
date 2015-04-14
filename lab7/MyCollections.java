package labs.lab7;


public class MyCollections {
	private MyCollections() {
		
	}
	
	public static void sort(MyList list) {
		if (list == null) {
			System.out.println("List is empty");
			return;
		}
		
		if ( !(list.get(0) instanceof java.lang.Comparable) ) {
			System.out.println("Object must implement Comparable or use sort with Comparator");
			return;
		}
		
		if (list instanceof java.util.RandomAccess)
			rapidSortAlgorithm(list, 0, list.size()-1, null);
		else
			bubbleSortAlgorithm(list, null);
		
	}
	
	public static void sort(MyList list, java.util.Comparator<Object> comp) {
		if (list == null) {
			System.out.println("List is empty");
			return;
		}
		
		if ( !(list.get(0) instanceof java.lang.Comparable) && (comp == null) ) {
			System.out.println("Comparator for sorting is null");
			return;
		}
		
		if (list instanceof java.util.RandomAccess)
			rapidSortAlgorithm(list, 0, list.size()-1, comp);
		else
			bubbleSortAlgorithm(list, comp);
		
	}
	
	private static int compare (Object object1, Object object2, java.util.Comparator<Object> comp) {
		if (object1 instanceof java.lang.Comparable) {			
			return ((java.lang.Comparable<Object>) object1).compareTo(object2);
		} else if (comp != null) {			
			return comp.compare(object1, object2);
		}
		System.out.println("Object must implement Comparable or use Comparator");
		return -2;
	}
	
	private static void rapidSortAlgorithm(MyList list, int left, int right, java.util.Comparator<Object> comp) {
		
		if (list == null) {
			System.out.println("List is empty");
			return;
		}
		if (left<0||right<0||left>=list.size()||right>=list.size()) {
			System.out.println("Index right or left is out of bounds !!!");
			return;
		}
		
		int leftIndex = left;
		int rightIndex = right;	    
	    int medIndex = (leftIndex+rightIndex)/2;
	    Object medValue = list.get(medIndex);

	    while (leftIndex <= rightIndex) {
	      while (compare(list.get(leftIndex),medValue,comp) == 1 )
	    	  leftIndex++;
	      while (compare(list.get(rightIndex),medValue,comp) == -1 )
	    	  rightIndex--;
	      if (leftIndex <= rightIndex) {
	    	  Object temp = list.get(leftIndex);
	    	  list.set(leftIndex, list.get(rightIndex));
	    	  list.set(rightIndex, temp);
	    	  leftIndex++;
	    	  rightIndex--;
	      }
	    }
	    if (left < rightIndex)
	    	rapidSortAlgorithm(list,left, rightIndex, comp);
	    if (leftIndex < right)
	    	rapidSortAlgorithm(list,leftIndex, right, comp);
	    
	}
	
	private static void bubbleSortAlgorithm(MyList list, java.util.Comparator<Object> comp) {		
		int size = list.size();
		for (int i=0; i<size-1; i++) {
			for (int j=0; j<size-i-1; j++) {
				int key = j+1;
				Object temp = list.get(key);
				if (compare(list.get(j),temp,comp) == -1 ) {
					list.set(key, list.get(j));
					list.set(j, temp);
				}
			}
		}
	}
	
	public static void swap(MyList list, int i, int j) {
		if (list == null) {
			System.out.println("List is empty");
			return;
		}
		if (i<0 || j<0 || i>=list.size() || j>=list.size()) {
			System.out.println("Wrong i or j parameter !!!");
			return;
		}
		Object tempElem = list.get(i);		
		list.set(i, list.get(j));
		list.set(j, tempElem);
	}
	
	public static void copy(MyList dest, MyList src) {
		if (dest == null || src == null) {
			System.out.println("Dest or Src list is empty");
			return;
		}
		for (int i=0; i<src.size(); i++) {
			dest.add(src.get(i));			
		}
	}
	
	public static void reverse(MyList list) {
		if (list == null) {
			System.out.println("List is empty");
			return;
		}
		int limit = (int)list.size()/2;
		int size = list.size()-1;
		for (int i=0; i<limit; i++)
			swap(list,i,size-i);
	}
}
