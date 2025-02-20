package mytests;

import org.openqa.selenium.WebDriver;
import org.sample.SeleniumDriverSingleton.WebDiverManager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;








public class AppTest {
	private WebDriver driver;
	
	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser) {
		driver = WebDiverManager.getInstance(browser).getDriver();
	}
	 @Test
	 public void testGoogle() {
		 driver.get("https://www.google.com");
		 System.out.print(Thread.currentThread().getName() +": " + driver.getTitle());
		 Assert.assertEquals(driver.getTitle(), "Google");
	 }
	 
	 @AfterClass
	 public void tearDown() {
		 WebDiverManager.quitBrowser();
		 
	 }
	
	

}
