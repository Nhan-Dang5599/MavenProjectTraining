package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import utilities.Log;
import utilities.PropertiesFile;
import utilities.TestListener;
import utilities.CaptureHelper;
public class FirstTestCase extends TestListener {
	public WebDriver driver;

//	@BeforeClass
//	public void setBrowser() {
//		String browser = System.getProperty("env.browser");
//		if(browser.equals("chrome")) {
//			driver = new ChromeDriver();
//		}
//		else if (browser.equals("firefox")) {
//			driver = new FirefoxDriver();
//		}
//		else {
//			driver = new EdgeDriver();
//		}
//	}
//	
 @Test
    public void openBrowser() {
        // Cấu hình ChromeDriver (đảm bảo đã cài đặt ChromeDriver trên hệ thống)
        driver = new ChromeDriver();

        // Mở trang web
        driver.get("https://www.youtube.com/");
        
        // Đường dẫn file properties
        PropertiesFile prop = new PropertiesFile("src/main/resources/Data.properties");
        prop.setPropertiesFile();

        // Lấy giá trị từ file properties
        String username = prop.getValuePropertiesFile("username");
        System.out.println("Username: " + username);

        // Chụp ảnh màn hình
        CaptureHelper.captureScreenshot(driver, "openBrowser");
        
        // Đóng trình duyệt
        driver.quit();
    }

}
