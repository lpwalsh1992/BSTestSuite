package BSProject;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TestNGFile {
	public String defaultPage = "https://login.yahoo.com/";
	String driverPath = "C:\\Selenium\\chromedriver_win32\\chromedriver.exe";
	public WebDriver driver;
	
	@AfterTest
	public void terminateBrowser() {
		System.out.println("Testing finished."); 
		driver.quit();
	}
	
	@BeforeTest
    public void launchBrowser() {
        System.out.println("Launching browser..."); 
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(defaultPage);
    }
	
  @Test(priority = 0)
  public void verifyCheckbox() {
		WebElement checkbox = driver.findElement(By.id("persistent"));
		Boolean verifyCheckbox = checkbox.isSelected();
		assertTrue(verifyCheckbox);
  }
  @Test(priority = 1)
  public void verifyPrivacyPage() {
		driver.findElement(By.id("createacc")).click();
		driver.findElement(By.className("termsLink")).click();
		String myWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(myWindowHandle);
		driver.findElement(By.className("privacyLink")).click();
		driver.close();
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	    String actualTitle = (driver.getTitle());
	    String expectedTitle = "Verizon Media Terms of Service | Verizon Media Policies";
	    assertEquals(expectedTitle,actualTitle);
  }
  
  @Test(priority = 2)
  public void verifyNavPane() {
  WebElement sidebar = driver.findElement(By.id("site-sidebar"));
  assertNotNull(sidebar);
}
}
