
public class ChainingHashMapNode<K extends Comparable<K>, V> {
	private K key;
	private V value;
	private ChainingHashMapNode<K,V> next;
	
	//Construction
	public ChainingHashMapNode(K key, V value){
		this.key = key;
		this.value = value;
		this.next = null;
	}
	
	//get methods
	public K getKey(){
		return key;
	}
	public V getValue(){
		return value;
	}
	public ChainingHashMapNode<K,V> getNext(){
		return next;
	}
	
	//Set methods
	public void setValue(V newValue){
		this.value = newValue;
	}
	public void setNext(ChainingHashMapNode<K,V> next){
		this.next = next;
	}

}
