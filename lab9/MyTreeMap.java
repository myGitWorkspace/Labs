package labs.lab9;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class MyTreeMap implements MyMap,Iterable {
	
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	Comparator comparator;
	private SimpleEntry root;

	public MyTreeMap() {
		this.comparator = null;
		this.root = null;
	}
	
	public MyTreeMap(Comparator comparator) {
		this.comparator = comparator;
		this.root = null;
	}
	
	private int compare(Object object1, Object object2) {
		if (this.comparator != null)
			return (this.comparator).compare(object1, object2);
		else if (object1 instanceof java.lang.Comparable) {			
			return ((java.lang.Comparable) object1).compareTo(object2);
		}
		 
		throw new IllegalArgumentException("Object must implement Comparable or use Comparator");		
	}
	
	public void clear() {         
        root = null;
        this.size();
	}
	
	public boolean containsKey(Object key) {
		if (key == null)
			throw new IllegalArgumentException("key parameter in containsKey method is null!!!");			

		return (this.get(key) != null);
	}
	
	public boolean containsValue(Object value) {		
		return containsValueStartFromElement(root, value);
	}
	
	public boolean containsValueStartFromElement(SimpleEntry element, Object value) {
		if (element == null)
			return false;
		if ( containsValueStartFromElement(element.left, value) )
			return true;
		if (compare(element.value, value) == 0)
			return true;		
		if ( containsValueStartFromElement(element.right, value) )
			return true;
		return false;
	}
	
	public void printAllElements() {
		printAllElementsFromStart(root);
	}
	
	private void printAllElementsFromStart(SimpleEntry element) {
		if (element == null)
			return;
		printAllElementsFromStart(element.left);
		Object leftSon = (element.left == null)?null:element.left.value;
		Object rightSon = (element.right == null)?null:element.right.value;
		System.out.println("Color="+element.color+";Node="+ element.value+" (left son="+leftSon+"; right son="+rightSon+")");		
		printAllElementsFromStart(element.right);		
	}
	
	public Object get(Object key) {
		return getFromStartElement(root,key);
	}
	
	private Object getFromStartElement(SimpleEntry element, Object key) {
		if (key == null)
			throw new IllegalArgumentException("key parameter in getFromStartElement method is null!!!");
		if (element == null)
			return null;
		int compare = compare(key, element.key);
		
		if (compare < 0)
			return getFromStartElement(element.left, key);
		else if (compare > 0)
			return getFromStartElement(element.right, key);
		else
			return element.value;
	}
	
	public boolean isEmpty() {
		return (this.size() == 0);
	}
	
	public Object put(Object key, Object value) {
		Object oldValue = getFromStartElement(root, key);
		root = findElement(root, key, value);		
		root.color = BLACK;
		return oldValue;
	}
	
	private SimpleEntry findElement(SimpleEntry element, Object key, Object value) {
		if (key == null)
			throw new IllegalArgumentException("key parameter in findElement method is null!!!");
		if (element == null) {			
			return new SimpleEntry(key, value, 1, RED);
		}
		
		int compare = compare(key, element.key);
		
		if (compare < 0)
			element.left = findElement(element.left, key, value);
		else if (compare > 0)
			element.right = findElement(element.right, key, value);
		else
			element.value = value;

		if (element.left != null && isRed(element.left) && isRed(element.left.right) && !isRed(element.right)) {			
			element.left = rotateLeft(element.left);
		}
		if (element.right != null && isRed(element.right) && isRed(element.right.left) && !isRed(element.left)) {			
			element.right = rotateRight(element.right);
			element = rotateLeft(element);
		}
		if (isRed(element.right) && isRed(element.right.right) && !isRed(element.left)) {			
			element = rotateLeft(element);
		}
		if (isRed(element.left) && isRed(element.left.left) && !isRed(element.right)) {			
			element = rotateRight(element);
		}
		if (isRed(element.left) && isRed(element.right) && ( (element.left != null && ( isRed(element.left.right) || isRed(element.left.left) )) || (element.right != null && (isRed(element.right.right) || isRed(element.right.left) )) ) ){			
			flipColors(element);			
		}
		
		element.numberNodesInSubTree = size(element.left) + size(element.right) + 1;
		return element;
	}
	
	public Object remove(Object key) {
		if (key == null)
			throw new IllegalArgumentException("key parameter in remove method is null!!!");		
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		Object oldValue = getFromStartElement(root, key);
		removeFromStartElement(root, key);		
		if (!isEmpty())
			root.color = BLACK;
		size(root);
		return oldValue;
	}
	
	public SimpleEntry removeFromStartElement(SimpleEntry elementStartFrom, Object key) {
		
		int compare = compare(key, elementStartFrom.key);
		SimpleEntry newElementStartFrom = null;
		SimpleEntry parent = getParentElement(root, elementStartFrom);
		
		if (compare < 0) {			
			newElementStartFrom = removeFromStartElement(elementStartFrom.left, key);			
			assignParentNewValue(elementStartFrom,parent,newElementStartFrom);			
		} else if (compare > 0) {			
			newElementStartFrom = removeFromStartElement(elementStartFrom.right, key);
			assignParentNewValue(elementStartFrom,parent,newElementStartFrom);			
		}
		else if (compare == 0) {
			boolean balanced = false;
			if (elementStartFrom.right == null && elementStartFrom.left == null){				
				assignParentNewValue(elementStartFrom, parent, null);
				elementStartFrom = null;
			} else if ( (elementStartFrom.right != null && elementStartFrom.left == null) || (elementStartFrom.right == null && elementStartFrom.left != null)  ){
				SimpleEntry tail = (elementStartFrom.right != null)?elementStartFrom.right : elementStartFrom.left;				
				assignParentNewValue(elementStartFrom, parent, tail);
				elementStartFrom = tail;				
			} else if ( elementStartFrom.right != null && elementStartFrom.left != null  ){				
				SimpleEntry[] minNode = deleteMin(elementStartFrom);
				elementStartFrom.key = minNode[0].key;
				elementStartFrom.value = minNode[0].value;
				elementStartFrom.numberNodesInSubTree = size(elementStartFrom.left) + size(elementStartFrom.right) + 1;
				balanceTree(null, minNode[1]);
				balanced = true;				
			}
			
			parent = (balanced)?parent:balanceTree(elementStartFrom, parent);			
		}
		
		if (elementStartFrom != null)
			elementStartFrom.numberNodesInSubTree = size(elementStartFrom.left) + size(elementStartFrom.right) + 1;		
		
		return parent;
	}
	
	private SimpleEntry balanceTree(SimpleEntry elementStartFrom, SimpleEntry parent) {

		if (parent == null)
			return elementStartFrom;
		
		if (elementStartFrom == root)
			return parent;
		
		SimpleEntry originParent = parent;
		SimpleEntry tempElement = null;
		if (elementStartFrom == null) {			
			tempElement = new SimpleEntry(new Object(),new Object(),0,BLACK);
			assignParentNewValue(elementStartFrom, parent, tempElement);
			elementStartFrom = tempElement;
		}
		
		SimpleEntry brother = (parent.right == elementStartFrom)?parent.left:parent.right;
		if (brother != null) {
			if (brother.color == RED) {
				
				SimpleEntry parentOfParentElem = getParentElement(root, parent);
				SimpleEntry newParent = rotateLeft(parent);
				assignParentNewValue(parent, parentOfParentElem, newParent);
				balanceTree(elementStartFrom, originParent);
				
			} else if (brother.color == BLACK) {
				
				SimpleEntry brotherRightChild = brother.right;
				SimpleEntry brotherLeftChild = brother.left;
				if ( (brotherLeftChild != null && brotherLeftChild.color == RED) && (brotherRightChild == null || brotherRightChild.color == BLACK )  ) {
					
					SimpleEntry parentOfParentElem = getParentElement(root, parent);
					SimpleEntry newParent = prepareBrotherAndSons(parent, brother);
					assignParentNewValue(parent, parentOfParentElem, newParent);
					newParent.left.color = BLACK;
					newParent.right.color = BLACK;
					
				} else if (brotherRightChild != null && brotherRightChild.color == RED ) {
					
					SimpleEntry parentOfParentElem = getParentElement(root, parent);
					SimpleEntry newParent = prepareBrotherAndSons(parent, brother);					
					assignParentNewValue(parent, parentOfParentElem, newParent);
					
					if (newParent.left != null)
						newParent.left.color = BLACK;					
					if (newParent.right != null)
						newParent.right.color = BLACK;					
					
				} else if ( ( brotherRightChild == null && brotherLeftChild == null) || (brotherRightChild.color == BLACK && brotherLeftChild.color == BLACK)  ) {
					
					boolean prevParentColor = parent.color;
					brother.color = RED;
					parent.color = BLACK;
					if (prevParentColor == BLACK)
						balanceTree(parent,getParentElement(root, parent));
				}			
				
				
			}
		}
		
		if (tempElement != null) {			
			assignParentNewValue(elementStartFrom, originParent,null);
			elementStartFrom = null;
		}
		
		return parent;
	}
	
	private SimpleEntry prepareBrotherAndSons(SimpleEntry parent, SimpleEntry brother) {
		if (parent == null)
			throw new IllegalArgumentException("parent parameter in prepareBrotherAndSons method is null!!!");
		if (brother == null)
			throw new IllegalArgumentException("brother parameter in prepareBrotherAndSons method is null!!!");		
		boolean isBrotherLeft = false;
		boolean isRedSonLeft = false;
		if (parent.left == brother)
			isBrotherLeft = true;
		if (brother.left != null && brother.left.color == RED)
			isRedSonLeft = true;
		SimpleEntry newBrother = null;
		SimpleEntry newParent = null;
		if (isBrotherLeft) {
			if (!isRedSonLeft) {
				newBrother = rotateLeft(brother);
				parent.left = newBrother;
			}			
			newParent = rotateRight(parent);
		} 
		if (!isBrotherLeft) {
			if (isRedSonLeft) {
				newBrother = rotateRight(brother);
				parent.right = newBrother;
			}			
			newParent = rotateLeft(parent);
		}
		return newParent;
	}
	
	private void assignParentNewValue(SimpleEntry childElem,SimpleEntry parentElem,SimpleEntry newValue) {
		if (parentElem != null) {
			if (parentElem == childElem)
				return;
			if (parentElem.right == childElem)
				parentElem.right = newValue;
			else if (parentElem.left == childElem)
				parentElem.left = newValue;
		}
	}
	
	private SimpleEntry getParentElement(SimpleEntry elementStartFrom, SimpleEntry targetElement) {
		
		if (elementStartFrom == null)
			throw new IllegalArgumentException("elementStartFrom parameter in getParentElement method is null!!!");
		if (targetElement == null)
			throw new IllegalArgumentException("targetElement parameter in getParentElement method is null!!!");		
		
		if (targetElement == root)
			return root;
		
		SimpleEntry parent = null;
		SimpleEntry currElem = elementStartFrom.left;
		
		if (currElem != null) {
			int compare = compare(targetElement.key, currElem.key);		
			if (compare == 0)
				return elementStartFrom;		
			parent = getParentElement(elementStartFrom.left, targetElement);		
			if (parent != null)
				return parent;
		}
				
		currElem = elementStartFrom.right;
		if (currElem != null) {
			int compare = compare(targetElement.key, currElem.key);
			if (compare == 0)
				return elementStartFrom;
			parent = getParentElement(elementStartFrom.right, targetElement);
		}
		
		return parent;
		
	}
	
	private SimpleEntry[] deleteMin(SimpleEntry elementStartFrom) {
		
		if (elementStartFrom == null)
			throw new IllegalArgumentException("elementStartFrom parameter in deleteMin method is null!!!");
		
		SimpleEntry parent = elementStartFrom;
		SimpleEntry minElem = null;
		if (elementStartFrom.left != null) {
			minElem = elementStartFrom.left;
			while(minElem.right != null) {
				parent = minElem;
				minElem = minElem.right;				
				parent.numberNodesInSubTree = size(parent.left) + size(parent.right);				
			}
			if (parent == elementStartFrom)
				parent.left = null;
			else if (parent != null)
				parent.right = null;
			
		} else if (elementStartFrom.right != null) {
			minElem = elementStartFrom.right;
			while(minElem.left != null) {
				parent = minElem;
				minElem = minElem.left;
				parent.numberNodesInSubTree = size(parent.left) + size(parent.right);
			}
			if (parent == elementStartFrom)
				parent.right = null;
			else if (parent != null)
				parent.left = null;
			
		}
		SimpleEntry[] returnValues = new SimpleEntry[2];
		returnValues[0] = minElem;
		returnValues[1] = parent;
		return returnValues;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(SimpleEntry element){
		if (element == null)
			return 0;
		else
			return element.numberNodesInSubTree;
	}
	
	public Iterator iterator() {
		
		Iterator iterator = new Iterator() {

			private int currentIndex = -1;
			SimpleEntry[] list = new SimpleEntry[size()];
			
			{
				createListOfElements(root, list, size()-1);
			}
            
            public boolean hasNext() {
            	if (currentIndex >= size()-1)
            		return false;
            	return true;
            }
            
            public SimpleEntry next() {
            	
            	if (hasNext()) {
            		currentIndex++;
            		return (SimpleEntry)list[currentIndex];
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
	
	private int createListOfElements(SimpleEntry element, SimpleEntry[] list, int index) {
		if (element == null)
			return index;		
		if (index < 0)
			return -1;
		
		int newIndex = index-1;
		newIndex = createListOfElements(element.left, list, newIndex);
		list[index] = element;		
		newIndex = createListOfElements(element.right, list, newIndex);
		return newIndex;		
	}
	
	private boolean isRed(SimpleEntry element) {
		if (element == null)
			return false;
		return element.color == RED;
	}
	
	private SimpleEntry rotateLeft(SimpleEntry element) {
		
		if (element == null)
			throw new IllegalArgumentException("element parameter in rotateLeft method is null!!!");
		
		SimpleEntry x = element.right;		
		element.right = x.left;
		x.left = element;
		x.color = element.color;
		
		element.color = RED;
		x.numberNodesInSubTree = element.numberNodesInSubTree;
		element.numberNodesInSubTree = 1 + size(element.left) + size(element.right);
		
		if (root == element)
			root = x;
		
		return x;
	}
	
	private SimpleEntry rotateRight(SimpleEntry element) {
		
		if (element == null)
			throw new IllegalArgumentException("element parameter in rotateRight method is null!!!");
		
		SimpleEntry x = element.left;
		element.left = x.right;
		x.right = element;
		x.color = element.color;
		element.color = RED;
		x.numberNodesInSubTree = element.numberNodesInSubTree;
		element.numberNodesInSubTree = 1 + size(element.left) + size(element.right);
		
		if (root == element)
			root = x;
		return x;
	}
	
	private void flipColors(SimpleEntry element) {
		
		if (element == null)
			throw new IllegalArgumentException("element parameter in flipColors method is null!!!");		
		
		element.color = RED;
		element.left.color = BLACK;
		element.right.color = BLACK;
	}
	
	
	class SimpleEntry implements Entry {
		
		private Object key;
		private Object value;
		private SimpleEntry left;
		private SimpleEntry right;
		private int numberNodesInSubTree;
		private boolean color;
		
		public SimpleEntry( Object key, Object value, int numberNodesInSubTree, boolean color) {
			this.key = key;
			this.value = value;
			this.numberNodesInSubTree = numberNodesInSubTree;
			this.color = color;
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
		
		public Object setValue(Object value) {
			Object oldValue = this.value;
			this.value = value;
			return oldValue;
		}
		
	}
	

}
