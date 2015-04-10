package labs.lab6;

import labs.lab4.MyLinkedList;
import labs.lab5.MyArrayList;

public class MyCollectionsTesting {
	public static void main(String[] args) {
		
		System.out.println("Collections - MyLinkedList ");
		MyLinkedList list = new MyLinkedList();
		for (int i=0; i<10; i++)
			list.add(i);
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");			
		}
		System.out.println("\nlist size="+list.size());
		
		System.out.println("sort->swap->copy(33333)->reverse");
		MyCollections.sort(list);
		MyCollections.swap(list,0,list.size()-1);
		MyLinkedList list2 = new MyLinkedList();
		for (int i=0; i<5; i++)
			list2.add(3);
		MyCollections.copy(list, list2);
		MyCollections.reverse(list);
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");			
		}
		
		System.out.println("\n\nCollections - MyArrayList ");
		MyArrayList list3 = new MyArrayList();
		for (int i=0; i<10; i++)
			list3.add(i);
		
		for (int i=0; i<list3.size(); i++) {
			System.out.print(list3.get(i)+", ");			
		}
		System.out.println("\nlist size="+list3.size());
		
		System.out.println("sort->swap->copy(33333)->reverse");
		MyCollections.sort(list3);
		MyCollections.swap(list3,0,list3.size()-1);
		MyArrayList list4 = new MyArrayList();
		for (int i=0; i<5; i++)
			list4.add(3);
		MyCollections.copy(list3, list4);
		MyCollections.reverse(list3);
		
		for (int i=0; i<list3.size(); i++) {
			System.out.print(list3.get(i)+", ");			
		}
		
		System.out.println("\n\nBinary search testing");
		MyArrayList list5 = new MyArrayList();
		for (int i=0; i<10; i++) {
			if (i == 4) continue;
			list5.add(i);
		}

		System.out.println("binary search result="+MyCollections.binarySearch(list5, 4));
		for (int i=0; i<list5.size(); i++) {
			System.out.print(list5.get(i)+", ");			
		}
		
	}
}
