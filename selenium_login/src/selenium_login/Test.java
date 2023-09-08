package selenium_login;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
//Importing required classes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

//Main class
public class Test {

	// Main driver method
	public static void main(String[] args) {

		// Path of chrome driver
		// that will be local directory path passed
		System.setProperty("webdriver.chrome.driver", "F:\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// URL of the login website that is tested
		String url = "file:///F:/Training_Project/login.html";

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		System.out.println("Selecting the gender");
		driver.findElement(By.id("male")).click();

		System.out.println("Choosing the food preference");
		driver.findElement(By.id("veg")).click();

		WebElement countryDropdown = driver.findElement(By.id("countries"));
		Select dropdown = new Select(countryDropdown);
		System.out.println("Choosing country from dropdown menu");
		dropdown.selectByValue("india");

		WebElement login = driver.findElement(By.id("signin-button"));
		System.out.println("Clicking on the login element in the main page");
		login.click();

		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		WebElement email = driver.findElement(By.id("username"));

		WebElement password = driver.findElement(By.id("password"));

		email.clear();
		System.out.println("Entering the email");
		email.sendKeys("himanshu@email.com");

		password.clear();
		System.out.println("entering the password");
		password.sendKeys("password@123");

		WebElement gender = driver.findElement(By.id("male"));
		boolean selectState = gender.isSelected();
		if (selectState == false) {
			gender.click();
		}

		WebElement food = driver.findElement(By.id("veg"));
		boolean isSelected = food.isSelected();
		if (isSelected == false) {
			food.click();
		}

		WebElement country = dropdown.getFirstSelectedOption();
		String selectedoption = country.getText();
		System.out.println(selectedoption);

		if (selectedoption == "India") {
			System.out.println("Correct option");
		}

		WebElement loginButton = driver.findElement(By.id("signin-button"));
		Actions builder = new Actions(driver);
		builder.moveToElement(email).perform();

		System.out.println("Clicking login button");
		loginButton.click();

		String title = "Welcome - LambdaTest";

		String actualTitle = driver.getTitle();

		System.out.println("Verifying the page title has started");
		Assert.assertEquals(actualTitle, title);

		System.out.println("The page title has been successfully verified");

		System.out.println("User logged in successfully");

		driver.quit();

	}
}