package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InstallationWebDriver3 {
	
	private String webServerAddress = "http://localhost/";
	private String application = "orangehrm-3.3.3";
	private String WebDriverVersion = "V3.6.0";
	
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
		// LE PLUS ... TOUCHY !?
		// ATTENTION : IE doit etre configure avec un ZOOM de 100%
		// ATTENTION : La cle de registre BFCache doit exister, voir sur internet
		// ATTENTION : Les 4 Zones de sécurité internet doivent être identiques ( Cochees ou non cochees )
		// Protected Mode settings are not the same for all zones. Enable Protected Mode must be set to 
		// the same value (enabled or disabled) for all zones.
		System.setProperty("webdriver.ie.driver","C:\\plateforme-tests\\selenium-wd\\"+WebDriverVersion+"\\IEDRiverServer.exe" );
		WebDriver driver = new InternetExplorerDriver();
		driver.get( webServerAddress + application );
		System.out.println("Titre de la page : " + driver.getTitle());
		driver.quit();
	}
	
	private void testFirefoxWebDriver()
	{
		// LE PLUS DECEVANT !? EN 2017
		// ATTENTION : Le profile suivant doit etre parametre
		// Ne marche plus avec Firefox 55 et WD V3.5.3  et Geckodriver 0.18
		
		// OK avec FireFox 56 et V3.6.0 et GeckoDriver 0.19 (64bit)
		// Capacité désirée
		DesiredCapabilities caps = DesiredCapabilities.firefox();
		caps.setPlatform(Platform.WINDOWS);
		caps.setJavascriptEnabled(true);

		// Nécessaire sinon driver.quit() plante.
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.tabs.remote.autostart", false);
		profile.setPreference("browser.tabs.remote.autostart.1", false);
		profile.setPreference("browser.tabs.remote.autostart.2", false);
		profile.setPreference("browser.tabs.remote.force-enable", "false");

		// Intégration du profile dans la capacité
		caps.setCapability(FirefoxDriver.PROFILE, profile);
		System.setProperty("webdriver.gecko.driver","C:\\plateforme-tests\\selenium-wd\\"+WebDriverVersion+"\\geckodriver.exe" );
		FirefoxOptions fo = new FirefoxOptions( caps );
		WebDriver driver = new FirefoxDriver( fo );
		driver.get( webServerAddress + application );
		System.out.println("Titre de la page : " + driver.getTitle());
		driver.quit();
	}
	
	private void testPhantomJSWebDriver()
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
		// On utilise ici un driver qui hérite de PhantomJSDriver et qui traite correctement les plateformes.
		WebDriver driver = new PhantomJSFixedDriver(caps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.google.fr");
		System.out.println("Titre de la page : " + driver.getTitle());
		driver.quit();
	}
	
	public static void main(String[] args) {
		InstallationWebDriver3 install = new InstallationWebDriver3();
		install.testChromeWebDriver();
		install.testFirefoxWebDriver();
		install.testIEWebDriver();
		install.testPhantomJSWebDriver();
		
		
	}

}
