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

import com.github.dockerjava.api.command.SearchImagesCmd;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BookMyShow {

	public static void main(String[] args) throws InterruptedException, IOException {
	//	01) Launch Edge / Chrome
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
	//02) Load  
		driver.get("https://in.bookmyshow.com/");
		System.out.println(driver.getCurrentUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	//03) Type the city as "Hyderabad" in Select City
		driver.findElement(By.xpath("//input[@placeholder='Search for your city']")).sendKeys("Hyderabad");
		driver.findElement(By.xpath("//span[@class='sc-fihHvN fUfZof']")).click();

	//04) Confirm the URL has got loaded with Hyderabad 
		System.out.println(driver.getCurrentUrl());
	//05) Search for your favorite movie 
		WebElement search = driver.findElement(By.xpath("//div[@class='sc-bZQynM sc-dEoRIm hSiMIq']"));
		search.click();
		WebElement film = driver.findElement(By.xpath("//input[@class='sc-jvEmr elijMA']"));
		film.sendKeys("GodFather");
		film.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("(//span[@class='sc-hdPSEv kzaUOq'])[1]")).click();
		Thread.sleep(2000);
//	06) Click Book Tickets
		driver.findElement(By.xpath("(//span[text()='Book tickets'])[1]")).click();
		driver.findElement(By.xpath("(//div[@class='sc-vhz3gb-3 dRokFO'])[2]")).click();
		Thread.sleep(5000);
//	07) Print the name of the theater displayed first
		driver.findElement(By.id("wzrk-cancel")).click();
		String text = driver.findElement(By.xpath("(//a[@class='__venue-name'])[2]")).getText();
		System.out.println("The theatre name is "+text);
//	08) Click on the info of the theater
		driver.findElement(By.xpath("(//div[@class='venue-info-text'])[2]")).click();
//	09) Confirm if there is a parking facility in the theater
		String text2 = driver.findElement(By.xpath("(//div[@class='facility-text'])[4]")).getText();
		System.out.println(text2);
		if(text2.contains("Parking")) {
			System.out.println("parking facility is available");
		}
		else {
			System.out.println("parking facility is not available");
		}
//	10) Close the theater popup
		driver.findElement(By.xpath("//div[@class='cross-container']")).click();
		
//	11) Click on the first green (available) timing
		driver.findElement(By.xpath("(//a[@data-venue-code='CPHY'])[3]")).click();
		
//	12) Click Accept
		driver.findElement(By.id("btnPopupAccept")).click();
		Thread.sleep(5000);
//	13) Choose 1 Seat and Click Select Seats
		driver.findElement(By.id("pop_1")).click();//li[@id='pop_1']
		driver.findElement(By.id("proceed-Qty")).click();//div[@id='proceed-Qty']
////	14) Choose any available ticket and Click Pay
		driver.findElement(By.xpath("(//a[@class='_available'])[1]")).click();
		driver.findElement(By.xpath("(//a[@id='btmcntbook'])[1]")).click();
//	15) Print the sub total
		Thread.sleep(5000);
		String text3 = driver.findElement(By.xpath("//span[@id='subTT']")).getText();
		System.out.println("The subtotal is "+ text3);
		
//	16) Take screenshot and close browser
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destin=new File("./snapsh/film.png");
		FileUtils.copyFile(source, destin);
		Thread.sleep(5000);
		driver.close();

	}

}
