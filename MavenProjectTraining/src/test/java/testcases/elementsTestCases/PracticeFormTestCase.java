package testcases.elementsTestCases;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.BaseTest;
import utilities.*;
import page.HomePage;
import page.elementsWebPage.PracticeFormPage;

public class PracticeFormTestCase {
	
	private WebDriver driver;
	private HomePage homepage;
	private PracticeFormPage pracFormPage;
	

	@BeforeTest
	public void setUp() {
		BaseTest basetest = new BaseTest();
		basetest.selecteBrowser("chrome");
		driver = BaseTest.getDriver();
	}
	
	@Test(priority = 0)
	public void verifyNavigatePracticeformBrowser() {
		homepage = new HomePage(driver);
		homepage.goToDemoQAWebPage();
		homepage.goToElementsPage();
	}
	
	
	@Test(priority = 1)
	public void navigateToPracticeFormPage() {
		pracFormPage = new PracticeFormPage(driver);
		pracFormPage.goToPracticeFormPage();	
		
	}
	
	@Test(priority = 2)
	public void checkTextInputField() {
		pracFormPage.setTextFeilds();
		CaptureHelper.captureScreenshot(driver, "Test_For_Text_Input_Field");
		
	}
	
	@Test(priority = 3)
	public void checkSetsetDateOfBirthWithDatePicker() {
		pracFormPage.setDateOfBirth();
		CaptureHelper.captureScreenshot(driver, "Test_For_DateOfBirt_With_DatePicker");
	}
	
	@Test(priority = 4)
	public void checkGenderRadio() {
		pracFormPage.selectGenderRadio();
		CaptureHelper.captureScreenshot(driver, "Test_For_SelectGender_With_Radio");
	}
	
	@Test(priority = 5)
	public void checkHobbiesCheckBox() {
		pracFormPage.selectHobbiesCheckBox();
		CaptureHelper.captureScreenshot(driver, "Test_For_SelectHobbies_With_CheckBox");
	}
	
	@Test(priority = 6)
	public void checkUploadPicture() {
		try {
			pracFormPage.uploadPicture();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CaptureHelper.captureScreenshot(driver, "Test_For_UploadPicture_With_UploadField");
	}
	
	@Test(priority = 7)
	public void checkSelectSubject() {
		pracFormPage.selectSubject();
		CaptureHelper.captureScreenshot(driver, "Test_For_SelectSubject_With_SelectOptionsField");
	}
	
	@Test(priority = 8)
	public void checkSelectStateAndCity() {
		pracFormPage.selectStateAndCityAndVerify();
		CaptureHelper.captureScreenshot(driver, "Test_For_SelectStateAndCity_And_Verify");
	}
	
	@Test(priority = 9)
	public void checkSubmitAndVerifyResult() {
		pracFormPage.submitAndVerify();
		CaptureHelper.captureScreenshot(driver, "Test_For_Verify_Normal_Display");
	}
	
	@AfterTest
	public void teardown() {
		if(driver!=null) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.close();
		}
	}

}
