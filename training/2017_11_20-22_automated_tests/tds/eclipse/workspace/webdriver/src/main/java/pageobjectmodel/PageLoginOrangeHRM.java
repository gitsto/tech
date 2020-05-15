/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pageobjectmodel;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Administrateur
 */
public class PageLoginOrangeHRM {
    
     private static String adressePage = "http://localhost/orangehrm-3.3.3";
     private static String loginAdmin = "admin";
     private static String passwordAdmin = "admin";
     
     
     private WebDriver driver;
     
     @FindBy(id = "txtUsername") 
     private WebElement utilisateur;
     @FindBy(id = "txtPassword") 
     private WebElement motDePasse;
     @FindBy(id = "btnLogin") 
     private WebElement boutonLogin;

     
     public PageLoginOrangeHRM()
     {
        System.setProperty("webdriver.chrome.driver", "C:\\plateforme-tests\\selenium-wd\\v3.6.0\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait( 30 , TimeUnit.SECONDS);
        driver.get(adressePage);
        PageFactory.initElements( driver , this );
      
     }
     
     public PageLoginOrangeHRM( WebDriver driver )
     {
        this.driver = driver;
        PageFactory.initElements( driver , this );
      
     }
     
     public static PageLoginOrangeHRM creerPageLoginHRM()
     {
         return new PageLoginOrangeHRM();
     }
     
     public static PageLoginOrangeHRM creerPageLoginHRM( WebDriver driver )
     {
         return new PageLoginOrangeHRM( driver );
     }
    
     public void saisirLogin( String login )
     {
         utilisateur.clear();
         utilisateur.sendKeys( login );
     }
     
     public void saisirMotDePasse( String motDePasse )
     {
         this.motDePasse.clear();
         this.motDePasse.sendKeys( motDePasse );
     }
     
     public void seConnecter()
     {
         boutonLogin.click();
     }
     
     
     
     
     public PageAccueilOrangeHRM seConnecterEnAdministrateur()
     {
         saisirLogin( loginAdmin );
         saisirMotDePasse( passwordAdmin );
         seConnecter();
         return PageAccueilOrangeHRM.creerPageAccueilOrangeHRM(driver);
     }
     
     public boolean VerifierAuthentificationOk()
     {
        WebElement welcome = null;
        welcome = driver.findElement(By.id("welcome"));
        return welcome!=null;
     }
     
     public void quitterNavigateur()
     {
         driver.quit();
     }
     
}
