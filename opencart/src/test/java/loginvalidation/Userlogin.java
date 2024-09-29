package loginvalidation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;


public class Userlogin {
	
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
    public void registerTest() throws InterruptedException {
        driver.get("https://demo.opencart.com/index.php?route=account/register");

        // Fill out registration form
        driver.findElement(By.id("input-firstname")).sendKeys("Kiran");
        driver.findElement(By.id("input-lastname")).sendKeys("Doe");
        driver.findElement(By.id("input-email")).sendKeys("kirandoe@example.com"); // Ensure this email is unique
  //      driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("password345");
  //      driver.findElement(By.id("input-confirm")).sendKeys("password123");

    
        WebElement agreeCheckbox = driver.findElement(By.name("agree"));

        // Scroll into view to ensure it's visible
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", agreeCheckbox);
        
        Thread.sleep(500); // Optional short wait before retry


        // Click the checkbox only if it's not already selected
        if (!agreeCheckbox.isSelected()) {
            try {
                agreeCheckbox.click();
            } catch (Exception e) {
                // Retry clicking if it fails
                System.out.println("Initial click failed, retrying...");
                Thread.sleep(500); // Optional short wait before retry
                agreeCheckbox.click(); // Retry click
            }
        }
       
        Thread.sleep(3000); 
        
        
        try {
            Thread.sleep(500); // Sleep for half a second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the Continue button
        WebElement continueButton = null;
        try {
            continueButton = driver.findElement(By.xpath("//button[.='Continue']"));
        } catch (Exception e) {
            System.out.println("Element not found using XPath, trying CSS selector.");
            continueButton = driver.findElement(By.cssSelector("input[value='Continue']")); // Alternative locator
        }

        // Scroll into view to ensure it's visible
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
        try {
            continueButton.click();
        } catch (Exception e) {
            // Retry clicking if it fails
            System.out.println("Initial click failed, retrying...");
            Thread.sleep(500); // Optional short wait before retry
            continueButton.click(); // Retry click
        }

        
        
        
        

        // Click on the Continue button
  //      driver.findElement(By.xpath("//input[contains(text(),'Continue')]")).click();
        Thread.sleep(5000);        


    }

    @Test(priority = 2)
    public void loginTest() throws InterruptedException {
        driver.get("https://demo.opencart.com/index.php?route=account/login");

        // Enter email
        WebElement emailField = driver.findElement(By.id("input-email"));
        emailField.sendKeys("johndoe@example.com");  // Use the registered account email

        // Enter password
        WebElement passwordField = driver.findElement(By.id("input-password"));
        passwordField.sendKeys("password123");

        
        Thread.sleep(2000);        

        // Click on Login button
        WebElement loginButton = driver.findElement(By.xpath("//button[.='Login']"));
        loginButton.click();
        Thread.sleep(2000);        

    }

    @Test(priority = 3)
    public void searchAndPurchaseProduct() {
        // Wait until login is completed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));

        // Search for a product
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("iPhone");
        searchBox.submit();

        // Click on the first product in the search results
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-layout")));
        WebElement firstProduct = driver.findElement(By.cssSelector(".product-layout .product-thumb:nth-child(1) a"));
        firstProduct.click();

        // Add product to cart
        WebElement addToCartButton = driver.findElement(By.id("button-cart"));
        addToCartButton.click();

        // Go to the shopping cart
        WebElement cartLink = driver.findElement(By.linkText("Shopping Cart"));
        cartLink.click();

        // Optionally, proceed to checkout here
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Checkout")));
        checkoutButton.click();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}