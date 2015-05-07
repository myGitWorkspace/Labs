package labs.lab9;

import java.util.Arrays;

public class Lab9Testing {
	
	public static void main(String[] args) {

		System.out.println( "MyHashMap testing =============" );
		MyHashMap myHashMap = new MyHashMap();
		for (int i=0; i<10; i++) {
			String key = "key"+i;
			Integer value = 1234+i*12;			
			myHashMap.put(key, value);
		}
		
		java.util.Iterator iterator = myHashMap.entryIterator();
		while(iterator.hasNext()) {
			MyHashMap.SimpleEntry entry = (MyHashMap.SimpleEntry)iterator.next();
			Integer result = (Integer)(entry.getValue());
			System.out.println(result);
		}
		
		System.out.println( "isEmpty - " + myHashMap.isEmpty() );
		
		System.out.println( "size - " + myHashMap.size() );
		
		System.out.println( "contains key=key9 - " + myHashMap.containsKey("key9") );
		
		System.out.println( "contains value=1246 - " + myHashMap.containsValue(new Integer(1246)));
		
		myHashMap.remove("key9");
		System.out.println(" --- remove key=key9");
		
		System.out.println( "contains key=key9 - " + myHashMap.containsKey("key9") );
		
		System.out.println( "size - " + myHashMap.size() );
		
		System.out.println( "is empty - " + myHashMap.isEmpty() );
		
		Integer getFromMap = (Integer)myHashMap.get("key10");
		System.out.println("get key10 - "+getFromMap);
		
		myHashMap.clear();
		System.out.println(" --- clear hashMap");
		
		System.out.println( "isEmpty - " + myHashMap.isEmpty() );
		System.out.println( "size - " + myHashMap.size() );
		


		
		System.out.println( "MyTreeMap testing =============" );
		MyTreeMap myTreeMap = new MyTreeMap();
		
		for (int i=1; i<11; i++) {
			Integer key = i;
			Integer value = i;			
			myTreeMap.put(key, value);
		}
		
		
		myTreeMap.remove( 8 );		
		System.out.println(" --- remove key=8");
		
		System.out.println( "size - " + myTreeMap.size() );
		
		myTreeMap.printAllElements();
		
		java.util.Iterator iterator2 = myTreeMap.entryIterator();
		while(iterator2.hasNext()) {
			MyTreeMap.SimpleEntry entry = (MyTreeMap.SimpleEntry)iterator2.next();
			Integer result = (Integer)(entry.getValue());
			System.out.println(result);
		}
		
		System.out.println( "isEmpty - " + myTreeMap.isEmpty() );
		
		System.out.println( "size - " + myTreeMap.size() );
		
		System.out.println( "contains key=9 - " + myTreeMap.containsKey(9) );
		
		System.out.println( "contains value=1246 - " + myTreeMap.containsValue(new Integer(1246)));
		
		myTreeMap.remove( 9 );
		System.out.println(" --- remove key=9");
		
		System.out.println( "contains key=9 - " + myTreeMap.containsKey(9) );
		
		System.out.println( "size - " + myTreeMap.size() );
		
		System.out.println( "is empty - " + myTreeMap.isEmpty() );
		
		Integer getFromMap2 = (Integer)myTreeMap.get(10);
		System.out.println("get key 10 - "+getFromMap2);
		
		myTreeMap.clear();
		System.out.println(" --- clear TreeMap");
		
		System.out.println( "isEmpty - " + myTreeMap.isEmpty() );
		System.out.println( "size - " + myTreeMap.size() );


	}
	

	
	
}
