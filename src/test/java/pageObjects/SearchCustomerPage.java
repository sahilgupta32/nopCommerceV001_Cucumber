package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.List;

public class SearchCustomerPage {

    public WebDriver ldriver;

    WaitHelper waithelper;

    public SearchCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
        waithelper = new WaitHelper(ldriver);
    }

    @FindBy(how = How.ID, using="SearchEMail")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(how = How.ID, using="SearchFirstName")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(how = How.ID, using="SearchLastName")
    @CacheLookup
    WebElement txtLastName;

    @FindBy(how = How.ID, using="SearchMonthOfBirth")
    @CacheLookup
    WebElement drpdobMonth;

    @FindBy(how = How.ID, using="SearchDayOfBirth")
    @CacheLookup
    WebElement drpdobDay;

    @FindBy(how = How.ID, using="SearchCompany")
    @CacheLookup
    WebElement txtCompany;

    @FindBy(how = How.XPATH, using="//ul[@class='select2-selection__rendered']")
    @CacheLookup
    WebElement txtcustomerRoles;

    @FindBy(how = How.XPATH, using="//li[@id='select2-SelectedCustomerRoleIds-result-qmfc-1']")
    @CacheLookup
    WebElement lstitemAdministrators;

    @FindBy(how = How.XPATH, using="//li[@id='select2-SelectedCustomerRoleIds-result-rxq5-4']")
    @CacheLookup
    WebElement lstitemGuest;

    @FindBy(how = How.ID, using="search-customers")
    @CacheLookup
    WebElement btnSearch;

    @FindBy(how = How.XPATH, using="//table[@role='grid']")
    @CacheLookup
    WebElement tblSearchResults;

    @FindBy(how = How.XPATH, using="//table[@id='customers-grid']")
    @CacheLookup
    WebElement table;

    @FindBy(how = How.XPATH, using="//table[@id='customers-grid']//body/tr")
    @CacheLookup
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using="//table[@id='customers-grid']//body/tr/td")
    @CacheLookup
    List<WebElement> tableColumns;

    public void setEmail(String email) {
        waithelper.waitForElement(txtEmail, 30);
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setFirstName(String fname) {
        waithelper.waitForElement(txtFirstName, 30);
        txtFirstName.clear();
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lName) {
        waithelper.waitForElement(txtLastName, 30);
        txtLastName.clear();
        txtLastName.sendKeys(lName);
    }

    public void ClickSearch(){
        waithelper.waitForElement(btnSearch, 30);
        btnSearch.click();
    }
    public int getnoOfRows(){
        return (tableRows.size());
    }

    public int getnoOfColumns(){
        return (tableColumns.size());
    }

    public boolean searchCustomerByEmail(String email){

        boolean flag=false;

        for(int i=1;i<=getnoOfRows();i++){
            String emailID=table.findElement(By.xpath("//table[@id='customers-grid']//body/tr["+i+"]/td[2]")).getText();

            System.out.println(emailID);
            if(emailID.equals("steve_gates@nopCommerce.com")){
                flag=true;
            }

        }
        return flag;

    }

    public boolean searchCustomerByName(String Name){

        boolean flag=false;

        for(int i=1;i<=getnoOfRows();i++){
            String name=table.findElement(By.xpath("//table[@id='customers-grid']//body/tr["+i+"]/td[2]")).getText();

            String names[]=name.split(" ");

            if(names[0].equals("John") && names[1].equals("Doe")){
                flag=true;
            }

        }
        return flag;

    }



}
