

import java.util.*;

import static org.mockito.Mockito.*;

public class Main {

	
	public static void main(String[] args) {
		
		//List est une interface ...
		 List mockedList = mock(List.class);
		 
		 //préparation de la réponse bidon ...
		 when(mockedList.get(0)).thenReturn("first");

		 //test de la réponse
		 System.out.println(mockedList.get(0));

		 //pas de réponse préparée pour cet indice
		 System.out.println(mockedList.get(999));

	}

}
