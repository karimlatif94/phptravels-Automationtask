package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InitialPage {
    public static JavascriptExecutor js;
    public static WebDriverWait wait ;
    public static Actions actions;
    public WebDriver driver;


    public InitialPage(WebDriver driver) {
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
    public static void clickButton(WebElement button) {
        button.click();
    }
    public static void setElementText (WebElement txtElement , String txt){
        txtElement.sendKeys(txt);
    }
    public static void overwriteElementText (WebElement txtElement , String txt) {
        txtElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), txt); //to overwrite not concatenate
    }
    public static void scroll(String distance) {
        js.executeScript(distance);
    }
    public static void scrolltoElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView()", element);
    }
    public static void waiting(WebElement button) {
        wait.until(ExpectedConditions.elementToBeClickable(button));
    }
    public static void selectItemfromList (WebElement DropdownlstElement , String txt){
        Select drpList = new Select(DropdownlstElement);
        drpList.selectByVisibleText(txt);
    }

    public void selectCheckboxItem (WebElement checkboxElement){
        actions.moveToElement(checkboxElement).click().build().perform();
    }

    public void sleep(int duration_min){
        try {
            Thread.sleep(duration_min*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
