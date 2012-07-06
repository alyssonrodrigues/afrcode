package datastruct;


public class SimpleLinkedList {
	private SimpleLinkedNode nil = null;
	
	public SimpleLinkedList() {
		nil = new SimpleLinkedNode(new Element(Integer.MAX_VALUE, null));
		nil.next = nil;
	}
	
	// O(1) ...
	public void insert(Element x) {
		SimpleLinkedNode oldFirst = nil.next;
		SimpleLinkedNode newFirst = new SimpleLinkedNode(x);
		
		nil.next = newFirst;
		newFirst.next = oldFirst;
	}
	
	// O(n) ...
	public void delete(Element x) {
		SimpleLinkedNode node = nil.next;
		SimpleLinkedNode prevNode = nil;
		SimpleLinkedNode nextNode = node.next;
		while (node != nil && !node.element.key.equals(x.key)) {
			prevNode = node;
			node = node.next;
			nextNode = node.next;
		}
		
		if (node != nil) {
			prevNode.next = nextNode;
		}
	}
	
	// O(n) ...
	public void revert() {
		SimpleLinkedNode node = nil.next;
		SimpleLinkedNode prevNode = nil;
		while (node != nil) {
			SimpleLinkedNode nextNode = node.next;
			node.next = prevNode;
			prevNode = node;
			node = nextNode;
		}
		nil.next = prevNode;
	}
	
	// O(n) ...
	public void swap(Element x, Element y) {
		SimpleLinkedNode a = searchSimpleLinkedNode(x);
		SimpleLinkedNode b = searchSimpleLinkedNode(y);
		swap(a, b);
	}
	
	// O(n^2) ...
	public void sort() {
		SimpleLinkedNode node = nil.next;
		while (node != nil) {
			SimpleLinkedNode minNode = node;
			SimpleLinkedNode other = minNode.next;
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

	// O(n) ...
	public void concat(SimpleLinkedList s) {
		SimpleLinkedNode thisPriorNil = searchPriorNode(nil, this);
		SimpleLinkedNode sPriorNil = searchPriorNode(s.nil, s);
		
		thisPriorNil.next = s.nil.next;
		sPriorNil.next = this.nil;
	}
	
	private void swap(SimpleLinkedNode a, SimpleLinkedNode b) {
		if (a.next == b) {
			SimpleLinkedNode prevA = searchPriorNode(a, this);
			a.next = b.next;
			b.next = a;
			prevA.next = b;
		} else if (b.next == a) {
			SimpleLinkedNode prevB = searchPriorNode(b, this);
			b.next = a.next;
			a.next = b;
			prevB.next = a;
		} else {
			SimpleLinkedNode[] priors = searchPriorNodes(a, b);
			SimpleLinkedNode prevA = priors[0];
			SimpleLinkedNode prevB = priors[1];
			SimpleLinkedNode nextA = a.next;
			a.next = b.next;
			prevA.next = b;
			b.next = nextA;
			prevB.next = a;
		}
	}
	
	private SimpleLinkedNode[] searchPriorNodes(SimpleLinkedNode a,
			SimpleLinkedNode b) {
		SimpleLinkedNode priorA = nil;
		SimpleLinkedNode priorB = nil;
		SimpleLinkedNode actualNode = nil.next;
		while (actualNode != nil && 
				(!actualNode.equals(a) || !actualNode.equals(b))) {
			SimpleLinkedNode prior = actualNode;
			actualNode = actualNode.next;
			if (actualNode.equals(a)) {
				priorA = prior;
			}
			if (actualNode.equals(b)) {
				priorB = prior;
			}
		}
		
		return new SimpleLinkedNode[] {priorA, priorB};
	}
	
	private SimpleLinkedNode searchPriorNode(SimpleLinkedNode node, 
			SimpleLinkedList l) {
		SimpleLinkedNode priorNode = l.nil;
		SimpleLinkedNode actualNode = l.nil.next;
		while (actualNode != l.nil && !actualNode.equals(node)) {
			priorNode = actualNode;
			actualNode = actualNode.next;
		}
		return priorNode;
	}

	public Element search(Element x) {
		Element e = null;
		SimpleLinkedNode node = searchSimpleLinkedNode(x);
		if (node != nil) {
			e = node.element;
		}
		return e;
	}
	
	// O(n) ...
	public Element[] minMax() {
		SimpleLinkedNode node = nil.next;
		SimpleLinkedNode minNode = node;
		SimpleLinkedNode maxNode = node;
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
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		SimpleLinkedNode node = nil.next;
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
	
	@Override
	public boolean equals(Object obj) {
		SimpleLinkedList sl = (SimpleLinkedList) obj;
		SimpleLinkedNode nA = nil.next;
		SimpleLinkedNode nB = sl.nil.next;
		if (nA != nil ^ nB != sl.nil) {
			return false;
		}
		while (nA != nil && nB != sl.nil) {
			if (!nA.element.key.equals(nB.element.key)) {
				return false;
			}
			nA = nA.next;
			nB = nB.next;
		}
		return nA == nil && nB == sl.nil;
	}
	
	private SimpleLinkedNode searchSimpleLinkedNode(Element x) {
		SimpleLinkedNode node = nil.next;
		while (node != null && !node.element.key.equals(x.key)) {
			node = node.next;
		}
		return node;
	}

	private class SimpleLinkedNode {
		public SimpleLinkedNode next;
		public Element element;
		
		public SimpleLinkedNode(Element e) {
			this.element = e;
		}
		
		public SimpleLinkedNode(SimpleLinkedNode next, Element e) {
			this.next = next;
			this.element = e;
		}
		
		@Override
		public String toString() {
			return "[" + element.toString() + ", " + 
			next.element.toString() + "]";
		}
		
		@Override
		public boolean equals(Object obj) {
			SimpleLinkedNode n = (SimpleLinkedNode) obj;
			return element.equals(n.element);
		}
	}
}
