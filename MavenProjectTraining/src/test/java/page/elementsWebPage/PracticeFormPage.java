package page.elementsWebPage;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.Select;

import common.CommonUtils;
import common.RobotHelper;
import utilities.CaptureHelper;

public class PracticeFormPage {
	private WebDriver driver;
	private JavascriptExecutor js;
	private Actions ac;
	private Pattern pattern;
	private Select select;
	private SoftAssert softassert = new SoftAssert();
	private Robot robot;
	
    public PracticeFormPage(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.ac = new Actions(driver);
    }
    
    //Web Elements
    private By  practicFormItem = By.xpath("//div[text()='Forms']");
    private By  practiceFormLink = By.xpath("//span[text()='Practice Form']/parent::li");
    
      //*** input text input field 
    private By inputFirstName = By.id("firstName");
    private By inputLastName = By.id("lastName");
    private By inputEmail = By.id("userEmail");
    private By inputMobile = By.id("userNumber");
    private By inputCurrentAddress = By.id("currentAddress");
    
      //*** Date picker input web elements
    private By dateOfBirthInputDatePicker = By.id("dateOfBirthInput");
    private By selectYearField = By.className("react-datepicker__year-select");
    private By selectMonthFiled = By.className("react-datepicker__month-select");
    private By selectDayField = By.xpath("//div[contains(@class, 'react-datepicker__day') and not(contains(@class,'outside')) and not(contains(@class,'name'))]");
    private By datePickerResult = By.id("dateOfBirthInput");
    
      //*** Radio elements
    private By genderSelect = By.xpath("//input[@name='gender']");
    
     //*** CheckBox elements
    private By sportsSelect = By.id("hobbies-checkbox-1");
    private By readingSelect = By.id("hobbies-checkbox-2");
    private By musicSelect = By.id("hobbies-checkbox-3");
    
     //*** Upload Picture
    private By upload = By.id("uploadPicture");
    
     //*** Select Subjects
    private By subjectsInput = By.id("subjectsInput");
    private By maths = By.xpath("//div[text()= 'Maths']");
    private By accounting = By.xpath("//div[text()= 'Accounting']");
    private By resultSubjectsInput = By.xpath("//div[@class= 'css-12jo7m5 subjects-auto-complete__multi-value__label']");
    
     //*** State and City
    private By stateAndCityElement = By.xpath("//div[@class=' css-11unzgr']/div");
    private By state = By.xpath("//*[@id='state']/div");
    private By city = By.id("city");
    
    private By uttarPradesh = By.xpath("//div[text()= 'Uttar Pradesh']");
    private By MerrutCity = By.xpath("//div[text()= 'Merrut']");
    
    private By NCR = By.xpath("//div[text()= 'NCR']");
    private By Haryana = By.xpath("//div[text()= 'Haryana']");
    private By Rajasthan = By.xpath("//div[text()= 'Rajasthan']");
    
    private By resultInputStateCity = By.xpath("//div[@class =' css-1uccc91-singleValue']");
    
    //*** Submit and Verify Result
    private By submitBtn = By.id("submit");
    private By titleSubmittedModal = By.xpath("//div[@class ='modal-content']//div[contains(@class, 'h4')]");
    private By tableResult = By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']");
    private By closeLargeModal = By.id("closeLargeModal");
    

    //String input value
    private String firstName = "Dang";
    private String lastName = "Nhan";
    private String email = "nhan@gmail.com";
    private String invalidEmail = "nhan.com";
    private String number = "0123456789";
    private String currentAddress = "94 Nguyen Du , phuong 7, Go Vap";
    private String dateOfBirth = "5 May 1999";
    private String gender = "Male";
    private String filePath = "c:\\Users\\Admin\\Documents\\Test.txt";
    private String fileName = "Test.txt";
    private String subject1 = "Maths";
    private String subject2 = "Accounting";
    private List<String> stateList = Arrays.asList("NCR", "Uttar Pradesh","Haryana","Rajasthan");
    private List<String> uttarPradeshCityList = Arrays.asList("Agra","Lucknow","Merrut");
    private List<String> NCRCity = Arrays.asList("Delhi","Gurgaon","Noida");
    private List<String> HaryanaCity = Arrays.asList("Karnal","Panipat");
    private List<String> RajasthanCity = Arrays.asList("Jaipur","Jaiselmer");
    private String mesSubmitted = "Thanks for submitting the form";
    private String subjectInTable = "Maths, Accounting";
    
    public void goToPracticeFormPage() {
    	WebElement practicFormItemElement = driver.findElement(practicFormItem);
    	js.executeScript("arguments[0].scrollIntoView(true);", practicFormItemElement);
    	practicFormItemElement.click();
    	
    	WebElement practiceFormLinkElement = driver.findElement(practiceFormLink);
    	js.executeScript("arguments[0].scrollIntoView(true);", practiceFormLinkElement);
    	practiceFormLinkElement.click();
    	
    	assertTrue(driver.getCurrentUrl().contains("https://demoqa.com/automation-practice-form"));
    }
    
    public void setTextFeilds() {
    	System.out.println("Input Name : ");
    	driver.findElement(inputFirstName).sendKeys(firstName);
    	driver.findElement(inputLastName).sendKeys(lastName);
    	
    	System.out.println("Input Email : ");
    	String formatPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    	pattern = Pattern.compile(formatPattern);
    	softassert.assertEquals(true, pattern.matcher(email).matches(), "Invalid format Email");
    	driver.findElement(inputEmail).sendKeys(email);
    	
    	System.out.println("Input User Number : ");
    	int requiredLength = Integer.valueOf(driver.findElement(inputMobile).getAttribute("maxlength"));
    	softassert.assertEquals(requiredLength, number.length(), "The lenght of User Number not properly");
    	driver.findElement(inputMobile).sendKeys(number);
    	
    	System.out.println("Input Current Address : ");
    	driver.findElement(inputCurrentAddress).sendKeys(currentAddress);
    }
    
    public void setDateOfBirth() {
    	System.out.println("Set Date Of Birth ");
    	String[] arr = dateOfBirth.split(" ");
    	
//    	if(!Character.isDigit(arr[1].charAt(0))) {
//    		arr[1] = CommonUtils.converNumberToTextForMonth(arr[1]);
//    	}
    	
    	arr[1] = CommonUtils.mapToFullMontName(arr[1]);

    	WebElement datePickerElt = driver.findElement(dateOfBirthInputDatePicker);
    	js.executeScript("arguments[0].scrollIntoView(true)", datePickerElt);
    	datePickerElt.click();
    	
    	System.out.println("Set Year Of Birth ");
    	select = new Select(driver.findElement(selectYearField));
    	select.selectByVisibleText(arr[2]);
    	
    	System.out.println("Set Month Of Birth ");
    	select = new Select(driver.findElement(selectMonthFiled));
    	select.selectByVisibleText(arr[1]);
    	
    	List<WebElement> days = driver.findElements(selectDayField);
    	for(WebElement e : days) {		
    		if(e.getText().equals(arr[0])) {
    			e.click();
    			break;
    		}
    	}
    	
    	System.out.println("Verify select DatePicker result ");
    	String actualDate = driver.findElement(datePickerResult).getAttribute("value");
    	System.out.println("actualDate : " + actualDate);
    	assertTrue(actualDate.equals("0" + dateOfBirth), "Set date of birth failed");
    	
    	System.out.println("Set date of birth successfully "); 	

    }
    
    public void selectGenderRadio() {
    	System.out.println("Select Gender With Radio");
    	List<WebElement> genderElements = driver.findElements(genderSelect);
    	js.executeScript("arguments[0].scrollIntoView(true)", genderElements.get(0));
    	
    	for(WebElement e : genderElements) {
	    	if(e.getAttribute("value").equals(gender)){
	    		//Due to <label> cover so need using executeScript to handle 
	    		//=>org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
	    		js.executeScript("arguments[0].click();", e);
	    	}
    	}
    	
    }
    
    public void selectHobbiesCheckBox() {
    	
    	System.out.println("Select Hobbies with checkbox : ");
    	js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(sportsSelect));
    	
    	js.executeScript("arguments[0].click()", driver.findElement(sportsSelect));
    	
    	js.executeScript("arguments[0].click()", driver.findElement(readingSelect));
    	
    	js.executeScript("arguments[0].click()", driver.findElement(musicSelect));
    	
    	if (driver.findElement(sportsSelect).isSelected()) {
    	    System.out.println("Sports is selected");
    	} else {
    	    System.out.println("Sports is not selected");
    	}
    	
    	if (driver.findElement(readingSelect).isSelected()) {
    	    System.out.println("Reading is selected");
    	} else {
    	    System.out.println("Reading is not selected");
    	}
    	
    	if (driver.findElement(musicSelect).isSelected()) {
    	    System.out.println("Music is selected");
    	} else {
    	    System.out.println("Musics is not selected");
    	}
    }
    
    public void uploadPicture() throws AWTException {
    	System.out.println("Upload picture in upload field : ");
    	
    	js.executeScript("arguments[0].scrollIntoView[true]", driver.findElement(upload));
    	js.executeScript("arguments[0].click()", driver.findElement(upload));
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Chờ cửa sổ sẵn sàng
    	
    	RobotHelper rbHpr = new RobotHelper();
    	rbHpr.enterStringByPressKeyIntoUploadFileWindow(filePath);	
    }
    
    public void selectSubject() {
    	
    	System.out.println("Select Optional Subjects : ");
    	
    	WebElement e = driver.findElement(subjectsInput);
    	js.executeScript("arguments[0].scrollIntoView(true)", e);
    	js.executeScript("arguments[0].click()", e);
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	e.sendKeys("mat");
    	assertNotNull(driver.findElement(maths), "Not display dropdown options select");
    	System.out.println("Select Math Subjects : ");
    	js.executeScript("arguments[0].click()", driver.findElement(maths));
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	e.sendKeys("acc");
    	assertNotNull(driver.findElement(accounting), "Not display dropdown options select");
    	System.out.println("Select Accounting Subjects : ");
    	js.executeScript("arguments[0].click()", driver.findElement(accounting));
    	
    	System.out.println("Verify ");
    	List<WebElement> resultSublectsInputElements = driver.findElements(resultSubjectsInput);
    	List<String> resultSublectsInput = new ArrayList<String>();
    	for(WebElement elt : resultSublectsInputElements) {
    		resultSublectsInput.add(elt.getText());
    	}
    	
    	assertTrue(resultSublectsInput.contains(subject1),"Cant not select " + subject1);
    	assertTrue(resultSublectsInput.contains(subject2),"Cant not select " + subject2);
    	
    }
    
    public void selectStateAndCityAndVerify() {
    	
    	System.out.println("Click into State field ");
    	js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(state));
    	//js.executeScript("arguments[0].click()", driver.findElement(state));
    	driver.findElement(state).click();
    	
    	List<WebElement> stateElements = driver.findElements(stateAndCityElement);
    	System.out.println("Size : " + stateElements.size());
    	List<String> acStateList = new ArrayList<String>();
    	for(WebElement elt : stateElements) {
    		acStateList.add(elt.getText());
    	}
    	
    	System.out.println("Verify all states display");
    	assertTrue(acStateList.containsAll(stateList),"Display All State Error");
    	
    	System.out.println("Select Uttar Pradesh State");
    	js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(uttarPradesh));
    	js.executeScript("arguments[0].click()", driver.findElement(uttarPradesh));
    	
    	System.out.println("Verify all cities of  Uttar Pradesh display");
    	driver.findElement(city).click();
    	List<WebElement> uttarPradeshCity = driver.findElements(stateAndCityElement);
    	List<String> acUttarPradeshCityList = new ArrayList<String>();
    	for(WebElement elt : uttarPradeshCity) {
    		acUttarPradeshCityList.add(elt.getText());
    	}
    	assertTrue(acUttarPradeshCityList.containsAll(uttarPradeshCityList),"Display All City of Uttar Pradesh Have Error");
    	
    	driver.findElement(MerrutCity).click();
    	List<WebElement> resultStateCityElements = driver.findElements(resultInputStateCity);
    	List<String> resultInputStateandCity = new ArrayList<String>();
    	for(WebElement elt : resultStateCityElements) {
    		resultInputStateandCity.add(elt.getText());
    	}
    	
    	assertTrue(resultInputStateandCity.contains(stateList.get(1)),"Display State Input Error");
    	assertTrue(resultInputStateandCity.contains(uttarPradeshCityList.get(2)),"Display City Input Error");
    	
    }
    
    public void submitAndVerify() {
    	
    	//Click submit button
    	System.out.println("Click SubmitButton : ");
    	WebElement submitBtnElement = driver.findElement(submitBtn);
    	js.executeScript("arguments[0].scrollIntoView(true);", submitBtnElement);
    	submitBtnElement.click();
    	
    	System.out.println("Verify title of result modal");
    	String actualTitle = driver.findElement(titleSubmittedModal).getText();
    	assertTrue(mesSubmitted.equals(actualTitle), "The titile of table submit INCORRECT");
    	
    	System.out.println("Verify content of result modal");
    	Map<String, String> result = getValueOfTableResult();
    	System.out.println("Verify Student Name");
    	assertTrue(result.get("Student Name").equals(firstName + " " + lastName), "The Student Name set INCORRECT");
    	
    	System.out.println("Verify Student Email");
    	assertTrue(result.get("Student Email").equals(email), "The email set INCORRECT");
    	
    	System.out.println("Verify Mobile");
    	assertTrue(result.get("Mobile").equals(number), "The user number set INCORRECT");
    	
    	System.out.println("Verify Subjects");
    	assertTrue(result.get("Subjects").equals(subjectInTable), "The subject set INCORRECT");
    	
    	CaptureHelper.captureScreenshot(driver, "Test_Submit_And_Verify_Modal_Result");
    	
    	js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(closeLargeModal));
    	js.executeScript("arguments[0].click();", driver.findElement(closeLargeModal));
    	assertNotNull(submitBtnElement.getText(), "Close Modal Unsuccessful");
    }
    
    private Map<String, String> getValueOfTableResult() {
    	
    	WebElement tableElement = driver.findElement(tableResult);
    	// Lấy tất cả các hàng trong <tbody>
        List<WebElement> rows = tableElement.findElements(By.xpath(".//tbody/tr"));
        
        Map<String, String> mapResult = new HashMap<String, String>();
        
        for(WebElement row : rows) {
        	 WebElement firstCol = row.findElement(By.xpath("td[1]"));
        	 WebElement secondCol = row.findElement(By.xpath("td[2]"));
        	 mapResult.put(firstCol.getText(), secondCol.getText());
        }
    	
		return mapResult;
    	
    }

}

