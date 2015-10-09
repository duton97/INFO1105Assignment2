import static org.junit.Assert.*;

import org.junit.Test;


public class DoubleHashMapTest {

	@Test
	public void test() {
		DoubleHashMap<Integer, String> map = new DoubleHashMap<Integer, String>(13, 1, 13, 7);
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
		
		
		System.out.println(map.secondaryHash(18));
		System.out.println(map.secondaryHash(41));
		System.out.println(map.secondaryHash(22));
		System.out.println(map.secondaryHash(44));
		System.out.println(map.secondaryHash(59));
		System.out.println(map.secondaryHash(32));
		System.out.println(map.secondaryHash(31));
		System.out.println(map.secondaryHash(73));
		
		assertEquals(4, map.putCollisions());
		assertEquals(4, map.totalCollisions());
		assertEquals(1, map.maxCollisions());
		
		
		
		System.out.println(map.keys());
	}

}
