package labs.lab5;

public class MyArrayListTesting {
	
	public static void main(String[] args) {
		MyArrayList list = new MyArrayList(12);
		for (int i=0; i<12; i++)
			list.add(i);
		
		list.add(22);
		list.add(3,11);
		Object[] obj = new Object[]{"a","b","c"};
		list.addAll(obj);
		list.addAll(5,obj);
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");			
		}
		System.out.println("\n");
		list.remove(9);
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");			
		}
	}
}
