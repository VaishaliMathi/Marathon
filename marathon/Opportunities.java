package marathon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Opportunities {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
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
		driver.findElement(By.xpath("//lightning-button[contains(@class,'slds-button')]/button")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));        
		//Click on Opportunity tab 
		WebElement click = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		driver.executeScript("arguments[0].click();", click);       
		//Click on New button
		driver.findElement(By.xpath("//div[@title='New']")).click();       
		//Enter 'your name' as account name
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("Vaishali");
		driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("Vaishali");
		driver.findElement(By.xpath("(//lightning-base-combobox-formatted-text[@title='Vaishali'])[1]")).click();
		//choose close date
		driver.findElement(By.xpath("//input[@name='CloseDate']")).click();
		driver.findElement(By.xpath("//td[@aria-current='date']/span")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));  
		//click stage(Needs Analysis)
		driver.findElement(By.xpath("//button[@data-value='--None--']")).click();                              
		driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();	
		String attribute = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]/a")).getAttribute("title");
		System.out.println(attribute);
		if (attribute.contains("Vaishali")) {
			System.out.println("verfied");
		}else {
			System.out.println("not verfied"); }
	}

}


