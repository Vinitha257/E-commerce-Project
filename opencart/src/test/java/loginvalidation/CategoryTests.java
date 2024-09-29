package loginvalidation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class CategoryTests {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://demo.opencart.com/");
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

    
    
    @Test(priority = 2)
    public void testCategoriesVisibilityAndClickability() {
        // Categories that need to expand
        String[] expandableCategories = {"Laptops & Notebooks", "Components", "MP3 Players"};
        // Other categories that navigate to a new page
        String[] navigableCategories = {"Desktops", "Tablets", "Software", "Phones & PDAs"};

     

        for (String category : navigableCategories) {
            checkCategoryNavigation(category);
        }
        
        for (String category : expandableCategories) {
            checkCategoryExpansion(category);
        }
    }

    private void checkCategoryExpansion(String categoryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement category = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(categoryName)));

        Assert.assertTrue(category.isDisplayed(), categoryName + " is not displayed.");

        // Click the category to expand it
        category.click();

        // Wait for the models to be visible (you might need to adjust this selector)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), '" + categoryName + "')]/following-sibling::div")));

        // Optionally, check that the expanded models are displayed (this will depend on your DOM structure)
        Assert.assertTrue(driver.findElements(By.xpath("//h2[contains(text(), '" + categoryName + "')]/following-sibling::div")).size() > 0, 
            "No items displayed for " + categoryName);
    }

    private void checkCategoryNavigation(String categoryName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement category = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(categoryName)));

        Assert.assertTrue(category.isDisplayed(), categoryName + " is not displayed.");

        // Click the category to navigate
        category.click();

        // Wait for the title to confirm navigation
        wait.until(ExpectedConditions.titleContains(categoryName));

        // Verify successful navigation to the category page
        Assert.assertTrue(driver.getTitle().contains(categoryName), "Failed to navigate to " + categoryName);

        // Navigate back to the homepage for the next check
        driver.navigate().back();
    }
}
