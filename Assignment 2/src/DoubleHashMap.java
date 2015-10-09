import java.util.ArrayList;
import java.util.List;


public class DoubleHashMap<K extends Comparable<K>, V> {
	private HashMapNode<K, V>[] items;
	private int hashMapSize;
	private int numberOfItems;
	private int multiplier;
	private int modulus;
	private int secondaryModulus;
	
	private int putCollisions;
	private int totalCollisions;
	private int maxCollisions;
	private int putFailures;
	
	//Defunct
	private HashMapNode<K, V> defunct = new HashMapNode<K, V>(null, null);
	
	
	//updated construction
	//construct a HashMap with 4000 places and given hash parameters
	@SuppressWarnings("unchecked")
	public DoubleHashMap(int multiplier, int modulus, int secondaryModulus){
		this.items = (HashMapNode<K, V>[]) new HashMapNode[4000];
		this.hashMapSize = 4000;
		this.numberOfItems = 0;
		this.multiplier = multiplier;
		this.modulus = modulus;
		this.secondaryModulus = secondaryModulus;
		
		putCollisions = 0;
		totalCollisions = 0;
		maxCollisions = 0;
	}
	
	//construct a HashMap with given capacity and given hash parameters
	@SuppressWarnings("unchecked")
	public DoubleHashMap(int hashMapSize, int multiplier, int modulus, int secondaryModulus){
		this.items = (HashMapNode<K, V>[]) new HashMapNode[hashMapSize];
		this.hashMapSize = hashMapSize;
		this.numberOfItems = 0;
		this.multiplier = multiplier;
		this.modulus = modulus;
		this.secondaryModulus = secondaryModulus;
		
		putCollisions = 0;
		totalCollisions = 0;
		maxCollisions = 0;
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
	
	public int hash(K key){
		return Math.abs(multiplier * key.hashCode())% modulus;
	}
	
	public int secondaryHash(K key){
		return Math.abs(secondaryModulus - key.hashCode()) % secondaryModulus;
	}
	
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
		int index = hash(key); //First hash 
		//Change to hash(key) + 0*secondaryHash(key) and in the for loop, start from 1
		int j;
		
		int newMaxCollisions = 0; //Same for maxCollision
		
		for (j = 1; j <= hashMapSize; j++){
			if (items[index]==null){
				numberOfItems += 1;
				items[index] = new HashMapNode<K, V>(key, value);
				return null;
			} else if (items[index]!=null && items[index].getKey() == key){ //When the node inserted into a non empty map with the same key
				V oldValue = items[index].getValue();
				items[index] = new HashMapNode<K, V>(key, value);
				return oldValue;
			} else {
				index = (hash(key) + (j*secondaryHash(key))) % modulus; //Secondary hash but repeats the first hash in the first iteration
				
				//putCollisions
				if (j == 1) {
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
		putFailures = putFailures + 1;
		throw new RuntimeException("Double Hashing failed to find a free position");
		//return null;
	}
	
	public V get(K key){
		int index = hash(key); // % items.length;
		int j;
		for (j = 1; j < hashMapSize; j++){
			HashMapNode<K, V> entry = items[index];
			if (entry == null || entry == defunct){
				return null;
			}
			else if (entry!=null && entry.getKey().equals(key)){
				return entry.getValue();
			}
			else {
				index = (hash(key) + (j*secondaryHash(key))) % modulus;
			}
		}
		return null;
	}
	
	public V remove(K key){
		int index = hash(key); // % items.length;
		int j;
		for (j = 1; j< hashMapSize; j++){
		HashMapNode<K, V> entry = items[index];
			if (entry == null || entry == defunct){
				return null;
			} else if (entry!=null && entry.getKey().equals(key)) {
				numberOfItems -=1;
				V oldValue = entry.getValue();
				items[index] = defunct; //place defunct
				return oldValue;
			} else {
				index = (hash(key) + (j*secondaryHash(key))) % modulus; //Secondary hash but repeats the first hash in the first iteration
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
	
	public int putFailures(){
		return putFailures;
	}
	
	public void resetStatistics(){
		putCollisions = 0;
		totalCollisions = 0;
		maxCollisions = 0;
		putFailures = 0;
	}

}
