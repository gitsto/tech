package webdriversikulix;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.*;
  
// Utile lorsque votre parefeu détecte les authentifications automatiques avec injection 
// dans le header, comme WebDriver fait.
// SOLUTION : utiliser l'API SIKULI pour détecter des images des champs login et password 
// et envoyer des evenements windows comme un utilisateur lambda fait avec son clavier.
// Les écrans d'authentification sont STABLES en general !

public class TestLoginSikulix {

	private static String webServerAddress = "http://localhost/";
	private static String application = "orangehrm-3.3.3";
	private static String WebDriverVersion = "V3.6.0";

	private static WebDriver getChromeWebDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\plateforme-tests\\selenium-wd\\" + WebDriverVersion + "\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	@Test
	public void testLoginAvecSikuli() {
		WebDriver driver = getChromeWebDriver();
		driver.get(webServerAddress + application);
		Screen s = new Screen();
		try {
			// REFLEXE AUTOMATICIEN : Attendre la presence des images
			// sur l'ecran avant d'agir.
			s.wait("boutonLogin.png");
			s.click("username.png");
			s.write("admin");
			s.click("motDePasse.png");
			s.write("admin");
			s.click("boutonLogin.png");
			s.wait("welcomeadmin.png");
			s.click("welcomeadmin.png");
			s.wait("logoutmenuitem.png");
			s.click("logoutmenuitem.png");
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		driver.quit();
	}

}
