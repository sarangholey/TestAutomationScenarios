package AutomationScenarios;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Pagination {

	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://selectorshub.com/xpath-practice-page/");
		
		while(true)
		{
			if(driver.findElements(By.xpath("//td[text()='Russia']")).size()>0)
			{
				try {
					selectCountry("Russia");
					System.out.println("Check Box Selected");
				}
				catch(StaleElementReferenceException e)
				{
					selectCountry("Russia");
					System.out.println("Check Box Selected");
				}
				
				break;
			}
			else
			{
				WebElement nextBtn = driver.findElement(By.xpath("//button[@type='button' and @role='link' and contains(text(),'›')]"));
				if(nextBtn.getAttribute("class").contains("disabled"))
				{
					System.out.println("Pagination Ended.... No Data Found");
					break;
				}
				nextBtn.click();
				Thread.sleep(1000);
			}
		}
	}
	
	public static void selectCountry(String CityName)
	{
		driver.findElement(By.xpath("//td[text()='"+CityName+"']/preceding-sibling::td/child::input[@type='checkbox']"))
		.click();
	}

}
