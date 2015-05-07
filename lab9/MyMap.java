package labs.lab9;

public interface MyMap {
	void clear();
	boolean containsKey(Object key);
	boolean containsValue(Object value);
	Object get(Object key);
	boolean isEmpty();
	Object put(Object key, Object value);
	Object remove(Object key);
	int size();
	java.util.Iterator entryIterator();
	
	interface Entry {
		boolean equals(Object element);
		Object getKey();
		Object getValue();
		int hashCode();
		Object setValue(Object value);
	}
}
