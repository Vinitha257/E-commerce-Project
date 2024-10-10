package opencartwebsite;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebDriver;

public class titlevalidation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//Opencart title validation
		
	//ChromeDriver driver=new ChromeDriver();

	//	WebDriver driver=new ChromeDriver();
		WebDriver driver=new EdgeDriver();
		driver.get("https://demo.opencart.com/");
//		driver.wait(10);
		driver.manage().window().maximize();
		
		String act_title=driver.getTitle();
		
		if(act_title.equals("Your Store"))
		{
			System.out.println("Test Passed");
			
		}
		else
		{
			System.out.println("Test Failed");
		}

		driver.quit();
}
}