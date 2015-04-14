package labs.lab7;


public class Lab7Testing {
	public static void main(String[] args) {
		
		System.out.println("MyLinkedList testing ========");
		MyLinkedList list = new MyLinkedList();
		for (int i=0; i<10; i++)
			list.add(i);
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");			
		}
		System.out.println("\nlist size="+list.size());
		
		list.add(9,44);
		System.out.println("element at position 9 = "+list.get(9));
		list.remove(9);
		Object[] subList = new Object[]{11,22,33,44,55};
		list.addAll(subList);
		list.addAll(1,subList);
		list.set(5,66);		
		System.out.println("index of val=8 is "+list.indexOf(8));
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");			
		}
		
		Object[] toArray = list.toArray();
		System.out.println("");
		for (int i=0; i<toArray.length; i++) {
			System.out.print(toArray[i]+", ");			
		}
		
		
		System.out.println("");			
		list.offer(77);		
		System.out.println(list.peek());
		System.out.println(list.poll());
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");			
		}
		
		System.out.println("");						
		list.push(88);
		list.push(-99);		
		System.out.println("stack pop value="+list.pop());		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");			
		}
		
		System.out.println("\n\nMyArrayList testing ========");
		MyArrayList list2 = new MyArrayList();
		for (int i=0; i<10; i++)
			list2.add(i);
		
		for (int i=0; i<list2.size(); i++) {
			System.out.print(list2.get(i)+", ");			
		}
		System.out.println("\nlist size="+list2.size());
		
		list2.add(9,44);
		System.out.println("element at position 9 = "+list2.get(9));
		list2.remove(9);
		Object[] subList2 = new Object[]{11,22,33,44,55};
		list2.addAll(subList2);
		list2.addAll(1,subList2);
		list2.set(5,66);		
		System.out.println("index of val=8 is "+list2.indexOf(8));
		
		for (int i=0; i<list2.size(); i++) {
			System.out.print(list2.get(i)+", ");			
		}
		
		Object[] toArray2 = list2.toArray();
		System.out.println("");
		for (int i=0; i<toArray2.length; i++) {
			System.out.print(toArray2[i]+", ");			
		}
		
		System.out.println("\n\nMyCollections testing ========");
		MyCollections.swap(list, 0, 1);
		MyLinkedList list3 = new MyLinkedList();
		list3.addAll(new Object[]{11,11,11});
		MyCollections.copy(list, list3);
		MyCollections.reverse(list);
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");
		}
		System.out.println("\n size = "+list.size());
		
		MyCollections.sort(list);
		MyCollections.sort(list2);
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");
		}
		
		
		class MyItem {
			String item;
			public MyItem(String item) {
				this.item = item;
			}
			public String getItem() {
				return item;
			}
		}
		
		java.util.Comparator<Object> comparator = new java.util.Comparator<Object>() {
			public int compare(Object object1, Object object2) {
				int length1 = ((MyItem)object1).getItem().length();
				int length2 = ((MyItem)object2).getItem().length();
				if (length1 > length2)
					return 1;
				if (length1 < length2)
					return -1;
				return 0;
			}
		};
		
		MyLinkedList list4 = new MyLinkedList();
		list4.add(new MyItem("a"));
		list4.add(new MyItem("bb"));
		list4.add(new MyItem("ccc"));
		list4.add(new MyItem("dddd"));
		MyCollections.sort(list4,comparator);
		
		System.out.println("");
		for (int i=0; i<list4.size(); i++) {
			System.out.print(((MyItem)list4.get(i)).getItem()+", ");
		}		
		
		
	}
}
