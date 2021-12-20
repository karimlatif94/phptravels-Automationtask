package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddAdminFormPage extends pages.InitialPage {
    public AddAdminFormPage(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;
        wait= new WebDriverWait(driver, 60);
        actions = new Actions(driver);
    }

    @FindBy(name = "fname")
    WebElement firstname;

    @FindBy(name = "lname")
    WebElement lastname;

    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(css = "span[class='select2-arrow'] b")//"#select2-drop-mask")//id = "select2-drop-mask")
    WebElement countrylist;

    @FindBy(xpath = "//input[@value='addHotels']")
    WebElement hotelsAddCheckbox;

    @FindBy(xpath = "//input[@value='editHotels']")
    WebElement hotelsEditCheckbox;

    @FindBy(xpath = "//input[@value='deleteHotels']")
    WebElement hotelsDeleteCheckbox;

    @FindBy(xpath = "//button[normalize-space()='Submit']")
    WebElement submitAdminbtn;

    @FindBy(xpath = "//i[@class='fa fa-edit']")
    WebElement editAdminInfobtn;

    @FindBy(xpath = "//a[@href='https://phptravels.net/api/admin/logout']")
    WebElement logoutbtn;

    public void FillAdminForm(String firstname, String lastname, String email, String password, String country) {

        setElementText(this.firstname, firstname);
        setElementText(this.lastname, lastname);
        setElementText(this.email, email);
        setElementText(this.password, password);
        PickCountry(country);
        GrantAdminPermissions();
        SubmitAdminForm();

    }

    private void PickCountry(String country) {
        countrylist.click();
        String subContainerClass = "#select2-drop:not([style*='display: none'])";
        WebElement searchBox = driver.findElement(By.cssSelector(subContainerClass + " .select2-input"));
        searchBox.sendKeys(country);
        WebElement selectedItem = driver.findElements(By.cssSelector(subContainerClass + " .select2-results li.select2-result-selectable")).get(0);
        selectedItem.click();
    }

    public void GrantAdminPermissions() {
        waiting(hotelsAddCheckbox);
        selectCheckboxItem(hotelsAddCheckbox);

        waiting(hotelsEditCheckbox);
        selectCheckboxItem(hotelsEditCheckbox);


        if(hotelsDeleteCheckbox.isSelected()){
            waiting(hotelsDeleteCheckbox);
            selectCheckboxItem(hotelsDeleteCheckbox);
        }

    }
    public void SubmitAdminForm() {
        clickButton(submitAdminbtn);
    }

    public void EditAdminForm(String newemail, String newpassword) {
        clickButton(editAdminInfobtn);
        sleep(10);
        overwriteElementText(email, newemail);
        overwriteElementText(password, newpassword);
        GrantAdminPermissions();
        SubmitAdminForm();
    }
    public void Logout() {
        clickButton(logoutbtn);
    }

}
