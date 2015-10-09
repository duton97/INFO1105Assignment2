import static org.junit.Assert.*;

import org.junit.Test;


public class HashMapTest {

	@Test
	public void test() {
		HashMap<Integer, String> map = new HashMap<Integer, String>(13, 1, 13);
		map.put(18, "1st");
		map.put(41, "2nd");
		map.put(22, "3rd");
		map.put(44, "4th");
		map.put(59, "5th");
		map.put(32, "6th");
		map.put(31, "7th");
		map.put(73, "8th");
		
		assertEquals(8, map.size());
		assertEquals("1st", map.get(18));
		assertEquals(null, map.get(19));
		
		assertEquals("1st", map.remove(18));
		assertEquals(null, map.remove(19));
		
		//map.remove(18);
		
		//assertEquals(7, map.size());
		
		assertEquals(3, map.putCollisions());
		assertEquals(11, map.totalCollisions());
		assertEquals(5, map.maxCollisions());
		
		System.out.println(map.keys());
		System.out.println(map.hash(18));
	}

}
