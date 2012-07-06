package datastruct;

public class LinkedList {
	// root sentinel node ...
	private LinkedNode nil;

	public LinkedList() {
		Element e = new Element(Integer.MAX_VALUE, null);
		nil = new LinkedNode(e);
		nil.prev = nil;
		nil.next = nil;
	}

	public void insert(Element x) {
		LinkedNode node = new LinkedNode(x);
		node.prev = nil;
		node.next = nil.next;
		nil.next.prev = node;
		nil.next = node;
	}
	
	// O(n) ...
	public Element[] minMax() {
		LinkedNode node = nil.next;
		LinkedNode minNode = node;
		LinkedNode maxNode = node;
		while (node != nil) {
			if (node.element.key.compareTo(minNode.element.key) < 0) {
				minNode = node;
			}
			if (node.element.key.compareTo(maxNode.element.key) > 0) {
				maxNode = node;
			}
			node = node.next;
		}
		return new Element[] {minNode.element, maxNode.element};
	}
	
	// O(n) ...
	public void revert() {
		LinkedNode newFirst = nil.prev;
		LinkedNode newLast = nil.next;
		// Reverts first and last nodes ...
		nil.next = newFirst;
		nil.prev = newLast;
		LinkedNode node = newFirst;
		while (node != nil) {
			LinkedNode tempNext = node.next;
			// Reverts internals pointers ...
			node.next = node.prev;
			node.prev = tempNext;
			node = node.next;
		}
	}
	
	// O(1) ...
	public void concat(LinkedList l) {
		LinkedNode thisLast = nil.prev;
		LinkedNode lFirst = l.nil.next;
		LinkedNode lLast = l.nil.prev;

		thisLast.next = lFirst;
		lFirst.prev = thisLast;
		lLast.next = nil;
		nil.prev = lLast;
	}

	// O(n^2) ...
	public void sort() {
		LinkedNode node = nil.next;
		while (node != nil.prev) {
			LinkedNode minNode = node;
			LinkedNode other = minNode.next;
			while (other != nil) {
				if (other.element.key.compareTo(minNode.element.key) < 0) {
					minNode = other;
				}
				other = other.next;
			}
			swap(node, minNode);
			// after swap actual node is minNode ...
			node = minNode.next;
		}
	}

	public void swap(Element a, Element b) {
		LinkedNode nodeA = searchLinkedNode(a);
		LinkedNode nodeB = searchLinkedNode(b);
		swap(nodeA, nodeB);
	}

	private void swap(LinkedNode nodeA, LinkedNode nodeB) {
		if (nodeA.next == nodeB) {
			LinkedNode tempAPrev = nodeA.prev;
			LinkedNode tempBNext = nodeB.next;

			tempAPrev.next = nodeB;
			nodeB.prev = tempAPrev;
			nodeB.next = nodeA;
			nodeA.prev = nodeB;
			nodeA.next = tempBNext;
			tempBNext.prev = nodeA;
		} else if (nodeB.next == nodeA) {
			LinkedNode tempBPrev = nodeB.prev;
			LinkedNode tempANext = nodeA.next;
			
			tempBPrev.next = nodeA;
			nodeA.prev = tempBPrev;
			nodeA.next = nodeB;
			nodeB.prev = nodeA;
			nodeB.next = tempANext;
			tempANext.prev = nodeB;
		} else {
			LinkedNode tempAPrev = nodeA.prev;
			LinkedNode tempANext = nodeA.next;

			nodeA.prev = nodeB.prev;
			nodeA.next = nodeB.next;
			tempAPrev.next = nodeB;
			tempANext.prev = nodeB;

			nodeB.prev.next = nodeA;
			nodeB.next.prev = nodeA;
			nodeB.prev = tempAPrev;
			nodeB.next = tempANext;
		}
	}

	public Element delete(Element x) {
		LinkedNode node = searchLinkedNode(x);
		if (node != nil) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			return node.element;
		} else {
			return null;
		}
	}

	public Element search(Element x) {
		Element e = null;
		LinkedNode node = searchLinkedNode(x);
		if (node != nil) {
			e = node.element;
		}
		return e; 
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		LinkedNode node = nil.next;
		while (node != nil) {
			sb.append(node.element.key.toString());
			node = node.next;
			if (node != nil) {
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}

	private LinkedNode searchLinkedNode(Element x) {
		LinkedNode node = nil.next;
		while (node != nil && !node.element.key.equals(x.key)) {
			node = node.next;
		}
		return node;
	}

	private class LinkedNode {
		public Element element;
		public LinkedNode prev;
		public LinkedNode next;

		public LinkedNode(Element element) {
			this.element = element;
		}

		public LinkedNode(LinkedNode prev, Element element, LinkedNode next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
		
		@Override
		public boolean equals(Object obj) {
			LinkedNode l = (LinkedNode) obj;
			return element.equals(l.element);
		}

		@Override
		public String toString() {
			return "[" + prev.element.toString() + ", " + 
			element.toString() + ", " + next.element.toString() + "]";
		}
	}

	@Override
	public boolean equals(Object o) {
		LinkedList l = (LinkedList) o;
		LinkedNode nA = nil.next;
		LinkedNode nB = l.nil.next;
		if (nA != nil ^ nB != l.nil) {
			return false;
		}
		while (nA != nil && nB != l.nil) {
			if (!nA.equals(nB)) {
				return false;
			}
			nA = nA.next;
			nB = nB.next;
		}
		return nA == nil && nB == l.nil;
	}
}
