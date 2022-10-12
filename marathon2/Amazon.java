package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.utils.FileUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {
//	01) Launch Chrome
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		
//	02) Load https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
//	03) Type "Bags" in the Search box
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Bags");
		Thread.sleep(2000);
//	04) Choose the displayed item in the result with bags for boys
		driver.findElement(By.xpath("//div[@aria-label='bags for boys']")).click();
		
//	05) Print the total number of results (like 30000) 1-48 of over 30,000 results for "bags for boys"
		String text = driver.findElement(By.xpath("(//div[contains(@class,'a-section a-spacing-small')])[1]")).getText();
		System.out.println(text);
		String s="1-48 of over 30,000 results for";
		int length=s.length();
		String[] split=s.split(" ");
		System.out.println(split[3]);
//	06) Select the first 2 brands in the left menu (like American Tourister, Generic)
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-checkbox'])[3]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-checkbox'])[4]")).click();
		Thread.sleep(2000);
//	07) Confirm the results have got reduced like 5000 &30,000     (use step 05 for compare)
	String text2 = driver.findElement(By.xpath("(//div[contains(@class,'a-section a-spacing-small')])[1]")).getText();
	System.out.println(text2);
	String s1="1-48 of over 5,000 results for";
	int length1=s.length();
	String[] split1=s1.split(" ");
	System.out.println(split1[3]);
	if(split[3] != split1[3]) {
		System.out.println("The count is reduced");
	}
//	08) Choose New Arrivals (Sort)
	driver.findElement(By.xpath("//span[text()='Featured']")).click();
	driver.findElement(By.xpath("//a[text()='Newest Arrivals']")).click();
	Thread.sleep(2000);

//	09) Print the first resulting bag info (name, discounted price)
	String text3 = driver.findElement(By.xpath("(//span[contains(@class,'a-size-base-plus a-color')])[2]")).getText();
	System.out.println(text3);
//	10) Take screenshot and close
	driver.findElement(By.xpath("(//img[@class='s-image'])[1]")).click();
	File source = driver.getScreenshotAs(OutputType.FILE);
	File destin=new File("./snapsh/bag.png");
	FileUtils.copyFile(source, destin);
	Thread.sleep(2000);
	Set<String> windowHandles = driver.getWindowHandles();
	List<String> lstwindow=new ArrayList<String>(windowHandles);
	driver.switchTo().window(lstwindow.get(1));
	Thread.sleep(2000);
	String text4 = driver.findElement(By.xpath("//span[contains(@class,'a-size-large a-color-price')]")).getText();
	System.out.println(text4);
	driver.close();
	}

}
