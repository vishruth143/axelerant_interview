package atc.saf;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;


public class automationpractice {
	
	public static WebDriver driver = null;

	public static void main(String[] args) throws InterruptedException, IOException, AWTException {
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// Launch the application
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
		
		// Verify whether the application launched 
		String title = driver.getTitle();
		System.out.println("Title: " + title);
		
		// Verify title is correct	
		if (title.equalsIgnoreCase("My Store"))
			System.out.println("Title matches");
		else 
			System.out.println("Title doesn't match");	
		
		// Click on 'Sign In'
		driver.findElement(By.linkText("Sign in")).click();
		
		// 1. Login to the application		
		driver.findElement(By.id("email")).sendKeys("vishruth.cse@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("Test@1234");		
		driver.findElement(By.id("SubmitLogin")).click();
		
		// 2. Navigate to 'My Addresses' and add a new address
		//	Click on 'MY ADDRESSES'	
		driver.findElement(By.xpath("//span[text()='My addresses']")).click();
		// Click on 'Add a new address' 	
		driver.findElement(By.xpath("//span[text()='Add a new address']")).click();
		
		// 3. Fill all the informations(not only the mandatory)		
		driver.findElement(By.id("address1")).sendKeys("Test Ave S");
		Thread.sleep(2000);
		driver.findElement(By.id("address2")).sendKeys("Test Apt");
		Thread.sleep(2000);
		driver.findElement(By.id("city")).sendKeys("Test City");
		Thread.sleep(2000);
		Select state = new Select(driver.findElement(By.id("id_state")));
		state.selectByVisibleText("Iowa");
		Thread.sleep(2000);
		driver.findElement(By.id("postcode")).sendKeys("50266");
		Thread.sleep(2000);
		driver.findElement(By.id("phone")).sendKeys("6128191111");
		Thread.sleep(2000);
		driver.findElement(By.id("phone_mobile")).sendKeys("6128192222");
		Thread.sleep(2000);
		driver.findElement(By.id("other")).sendKeys("Testing");
		Thread.sleep(2000);
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("My Address" + new Random().nextInt(1000));
		
		// 4. Click on 'Save'
		driver.findElement(By.xpath("//button[@id='submitAddress']/span")).click();	
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-250)");
		
		// 5. Navigate to 'Women' --> Summer dresses
		WebElement we = driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li/a"));
		Actions action = new Actions(driver);		
		action.moveToElement(we).build().perform();		
		
		driver.findElement(By.xpath("//a[text()='Summer Dresses']")).click();	
		
		// 6. The Items would be in 'Grid view'. Change it to 'List View'.
		driver.findElement(By.xpath("//li[@id='list']/a/i")).click();
		
		String home = driver.getCurrentUrl();
		
		// 7. Click on the first item to view.
		driver.findElement(By.xpath("(//div[2]/h5/a)[1]")).click();			
		// 8. Increase the quantity to 5
		for (int i=0; i<4; i++) {
			driver.findElement(By.xpath("//p[@id='quantity_wanted_p']/a[2]/span/i")).click();	
		}		
		// 9. Change the size to 'L'
		Select size = new Select(driver.findElement(By.id("group_1")));
		size.selectByVisibleText("L");		
		// 10. Select any color
		driver.findElement(By.id("color_11")).click();		
		// 11. Add to cart
		driver.findElement(By.xpath("//span[text()='Add to cart']")).click();
		
		Thread.sleep(10000);
		
		// 12. Click 'Continue shopping' and repeat the same for the other 2 items as well under the summer dresses.
		driver.findElement(By.xpath("//div[@id='layer_cart']/div/div[2]/div[4]/span/span")).click();
		driver.get(home);
		driver.findElement(By.xpath("(//div[2]/h5/a)[2]")).click();		
		for (int i=0; i<4; i++) {
			driver.findElement(By.xpath("//p[@id='quantity_wanted_p']/a[2]/span/i")).click();
		}
		size = new Select(driver.findElement(By.id("group_1")));
		size.selectByVisibleText("L");		
		driver.findElement(By.id("color_8")).click();		
		driver.findElement(By.xpath("//span[text()='Add to cart']")).click();	
		
		Thread.sleep(10000);		
		
		driver.findElement(By.xpath("//div[@id='layer_cart']/div/div[2]/div[4]/span/span")).click();
		driver.get(home);
		driver.findElement(By.xpath("(//div[2]/h5/a)[3]")).click();		
		for (int i=0; i<4; i++) {
			driver.findElement(By.xpath("//p[@id='quantity_wanted_p']/a[2]/span/i")).click();			
		}
		size = new Select(driver.findElement(By.id("group_1")));
		size.selectByVisibleText("L");		
		driver.findElement(By.id("color_15")).click();		
		driver.findElement(By.xpath("//span[text()='Add to cart']")).click();	
		
		// 13. Proceed to checkout and complete the payment
		driver.findElement(By.xpath("//div[@id='layer_cart']/div/div[2]/div[4]/a/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Proceed to checkout']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Proceed to checkout']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("cgv")).click();		
		driver.findElement(By.xpath("//form[@id='form']/p/button/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='bankwire']")).click();
		driver.findElement(By.xpath("//span[text()='I confirm my order']")).click();
		
		// 14. Move to your profile and check 'order history and details'
		driver.findElement(By.xpath("//header[@id='header']/div[2]/div/div/nav/div/a/span")).click();
		driver.findElement(By.xpath("//span[text()='Order history and details']")).click();			
		
		// 15. Capture screenshot of the order history
		File theDir = new File(".\\output");
		if (!theDir.exists()) {	
			theDir.mkdirs();
		}
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile , new File(".\\output\\screenshot.png"));
		
		// 16. Sign out from the application
		driver.findElement(By.linkText("Sign out")).click();
					
		// Close the web driver		
		driver.close();
		driver.quit();
	}
}
