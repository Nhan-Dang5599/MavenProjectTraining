package common;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils {
	
	public static void waitForPageReady(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    wait.until(webDriver -> 
	        ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
	    );
	}
	
	public static String converNumberToTextForMonth(String month) {
    	Map<String, String> map = Map.ofEntries(
    			Map.entry("1", "January"),
    			Map.entry("2", "February"),
    			Map.entry("3", "March"),
    			Map.entry("4", "April"),
    			Map.entry("5","May"),
    			Map.entry("6", "June"),
    			Map.entry("7", "July"),
    			Map.entry("8", "August"),
    			Map.entry("9", "September"),
    			Map.entry("10", "October"),
    			Map.entry("11", "November"),
    			Map.entry("12", "December")
    		);
    	
    	return map.get(month);
    }
	
	public static String mapToFullMontName(String month) {
    	Map<String, String> map = Map.ofEntries(
    			Map.entry("Jan", "January"),
    			Map.entry("Feb", "February"),
    			Map.entry("Mar", "March"),
    			Map.entry("Apr", "April"),
    			Map.entry("May","May"),
    			Map.entry("Jun", "June"),
    			Map.entry("Jul", "July"),
    			Map.entry("Aug", "August"),
    			Map.entry("Sep", "September"),
    			Map.entry("Oct", "October"),
    			Map.entry("Nov", "November"),
    			Map.entry("Dec", "December")
    		);
    	
    	return map.get(month);
    }

}