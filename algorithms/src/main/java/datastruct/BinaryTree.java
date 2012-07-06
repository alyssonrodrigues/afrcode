package datastruct;

import java.util.ArrayList;
import java.util.List;


public class BinaryTree {
	private TreeNode root = null;
	
	public BinaryTree(Element x) {
		root = new TreeNode(x);
		root.p = null;
		root.left = null;
		root.right = null;
	}
	
	public Element delete(Element x) {
		TreeNode node = search(root, x.key);
		TreeNode nodeRemoved = null;
		if (node.left == null || node.right == null) {
			// Node to be removed has at most on child ...
			nodeRemoved = node;
		} else {
			// Node to be removed has two children ...
			nodeRemoved = sucessorTreeNode(node);
		}
		TreeNode aux = null;
		if (nodeRemoved.left != null) {
			// Node to be removed has one child ...
			aux = nodeRemoved.left;
		} else {
			// Node to be removed has two children ...
			aux = nodeRemoved.right;
		}
		if (aux != null) {
			// Removes the node ...
			aux.p = nodeRemoved.p;
		}
		if (nodeRemoved.p == null) {
			root = aux;
		} else if (nodeRemoved == nodeRemoved.p.left) {
			// Node removed is on left so will be aux ...
			nodeRemoved.p.left = aux;
		} else {
			// Node removed is on right so will be aux ...
			nodeRemoved.p.right = aux;
		}
		if (!nodeRemoved.element.equals(node.element)) {
			// We removed the node's sucessor, now we need to copy key and data
			// from it to node ...
			node.element = nodeRemoved.element;
		}
		return nodeRemoved != null ? nodeRemoved.element : null;
	}
	
	// O(n) ...
	public int height() {
		return height(root);
	}
	
	private int height(TreeNode node) {
		int hl = 0;
		int hr = 0;
		if (node == null) {
			// End of subtree ...
			return 0;
		}
		if (node.left != null) {
			hl = height(node.left);
		}
		if (node.right != null) {
			hr = height(node.right);
		}
		// returns max(hl, hr) + 1, bottom up numbering ...
		return hl > hr ? hl + 1 : hr + 1;
	}
	
	// O(n) ...
	public int n() {
		return n(root);
	}
	
	private int n(TreeNode node) {
		if (node == null) {
			return 0;
		}
		return n(node.left) + n(node.right) + 1;
	}
	
	// O(n) ...
	public Element[] inOrderWalk() {
		List<Element> r = new ArrayList<Element>();
		inOrderWalk(root, r);
		return r.toArray(new Element[0]);
	}
	
	public Element[] inPreOrderWalk() {
		List<Element> r = new ArrayList<Element>();
		inPreOrderWalk(root, r);
		return r.toArray(new Element[0]);
	}
	
	private void inPreOrderWalk(TreeNode node, List<Element> elements) {
		int n = n();
		Queue queue = new Queue(n);
		queue.enqueue(new Element(1, node));
		while (!queue.empty()) {
			TreeNode oneNode = (TreeNode) queue.dequeue().data;
			elements.add(oneNode.element);
			if (oneNode.left != null) {
				queue.enqueue(new Element(1, oneNode.left));
			}
			if (oneNode.right != null) {
				queue.enqueue(new Element(1, oneNode.right));
			}
		}
	}
	
	// O(h) ...
	public void insert(Element v) {
		TreeNode newNode = new TreeNode(v);
		newNode.p = null;
		newNode.left = null;
		newNode.right = null;
		
		TreeNode x = root;
		TreeNode newNodeP = null;
		while (x != null) {
			newNodeP = x;
			if (newNode.element.key.compareTo(x.element.key) < 0) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		
		newNode.p = newNodeP;
		if (newNodeP == null) {
			root = newNode;
		} else if (newNode.element.key.compareTo(newNodeP.element.key) < 0) {
			newNodeP.left = newNode;
		} else {
			newNodeP.right = newNode;
		}
	}
	
	// O(h) ...	
	public Element maximum() {
		return maximum(root);
	}
	
	// O(h) ...
	public Element minimum() {
		return minimum(root);
	}
	
	// O(h) ...
	public Element predecessor(Element x) {
		return predecessor(search(root, x.key));
	}
	
	// O(h) ...
	public Element search(Comparable key) {
		TreeNode node = search(root, key);
		return node != null ? node.element : null;
	}

	// O(h) ...
	public Element sucessor(Element x) {
		return sucessor(search(root, x.key));
	}
	
	private void inOrderWalk(TreeNode node, List<Element> inOrderElements) {
		if (node != null) {
			inOrderWalk(node.left, inOrderElements);
			inOrderElements.add(node.element);
			inOrderWalk(node.right, inOrderElements);
		}
	}

	private Element maximum(TreeNode node) {
		while (node.right != null) {
			node = node.right;
		}
		return node.element;
	}

	private Element minimum(TreeNode node) {
		return minimumTreeNode(node).element;
	}

	private TreeNode minimumTreeNode(TreeNode node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	private Element predecessor(TreeNode node) {
		if (node.left != null) {
			return maximum(node.left);
		}
		TreeNode lowestP = node.p;
		// node == lowestP.left means y is greater than node so we need
		// to go to lowestP's parent 
		// (when node == lowestP.right lowestP will be less than node = node's predecessor)
		while (lowestP != null && node == lowestP.left) {
			node = lowestP;
			lowestP = lowestP.p;
		}
		return lowestP.element;
	}
	
	private TreeNode search(TreeNode node, Comparable key) {
		if (node == null || node.element.key.equals(key)) {
			return node;
		}
		if (key.compareTo(node.element.key) < 0) {
			return search(node.left, key);
		} else {
			return search(node.right, key);
		}
	}
	
	private Element sucessor(TreeNode node) {
		return sucessorTreeNode(node).element;
	}

	private TreeNode sucessorTreeNode(TreeNode node) {
		if (node.right != null) {
			return minimumTreeNode(node.right);
		}
		TreeNode greatestP = node.p;
		// node == greatestP.right means y is less than node so we need 
		// to go to greatestP's parent 
		// (when node == greatestP.left greatestP will be greater than node = node's sucessor)
		while (greatestP != null && node == greatestP.right) {
			node = greatestP;
			greatestP = greatestP.p;
		}
		return greatestP;
	}
	
	private class TreeNode {
		public TreeNode p;
		public TreeNode left;
		public TreeNode right;
		public Element element;
		// TODO: Not implemented at all ...
		public int nELeft;
		
		public TreeNode(Element x) {
			this.element = x;
		}
	}
}
