package labs.lab7;

public interface MyList {
	void add (Object element);
	void add(int index, Object element);
	void addAll(Object[] elementList);
	void addAll(int index, Object[] elementList);
	Object get(int index);
	Object remove(int index);	
	void set(int index, Object element);
	int indexOf(Object element);
	int size();
	Object[] toArray();
}
