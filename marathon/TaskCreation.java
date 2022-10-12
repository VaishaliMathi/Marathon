package marathon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TaskCreation {

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
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("task");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); 
		driver.findElement(By.xpath("//p[@class='slds-truncate']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//a[@title='Show one more action']")).click();
		driver.findElement(By.xpath("//a[@title='New Task']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("Bootcamp");
		driver.findElement(By.xpath("//a[@class='select']")).click();
		driver.findElement(By.xpath("//a[@title='Waiting on someone else']")).click();
		driver.findElement(By.xpath("(//span[@class=' label bBody'])[3]")).click();
		String attribute = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]/a")).getAttribute("title");
		System.out.println(attribute);
		if(attribute.contains("Bootcamp")) 
		{ 
			System.out.println("Verified");
		}
		else { System.out.println("not verified");
		}
	}

}
