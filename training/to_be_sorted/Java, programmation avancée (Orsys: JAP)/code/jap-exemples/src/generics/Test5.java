package generics;

import java.util.ArrayList;
import java.util.List;

public class Test5 {
	public static void main(String[] args) {
		List<?> l = new ArrayList<Object>();		//OK mais inutilisable 
		//l.add(new Object());						//erreur		
		//l.add("bonjour");							//erreur	
		//List<?> l1 = new ArrayList<?>();			//erreur	
		List<?> l1 = new ArrayList<String>();		//OK mais inutilisable 
		//l1.add("bonjour");						//erreur	
		
		List<List<?>> list0 = new ArrayList<List<?>>();
		//List<List<?>> list = new ArrayList<List>();
		list0.add(new ArrayList<String>());
		list0.add(new ArrayList());
		List<List> list1 = new ArrayList<List>();		
		//List<List> list1 = new ArrayList<List<?>>();
		list1.add(new ArrayList<String>());
		list1.add(new ArrayList());
		//List<List<?>> list3 = new ArrayList<List<Object>>();
		
		List<List<Object>> list4 = new ArrayList<List<Object>>();
		list4.add(new ArrayList<Object>());
	}
}
