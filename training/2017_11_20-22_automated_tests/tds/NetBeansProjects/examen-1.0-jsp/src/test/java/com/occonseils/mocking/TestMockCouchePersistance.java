/*
 * LIBRE DE DROIT
 */
package com.occonseils.mocking;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Olivier CHARLES
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMockCouchePersistance {
    
    @Mock
    CouchePersistance mockCouchePersistance;
   
    
    @Test
    public void testMockCouchePersistance()
    {
        CoucheMetier cm = new CoucheMetier( mockCouchePersistance );
        Object personneImportante = new Object();
        cm.operationMetierComplexeAvec3CreationsAttendues( personneImportante );
        
        // On vérifie des appels indirects
        // verifier, debuger, concevoir et accélerer les tests ...
        
        verify( mockCouchePersistance , times(3)).creer(any());
        verify( mockCouchePersistance , times(1)).creer(personneImportante);
        
    }
    
    
}
