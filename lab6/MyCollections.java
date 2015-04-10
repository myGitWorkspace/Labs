package labs.lab6;

import labs.lab4.MyLinkedList;
import labs.lab5.MyArrayList;

public class MyCollections {
	private MyCollections() {
		
	}
	
	public static void sort(MyLinkedList list) {
		if (list == null) {
			System.out.println("List is empty");
			return;
		}
		
		sortAlgorithm(list, 0, list.size()-1);
	}
	
	private static void sortAlgorithm(MyLinkedList list, int left, int right) {
		
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
	    Integer medValue = list.get(medIndex);

	    while (leftIndex <= rightIndex) {
	      while (list.get(leftIndex) > medValue)
	    	  leftIndex++;
	      while (list.get(rightIndex) < medValue)
	    	  rightIndex--;
	      if (leftIndex <= rightIndex) {
	    	  Integer temp = list.get(leftIndex);
	    	  list.set(leftIndex, list.get(rightIndex));
	    	  list.set(rightIndex, temp);
	    	  leftIndex++;
	    	  rightIndex--;
	      }
	    }
	    if (left < rightIndex)
	    	sortAlgorithm(list,left, rightIndex);
	    if (leftIndex < right)
	    	sortAlgorithm(list,leftIndex, right);
	    
	}
	
	public static void swap(MyLinkedList list, int i, int j) {
		if (list == null) {
			System.out.println("List is empty");
			return;
		}
		if (i<0 || j<0 || i>=list.size() || j>=list.size()) {
			System.out.println("Wrong i or j parameter !!!");
			return;
		}
		Integer tempElem = list.get(i);		
		list.set(i, list.get(j));
		list.set(j, tempElem);
	}
	
	public static void copy(MyLinkedList dest, MyLinkedList src) {
		if (dest == null || src == null) {
			System.out.println("Dest or Src list is empty");
			return;
		}
		for (int i=0; i<src.size(); i++) {
			dest.add(new Integer(src.get(i)));
		}
	}
	
	public static void reverse(MyLinkedList list) {
		if (list == null) {
			System.out.println("List is empty");
			return;
		}
		int limit = (int)list.size()/2;
		int size = list.size()-1;
		for (int i=0; i<limit; i++)
			swap(list,i,size-i);
	}
	
	public static void sort(MyArrayList list) {
		if (list == null) {
			System.out.println("List is empty");
			return;
		}		
		sortAlgorithm(list, 0, list.size()-1);
	}
	
	private static void sortAlgorithm(MyArrayList list, int left, int right) {
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
	    Integer medValue = (Integer)list.get(medIndex);

	    while (leftIndex <= rightIndex) {
	      while ((Integer)list.get(leftIndex) > medValue)
	    	  leftIndex++;
	      while ((Integer)list.get(rightIndex) < medValue)
	    	  rightIndex--;
	      if (leftIndex <= rightIndex) {
	    	  Integer temp = (Integer)list.get(leftIndex);
	    	  list.set(leftIndex, list.get(rightIndex));
	    	  list.set(rightIndex, temp);
	    	  leftIndex++;
	    	  rightIndex--;
	      }
	    }
	    if (left < rightIndex)
	    	sortAlgorithm(list,left, rightIndex);
	    if (leftIndex < right)
	    	sortAlgorithm(list,leftIndex, right);	
	}
	
	public static void swap(MyArrayList list, int i, int j) {
		if (list == null) {
			System.out.println("List is empty");
			return;
		}
		if (i<0 || j<0 || i>=list.size() || j>=list.size()) {
			System.out.println("Wrong i or j parameter !!!");
			return;
		}
		Integer tempElem = (Integer)list.get(i);
		list.set(i, list.get(j));
		list.set(j, tempElem);
	}
	
	public static void copy(MyArrayList dest, MyArrayList src) {
		if (dest == null || src == null) {
			System.out.println("Dest or Src list is empty");
			return;
		}
		for (int i=0; i<src.size(); i++) {
			dest.add(new Integer((int)src.get(i)));
		}
	}
	
	public static void reverse(MyArrayList list) {
		if (list == null) {
			System.out.println("List is empty");
			return;
		}
		int limit = (int)list.size()/2;
		int size = list.size()-1;
		for (int i=0; i<limit; i++)
			swap(list,i,size-i);
	}
	
	public static int binarySearch(MyArrayList list, Integer key) {
		if (list == null) {
			System.out.println("List is empty");
			return -1;
		}
		
		sort(list);
		int leftIndex = 0;
		int rightIndex = list.size()-1;
		if (list.get(leftIndex) == key)
			return leftIndex;
		if (list.get(rightIndex) == key)
			return rightIndex;
		int centerIndex = (rightIndex - leftIndex)/2;
		while (centerIndex > 0) {			
			int centerIndexTotal = centerIndex+leftIndex;
			if (list.get(centerIndexTotal) == key)
				return centerIndexTotal;
			if ((Integer)list.get(centerIndexTotal) > key)
				leftIndex = centerIndexTotal;
			else
				rightIndex = centerIndexTotal;
			centerIndex = (rightIndex - leftIndex)/2;
		}
		int resultIndex = (-1)*leftIndex - 1;
		return resultIndex;
	}
	
}
