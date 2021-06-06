package BSProject;


import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class TestNGFile {
	public String defaultPage = "https://www.nufc.co.uk/";
	String driverPath = "C:\\Selenium\\chromedriver_win32\\chromedriver.exe";
	public WebDriver driver;
	
	@AfterTest
	public void terminateBrowser() {
		System.out.println("Testing finished."); 
		driver.quit();
	}
	
	@BeforeTest
    public void launchBrowser() throws InterruptedException, AWTException {
        System.out.println("Launching browser..."); 
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(defaultPage);
        Thread.sleep(2000);
    }
	
  @Test(priority = 0)
  public void verifyBanner() {
		WebElement BannerInfo = driver.findElement(By.className("banner__content"));
		Boolean verifyBanner = BannerInfo.isDisplayed();
		assertTrue(verifyBanner);
  }
  @Test(priority = 1)
  public void verifySocialLinks() {
		WebElement socialLinks = driver.findElement(By.className("social-links"));
		Boolean verifySocial = socialLinks.isDisplayed();
		assertTrue(verifySocial);
  }
  
  @Test(priority = 2)
  public void verifyNUFCTV() {
	  driver.get("https://www.nufc.co.uk/nufc-tv/");
	  String actualTitle = driver.getTitle();
	  String expectedTitle = "Newcastle United - NUFC TV";
	  assertEquals(expectedTitle, actualTitle);
}
}
