import java.util.ArrayList;
import java.util.List;


public class ChainingHashMap<K extends Comparable<K>, V> {
	private ChainingHashMapNode<K,V>[] items;
	private int hashMapSize;
	private int numberOfItems;
	private int multiplier;
	private int modulus;
	
	private int numberOfNodes;
	private int numberOfIndex;
	
	//construct a hashmap with 4000 places and given hash parameters
	@SuppressWarnings("unchecked")
	public ChainingHashMap(int multiplier, int modulus){
		this.items = (ChainingHashMapNode<K, V>[]) new ChainingHashMapNode[4000];
		this.hashMapSize = 4000;
		this.numberOfItems = 0;
		this.multiplier = multiplier;
		this.modulus = modulus;
	}
	
	//construct a hashmap with given capacity and given hash parameters
	@SuppressWarnings("unchecked")
	public ChainingHashMap(int hashMapSize, int multiplier, int modulus){
		this.items = (ChainingHashMapNode<K, V>[]) new ChainingHashMapNode[hashMapSize];
		this.hashMapSize = hashMapSize;
		this.numberOfItems = 0;
		this.multiplier = multiplier;
		this.modulus = modulus;
	}
	
	//hashing
	public int hash(K key){
		return Math.abs(multiplier * key.hashCode())% modulus;
	}
	
	public int size(){
		return numberOfItems;
	}
	
	public boolean isEmpty(){
		if (numberOfItems == 0){
			return true;
		} else {
			return false;
		}
	}
	
	public int[] getFullestBuckets(){
		int[] number = new int[2];
		numberOfNodes = 0;
		numberOfIndex = 0;
		
		for (int i = 0; i < hashMapSize; i++){ //Iterate over the array of nodes
			ChainingHashMapNode<K,V> current = items[i];
			ChainingHashMapNode<K,V> next = items[i].getNext();
			int oldNumberOfNodes = 0;
			
			for (int j = 0; j <= hashMapSize; j++){ //Iterate over the list in each node
				if (items[i] != null){//Checks if the node in the array is empty or not
					oldNumberOfNodes = 1;
					if (next != null){ //Checks if the node has chained nodes
						oldNumberOfNodes += 1;
						current = next;
						next = next.getNext();
					} else {
						break; //ask what break does
					}
				}
			}
			if (oldNumberOfNodes>numberOfNodes){ //If oldNumberOfNodes is higher than numberOfNodes, this means that in this index, there are more nodes
				numberOfIndex = i; //Set the number of index at the index in which the bucket is fullest
			}
			numberOfNodes = Math.max(oldNumberOfNodes, numberOfNodes);
		}
		
		number[0] = numberOfNodes;
		number[1] = numberOfIndex;
		return number;
	}
	
	public List<K> keys(){
		List<K> keys = new ArrayList<K>();
		for (ChainingHashMapNode<K, V> entry : items){
			if (entry != null){
				keys.add(entry.getKey());
				while (entry.getNext() != null){
					keys.add(entry.getKey());
					entry = entry.getNext();
				}
			}
		}
		return keys;
	}
	
	public V put(K key, V value){
		int index = hash(key); //% items.length;
		
		items[index] = null;
		items[index].setNext(items[index]);
		//ChainingHashMapNode<K,V> current = items[index];
		//ChainingHashMapNode<K,V> next = items[index].getNext(); //this is pointing to null
		
		for (int j = 0; j < hashMapSize; j++){
			if (items[index] == null){
				numberOfItems += 1;
				items[index] = new ChainingHashMapNode<K, V>(key, value);
				return null;
			}/* else if (current.getNext() == null){
				V oldValue = current.getValue();
				current.setNext(new ChainingHashMapNode<K, V>(key, value));
				return oldValue;
			} else {
				current = next;
				next = next.getNext();		
			}*/
		}
		return null;
	}
	
	public V get(K key){
		int index = hash(key); //% items.length;
		ChainingHashMapNode<K,V> current = items[index];
		ChainingHashMapNode<K,V> next = items[index].getNext();
		
		for (int j = 0; j <= hashMapSize; j++){
			if (current == null){
				return null;
			} else if (current != null && current.getKey()==key){
				return current.getValue();
			} else {
				current = next;
				next = next.getNext();
			}
		}
		return null;
		
		
		
	}
	
	public V remove(K key){
		int index = hash(key);
		ChainingHashMapNode<K,V> current = items[index];
		ChainingHashMapNode<K,V> next = items[index].getNext();
		
		for (int j = 0; j <= hashMapSize; j++){
			if (current == null){
				return null;
			} else if (current != null && current.getKey()==key){
				numberOfItems -= 1;
				V oldValue = current.getValue();
				current.setNext(next.getNext());
				next.setNext(null);
				return oldValue;
			} else {
				current = next;
				next = next.getNext();
			}
		}
		return null;
	}
	
}
