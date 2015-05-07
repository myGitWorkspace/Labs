package labs.lab9;

import java.lang.IllegalArgumentException;
import java.util.Iterator;

public class MyHashMap implements MyMap,Iterable {
	private int capacity;
	private float loadFactor;
	private int countElements = 0;
	private SimpleEntry[] container;
	
	public MyHashMap() {
		this(16,(float)0.75);
	}
	
	public MyHashMap(int initialCapacity) {
		this(initialCapacity,(float)0.75);
	}
	
	public MyHashMap(int initialCapacity, float loadFactor) {
		if (initialCapacity < 0 || loadFactor < 0 ) 
			throw new IllegalArgumentException("initialCapacity and loadFactor must be > 0");
		this.capacity = initialCapacity;
		this.loadFactor = loadFactor;
		container = new SimpleEntry[this.capacity];
	}
	
	private int hash(int hashCode) {
		hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
	    return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);		
	}
	
	private int getIndex(int hash, int length) {
		if (length < 0)
			throw new IllegalArgumentException("length parameter in getIndex method < 0!!!");
		return hash & (length-1);
	}
	
	public void clear() {
		
		if ( container != null && container.length >0 ) {
			for (int i=0; i<container.length; i++)
				container[i] = null;
		}
		countElements = 0;
			
	}
	
	public boolean containsKey(Object key) {
		
		if (key == null)
			throw new IllegalArgumentException("key parameter in containsKey method is null!!!");
		
		int hash = hash(key.hashCode());
		int index = getIndex(hash, container.length);
		SimpleEntry currentElem = container[index];
		for (;currentElem != null; currentElem = currentElem.next) {				
			if ( key.equals(currentElem.key) )
				return true;
		}
		return false;
	}
	
	public boolean containsValue(Object value) {
		
		SimpleEntry currentElem;
		for (int i=0; i<capacity; i++) {
			currentElem = container[i];
			for (;currentElem != null; currentElem = currentElem.next) {				
				if ( value.equals(currentElem.value) )
					return true;
			}
		}		
		return false;
	}
	
	public Object get(Object key) {
		
		if (key == null)
			return getNullKey();
		int hash = hash(key.hashCode());
		int index = getIndex(hash,container.length);
		
		SimpleEntry currentElem = container[index];
		for (;currentElem != null; currentElem = currentElem.next) {
			Object tempKey = currentElem.key;
			if ( tempKey == key || key.equals(tempKey) )
				return currentElem.value;
		}		
		return null;
	}
	
	public boolean isEmpty() {
		return (countElements == 0) ? true : false;
	}
	
	public Object put(Object key, Object value) {
		
		if (value == null)
			throw new NullPointerException("value parameter is null");
		if (key == null)
			return putNullKey(value);
		
		int hash = hash(key.hashCode());
		int index = getIndex(hash,container.length);
		SimpleEntry currentElem = container[index];
		for(;currentElem != null; currentElem = currentElem.next) {
			Object tempKey = currentElem.key;
			if ( tempKey == key || key.equals(tempKey) ) {
				Object oldValue = currentElem.value;
				currentElem.value = value;
				return oldValue;
			}
		}
						
		addEntry(hash, key, value, index);
		return null;		
		
	}
	
	public Object remove(Object key) {
		
		if (key == null)
			throw new IllegalArgumentException("key parameter in remove method is null!!!");
		
		int hash = hash(key.hashCode());
		int index = getIndex(hash,container.length);
		SimpleEntry currentElem = container[index];
		SimpleEntry prevElem = null;
		for(;currentElem != null; currentElem = currentElem.next) {
			if ( currentElem != null && currentElem.key.equals(key) )
				break;
			prevElem = currentElem;
		}
		if (currentElem == null) {
			return null;
		} else {
			if (prevElem == null)
				container[index] = currentElem.next;
			else
				prevElem.next = currentElem.next;
			
			countElements--;
			return currentElem.value;
		}
		
	}
	
	public int size() {
		return countElements;
	}
	
	public Iterator iterator() {
		
		Iterator iterator = new Iterator() {

			private int currentIndex = 0;
			private int currentIndexInBucket = 0;
            
            public boolean hasNext() {
            	
            	for (int i=capacity-1; i>=currentIndex+1; i--) {
            		if (container[i] != null)
            			return true;            		
            	}
            	SimpleEntry currentElem = container[currentIndex];
            	for (int i=currentIndexInBucket; i>0; i--) {
            		currentElem = currentElem.next;
            	}
            	if (currentElem != null && currentElem.next != null)
            		return true;
            	
            	return false;
            }
            
            public SimpleEntry next() {
            	
            	SimpleEntry currentElem = container[currentIndex];
            	for (int i=currentIndexInBucket; i>0; i--) {
            		currentElem = currentElem.next;
            	}
            	if (currentElem != null && currentElem.next != null) {
            		currentIndexInBucket++;
            		return currentElem.next;
            	} else {
            		if ( currentIndex+1 == capacity )
            			return null;
            		for (int i=currentIndex+1; i<capacity; i++) {
                		if (container[i] != null) {
                			currentIndexInBucket = 0;
                			currentIndex = i;
                			return container[i];
                		}
                			            		
                	}
            	}
            	return null;
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iterator;
	}
	
	public Iterator entryIterator() {
		return iterator();
	}
	
	private void rehash(int newSize) {
		
		if ( newSize < 0 || newSize > Integer.MAX_VALUE )
			throw new IllegalArgumentException("newSize parameter must be > 0 && < Integer.MAX_VALUE ");
		
		SimpleEntry[] newContainer = new SimpleEntry[newSize];
		for (int i=0; i<container.length; i++)
			newContainer[i] = container[i];
		container = newContainer;
		capacity = newSize;
	}
	
	private Object getNullKey() {
		
		SimpleEntry currentElem = container[0];
		for (;currentElem != null; currentElem = currentElem.next) {
			if (currentElem.key == null)
				return currentElem.value;			
		}
		return null;
	}
	
	private Object putNullKey( Object value ) {
		
		SimpleEntry currentElem = container[0];		
		for (;currentElem != null; currentElem = currentElem.next) {
			if (currentElem.key == null) {
				Object oldValue = currentElem.value;
				currentElem.value = value;
				return oldValue;
			}			
		}
		if (currentElem == null)
			addEntry(0, null, value, 0);		
			
		return null;
	}
	
	private void addEntry(int hash, Object key, Object value, int indexContainer) {
		
		if (indexContainer < 0)
			throw new IllegalArgumentException("indexContainer parameter in addEntry method < 0!!!");		
		
		SimpleEntry startEntry = container[indexContainer];		
		container[indexContainer] = new SimpleEntry(hash, key, value, startEntry);
		if (countElements++ >= (int)(loadFactor*capacity) ) {			
			rehash(2*container.length);
		}
	}
	
	class SimpleEntry implements Entry {
		private int hashKey;
		private Object key;
		private Object value;
		private SimpleEntry next;
		
		public SimpleEntry(int hashKey, Object key, Object value, SimpleEntry startElement) {
			this(hashKey, key,value);
			if (startElement != null)
				addLast(hashKey, key, value, startElement);
		}
		
		public SimpleEntry(int hashKey, Object key, Object value) {	
			this.hashKey = hashKey;
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
		private void addLast(int hashKey, Object key, Object value, SimpleEntry rootElem) {
			
			if (rootElem == null)
				throw new IllegalArgumentException("rootElem parameter in addLast method is null!!!");
			
			SimpleEntry currentElem = rootElem;
			while (currentElem.next != null) {
				currentElem = currentElem.next;
			}
			SimpleEntry newElement = new SimpleEntry(hashKey, key, value);
			currentElem.next = newElement;
		}
		
		public boolean equals(Object element) {
			return this.value.equals(element);
		}
		
		public Object getKey() {
			return key;
		}
		
		public Object getValue() {
			return value;
		}
		
		public int hashCode() {
			return hashKey;
		}
		
		public Object setValue(Object value) {
			Object oldValue = this.value;
			this.value = value;
			return oldValue;
		}
		
	}
	
	
}
