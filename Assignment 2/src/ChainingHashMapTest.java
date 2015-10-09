import static org.junit.Assert.*;

import org.junit.Test;


public class ChainingHashMapTest {

	@Test
	public void test() {
		ChainingHashMap<Integer, String> map = new ChainingHashMap<Integer, String>(13, 1, 13);
		System.out.println(map.hash(18));
		map.put(18, "1st");
		map.put(10, "2nd");
		map.put(25, "3rd");
		map.put(54, "4th");
		map.put(36, "5th");
		map.put(38, "6th");
		map.put(28, "7th");
		map.put(12, "8th");
		map.put(41, "9th");
		map.put(90, "10th");
		
		//assertEquals(10, map.size());
		
		//System.out.println(map.getFullestBuckets());
	}

}
