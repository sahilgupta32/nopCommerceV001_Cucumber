package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Steps extends BaseClass{

    @Before
    public void setup() throws IOException {

        logger= Logger.getLogger("nopCommerce");
        PropertyConfigurator.configure("log4j.properties");

        //reading properties
        configProp = new Properties();
        FileInputStream configPropFile = new FileInputStream("config.properties");
        configProp.load(configPropFile);

        String br=configProp.getProperty("browser");

        if(br.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }
        else if(br.equalsIgnoreCase("firefox")) {

            System.setProperty("webdriver.chrome.driver", configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        }
        else if(br.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("firefoxpath"));
            driver = new InternetExplorerDriver();
        }
        logger.info("**** Launching Browser ****");
    }

    @Given("User Launch Chrome Browser")
    public void user_launch_chrome_browser() {

        lp=new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @When("User Open URL {string}")
    public void user_open_url(String url) {

        logger.info("**** Opening URL ****");
        driver.get(url);
    }

    @When("User enter email as {string} and password as {string}")
    public void user_enter_email_as_and_password_as(String email, String password) {
        logger.info("**** Providing Login Details ****");
        lp.setUserName(email);
        lp.setPassword(password);
    }

    @When("Click on Login")
    public void click_on_login() {
        logger.info("**** Started Logging ****");
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) {

        if(driver.getPageSource().contains("Login was unsuccessful."))
        {
            driver.close();
            logger.info("**** Logging Passed ****");
            Assert.assertTrue(false);
        }
        else
        {
            logger.info("**** Logging Failed ****");
            Assert.assertEquals(title, driver.getTitle());
        }

    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
        lp.clickLogout();
        Thread.sleep(5000);
    }

    @Then("Close Browser")
    public void close_browser(){
        driver.close();
    }



//Customer feature step definitions
@Then("User can view Dashboard")
public void user_can_view_dashboard() {
    addCust=new AddCustomerPage(driver);
    Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle() );


}
@When("User click on customers Menu")
public void user_click_on_customers_menu() {
        addCust.clickOnCustomersMenu();

}
@When("click on customers Menu Item")
public void click_on_customers_menu_item() {
    addCust.clickOnCustomersMenuItems();

}
@When("click on Add new button")
public void click_on_add_new_button() {
        addCust.clickOnAddNew();

}
@Then("User can view Add new Customer Page")
public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Customers / nopCommerce administration", addCust.getPageTitle());

}
@When("User enter customer info")
public void user_enter_customer_info() {
        String email=randomString()+"@gmail.com";
    addCust.setEmail(email);
    addCust.setAdminContent("test123");
    addCust.setCustomerRole("admin");
    addCust.setManagerOfVendor("Vendor 2");
    addCust.setGender("Male");
    addCust.setFirstName("John");
    addCust.setLastName("Smith");
    addCust.setCompanyName("John Smith");
    addCust.setAdminContent("This is for testing..");


}
@When("click on save button")
public void click_on_save_button() throws InterruptedException {
        addCust.clickSave();
        Thread.sleep(5000);

}
@Then("User can view Confirmation message {string}")
public void user_can_view_confirmation_message(String msg) {

        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully."));
}
//Steps for searching customer using email id
    @When("Enter Customer Email")
    public void enter_customer_email() {
searchCustomer=new SearchCustomerPage(driver);
searchCustomer.setEmail("steve_gates@nopCommerce.com");

    }
    @When("Click on Search Button")
    public void click_on_search_button() throws InterruptedException {
        searchCustomer.ClickSearch();
        Thread.sleep(5000);

    }
    @Then("User should found Email in the Search Table")
    public void user_should_found_email_in_the_search_table() {

        boolean status=searchCustomer.searchCustomerByEmail("steve_gates@nopCommerce.com");
        Assert.assertEquals(true, status);
    }

    //Steps for searching customer using FirstName & LastName
    @When("Enter Customer FirstName")
    public void enter_customer_first_name() {
        searchCustomer=new SearchCustomerPage(driver);
        searchCustomer.setFirstName("John");
    }
    @When("Enter Customer LastName")
    public void enter_customer_last_name() {
        searchCustomer.setLastName("Doe");
    }

    @Then("User should found Name in the Search Table")
    public void user_should_found_name_in_the_search_table() {

       boolean status=searchCustomer.searchCustomerByName("John Doe");
       Assert.assertEquals(true, status);
    }

}
