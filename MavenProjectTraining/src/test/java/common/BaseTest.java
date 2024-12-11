package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	public static WebDriver driver;
	
	
	public void selecteBrowser(String browserType) {
		browserType = browserType.toLowerCase();
		
		if (browserType.equals("chrome")) {
		    driver = new ChromeDriver();
		} else if (browserType.equals("firefox")) {
		    driver = new FirefoxDriver();
		} else if (browserType.equals("edge")) {
		    driver = new EdgeDriver();
		} else {
		    // Handle default case here, if necessary
		    throw new IllegalArgumentException("Unsupported browser type: " + browserType);
		}
		
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	
	public void teardown() throws InterruptedException {
		if(driver != null) {
			Thread.sleep(2000);
			driver.quit();
		}
	}

}
