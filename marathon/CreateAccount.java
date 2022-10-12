package marathon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAccount {

	public static void main(String[] args) throws Throwable {



		//Click view All and click Sales from App Launcher          
		//Click on Account tab                 
		//Click on New button        
		//Enter 'your name' as account name        
		//Select Ownership as Public       
		//click save        
		//verify message
		WebDriverManager.chromedriver().setup();
		ChromeOptions ch=new ChromeOptions();
		ch.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(ch);
		driver.get("https://login.salesforce.com/?locale=in");
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password#123");
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.xpath("//div[@role='navigation']/button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//lightning-button[contains(@class,'slds-button slds')]/button")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement click = driver.findElement(By.xpath("//span[text()='Accounts']"));
		driver.executeScript("arguments[0].click();", click);

		driver.findElement(By.xpath("//a[@title='New']/div")).click();
		driver.findElement(By.xpath("//label[text()='Account Name']/following::input")).sendKeys("Vaishali");
		Thread.sleep(1000);
		WebElement cl = driver.findElement(By.xpath("(//span[text()='--None--'])[3]"));
		driver.executeScript("arguments[0].click();", cl);
		driver.findElement(By.xpath("//span[text()='Public']")).click();
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();	
		String attribute = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]/a")).getAttribute("title");
		System.out.println(attribute);
		if (attribute.contains("Vaishali")) {
			System.out.println("verfied");
		}else {
			System.out.println("not verfied"); }
	}

}
