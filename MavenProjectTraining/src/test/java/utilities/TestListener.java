package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import common.BaseTest;

public class TestListener implements ITestListener {
    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("Start testing: " + iTestContext.getName());
        try {
            RecordHelper.startRecord(iTestContext.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info(iTestContext.getName() + " Test is finish");
        try {
            RecordHelper.stopRecord();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestName(iTestResult) + " Test is start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestName(iTestResult) + " Test is pass.");
        try {
        	CaptureHelper.takeScreenshot(BaseTest.getDriver(), iTestResult); // Take screenshot when test case Failed
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot on passed: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.error((getTestName(iTestResult) + " Test is false."));
        try {
        	CaptureHelper.takeScreenshot(BaseTest.getDriver(), iTestResult); // Take screenshot when test case Failed
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot on failed: " + e.getMessage());
        }
    }
   

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.warn(getTestName(iTestResult) + " Test is skip.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }
}