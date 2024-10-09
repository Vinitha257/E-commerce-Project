package loginvalidation;
	
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
	
	public class Locators {
		
		   private WebDriver driver;

		    @BeforeClass
		    public void setUp() {
		        // Set up ChromeDriver using WebDriverManager
		        WebDriverManager.chromedriver().setup();
		        driver = new ChromeDriver();
		        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		        driver.manage().window().maximize();
		    }
	
		    
@Test(priority = 1)    
	public void Locators() {	    
		    driver.get("https://demo.opencart.com");
			//name
			driver.findElement(By.name("search")).sendKeys("iphone");
			//id
			boolean logodisplay = driver.findElement(By.id("logo")).isDisplayed();
			System.out.println(logodisplay);
			
			//linktext & partiallinktext
			driver.findElement(By.linkText("Tablets")).click();
			driver.findElement(By.partialLinkText("Table")).click();
			
			
			//className - locating group of elements
			List<WebElement> headerlink=driver.findElements(By.className("list-inline-item"));
			System.out.println("total no.of header links:" +headerlink.size());
			
			//tagName - locating group of elements
			List<WebElement> links=driver.findElements(By.tagName("a"));
			System.out.println("total no.of links:" +links.size());
			
			List<WebElement> images=driver.findElements(By.tagName("img"));
			System.out.println("total no.of images:" +images.size());
			
		//CSS - Cascading style sheets	
		//	tagid - tag#id - for id use #
	    //  tagclass tag.classname - for class use .
		//  tag attribute tag[attribute="value"]
		//  tag class attribute  tag.classname[attribute="value"]	
			
		//tag id and tag class name - captured from demonopcommerce
	    //tag#id
		  driver.findElement(By.cssSelector("input#small-searchterms")).sendKeys("T-shirts");
		  driver.findElement(By.cssSelector("#small-searchterms")).sendKeys("T-shirts");

		 // tag.classname
		  driver.findElement(By.cssSelector("input.search-box-text ui-autocomplete-input")).sendKeys("T-shirts");
		  driver.findElement(By.cssSelector(".search-box-text ui-autocomplete-input")).sendKeys("T-shirts");

			
		//tag attribute tag[attribute='value']	
	      driver.findElement(By.cssSelector("input[placeholder='Search']")).sendKeys("iPhone"); //with tag
	      driver.findElement(By.cssSelector("[placeholder='Search']")).sendKeys("iPhone"); //without tag

		// tag class attribute - using this we can locate only one element 
	      driver.findElement(By.cssSelector("input.search-box-text[name='q']")).sendKeys("T-shirts");
	      driver.findElement(By.cssSelector(".search-box-text[name='q']")).sendKeys("T-shirts");

			
			
			
			
}
	}

	



