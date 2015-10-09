import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class DataSetA {
	public void exploreData(String pathToFile) throws FileNotFoundException, IOException {
		// Instantiate hash maps
		HashMap<String,Double> hashmap = new HashMap<String,Double>(1, 4000); //HashMap first constructor
		BufferedReader br = new BufferedReader(new FileReader(pathToFile));
		try {
			String line = br.readLine();
			while (line != null){
				String[] pieces = line.trim().split("\\s+");
				if (pieces.length == 4){
					//pieces[0]
					//TODO : put data into hash maps
					hashmap.put(pieces[0], null);
				}
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		//TODO : print collision statistics
		System.out.println(hashmap.putCollisions());
		System.out.println(hashmap.totalCollisions());
		System.out.println(hashmap.maxCollisions());
	}
}
