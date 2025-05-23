/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occonseils.mocking;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Olivier CHARLES
 */
@RunWith(MockitoJUnitRunner.class)
public class TestStubCouchePersistance {
    
    @Mock
    CouchePersistance stubCouchePersistance;
   
    
    @Test
    public void testStubLirePersistance()
    {
        Object personneImportante = new Object();
        // Programmation du stub.
        when( stubCouchePersistance.lire(175250367)).thenReturn( personneImportante);
        CoucheMetier cm = new CoucheMetier( stubCouchePersistance );
        
        // Appel indirecte du stub ...
        Object personne = cm.lirePersonne(175250367);
        
        
        // Verification que la fonction lire du stub à bien été appelée
        verify( stubCouchePersistance , times(1)).lire(175250367);
        // Verification que le stub  a bien retourné la bonne valeur.
        assertEquals( personneImportante , personne);
    }
    
    
}
