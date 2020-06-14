

import java.util.*;

import static org.mockito.Mockito.*;

public class Main {

	
	public static void main(String[] args) {
		
		//List est une interface ...
		 List mockedList = mock(List.class);
		 
		 //pr�paration de la r�ponse bidon ...
		 when(mockedList.get(0)).thenReturn("first");

		 //test de la r�ponse
		 System.out.println(mockedList.get(0));

		 //pas de r�ponse pr�par�e pour cet indice
		 System.out.println(mockedList.get(999));

	}

}
