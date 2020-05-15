package webdriver;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// Teste le 04/10/2017 sur une configuration 3.6.0 avec les derniers navigateurs
// Teste le 14/11/2017 sur une configuration 3.7.1 java et les dernier navigateurs Firefox 57 Quantum (Probleme avec #items en css !!! )

public class Exercices {

	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		exercicesRecherchesElements();
		//exerciceJQuery();
		//testConfigurateur();
		//testAttenteSelectionBlancEtMarron();
		//actionsComplexes();
		//testAlertes();
	}

	
	
	private static String webServerAddress = "http://localhost/";
	private static String application = "orangehrm-3.3.3";
	private static String WebDriverVersion = "V3.7.1";
	
	private static WebDriver getChromeWebDriver()
	{
		// LE PLUS STABLE DANS LE TEMPS !? 
		System.setProperty("webdriver.chrome.driver","C:\\plateforme-tests\\selenium-wd\\"+WebDriverVersion+"\\chromedriver.exe" );
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	private static WebDriver getIEWebDriver()
	{
		System.setProperty("webdriver.ie.driver","C:\\plateforme-tests\\selenium-wd\\"+WebDriverVersion+"\\IEDRiverServer.exe" );
		WebDriver driver = new InternetExplorerDriver();
		return driver;
	}
	
	private static WebDriver getFirefoxWebDriver()
	{
		// LE PLUS DECEVANT !? EN 2017
		// ATTENTION : Le profile suivant doit etre parametre
		// Ne marche plus avec Firefox 55 et WD V3.5.3  et Geckodriver 0.18
		
		// OK avec FireFox 56 et V3.6.0 et GeckoDriver 0.19 (64bit)
		// Capacit
		DesiredCapabilities caps = DesiredCapabilities.firefox();
		caps.setPlatform(Platform.WINDOWS);
		caps.setJavascriptEnabled(true);

		// N�cessaire sinon driver.quit() plante.
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.tabs.remote.autostart", false);
		profile.setPreference("browser.tabs.remote.autostart.1", false);
		profile.setPreference("browser.tabs.remote.autostart.2", false);
		profile.setPreference("browser.tabs.remote.force-enable", "false");

		// Int�gration du profile dans la capacite
		caps.setCapability(FirefoxDriver.PROFILE, profile);
		System.setProperty("webdriver.gecko.driver","C:\\plateforme-tests\\selenium-wd\\"+WebDriverVersion+"\\geckodriver.exe" );
		FirefoxOptions fo = new FirefoxOptions( caps );
		WebDriver driver = new FirefoxDriver( fo );
		return driver;
	}
	
	private static WebDriver getPhantomJSWebDriver()
	{
		// STABLE SUR 2 ANS SAUF SUR LA DERNIERE VERSION V3.6.0 !?
		// Marche en V3.5.3 mais plus en V3.6.0 : le driver ne reconnait plus les plateformes !!!!
		// Lire les commentaires du 4 octobre 2017 sur https://www.bountysource.com/issues/49856648-unrecognized-platform-mac-unknown-64bit
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("phantomjs.binary.path","c:/plateforme-tests/phantomjs/bin/phantomjs.exe");
		caps.setJavascriptEnabled(true);
		caps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"c:/plateforme-tests/phantomjs/bin/phantomjs.exe");
		// On utilise ici un driver qui h�rite de PhantomJSDriver et qui traite correctement les plateformes.
		WebDriver driver = new PhantomJSFixedDriver(caps);
		return driver;
	}
	
	
	
	
	public static void exercicesRecherchesElements() {
		
		WebDriver piloteChrome = getChromeWebDriver();
		//WebDriver piloteChrome = getFirefoxWebDriver();
		
		// Saisie formulaire de login
		String baseURL = "http://localhost/wd/composants.html";

		piloteChrome.get(baseURL);
		WebElement login = piloteChrome.findElement(By.id("username"));

		WebElement password = piloteChrome.findElement(By
				.cssSelector("#password"));   

		WebElement bouton = piloteChrome.findElement(By
				.cssSelector("input[type='submit'][value='Login']"));

		// Saisie dans les champs du formulaire de login
		login.sendKeys("admin");
		password.sendKeys("admin");
		bouton.submit();

		// R�cup�ration des �l�ments de la table simple
		// piloteChrome.get(baseURL);
		WebElement table = piloteChrome.findElement(By.cssSelector("#items"));
		List<WebElement> lesTR = table.findElements(By.tagName("tr"));
		for (WebElement unTR : lesTR) {
			List<WebElement> lesTDduTR = unTR.findElements(By.tagName("td"));
			for (WebElement unTD : lesTDduTR) {
				System.out.print(unTD.getText() + "\t");
			}
			System.out.println();
		}

		piloteChrome.quit();

	}

	public static void exerciceJQuery() throws InterruptedException {
		
		WebDriver driver = getChromeWebDriver();

		// Saisie formulaire de login
		String baseURL = "http://localhost/wd/composants.html";

		driver.get(baseURL);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		@SuppressWarnings("unchecked")
		List<WebElement> listeJQuery = (List<WebElement>) js
				.executeScript("return jQuery.find(':checked')");
		System.out.println("Nombre de cases coch�es : " + listeJQuery.size());

		List<WebElement> lesBoitesACocher = driver.findElements(By
				.cssSelector("input[type='checkbox'][checked=true]"));
		System.out.println("Nombre de cases coch�ees : "
				+ lesBoitesACocher.size());
		for (WebElement caseACocher : lesBoitesACocher) {
			caseACocher.click();
		}

		driver.quit();

	}

	public static void exemplesCSS() {
		
		WebDriver driver = getChromeWebDriver();
		String baseURL = "http://localhost/wd/composants.html";
		driver.get(baseURL);

		WebElement nomUtilisateur = driver.findElement(By
				.cssSelector("html > body > div > div > form > input"));

		nomUtilisateur = driver.findElement(By.cssSelector("input"));

		WebElement boutonLogin = driver.findElement(By
				.cssSelector("input.login"));

		nomUtilisateur = driver.findElement(By.cssSelector("input#username"));

		nomUtilisateur = driver.findElement(By.cssSelector("#username"));

		nomUtilisateur = driver.findElement(By
				.cssSelector("input[name=username]"));

		List<WebElement> elements = driver.findElements(By
				.cssSelector("div, p"));

		elements = driver.findElements(By.cssSelector("div.first, div.last"));

		driver.quit();
	}

	public static void testConfigurateur() {

		WebDriver driver = getChromeWebDriver();

		driver.get("http://localhost/wd/Configurateur.html");
		Select make = new Select(driver.findElement(By.name("make")));
		System.out.println("Is multiple ? : " + make.isMultiple());
		System.out.println("Nombre d'options : " + make.getOptions().size());
		List<String> exp_options = Arrays.asList("BMW", "Mercedes", "Audi",
				"Honda");
		List<String> act_options = new ArrayList<String>();
		for (WebElement option : make.getOptions()) {
			act_options.add(option.getText());
		}
		System.out.println(act_options.toString());

		make.selectByVisibleText("Honda");
		System.out.println("Honda ? : "
				+ make.getFirstSelectedOption().getText());
		make.selectByValue("audi");
		System.out.println("Audi ? : "
				+ make.getFirstSelectedOption().getText());
		make.selectByIndex(0);
		System.out
				.println("BMW ? : " + make.getFirstSelectedOption().getText());

		Select color = new Select(driver.findElement(By.name("color")));

		System.out.println("isMultiple ? : " + color.isMultiple());

		System.out.println("Nombre d'options couleur "
				+ color.getOptions().size());

		color.selectByVisibleText("Noir");
		color.selectByVisibleText("Rouge");
		color.selectByVisibleText("Argent");

		assertEquals(3, color.getAllSelectedOptions().size());

		List<String> exp_sel_options = Arrays.asList("Noir", "Rouge", "Argent");
		List<String> act_sel_options = new ArrayList<String>();

		for (WebElement option : color.getAllSelectedOptions()) {
			act_sel_options.add(option.getText());
		}

		System.out.println("Liste des couleurs select. "
				+ act_sel_options.toString());

		color.deselectByVisibleText("Argent");
		System.out.println("Options restantes "
				+ color.getAllSelectedOptions().size());

		color.deselectByValue("rd");
		System.out.println("Options restantes "
				+ color.getAllSelectedOptions().size());

		color.deselectByIndex(0);
		System.out.println("Options restantes "
				+ color.getAllSelectedOptions().size());

		WebElement airbags = driver.findElement(By
				.xpath("//input[@value='Airbags']"));

		if (!airbags.isSelected()) {
			airbags.click();
		}

		if (airbags.isSelected()) {
			airbags.click();
		}

		WebElement petrol = driver.findElement(By
				.xpath("//input[@value='Petrol']"));

		if (!petrol.isSelected()) {
			petrol.click();
		}

		List<WebElement> fuel_type = driver.findElements(By.name("fuel_type"));
		for (WebElement type : fuel_type) {
			if (type.getAttribute("value").equals("Diesel")) {
				if (!type.isSelected()) {
					type.click();
				}

				break;
			}
		}

		driver.quit();
	}

	public static void testAttenteSelectionBlancEtMarron() {

	    WebDriver driver = getChromeWebDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://localhost/wd/Configurateur.html");
		Select color = new Select(driver.findElement(By.name("color")));

		try {

			WebDriverWait attente = new WebDriverWait(driver, 10);
			// attente.until( ExpectedConditions.jsReturnsValue(""));
			attente.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					List<String> listeAttendue = Arrays.asList("Blanc",
							"Marron");
					List<String> listeActuelle = new ArrayList<String>();
					for (WebElement option : color.getAllSelectedOptions()) {
						listeActuelle.add(option.getText());
					}
					return listeAttendue.equals(listeActuelle);
				}
			});
			System.out.println("Condition OK : Blanc et Marron s�lectionn�s ");

		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println(e.getMessage());

		}

		driver.quit();

	}
	
	
	public static void actionsComplexes() throws IOException, InterruptedException
	{
		        WebDriver driver = getChromeWebDriver();
		
		        driver.get("http://localhost/wd/doubleclique.html");
		        WebElement message = driver.findElement(By.id("message"));
		        Actions lesActions = new Actions(driver);
		        lesActions.doubleClick(message).perform();
		       
		        
		        driver.get("http://www.williammalone.com/articles/create-html5-canvas-javascript-drawing-app/");
		        Thread.sleep(1000);
		        WebElement canvas = driver.findElement(By.id("canvasSimpleTools"));
		        lesActions = new Actions(driver);
		        lesActions.moveToElement(canvas, 100, 100)
		                .clickAndHold()
		                .moveByOffset(50, 0)
		                .release()
		                .clickAndHold()
		                .moveByOffset(0, 50)
		                .release()
		                .clickAndHold()
		                .moveByOffset(-50, 0)
		                .release()
		                .clickAndHold()
		                .moveByOffset(0, -50)
		                .release().perform();
		        // Copie d'Ecran
		        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		        FileUtils.copyFile(scrFile, new File("c:\\plateforme-tests\\selenium-wd\\v3.6.0\\pageCanvas.png"));

		        driver.get("http://localhost/wd/glisserdeposer.html");
		        WebElement source = driver.findElement(By.id("draggable"));
		        WebElement cible = driver.findElement(By.id("droppable"));
		        new Actions(driver).dragAndDrop(source, cible).perform();
		        
		        //new Actions(driver).wait(200);
		        Thread.sleep(1000);
		        driver.quit();

		    

	}
	
	
	 public static  void testAlertes() {
		 
		 	WebDriver driver = getChromeWebDriver();
	        driver.get("http://localhost/wd/alertes.html");

	        driver.findElement(By.id("simple")).click();

	        Alert alerte = driver.switchTo().alert();
	        String texteAlerte = alerte.getText();
	        System.out.println("Texte de l'alerte : " + texteAlerte);
	        attendre(2000);
	        alerte.accept(); // Femeture de l’alerte par ok

	        driver.findElement(By.id("confirm")).click();
	        alerte = driver.switchTo().alert();
	        attendre(2000);
	        alerte.dismiss(); // Fermeture par refus

	        driver.findElement(By.id("prompt")).click();
	        alerte = driver.switchTo().alert();

	        alerte.sendKeys("Bonjour Spock"); // 
	        attendre(2000);
	        alerte.accept();

	        if (driver.getPageSource().contains("Bonjour Spock")) {
	            System.out.println("ok");
	        }

	        attendre(2000);

	        driver.get("http://localhost/wd/configurateur.html");

	        driver.findElement(By.id("helpbutton")).click();
	        attendre(2000);
	        for (String windowId : driver.getWindowHandles()) {
	            String title = driver.switchTo().window(windowId).getTitle();
	            System.out.println(title);
	        }

            driver.quit();	       

	    }

	  public static void attendre(long ms) {
	        try {
	            Thread.sleep(ms);
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	

}
