package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InstallationWebDriver2 {
	
	private String webServerAddress = "http://localhost/";
	private String application = "orangehrm-3.3.3";
	private String WebDriverVersion = "V2.53";
	
	private void testChromeWebDriver()
	{
		// LE PLUS STABLE DANS LE TEMPS !? 
		System.setProperty("webdriver.chrome.driver","C:\\plateforme-tests\\selenium-wd\\"+WebDriverVersion+"\\chromedriver.exe" );
		WebDriver driver = new ChromeDriver();
		driver.get( webServerAddress + application );
		System.out.println("Titre de la page : " + driver.getTitle());
		driver.quit();
	}
	
	private void testIEWebDriver()
	{
		// LE PLUS ... TOUCHY !? Très sensible aux librairies ne marche pas en V2.53.1 avec cette configuration
		// ATTENTION : IE doit etre configure avec un ZOOM de 100%
		// ATTENTION : La cle de registre BFCache doit exister, voir sur internet
		// ATTENTION : Les 4 Zones de sécurité internet doivent être identiques ( Cochees ou non cochees )
		// Protected Mode settings are not the same for all zones. Enable Protected Mode must be set to 
		// the same value (enabled or disabled) for all zones.
		System.setProperty("webdriver.ie.driver","C:\\plateforme-tests\\selenium-wd\\"+WebDriverVersion+"\\IEDRiverServer.exe" );
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);
		WebDriver driver = new InternetExplorerDriver( caps );
		driver.get( webServerAddress + application );
		System.out.println("Titre de la page : " + driver.getTitle());
		driver.quit();
	}
	
	private void testFirefoxWebDriver()
	{
		System.setProperty("webdriver.firefox.bin","C:\\plateforme-tests\\selenium-wd\\v2.53\\navigateurs\\firefox46.0.1\\firefox.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get( webServerAddress + application );
		System.out.println("Titre de la page : " + driver.getTitle());
		driver.quit();
	}
	
	private void testPhantomJSWebDriver()
	{
		DesiredCapabilities caps = DesiredCapabilities.phantomjs();
		caps.setJavascriptEnabled(true);
		caps.setCapability(
				PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"c:/plateforme-tests/phantomjs/bin/phantomjs.exe");
		WebDriver driver = new PhantomJSDriver(caps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(webServerAddress + application );
		System.out.println("Titre de la page : " + driver.getTitle());
		driver.quit();
	}
	
	public static void main(String[] args) {
		InstallationWebDriver2 install = new InstallationWebDriver2();
		install.testChromeWebDriver();
		install.testFirefoxWebDriver();
		//install.testIEWebDriver();
		install.testPhantomJSWebDriver();
		
		
	}

}
