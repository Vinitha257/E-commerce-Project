package opencartwebsite;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;




public class Locators {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demo.opencart.com/");
		driver.manage().window().maximize();
		
		
		//name
		driver.findElement(By.name("email")).sendKeys("Kiran123");
		
	}
	

}
