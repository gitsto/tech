package generics;

import java.util.ArrayList;
import java.util.List;

public class Test<T> {
	/**
	 * @param args
	 */
	
	@SuppressWarnings("unchecked")
	public void f(String[] args) {
		List<T> list = new ArrayList<T>();
		T ref2 = null; 							//OK : reference
		list.add(ref2);
		
		if (ref2 != null) System.out.println(ref2.toString());
		//ref2 = new T() ; 						//ERREUR : instanciation
		
		ref2 = (T) "bonjour";
		
		List<String>[ ] list1;		 			//OK : reference
		//list1 = new List<String>[10] ;		//ERREUR : instanciation
		list1 = new List[10] ;					//WARNING !!!
		list1[0] = new ArrayList<String>();
		list1[0].add(args[0]);

		List<T>[ ] list2;		 				//OK : reference
		//list2 = new List<String>[10] ;		//ERREUR : instanciation
		list2 = new List[10] ;					//WARNING !!!
		list2[0] = (List<T>) new ArrayList<T>();
		list2[0].add((T) args[0]);
		
		T[ ] ref1 = null;					 	//OK mais !!!!
		//ref1 = new T[10] ; 					//ERREUR
		ref1 = (T[]) new String[10];
		ref1[0] = ref2;
	}
	
	public void g(T[] args) {
		List<T> list = new ArrayList<T>();
		list.add(args[0]);					
		
		T[ ] ref1 = args;					 	//OK mais !!!!
		System.out.println(ref1);
	}
	
	public static void main(String[] args) {
		Test<String> test = new Test<String>();
		test.f(new String[] { "bonjour" });
	}

}
