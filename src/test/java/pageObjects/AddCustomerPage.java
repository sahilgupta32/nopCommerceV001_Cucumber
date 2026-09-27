package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

    public WebDriver ldriver;

    public AddCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    By lnkCustomers_menu=By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
    By getLnkCustomers_menuitem=By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");

    By btnAddnew=By.xpath("//a[@class='btn btn-primary']");

    By txtEmail=By.xpath("//input[@id='Email']");
    By txtPassword=By.xpath("//input[@id='Password']");

    By txtFirstName=By.xpath("//input[@id='FirstName']");
    By txtLastName=By.xpath("//input[@id='LastName']");

    By rdMaleGender=By.xpath("//input[@id='Gender_Male']");
    By rdFemaleGender=By.xpath("//input[@id='Gender_Female']");

    By txtCompanyName=By.xpath("//input[@id='Company']");

    By chkTax=By.xpath("//input[@id='IsTaxExempt']");

    By txtCustomerRole=By.xpath("//ul[@class='select2-selection__rendered']");
    By lstItemAdmin=By.xpath("//li[contains(text(),'Administrators')]");
    By lstItemForumModerators=By.xpath("//li[contains(text(),'Forum Moderators')]");
    By lstItemRegistered=By.xpath("//li[contains(text(),'Registered')]");
    By lstItemGuests=By.xpath("//li[contains(text(),'Guests')]");
    By lstItemVendors=By.xpath("//li[contains(text(),'Vendors')]");

    By drpmgrOfVendor=By.xpath("//span[@id='select2-VendorId-container']");

    By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");

    By btnSave=By.xpath("//button[@name='save']");

    //Action Methods

    public String getPageTitle(){
        return ldriver.getTitle();
    }


    public void clickOnCustomersMenu(){
        ldriver.findElement(lnkCustomers_menu).click();
    }

    public void clickOnCustomersMenuItems(){
        ldriver.findElement(getLnkCustomers_menuitem).click();
    }

    public void clickOnAddNew(){
        ldriver.findElement(btnAddnew).click();
    }
    public void setEmail(String email){
        ldriver.findElement(txtEmail).sendKeys(email);
    }
    public void setPassword(String password){
        ldriver.findElement(txtPassword).sendKeys(password);
    }
    public void setFirstName(String fname){
        ldriver.findElement(txtFirstName).sendKeys(fname);
    }
    public void setLastName(String lname){
        ldriver.findElement(txtLastName).sendKeys(lname);
    }

    public void clickMaleGender(){
        ldriver.findElement(rdMaleGender).click();
    }
    public void clickFemaleGender(){
        ldriver.findElement(rdFemaleGender).click();
    }

    public void setCompanyName(String companyName){
        ldriver.findElement(txtCompanyName).sendKeys(companyName);
    }
    public void setTax(){
        ldriver.findElement(chkTax).click();
    }

    public void setCustomerRole(String role){
        if(!role.equals("Vendors")){
            ldriver.findElement(By.xpath("//span[@role='presentation'][normalize-space()='×']"));
        }
        ldriver.findElement(txtCustomerRole).click();
        WebElement listitem;

        if(role.equals("Administrators")){
            listitem=ldriver.findElement(lstItemAdmin);
        }
        else if (role.equals("Forum Moderators")){
            listitem=ldriver.findElement(lstItemForumModerators);
        }
        else if (role.equals("Guests")){
            listitem=ldriver.findElement(lstItemGuests);
        }
        else if (role.equals("Vendors")) {
            listitem=ldriver.findElement(lstItemVendors);
        }
        else if (role.equals("Registered")) {
            listitem=ldriver.findElement(lstItemRegistered);
        }
        else {
            listitem=ldriver.findElement(lstItemVendors);
        }

        listitem.click();
    }

public void setManagerOfVendor(String value){
        Select drp=new Select(ldriver.findElement(drpmgrOfVendor));
        drp.selectByVisibleText(value);
}

public void setGender(String gender){
        if(gender.equals("Male")){
            ldriver.findElement(rdMaleGender).click();
        }
        else if(gender.equals("Female")){
            ldriver.findElement(rdFemaleGender).click();
        }
        else {
            ldriver.findElement(rdMaleGender).click();
        }
}

public void setAdminContent(String adContent){
        ldriver.findElement(txtAdminContent).sendKeys(adContent);
}

public void clickSave(){
        ldriver.findElement(btnSave).click();
}

}
