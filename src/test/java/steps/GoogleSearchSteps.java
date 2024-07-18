package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.testng.Assert.assertTrue;

public class GoogleSearchSteps {
   public WebDriver driver;

    @Given("User is on Google search page")
    public void user_is_on_google_search_page() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().driverVersion("126.0.6478.183").setup();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.com");
    }
    @When("User types {string}")
    public void user_types(String query) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(query);
    }

    @When("User clicks on search button")
    public void user_clicks_on_search_button() {
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]"));
        searchButton.click();
    }

    @Then("User sees search results")
    public void user_sees_search_results() {
        assertTrue(driver.getTitle().contains("Cucumber - بحث Google"));
        driver.quit();
    }
}
