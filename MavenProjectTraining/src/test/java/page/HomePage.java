package page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.util.Assert;

public class HomePage{
	
	protected WebDriver driver;
	JavascriptExecutor js;
	Actions actions;
	
	public HomePage(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }
	
	//WebElement
	private By elementsPage = By.xpath("//h5[text()='Elements']/parent::div/preceding-sibling::div[1]");
	
	public void goToDemoQAWebPage() {
		driver.get("https://demoqa.com/");
		driver.manage().window().maximize();
		assertEquals(driver.getCurrentUrl(),"https://demoqa.com/","Can't navigate to DEMOQA web");
	}
	
	public void goToElementsPage() {
		WebElement elementsPageLink = driver.findElement(elementsPage);
    	js.executeScript("arguments[0].scrollIntoView(true);", elementsPageLink);
    	elementsPageLink.click();
		System.out.println("Verify navigate to Elements web page ");
		assertTrue(driver.getCurrentUrl().contains("elements"), "Navigate to Elements Web Page Failed");
	}



}
