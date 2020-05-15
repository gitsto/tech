/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pageobjectmodel;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Administrateur
 */
public class PageAccueilOrangeHRM {
    private WebDriver driver;
     
     @FindBy(id = "welcome") 
     private WebElement welcome;
     @FindBy( linkText = "Logout") 
     private WebElement logoutLink;
    

     
     public PageAccueilOrangeHRM( WebDriver driver )
     {
        this.driver=driver;
        driver.manage().timeouts().implicitlyWait( 30 , TimeUnit.SECONDS);
        PageFactory.initElements( driver , this );
      
     }
     
     public static PageAccueilOrangeHRM creerPageAccueilOrangeHRM( WebDriver driver )
     {
         return new PageAccueilOrangeHRM(driver);
     }
     
     public PageLoginOrangeHRM seDeconnecter()
     {
         welcome.click();
         WebDriverWait wait  = new WebDriverWait(driver, 5 );
         // TRES IMPORTANT !
         wait.until(ExpectedConditions.visibilityOf( logoutLink) );
         logoutLink.click();
         return PageLoginOrangeHRM.creerPageLoginHRM( driver );
     }
    
   
    
}
