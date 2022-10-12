package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.utils.FileUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBus {

	public static void main(String[] args) throws InterruptedException, IOException {
//	01) Launch Firefox / Chrome
		WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.redbus.in");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//input[@id='src']")).sendKeys("Chennai");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='dest']")).sendKeys("Bangalore");
        Thread.sleep(2000);
        
        driver.findElement(By.id("onward_cal")).click();
        
        driver.findElement(By.xpath("//table[@class='rb-monthTable first last']/tbody/tr[4]/td[5]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id='search_btn']")).click();
//	07) Print the number of buses shown as results (104 Buses found)
       String buses = driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
        System.out.println(buses);
//	08) Choose SLEEPER in the left menu 
        WebElement findElement = driver.findElement(By.xpath("//label[@title='SLEEPER']"));
        driver.executeScript("arguments[0].click();" ,findElement);
        Thread.sleep(2000);
//	09) Print the name of the second resulting bus 
        String text = driver.findElement(By.xpath("(//div[@class='travels lh-24 f-bold d-color'])[2]")).getText();
        System.out.println("The bus name is "+text);
//	10) Click the VIEW SEATS of that bus
        Thread.sleep(2000);
        WebElement click = driver.findElement(By.xpath("(//div[text()='View Seats'])[2]"));
        driver.executeScript("arguments[0].click();" ,click);
//	11) Take screenshot and close browser
        File source = driver.getScreenshotAs(OutputType.FILE);
		File destin=new File("./snapsh/redbus.png");
		FileUtils.copyFile(source, destin);

	}

}
