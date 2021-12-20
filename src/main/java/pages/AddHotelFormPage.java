package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddHotelFormPage extends pages.InitialPage {
    public AddHotelFormPage(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }
    @FindBy(name = "hotelname")
    WebElement hotelname;

    @FindBy(css =  "html")
    WebElement hoteldesc;

    @FindBy(css = "#mapaddress")
    WebElement mapAddress;

    @FindBy(css = "#latitude")
    WebElement mapLatitude;

    @FindBy(css = "#longitude")
    WebElement mapLongitude;

    @FindBy(css = "span[class='select2-arrow'] b")
    WebElement locList;

    @FindBy(xpath = "//input[@placeholder='B2C Markup']")
    WebElement B2CMarkup;

    @FindBy(xpath = "//input[@placeholder='B2B Markup']")
    WebElement B2BMarkup;

    @FindBy(xpath = "//input[@placeholder='B2E Markup']")
    WebElement B2EMarkup;

    @FindBy(xpath = "//input[@placeholder='Service Fee']")
    WebElement serviceFee;

    @FindBy(xpath = "//input[@name='depositvalue']")
    WebElement depositValue;

    @FindBy(xpath = "//input[@name='taxvalue']")
    WebElement taxValue;
    //2nd tab
    @FindBy(xpath = "//a[normalize-space()='Facilities']")
    WebElement FacilitiesTab;

    @FindBy(xpath = "//label[normalize-space()='Select All']")
    WebElement selectAllFacilities;

    //3rd tab
    @FindBy(xpath = "//a[normalize-space()='Meta Info']")
    WebElement metaInfoTab;

    @FindBy(xpath = "//input[@name='hotelmetatitle']" )
    WebElement metaTitle;

    @FindBy(xpath = "//textarea[@name='hotelkeywords']")
    WebElement metaKeywords;

    @FindBy(xpath = "//textarea[@name='hotelmetadesc']")
    WebElement metaDesc;

    //4th tab
    @FindBy(xpath = "//a[normalize-space()='Policy']")
    WebElement policyTab;

    @FindBy(xpath = "//input[@placeholder='Check In']")
    WebElement checkinTime;

    @FindBy(xpath = "//input[@placeholder='Check Out']")
    WebElement checkoutTime;

    @FindBy(css = ".select2-choices")
    WebElement paymentList;

    @FindBy(css = "option[value='27']")
    WebElement paymentOption;

    @FindBy(xpath = "//textarea[@name='hotelpolicy']")
    WebElement policyTerms;

    @FindBy(xpath = "(//a[@title='Edit'])[1]")
    WebElement editHotelInfobtn;

    @FindBy(xpath = "//button[@id='add']")
    WebElement submitBtn;

    @FindBy(xpath = "//*[@id='update']")
    WebElement updateSubmitBtn;

    @FindBy(xpath = "//a[@href='https://phptravels.net/api/admin/logout']")
    WebElement logoutbtn;


    public void FillHotelForm1stTab(String hotelname, String hoteldesc, String mapAddress, String mapLatitude, String mapLongitude, String location, String B2CMarkup, String B2BMarkup, String B2EMarkup, String serviceFee, String depositValue, String taxValue) {
        PickLocation(location);
        setElementText(this.hotelname, hotelname);
        WriteHotelDesc(hoteldesc);
        //clickButton(this.hoteldesc);
//        setElementText(this.hoteldesc, hoteldesc);
        setElementText(this.mapAddress, mapAddress);
        setElementText(this.mapLatitude, mapLatitude);
        setElementText(this.mapLongitude, mapLongitude);
        setElementText(this.B2CMarkup, B2CMarkup);
        setElementText(this.B2BMarkup, B2BMarkup);
        setElementText(this.B2EMarkup, B2EMarkup);
        setElementText(this.serviceFee, serviceFee);
        setElementText(this.depositValue, depositValue);
        setElementText(this.taxValue, taxValue);
        sleep(5);

    }

    public void FillHotelForm2ndTab() {
        scroll("scrollBy(0,0)");

        waiting(FacilitiesTab);
        selectCheckboxItem(FacilitiesTab);
        waiting(selectAllFacilities);
        selectCheckboxItem(selectAllFacilities);
        sleep(5);

    }

    public void FillHotelForm3rdTab(String metaTitle, String metaKeywords, String metaDesc){
        clickButton(metaInfoTab);
        setElementText(this.metaTitle, metaTitle);
        setElementText(this.metaKeywords, metaKeywords);
        setElementText(this.metaDesc, metaDesc);
        sleep(5);
    }

    public void FillHotelForm4thTab(String checkinTime, String checkoutTime, String policyTerms) {

        clickButton(policyTab);
        setElementText(this.checkinTime, checkinTime);
        setElementText(this.checkoutTime, checkoutTime);
        PickPaymentMethod();
        setElementText(this.policyTerms, policyTerms);
        clickButton(policyTab);//to remove ui pickers
        sleep(5);


    }

    private void WriteHotelDesc(String desc) {

        driver.switchTo().frame(0);
        WebElement body = driver.findElement(By.xpath("//body"));
        body.click();
        js.executeScript("arguments[0].innerHTML = '"+ desc+"'", body);
        driver.switchTo().defaultContent();

    }

    private void PickLocation(String location) {
        selectCheckboxItem(locList);
        sleep(2);
        String subContainerClass = "#select2-drop:not([style*='display: none'])";
        WebElement searchBox = driver.findElement(By.cssSelector(subContainerClass + " .select2-input"));
        searchBox.sendKeys(location);
        WebElement selectedItem = driver.findElements(By.cssSelector(subContainerClass + " .select2-results li.select2-result-selectable")).get(0);
        selectedItem.click();
    }

    private void PickPaymentMethod() {
        paymentList.click();
        waiting(paymentOption);
        paymentOption.click();
    }

    public void SubmitHotelForm() {

        waiting(submitBtn);
        (submitBtn).click();
        sleep(10);
    }
    public void UpdateSubmitHotelForm() {

        scrolltoElement(updateSubmitBtn);
        clickButton(updateSubmitBtn);
        sleep(10);
    }

    public void EditHotelForm(String hotelname) {
        clickButton(editHotelInfobtn);
        sleep(5);
        overwriteElementText(this.hotelname, hotelname);

        UpdateSubmitHotelForm();
    }


    public void Logout() {
        sleep(10);
        clickButton(logoutbtn);
    }



}
