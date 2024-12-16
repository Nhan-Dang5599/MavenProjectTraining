package common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotHelper {
	
	private Robot robot;
	
	public void enterStringByPressKeyIntoUploadFileWindow(String filePath) {
		
	    try {
	        robot = new Robot();
	        for (char c : filePath.toCharArray()) {
	        	robot.delay(50); // Thêm độ trễ nhỏ để tránh mất ký tự
	            if (Character.isUpperCase(c)) {
	                robot.keyPress(KeyEvent.VK_SHIFT);
	                robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(Character.toLowerCase(c)));
	                robot.delay(50);
	                robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(Character.toLowerCase(c)));
	                robot.keyRelease(KeyEvent.VK_SHIFT);
	                robot.delay(50);
	            } else if (c == '\\') { // Xử lý ký tự '\'
	                robot.keyPress(KeyEvent.VK_ALT);
	                robot.keyPress(KeyEvent.VK_NUMPAD9);
	                robot.keyPress(KeyEvent.VK_NUMPAD2);
	                robot.delay(50);
	                robot.keyRelease(KeyEvent.VK_NUMPAD9);
	                robot.keyRelease(KeyEvent.VK_NUMPAD2);
	                robot.keyRelease(KeyEvent.VK_ALT);
	                robot.delay(50);
	            } else if (c == ':') { // Xử lý ký tự ':'
	                robot.keyPress(KeyEvent.VK_SHIFT);
	                robot.keyPress(KeyEvent.VK_SEMICOLON);
	                robot.delay(50);
	                robot.keyRelease(KeyEvent.VK_SEMICOLON);
	                robot.keyRelease(KeyEvent.VK_SHIFT);
	                robot.delay(50);
	            } else {
	                robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
	                robot.delay(50);
	                robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
	                robot.delay(50);
	            }
	        }

	        // Nhấn Enter để xác nhận
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.delay(50);
	        robot.keyRelease(KeyEvent.VK_ENTER);

	    } catch (AWTException e) {
	        e.printStackTrace();
	    }
	}


	
}