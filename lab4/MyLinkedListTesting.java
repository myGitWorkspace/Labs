package labs.lab4;


public class MyLinkedListTesting {

	public static void main(String[] args) {
		
		MyLinkedList list = new MyLinkedList();
		for (int i=0; i<10; i++)
			list.add(i);
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");			
		}
		System.out.println("\nlist size="+list.size());
		
		list.addFirst(-56);
		list.addFirst(0);
		list.addFirst(22);
		list.addLast(33);
		list.add(11,44);
		System.out.println("first element="+list.getFirst());
		System.out.println("last element="+list.getLast());
		System.out.println("element at position 12 = "+list.get(12));
		list.remove(3);
		list.removeFirst();
		list.removeLast();
		list.set(5,66);		
		System.out.println("index of val=8 is "+list.indexOf(8));
		
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+", ");			
		}
		System.out.println("\nlist size="+list.size());
		
		
		System.out.println("\n =====MyQueue testing=====");
		MyQueue queue = new MyQueue();
		for (int i=0; i<10; i++)
			queue.add(i);
		
		queue.offer(new Element(22));
		System.out.println(queue.peek().getValue());
		System.out.println(queue.poll().getValue());
		
		for (int i=0; i<queue.size(); i++) {
			System.out.print(queue.get(i)+", ");			
		}
		System.out.println("\n queue size="+queue.size());
		
		System.out.println("\n =====MyStack testing=====");
		
		MyStack stack = new MyStack();
		for (int i=0; i<10; i++)
			stack.add(i);
		
		stack.push(new Element(11));
		stack.push(new Element(222));
		stack.push(new Element(-333));
		stack.push(new Element(4444));
		stack.push(new Element(5555));
		System.out.println("stack pop value="+stack.pop().getValue());
		
		for (int i=0; i<stack.size(); i++) {
			System.out.print(stack.get(i)+", ");			
		}
		System.out.println("\n stack size="+stack.size());

	}

}
