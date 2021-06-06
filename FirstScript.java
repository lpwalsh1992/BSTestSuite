package BSProject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstScript {
	public static void main(String[] args) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://login.yahoo.com/");
		Thread.sleep(2000);
		WebElement checkbox = driver.findElement(By.id("persistent"));
		 if(checkbox.isSelected())
		    {
		    	System.out.println("Test Successful: Checkbox selected by default");
		    }
		    else
		    {
			System.out.println("Test Failure: Checkbox not selected by default");
		    }
		driver.findElement(By.id("createacc")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("termsLink")).click();
		Thread.sleep(2000);
		String myWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(myWindowHandle);
		Thread.sleep(2000);
		driver.findElement(By.className("privacyLink")).click();
		Thread.sleep(2000);
		driver.close();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	    String actualTitle = (driver.getTitle());
	    String expectedTitle = "Verizon Media Terms of Service | Verizon Media Policies";
	    if(actualTitle.equalsIgnoreCase(expectedTitle))
	    {
	    	System.out.println("Test Successful: Title Match");
	    }
	    else
	    {
		System.out.println("Test Failure");
		System.out.println("Actual Title: " + actualTitle);
		System.out.println("Expected Title: "+ expectedTitle);
	    }
	    WebElement sidebar = driver.findElement(By.id("site-sidebar"));
	    if(sidebar.isDisplayed())
	    {
	    	System.out.println("Test Successful: Navigator Pane/Sidebar displayed");
	    }
	    else
	    {
		System.out.println("Test Failure: No Nav Pane/Sidebar displayed");
	    }
	    driver.quit();
	}
	}
