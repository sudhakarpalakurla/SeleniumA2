package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtilities123.JavaUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This class contains all reusable methods to perform driver related operations
 */
public class WebDriverUtility {
	WebDriver driver;
	/**
	 * This method is used to launch specified browser and maximize it
	 * @param browser
	 * @return
	 */
	
	public WebDriver launchBrowserAndMaximize(String browser) {
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
			 driver= new ChromeDriver();
		        break;
		
		case "firefox" :
		       WebDriverManager.firefoxdriver().setup();
		       driver = new FirefoxDriver();
		       break;
		       
		case "edge" :
		       WebDriverManager.edgedriver().setup();
		       driver = new EdgeDriver();
		       break;
		        
		   default :
		    System.out.println("invalid browser info");
	
		}
		driver.manage().window().maximize();
		return driver;
		
}
	/**
	 * This method is used to navigate to specified url
	 * @param url
	 */
	public void navigateToApp(String url) {
		driver.get(url);
		
	}
	/**
	 * This method is an implicit wait statement which element is found
	 * @param time
	 */
		 public void waitTillElementFound(long time) {
			   
				driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
			}
		 /**
		  * This method waits until element is displayed on the web page 
		  * @param time
		  * @param element
		  * @return
		  */
		 
		 public WebElement explicitWait(long time, WebElement element) {
				WebDriverWait wait = new WebDriverWait(driver, time);
				return wait.until(ExpectedConditions.visibilityOf(element));
			}
		 /**
		  * This method waits until element on the web page is enabled to receive click
		  * @param element
		  * @param time
		  * @return
		  */

			public WebElement explicitWait(WebElement element, long time) {
				WebDriverWait wait = new WebDriverWait(driver, time);
				return wait.until(ExpectedConditions.elementToBeClickable(element));
			}
			/**
			 * This method is used wait until title appear on the web page 
			 * @param time
			 * @param title
			 * @return
			 */

			public Boolean explicitWait(long time, String title) {
				WebDriverWait wait = new WebDriverWait(driver, time);
				return wait.until(ExpectedConditions.titleContains(title));
			}
			
			/**
			 * This method is used to mouse hover on an element
			 * @param element
			 */
			public void mouseHoverToElement(WebElement element) {
				Actions action = new Actions (driver);
				action.moveToElement(element).perform();
			}
			
			/**
			 * This method is used to double click on an element
			 * @param element
			 */
			public void doubleClickonElement(WebElement element) {
				Actions action = new Actions (driver);
				action.doubleClick(element).perform();
			}
			
			/**
			 * This method is used to perform right click on an element
			 * @param element
			 */
			public void rightClick(WebElement element) {
				Actions action = new Actions (driver);
				action.contextClick(element).perform();
			}
			
			/**
			 * This method is used to drag and drop an element to target location
			 * @param element
			 * @param target
			 */
			public void draAndDropAnElement(WebElement element,WebElement target) {
				Actions action = new Actions (driver);
				action.dragAndDrop(element,target).perform();
			}
			
			/**
			 * This method is used switch to frame based on specified index
			 * @param index
			 */
			public void switchToFrame(int index) {
				driver.switchTo().frame(index);
				
			}
			/**
			 * This method is used to switch to frame based on specified id or name attribute
			 * @param inOrName
			 */
			public void switchToFrame(String inOrName) {
				String idOrName = null;
				driver.switchTo().frame(idOrName);
			}
			/**
			 * This method is used to switch to frame based on frame element	
			 * @param frameElement
			 */
			public void switchToFrame1(WebElement frameElement) {
				driver.switchTo().frame(frameElement);
			}
			/**
			 * This method is used to switch back to the default web page
			 * @param frameElement
			 */
			
		
			public void switchToFrame(WebElement frameElement) {
				driver.switchTo().defaultContent();
			}
			/**
			 * This method is used to select an element from drop down based on element index
			 * @param element
			 * @param index
			 */
			public void selectFromDropdown(WebElement element,int index) {
				Select select = new Select(element);
				select.selectByIndex(index);
			}
			
			/**
			 * This method is used to select an element from drop down based on the value
			 * @param element
			 * @param value
			 */
			public void selectFromDropdown(WebElement element,String value) {
				Select select = new Select(element);
				select.selectByValue(value);
			}
			
			/**
			 * This method is used to select an element from drop down based on visible text
			 * @param text
			 * @param element
			 */
			public void selectFromDropdown(String text, WebElement element) {
				Select select = new Select(element);
				select.selectByVisibleText(text);
			}
			
			/**
			 * This method is fetches all the elements from drop down
			 * @param element
			 * @return
			 */
			public List<WebElement> getDropdownList(WebElement element) {
				Select select = new Select(element);
				return select.getOptions();
			}
			/**
			 * This method is used to capture screenshot of the web page
			 * @param driver
			 */
			
			public void captureScreenshot(WebDriver driver, JavaUtility jutil, String className) {
				TakesScreenshot ts = (TakesScreenshot)driver;
				File src = ts.getScreenshotAs(OutputType.FILE);
				
				File dest =  new File("./Screenshot/"+className+"_"+  jutil.getCurrentTime() +".png");
				try {
					FileUtils.copyFile(src, dest);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			/**
			 * This method is used to scroll till the specified element on the web page
			 * @param element
			 */
			
     public void scrollTillElement(WebElement element) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true)", element);
}
     /**
      * This method is used to handle alert pop up
      * @param status
      */

     public void handleAlert(String status) {
	Alert a1 = driver.switchTo().alert();
	if(status.equalsIgnoreCase("ok"))
		a1.accept();
	else
		a1.dismiss();
}
     
     /**
      * This method is used to switch to child browser
      */
     public void switchToChildBrowser() {
	Set<String> windowIDs = driver.getWindowHandles();
	for(String window : windowIDs) {
		driver.switchTo().window(window);
	}
}
     /**
      * This method returns parent window address
      * @return
      */
     public String getParentWindow() {
	return driver.getWindowHandle();
}
     
     /**
      * This method is used to switch to specified window
      * @param windowID
      */
     public void switchToWindow(String windowID) {
	driver.switchTo().window(windowID);
     }
     /**
      * This method is used to close the current tab
      */
     public void closeBrowser() {
    	 driver.close();
     }
     /**
      * This method is used to quit all the windows
      */
     public void quitAllWindows() {
    	 driver.quit();
     }
     /**
      * This method converts dynamic path to WebElement
      * @param path
      * @param replaceData
      * @return
      */
     
     public WebElement convertPathToWebElement(String path,String replaceData) {
    	 String requiredPath = String.format(path, replaceData);
    	 WebElement element = driver.findElement(By.xpath(requiredPath));
    	 return element;
     }
	public void switchBackFromFrame() {
		// TODO Auto-generated method stub
		
	}
}

				