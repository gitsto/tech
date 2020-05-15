/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pageobjectmodel;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Administrateur
 */
public class TestLoginOrangeHRM {
    
    @Test
    public void testLoginLogoutAdmin()
    {
        PageLoginOrangeHRM pageLogin =  PageLoginOrangeHRM.creerPageLoginHRM();
        
        // L'action de se connecter renvoie la page suivante
        PageAccueilOrangeHRM pageAccueil = pageLogin.seConnecterEnAdministrateur();
        
        // On verifie que l'authentification administrateur
        Assert.assertTrue( "Connexion reussie" , pageLogin.VerifierAuthentificationOk() );
        
        // On se déconnecte et on récupère la page suivante : la page de login
        pageLogin = pageAccueil.seDeconnecter();
        
        // On quitte le navigateur
        pageLogin.quitterNavigateur();        
    }
    
}
