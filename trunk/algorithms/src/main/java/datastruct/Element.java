package datastruct;


public class Element {
	public Comparable key;
	public Object data;
	
	public Element(Comparable key, Object data) {
		this.key = key;
		this.data = data;
	}
	
	@Override
	public String toString() {
		return key.toString();
	}

	@Override
	public boolean equals(Object obj) {
		Element e = (Element) obj;
		return key.equals(e.key);
	}
}
