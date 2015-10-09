import java.util.List;


public class SimplePasswordManager {
	private HashMapNode<String, String>[] items;
	private int hashMapSize;
	private int numberOfItems;
	private int multiplier;
	private int modulus;
	private String username;
	private String password;
	
	//Construct a SimplePasswordManager with 4000 places and default hash parameters
	// multiplier = 1 and modulus = 4271
	@SuppressWarnings("unchecked")
	public SimplePasswordManager(){
		this.items = (HashMapNode<String, String>[]) new HashMapNode[4000];
		this.hashMapSize = 4000;
		this.numberOfItems = 0;
		this.multiplier = 1;
		this.modulus = 4271;
	}
	
	//Construct a SimplePasswordManager with the supplied parameters
	@SuppressWarnings("unchecked")
	public SimplePasswordManager(int size, int multiplier, int modulus){
		this.items = (HashMapNode<String, String>[]) new HashMapNode[4000];
		this.hashMapSize = size;
		this.numberOfItems = 0;
		this.multiplier = multiplier;
		this.modulus = modulus;
	}
	
	//hashing
	public Long hashPassword(String password){
		 unsigned long
		    hash(unsigned char *str)
		    {
		        unsigned long hash = 5381;
		        int c;

		        while (c = *str++)
		            hash = ((hash << 5) + hash) + c; /* hash * 33 + c */

		        return hash;
		    }
	}
	
	//Interface methods
	
	//return an array of the usernames of the users currently stored
	public List<String> listUsers(){
		
	}
	
	public String authenticate(String username, String password){
		
	}
	
	public String addNewUser(String username, String password){
		
	}	
	public String deleteUser(String username, String Password){
		
	}
	
	public String resetPassword(String username, String oldPassword, String newPassword){
		
	}
}
