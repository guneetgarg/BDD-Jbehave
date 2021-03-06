package stepDefinition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Test_Steps {
	public static WebDriver driver;

	@Given("^User is on Home Page \"(.*)\"")
	public void user_is_on_Home_Page(String url) throws Throwable {

		System.setProperty("webdriver.chrome.driver", "Drivers_executable/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
	}

	@When("^User Navigate to LogIn Page$")
	public void user_Navigate_to_LogIn_Page() throws Throwable {
		driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	}

	@When("^User enters \"(.*)\" and \"(.*)\"$")
	public void user_enters_UserName_and_Password(String username, String password) throws Throwable {
		driver.findElement(By.id("log")).sendKeys(username);
		driver.findElement(By.id("pwd")).sendKeys(password);
		driver.findElement(By.id("login")).click();
	}

	@When("^User enters Credentials to LogIn$")
	public void user_enters_UserName_and_Password(DataTable arg1) {
		List<List<String>> data = arg1.raw();
		driver.findElement(By.id("log")).sendKeys(data.get(0).get(0));
		driver.findElement(By.id("pwd")).sendKeys(data.get(0).get(1));

		driver.findElement(By.id("login")).click();
	}

	@When("^User enters Credentials to LogIn1$")
	public void user_enters_UserName_and_Password1(DataTable arg1) {
		List<Map<String, String>> data = arg1.asMaps(String.class, String.class);
		driver.findElement(By.id("log")).sendKeys(data.get(0).get("Username"));
		driver.findElement(By.id("pwd")).sendKeys(data.get(0).get("Password"));

		driver.findElement(By.id("login")).click();
	}

	@Then("^Message displayed Login Successfully$")
	public void message_displayed_Login_Successfully() throws Throwable {
		System.out.println("Login Successfully");
	}

	@When("^User LogOut from the Application$")
	public void user_LogOut_from_the_Application() throws Throwable {
		driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();
	}

	@Then("^Message displayed LogOut Successfully$")
	public void message_displayed_Logout_Successfully() throws Throwable {
		System.out.println("LogOut Successfully");
	}

	// MAP Data Table Multiple Test Data
	@When("^User enters Credentials to LogIn2$")
	public void user_enters_testuser_and_Test(DataTable usercredentials) throws Throwable {
		for (Map<String, String> data : usercredentials.asMaps(String.class, String.class)) {
			driver.findElement(By.id("log")).sendKeys(data.get("Username"));
			driver.findElement(By.id("pwd")).sendKeys(data.get("Password"));
			driver.findElement(By.id("login")).click();
		}
	}
	
	//class object
	@When("^User enters Credentials to LogIn4$")
	public void user_enters_testuser_and_Test(List<Credentials>  usercredentials) throws Throwable {
 
		//Write the code to handle Data Table
		for (Credentials credentials : usercredentials) {			
			driver.findElement(By.id("log")).sendKeys(credentials.getUsername()); 
		    driver.findElement(By.id("pwd")).sendKeys(credentials.getPassword());
		    driver.findElement(By.id("login")).click();
			}		
	}
	
}