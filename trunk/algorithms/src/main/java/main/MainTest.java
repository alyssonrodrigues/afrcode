package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import algorithm.AlienNumbersConverter;
import algorithm.ArrayUtil;
import algorithm.BubbleSort;
import algorithm.HeapSort;
import algorithm.KnightTour;
import algorithm.MinMax;
import algorithm.StringUtil;
import datastruct.BinaryTree;
import datastruct.Element;
import datastruct.LinkedList;
import datastruct.MaxHeap;
import datastruct.Queue;
import datastruct.SimpleLinkedList;
import datastruct.Stack;

public class MainTest {
	
	@Test
	public void testAlienNumbersConverter() {
		assertEquals("9", 
				AlienNumbersConverter.convertOneNumber(
						"Foo", "oF8" , "0123456789"));
		assertEquals("100", 
				AlienNumbersConverter.convertOneNumber(
						"100", "0123456789" , "0123456789"));
		assertEquals("JAM!", 
				AlienNumbersConverter.convertOneNumber(
						"CODE", "O!CDE?" , "A?JM!."));
	}
	
	@Test
	public void testKnightTour() {
		KnightTour tour = new KnightTour(8);
		assertEquals(64, tour.walkFrom(4, 1));
		assertEquals(64, tour.walkFrom(0, 0));
		assertEquals(64, tour.walkFrom(7, 7));
		assertEquals(64, tour.walkFrom(4, 7));
		
		tour = new KnightTour(5);
		// suscessful only from 0,0
		assertEquals(25, tour.walkFrom(0, 0));
		
		tour = new KnightTour(6);
		assertEquals(36, tour.walkFrom(0, 0));
		assertEquals(36, tour.walkFrom(5, 3));
		
		tour = new KnightTour(16);
		assertEquals(16*16, tour.walkFrom(0, 0));
		assertEquals(16*16, tour.walkFrom(4, 7));
	}
	
	@Test
	public void testBubleSort() {
		Element[] elements = createElements();
		Element[] sortedElements = createSortedElements();
		Element[] aux = Arrays.copyOfRange(elements, 0, elements.length);
		BubbleSort.sort(aux);
		assertTrue(Arrays.equals(sortedElements, aux));
	}
	
	@Test
	public void testDataStructs() {
		Element[] elements = createElements();
		MaxHeap heap = new MaxHeap(elements);
		Element[] elementsMaxHeapExpected =
			createMaxHeapElements();
		assertTrue(Arrays.equals(elementsMaxHeapExpected, heap.getElements()));
		
		elements = createElements();
		Stack stack = new Stack(10);
		for (Element e : elements) {
			stack.push(e);
		}
		assertEquals(7, stack.pop().key);
		
		Queue queue = new Queue(10);
		for (Element e : elements) {
			queue.enqueue(e);
		}
		assertEquals(4, queue.dequeue().key);
		queue.enqueue(new Element(100, null));
		assertEquals(1, queue.dequeue().key);
		
		elements = createElements();
		LinkedList linkedList = new LinkedList();
		LinkedList linkedListExpected = createLinkedListSem16();
		for (Element e : elements) {
			linkedList.insert(e);
		}
		linkedList.delete(new Element(16, null));
		assertEquals(linkedListExpected, linkedList);
		linkedListExpected = createLinkedListSem16E1();
		linkedList.delete(new Element(1, null));
		assertEquals(linkedListExpected, linkedList);
		linkedListExpected = createLinkedListSem16E1E8();
		linkedList.delete(new Element(8, null));
		assertEquals(linkedListExpected, linkedList);
	}

	@Test
	public void testMinMax() {
		Element[] elements = createElements();
		Element[] minMax = {new Element(1, null),
				new Element(16, null)};
		assertTrue(Arrays.equals(minMax, MinMax.minMax(elements)));
	}
	@Test
	public void testSortLinkedList() {
		LinkedList linkedListExpected =
			new LinkedList();
		linkedListExpected.insert(new Element(16, null));
		linkedListExpected.insert(new Element(14, null));
		linkedListExpected.insert(new Element(10, null));
		linkedListExpected.insert(new Element(9, null));
		linkedListExpected.insert(new Element(8, null));
		linkedListExpected.insert(new Element(7, null));
		linkedListExpected.insert(new Element(4, null));
		linkedListExpected.insert(new Element(3, null));
		linkedListExpected.insert(new Element(2, null));
		linkedListExpected.insert(new Element(1, null));
		LinkedList linkedList = new LinkedList();
		Element[] elements = createElements();
		for (Element e : elements) {
			linkedList.insert(e);
		}
		linkedList.sort();
		assertEquals(linkedListExpected, linkedList);
	}
	
	@Test
	public void testSwapLinkedList() {
		Element[] elements = createElements();
		LinkedList linkedListExpected =
			new LinkedList();
		linkedListExpected.insert(new Element(4, null));
		linkedListExpected.insert(new Element(1, null));
		linkedListExpected.insert(new Element(3, null));
		linkedListExpected.insert(new Element(2, null));
		linkedListExpected.insert(new Element(8, null));
		linkedListExpected.insert(new Element(9, null));
		linkedListExpected.insert(new Element(10, null));
		linkedListExpected.insert(new Element(14, null));
		linkedListExpected.insert(new Element(16, null));
		linkedListExpected.insert(new Element(7, null));
		LinkedList linkedList = new LinkedList();
		for (Element e : elements) {
			linkedList.insert(e);
		}
		linkedList.swap(new Element(16, null), new Element(8, null));
		assertEquals(linkedListExpected, linkedList);
	}
	
	@Test
	public void testSwapLinkedListNodeANextToNodeB() {
		Element[] elements = createElements();
		LinkedList linkedListExpected =
			new LinkedList();
		linkedListExpected.insert(new Element(4, null));
		linkedListExpected.insert(new Element(1, null));
		linkedListExpected.insert(new Element(2, null));
		linkedListExpected.insert(new Element(3, null));
		linkedListExpected.insert(new Element(16, null));
		linkedListExpected.insert(new Element(9, null));
		linkedListExpected.insert(new Element(10, null));
		linkedListExpected.insert(new Element(14, null));
		linkedListExpected.insert(new Element(8, null));
		linkedListExpected.insert(new Element(7, null));
		LinkedList linkedList = new LinkedList();
		for (Element e : elements) {
			linkedList.insert(e);
		}
		linkedList.swap(new Element(2, null), new Element(3, null));
		linkedList.swap(new Element(3, null), new Element(2, null));
		linkedList.swap(new Element(3, null), new Element(2, null));
		assertEquals(linkedListExpected, linkedList);
	}
	
	@Test
	public void testMinMaxSwapLinkedList() {
		Element[] elements = createElements();
		Element[] expectedElements = new Element[] {
				new Element(1, null),
				new Element(16, null)};
		LinkedList linkedList = new LinkedList();
		for (Element e : elements) {
			linkedList.insert(e);
		}
		Element[] minMaxElements = linkedList.minMax();
		assertTrue(Arrays.equals(expectedElements, minMaxElements));
		LinkedList linkedListExpected = createLinkedListMinMaxSwap();
		linkedList.swap(minMaxElements[0], minMaxElements[1]);
		assertEquals(linkedListExpected, linkedList);
	}
	
	@Test
	public void testConcatLinkedLists() {
		Element[] elements = createElements();
		LinkedList linkedListA = new LinkedList();
		for (Element e : elements) {
			linkedListA.insert(e);
		}
		LinkedList linkedListB = new LinkedList();
		for (Element e : elements) {
			linkedListB.insert(e);
		}
		LinkedList linkedListExpected = new LinkedList();
		for (int i = 0; i < 2; i++) {
			for (Element e : elements) {
				linkedListExpected.insert(e);
			}
		}
		linkedListA.concat(linkedListB);
		assertEquals(linkedListExpected, linkedListA);
	}
	
	@Test
	public void testReverLinkedList() {
		Element[] elements = createElements();
		LinkedList linkedList = new LinkedList();
		for (Element e : elements) {
			linkedList.insert(e);
		}
		LinkedList linkedListExpected = new LinkedList();
		for (int i = elements.length - 1; i >= 0; i--) {
			linkedListExpected.insert(elements[i]);
		}
		linkedList.revert();
		assertEquals(linkedListExpected, linkedList);
	}
	
	@Test
	public void testRevertString() {
		char[] s = "lago".toCharArray();
		StringUtil.revert(s);
		assertTrue(Arrays.equals("ogal".toCharArray(), s));
		s = "opa".toCharArray();
		StringUtil.revert(s);
		assertTrue(Arrays.equals("apo".toCharArray(), s));
	}
	
	@Test
	public void testPermutString() {
		char[] s = "ab".toCharArray();
		List<String> pExpected = Arrays.asList(
				new String[] {"ab", "ba"});
		List<String> p = StringUtil.permut(s, 0);
		assertTrue(p.containsAll(pExpected) && pExpected.containsAll(p));
		s = "lago".toCharArray();
		pExpected = Arrays.asList(
				new String[] {"lago", "laog", "lgao", "lgoa", "loag",
						"loga", "algo", "alog", "aglo",
						"agol", "aolg", "aogl", "glao",
						"gloa", "galo", "gaol", "gola",
						"goal", "olag", "olga", "oalg",
						"oagl", "ogla", "ogal"});
		p = StringUtil.permut(s, 0);
		assertTrue(p.containsAll(pExpected) && pExpected.containsAll(p));
	}
	
	@Test
	public void testSimpleLinkedList() {
		Element[] elements = createElements();
		SimpleLinkedList simpleLinkedList =
			new SimpleLinkedList();
		for (Element e : elements) {
			simpleLinkedList.insert(e);
		}
		simpleLinkedList.delete(new Element(16, null));
		SimpleLinkedList simpleLinkedListExpected =
			createSimpleLinkedListSem16();
		assertEquals(simpleLinkedListExpected, simpleLinkedList);
		assertTrue(Arrays.equals(
				new Element[] {new Element(1, null),
						new Element(14, null)}, simpleLinkedList.minMax()));
	}
	
	@Test
	public void testRevertSimpleLinkedList() {
		Element[] elements = createElements();
		SimpleLinkedList simpleLinkedList =
			new SimpleLinkedList();
		for (Element e : elements) {
			simpleLinkedList.insert(e);
		}
		simpleLinkedList.revert();
		SimpleLinkedList simpleLinkedListExpected =
			new SimpleLinkedList();
		for (int i = elements.length - 1; i >= 0; i--) {
			simpleLinkedListExpected.insert(elements[i]);
		}
		assertEquals(simpleLinkedListExpected, simpleLinkedList);
	}
	
	@Test
	public void testSwapSimpleLinkedList() {
		Element[] elements = createElements();
		SimpleLinkedList linkedListExpected =
			new SimpleLinkedList();
		linkedListExpected.insert(new Element(4, null));
		linkedListExpected.insert(new Element(1, null));
		linkedListExpected.insert(new Element(3, null));
		linkedListExpected.insert(new Element(2, null));
		linkedListExpected.insert(new Element(8, null));
		linkedListExpected.insert(new Element(9, null));
		linkedListExpected.insert(new Element(10, null));
		linkedListExpected.insert(new Element(14, null));
		linkedListExpected.insert(new Element(16, null));
		linkedListExpected.insert(new Element(7, null));
		SimpleLinkedList linkedList = new SimpleLinkedList();
		for (Element e : elements) {
			linkedList.insert(e);
		}
		linkedList.swap(new Element(16, null), new Element(8, null));
		assertEquals(linkedListExpected, linkedList);
	}
	
	@Test
	public void testSortSimpleLinkedList() {
		SimpleLinkedList linkedListExpected =
			new SimpleLinkedList();
		linkedListExpected.insert(new Element(16, null));
		linkedListExpected.insert(new Element(14, null));
		linkedListExpected.insert(new Element(10, null));
		linkedListExpected.insert(new Element(9, null));
		linkedListExpected.insert(new Element(8, null));
		linkedListExpected.insert(new Element(7, null));
		linkedListExpected.insert(new Element(4, null));
		linkedListExpected.insert(new Element(3, null));
		linkedListExpected.insert(new Element(2, null));
		linkedListExpected.insert(new Element(1, null));
		SimpleLinkedList linkedList = new SimpleLinkedList();
		Element[] elements = createElements();
		for (Element e : elements) {
			linkedList.insert(e);
		}
		linkedList.sort();
		assertEquals(linkedListExpected, linkedList);
	}
	
	@Test
	public void testConcatSimpleLinkedLists() {
		Element[] elements = createElements();
		SimpleLinkedList linkedListA = new SimpleLinkedList();
		for (Element e : elements) {
			linkedListA.insert(e);
		}
		SimpleLinkedList linkedListB = new SimpleLinkedList();
		for (Element e : elements) {
			linkedListB.insert(e);
		}
		SimpleLinkedList linkedListExpected = new SimpleLinkedList();
		for (int i = 0; i < 2; i++) {
			for (Element e : elements) {
				linkedListExpected.insert(e);
			}
		}
		linkedListA.concat(linkedListB);
		assertEquals(linkedListExpected, linkedListA);
	}
	
	@Test
	public void testSplitString() {
		char[] s = "Alysson Felix Rodrigues".toCharArray();
		char t = ' ';
		String[] wordsExpected = new String[] {"Alysson", "Felix", "Rodrigues"};
		String[] words = StringUtil.split(s, t).toArray(new String[0]);
		assertTrue(Arrays.equals(wordsExpected, words));
		s = "a b ".toCharArray();
		wordsExpected = new String[] {"a", "b"};
		words = StringUtil.split(s, t).toArray(new String[0]);
		assertTrue(Arrays.equals(wordsExpected, words));
	}
	
	@Test
	public void testHeapSort() {
		Element[] elements = createElements();
		Element[] sortedElements = createSortedElements();
		HeapSort.sort(elements);
		assertTrue(Arrays.equals(sortedElements, elements));
	}
	
	@Test
	public void testNonZeroFirst() {
		int[] elements = new int[] {0, -10, 5, 0, 0, 10, 4, 9, 0, 6};
		int[] elementsExpec = new int[] {-10, 10, 9, 6, 5, 4, 0, 0, 0, 0};
		ArrayUtil.nonZeroFirst(elements);
		assertTrue(Arrays.equals(elementsExpec, elements));
	}
	
	@Test
	public void testFasterNonZeroFirst() {
		int[] elements = new int[] {0, -10, 5, 0, 0, 10, 4, 9, 0, 6};
		int[] elementsExpec = new int[] {-10, 5, 10, 4, 9, 6, 0, 0, 0, 0};
		ArrayUtil.fasterNonZeroFirst(elements);
		assertTrue(Arrays.equals(elementsExpec, elements));
	}
	
	@Test
	public void testWhichNumberIsMissing() {
		int[] elements = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 10};
		int missingNumber = 9;
		assertEquals(missingNumber, ArrayUtil.whichNumberIsMissing(elements));
	}
	
	@Test
	public void testHasNumberTwice() {
		int[] elements = new int[] {1, 2, 3, 4, 5, 6, 6, 8, 9, 10};
		boolean has = true;
		assertEquals(has, ArrayUtil.hasNumberTwice(elements));
		elements = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		has = false;
		assertEquals(has, ArrayUtil.hasNumberTwice(elements));
	}
	
	@Test
	public void testTreeInsert() {
		BinaryTree tree = createBinaryTree();
		Element[] elements = new Element[] {
				new Element(2, null),
				new Element(5, null),
				new Element(9, null),
				new Element(12, null),
				new Element(13, null),
				new Element(15, null),
				new Element(17, null),
				new Element(18, null),
				new Element(19, null)};
		assertTrue(Arrays.equals(elements, tree.inOrderWalk()));
	}
	
	@Test
	public void testTreeDeleteLeave() {
		BinaryTree tree = createBinaryTree();
		Element[] elements = new Element[] {
				new Element(2, null),
				new Element(5, null),
				new Element(9, null),
				new Element(12, null),
				new Element(15, null),
				new Element(17, null),
				new Element(18, null),
				new Element(19, null)};
		tree.delete(new Element(13, null));
		assertTrue(Arrays.equals(elements, tree.inOrderWalk()));
	}
	
	@Test
	public void testTreeInPreOrderWalk() {
		BinaryTree tree = createBinaryTree();
		Element[] elements = new Element[] {
				new Element(12, null),
				new Element(5, null),
				new Element(18, null),
				new Element(2, null),
				new Element(9, null),
				new Element(15, null),
				new Element(19, null),
				new Element(13, null),
				new Element(17, null)};
		assertTrue(Arrays.equals(elements, tree.inPreOrderWalk()));
	}
	
	@Test
	public void testTreeDeleteNodeWithOneChild() {
		BinaryTree tree = createBinaryTree();
		Element[] elements = new Element[] {
				new Element(2, null),
				new Element(5, null),
				new Element(9, null),
				new Element(12, null),
				new Element(17, null),
				new Element(18, null),
				new Element(19, null)};
		tree.delete(new Element(13, null));
		tree.delete(new Element(15, null));
		assertTrue(Arrays.equals(elements, tree.inOrderWalk()));
	}
	
	@Test
	public void testTreeDeleteNodeWithTwoChildren() {
		BinaryTree tree = createBinaryTree();
		Element[] elements = new Element[] {
				new Element(2, null),
				new Element(9, null),
				new Element(12, null),
				new Element(13, null),
				new Element(15, null),
				new Element(17, null),
				new Element(18, null),
				new Element(19, null)};
		tree.delete(new Element(5, null));
		assertTrue(Arrays.equals(elements, tree.inOrderWalk()));
	}

	@Test
	public void testTreeSearch() {
		BinaryTree tree = createBinaryTree();
		assertEquals(new Element(5, null), tree.search(5));
	}
	
	@Test
	public void testTreeMinimum() {
		BinaryTree tree = createBinaryTree();
		assertEquals(new Element(2, null), tree.minimum());
	}
	
	@Test
	public void testTreeMaximum() {
		BinaryTree tree = createBinaryTree();
		assertEquals(new Element(19, null), tree.maximum());
	}
	
	@Test
	public void testTreePredecessor() {
		BinaryTree tree = createBinaryTree();
		assertEquals(new Element(13, null), 
				tree.predecessor(new Element(15, null)));
	}
	
	@Test
	public void testTreeSucessor() {
		BinaryTree tree = createBinaryTree();
		assertEquals(new Element(18, null), 
				tree.sucessor(new Element(17, null)));
	}
	
	@Test
	public void testTreeHeight() {
		BinaryTree tree = createSortedBinaryTree();
		assertEquals(8, tree.height());
		tree = createBinaryTree();
		assertEquals(4, tree.height());
		tree = new BinaryTree(new Element(5, null));
		tree.insert(new Element(3, null));
		tree.insert(new Element(6, null));
		assertEquals(2, tree.height());
	}
	
	@Test
	public void testTreeN() {
		BinaryTree tree = createBinaryTree();
		assertEquals(9, tree.n());
	}
	
	private BinaryTree createSortedBinaryTree() {
		BinaryTree tree = new BinaryTree(new Element(2, null));
		tree.insert(new Element(5, null));
		tree.insert(new Element(9, null));
		tree.insert(new Element(13, null));
		tree.insert(new Element(15, null));
		tree.insert(new Element(17, null));
		tree.insert(new Element(18, null));
		tree.insert(new Element(19, null));
		return tree;
	}

	private BinaryTree createBinaryTree() {
		BinaryTree tree = new BinaryTree(new Element(12, null));
		tree.insert(new Element(5, null));
		tree.insert(new Element(2, null));
		tree.insert(new Element(9, null));
		tree.insert(new Element(18, null));
		tree.insert(new Element(15, null));
		tree.insert(new Element(13, null));
		tree.insert(new Element(17, null));
		tree.insert(new Element(19, null));
		return tree;
	}

	private Element[] createElements() {
		Element[] elements = 
			new Element[] {
			new Element(4, null),
			new Element(1, null),
			new Element(3, null),
			new Element(2, null),
			new Element(16, null),
			new Element(9, null),
			new Element(10, null),
			new Element(14, null),
			new Element(8, null),
			new Element(7, null)};
		
		return elements;
	}
	
	private LinkedList createLinkedListMinMaxSwap() {
		LinkedList l = new LinkedList();
		l.insert(new Element(4, null));
		l.insert(new Element(16, null));
		l.insert(new Element(3, null));
		l.insert(new Element(2, null));
		l.insert(new Element(1, null));
		l.insert(new Element(9, null));
		l.insert(new Element(10, null));
		l.insert(new Element(14, null));
		l.insert(new Element(8, null));
		l.insert(new Element(7, null));
		return l;
	}

	private LinkedList createLinkedListSem16() {
		LinkedList l = new LinkedList();
		l.insert(new Element(4, null));
		l.insert(new Element(1, null));
		l.insert(new Element(3, null));
		l.insert(new Element(2, null));
//		l.insert(new Element(16, null));
		l.insert(new Element(9, null));
		l.insert(new Element(10, null));
		l.insert(new Element(14, null));
		l.insert(new Element(8, null));
		l.insert(new Element(7, null));
		return l;
	}
	
	private SimpleLinkedList createSimpleLinkedListSem16() {
		SimpleLinkedList l = new SimpleLinkedList();
		l.insert(new Element(4, null));
		l.insert(new Element(1, null));
		l.insert(new Element(3, null));
		l.insert(new Element(2, null));
//		l.insert(new Element(16, null));
		l.insert(new Element(9, null));
		l.insert(new Element(10, null));
		l.insert(new Element(14, null));
		l.insert(new Element(8, null));
		l.insert(new Element(7, null));
		return l;
	}

	private LinkedList createLinkedListSem16E1() {
		LinkedList l = new LinkedList();
		l.insert(new Element(4, null));
//		l.insert(new Element(1, null));
		l.insert(new Element(3, null));
		l.insert(new Element(2, null));
//		l.insert(new Element(16, null));
		l.insert(new Element(9, null));
		l.insert(new Element(10, null));
		l.insert(new Element(14, null));
		l.insert(new Element(8, null));
		l.insert(new Element(7, null));
		return l;
	}

	private LinkedList createLinkedListSem16E1E8() {
		LinkedList l = new LinkedList();
		l.insert(new Element(4, null));
//		l.insert(new Element(1, null));
		l.insert(new Element(3, null));
		l.insert(new Element(2, null));
//		l.insert(new Element(16, null));
		l.insert(new Element(9, null));
		l.insert(new Element(10, null));
		l.insert(new Element(14, null));
//		l.insert(new Element(8, null));
		l.insert(new Element(7, null));
		return l;
	}

	private Element[] createMaxHeapElements() {
		Element[] elements = 
			new Element[] {
			new Element(16, null),
			new Element(14, null),
			new Element(10, null),
			new Element(8, null),
			new Element(7, null),
			new Element(9, null),
			new Element(3, null),
			new Element(2, null),
			new Element(4, null),
			new Element(1, null)};
		
		return elements;
	}

	private Element[] createSortedElements() {
		Element[] elements = 
			new Element[] {
			new Element(1, null),
			new Element(2, null),
			new Element(3, null),
			new Element(4, null),
			new Element(7, null),
			new Element(8, null),
			new Element(9, null),
			new Element(10, null),
			new Element(14, null),
			new Element(16, null)};
		
		return elements;
	}
}
