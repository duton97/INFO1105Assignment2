import java.util.ArrayList;
import java.util.List;


public class HashMap<K extends Comparable<K>, V> {
	private HashMapNode<K, V>[] items;
	private int hashMapSize;
	private int numberOfItems;
	private int multiplier;
	private int modulus;
	
	private int putCollisions = 0;
	private int totalCollisions = 0;
	private int maxCollisions = 0;
	
	//Defunct
	private HashMapNode<K, V> defunct = new HashMapNode<K, V>(null, null);
	
	// construct a HashMap with 4000 places and given hash parameters
	@SuppressWarnings("unchecked")
	public HashMap (int multiplier, int modulus){
		this.items = (HashMapNode<K, V>[]) new HashMapNode[4000];
		this.hashMapSize = 4000;
		this.numberOfItems = 0;
		this.multiplier = multiplier;
		this.modulus = modulus;
		
		/*putCollisions = 0;
		totalCollisions = 0;
		maxCollisions = 0;*/
	}
	
	// construct a HashMap with given capacity and given hash parameters
	@SuppressWarnings("unchecked")
	public HashMap (int hashMapSize, int multiplier, int modulus){
		this.items = (HashMapNode<K, V>[]) new HashMapNode[hashMapSize];
		this.hashMapSize = hashMapSize;
		this.numberOfItems = 0;
		this.multiplier = multiplier;
		this.modulus = modulus;
		
		/*putCollisions = 0;
		totalCollisions = 0;
		maxCollisions = 0;*/
	}
	
	//hashing
	public int hash(K key){
		return Math.abs(multiplier * key.hashCode())% modulus;
	}
	
	// size (return the number of nodes currently stored in the map)
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
	
	//interface methods
	public List<K> keys(){
		List<K> keys = new ArrayList<K>();
		for (HashMapNode<K, V> entry : items){
			if (entry != null){
				keys.add(entry.getKey());
			}
		}
		return keys;
	}
	
	public V put(K key, V value){
		int index = hash(key); // % items.length;
		int p;
	
		int newMaxCollisions = 0;
		
		for (p = 0; p <= hashMapSize; p++){
			if (items[index]==null){
				numberOfItems += 1;
				items[index] = new HashMapNode<K, V>(key, value);
				return null;
			} else if (items[index]!=null && items[index].getKey() == key){ //When the node inserted into a non empty map with the same key
				V oldValue = items[index].getValue();
				items[index] = new HashMapNode<K, V>(key, value);
				return oldValue;
			} else {
				index = (index + 1) % modulus;
				
				//putCollisions
				if (p == 1) {
					putCollisions = putCollisions + 1;
				}
				
				//totalCollisions
				totalCollisions = totalCollisions + 1;
				
				//maxCollisions
				//oldMaxCollisions = maxCollisions; //Takes the first value of maxCollisions
				newMaxCollisions++; //= maxCollisions + 1; //Takes the updates value of maxCollisions
				maxCollisions = Math.max(newMaxCollisions, maxCollisions); //Compares the 2 variables and sets maxCollisions to the highest number
			}
		}
		return null;
	
	}
	
	public V get(K key){
		int index = hash(key); // % items.length;
		int p;
		for (p = 0; p <= hashMapSize; p++){
			HashMapNode<K, V> entry = items[index];
			if (entry == null || entry == defunct){
				return null;
			}
			else if (entry!=null && entry.getKey().equals(key)){
				return entry.getValue();
			}
			else {
				index = (index + 1) % modulus;
			}
		}
		return null;
	}
	
	public V remove(K key){
		int index = hash(key); // % items.length;
		int p;
		for (p = 0; p<= hashMapSize; p++){
		HashMapNode<K, V> entry = items[index];
			if (entry == null || entry == defunct){
				return null;
			} else if (entry!=null && entry.getKey().equals(key)) {
				numberOfItems -=1;
				V oldValue = entry.getValue();
				items[index] = defunct; //defunct is wrong, defunt is a node that has key and value as null
				return oldValue;
			} else {
				index = (index + 1) % modulus;
			}
		}
		return null;	
	}
	
	public int putCollisions(){
		return putCollisions;
	}
	
	public int totalCollisions(){
		return totalCollisions;
	}
	
	public int maxCollisions(){
		return maxCollisions;
	}
	
	public void resetStatistics(){
		//System.out.println(items);
		putCollisions = 0;
		totalCollisions = 0;
		maxCollisions = 0;
		
	}
}
