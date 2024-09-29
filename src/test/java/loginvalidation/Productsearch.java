package loginvalidation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Productsearch {
	
	
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
	        public void loginTest() throws InterruptedException {
	            driver.get("https://demo.opencart.com/index.php?route=account/login");

	            // Enter email
	            WebElement emailField = driver.findElement(By.id("input-email"));
	            emailField.sendKeys("kirandoe@example.com");  // Use the registered account email

	            // Enter password
	            WebElement passwordField = driver.findElement(By.id("input-password"));
	            passwordField.sendKeys("password345");

	            
	            Thread.sleep(2000);        

	            // Click on Login button
	            WebElement loginButton = driver.findElement(By.xpath("//button[.='Login']"));
	            loginButton.click();
	            Thread.sleep(7000);        

	        }

	        @Test(priority = 3)
	        public void searchAndPurchaseProduct() throws InterruptedException {
	            // Wait until login is completed
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        //    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));

	            // Search for a product
	            WebElement searchBox = driver.findElement(By.name("search"));
	            searchBox.sendKeys("iPhone");
	            searchBox.sendKeys(Keys.ENTER);
	            
	            Thread.sleep(3000);        


	            // Click on the first product in the search results
	       //     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-layout")));
	            WebElement firstProduct = driver.findElement(By.xpath("//a[.='iPhone']"));
	            
	            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
	            Thread.sleep(7000);        

	         //   firstProduct.click();
	            firstProduct.click();	            
	            Thread.sleep(3000);        


	            // Add product to cart
	            WebElement addToCartButton = driver.findElement(By.id("button-cart"));
	            Thread.sleep(3000);        
	            addToCartButton.click();	     
	            Thread.sleep(3000);        

	            // Go to the shopping cart
	            WebElement cartLink = driver.findElement(By.linkText("Shopping Cart"));
	            Thread.sleep(3000);        
	            cartLink.click();
	            Thread.sleep(3000);        

	            // Optionally, proceed to checkout here
	            WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Checkout")));
	            checkoutButton.click();
	        }
	        
	        @Test(priority = 4)
	        public void fillPersonalDetailsAndPlaceOrder() throws InterruptedException {
	            // Wait for the personal details page to load
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-firstname")));
	            Thread.sleep(3000);        

	            // Fill in personal details
	            driver.findElement(By.id("input-firstname")).sendKeys("John");
	            driver.findElement(By.id("input-lastname")).sendKeys("Doe");
	            driver.findElement(By.id("input-email")).sendKeys("kirans@example.com"); // Ensure this email is unique
	  //          driver.findElement(By.id("input-telephone")).sendKeys("1234567890");

	            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);"); // Adjust the scroll value as needed

	            
	            // Fill in address details
	            driver.findElement(By.name("shipping_address_1")).sendKeys("123 Main St");
	            driver.findElement(By.name("shipping_city")).sendKeys("New York");
	            
	            
	         // Select the country
	            WebElement countryDropdown = driver.findElement(By.xpath("//select[@name='shipping_country_id']"));
	            Thread.sleep(5000); // Allow the next page to load
	            countryDropdown.click();
	            Thread.sleep(5000); // Allow the next page to load
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[contains(text(),'United States')]"))); // Adjust if needed
	            countryDropdown.findElement(By.xpath("//option[contains(text(),'United States')]")).click(); // Change as needed

	            // Select a region (you may need to adjust based on available options)
	            WebElement regionDropdown = driver.findElement(By.xpath("//select[@name='shipping_zone_id']"));
	            Thread.sleep(5000); // Allow the next page to load
	            regionDropdown.click();
	            Thread.sleep(5000); // Allow the next page to load
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[contains(text(),'New York')]"))); // Adjust if needed
	            regionDropdown.findElement(By.xpath("//option[contains(text(),'New York')]")).click(); // Adjust the state/region as necessary

	            driver.findElement(By.id("input-shipping-postcode")).sendKeys("10001");
	            Thread.sleep(3000);        
	            
	            
	            // Enter password
	            driver.findElement(By.id("input-password")).sendKeys("password345"); // Enter the desired password
	        //    driver.findElement(By.id("input-confirm")).sendKeys("password123"); // Confirm the password


	            
	            WebElement agreeCheckbox = driver.findElement(By.name("agree"));
	            Thread.sleep(500); 
	            // Click the checkbox only if it's not already selected
	            if (!agreeCheckbox.isSelected()) {
	                try {
	                    agreeCheckbox.click();
	                } catch (Exception e) {
	                    // Retry clicking if it fails
	                    System.out.println("Initial click failed, retrying...");
	                    Thread.sleep(500); 
	                    agreeCheckbox.click(); // Retry click
	                }
	            }
	            
	            // Click Continue to proceed to the shipping method
	            driver.findElement(By.xpath("//button[.='Continue']")).click();
	            Thread.sleep(3000); // Allow the next page to load

	            // Choose a shipping method (if necessary)
	            // For the demo site, you may not need to select an option here, just proceed
	            driver.findElement(By.xpath("//input[@value='Continue']")).click();
	            Thread.sleep(3000); // Allow the next page to load

	            // Choose a payment method (if necessary)
	            // For the demo site, you may not need to select an option here, just proceed
	            driver.findElement(By.xpath("//input[@value='Continue']")).click();
	            Thread.sleep(3000); // Allow the next page to load

	            // Confirm the order
	            driver.findElement(By.xpath("//input[@value='Confirm Order']")).click();
	            
	            // Wait for the confirmation message
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));

	            // Check for success message (can be an assert or just a print statement)
	            if (driver.findElements(By.cssSelector(".alert-success")).size() > 0) {
	                System.out.println("Order placed successfully!");
	            } else {
	                System.out.println("Order placement failed!");
	            }
	       
	        
	        
	        }
	        @AfterClass
	        public void tearDown() {
	            // Close the browser
	            if (driver != null) {
	                driver.quit();
	            }
	        }
	    
	        
	        
	    }
	    
	    
	    


