package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.awt.*;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class CaptureHelper extends ScreenRecorder {
	
	static String projectPath = System.getProperty("user.dir") + "/";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    
    public static void captureScreenshot(WebDriver driver, String screenName) {
    	
    	PropertiesFile prop = new PropertiesFile("src/main/resources/Data.properties");
    	prop.setPropertiesFile();
        try {
            Thread.sleep(1000);
            Reporter.log("Driver for Screenshot: " + driver);
            
            // Create a reference of TakesScreenshot with the current driver
            TakesScreenshot ts = (TakesScreenshot) driver;
            
            // Call the capture screenshot function - getScreenshotAs
            File source = ts.getScreenshotAs(OutputType.FILE);
            
            // Check if folder exists. If not, create new folder
            File theDir = new File(projectPath + prop.getValuePropertiesFile("exportCapturePath"));
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            
            // result.getName() gets the name of the test case and assigns it to the screenshot file name
            FileHandler.copy(source, new File(projectPath + prop.getValuePropertiesFile("exportCapturePath") + "/" + screenName + "_" + dateFormat.format(new Date()) + ".png"));
            System.out.println("Screenshot taken: " + screenName);
            Reporter.log("Screenshot taken current URL: " + driver.getCurrentUrl(), true);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }

    }
    
	
	public static void takeScreenshot(WebDriver driver, ITestResult result) {
		 if (driver == null) {
			 System.out.println("Driver is null, unable to take screenshot.");
			 return;
		 }

		 try {
		 // Format appropriate date and time
			 DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
			 String timestamp = dateTimeFormatter.format(LocalDateTime.now());
	
			 // Get test case name and status
			 String testName = result.getName();
			 String status = result.getStatus() == ITestResult.SUCCESS ? "Passed" : "Failed";
	
			 // Create TakesScreenshot object
			 TakesScreenshot ts = (TakesScreenshot) driver;
			 File source = ts.getScreenshotAs(OutputType.FILE);
	
			 // Create Screenshots folder if it does not exist
			 File screenshotDir = new File("./exportData/Images/");
			 if (!screenshotDir.exists()) {
				 screenshotDir.mkdirs();
			 }
	
			 // File path
			 String filePath = "./exportData/Images/" + testName + "_" + status + "_" + timestamp + ".png";
			 FileHandler.copy(source, new File(filePath));
	
			 Reporter.log("Screenshot saved at: " + filePath, true);
		 } catch (Exception e) {
			 System.out.println("Exception while taking screenshot: " + e.getMessage());
		 }
		}
	
    
 // ------Record with Monte Media library---------
 		public static ScreenRecorder screenRecorder;
 		public String name;
 		
 		//Hàm xây dựng
 		public CaptureHelper(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat,
 				Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
 			super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
 			this.name = name;
 		}
 		
 		//Hàm này bắt buộc để ghi đè custom lại hàm trong thư viên viết sẵn
 		@Override
 		protected File createMovieFile(Format fileFormat) throws IOException {

 			if (!movieFolder.exists()) {
 				movieFolder.mkdirs();
 			} else if (!movieFolder.isDirectory()) {
 				throw new IOException("\"" + movieFolder + "\" is not a directory.");
 			}
 			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
 			return new File(movieFolder,
 					name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
 		}

 		// Hàm Start record video
 		public static void startRecord(String methodName) throws Exception {
 			//Tạo thư mục để lưu file video vào
 			File file = new File("./exportData/Videos/");
 			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
 			int width = screenSize.width;
 			int height = screenSize.height;

 			Rectangle captureSize = new Rectangle(0, 0, width, height);

 			GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
 					.getDefaultConfiguration();
 			screenRecorder = new CaptureHelper(gc, captureSize,
 					new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
 					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
 							CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
 							Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
 					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
 					null, file, methodName);
 			screenRecorder.start();
 		}

 		// Stop record video
 		public static void stopRecord() throws Exception {
 			screenRecorder.stop();
 		}


}

