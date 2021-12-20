package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class NewAdminFuncPage extends pages.InitialPage {
    public NewAdminFuncPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//a[normalize-space()='hotels']")
    WebElement hotelsMenu;

    @FindBy(xpath ="//a[@class='dropdown-submenu-toggle']")
    WebElement HotelsSubMenu;

    @FindBy(css ="a[href='https://phptravels.net/api/admin/hotels']")
    WebElement HotelsItem;


    @FindBy(xpath = "//*[@class='btn btn-success']")
    WebElement addHotelBtn;

    public boolean isHotelsMenuDisplayed() {
        boolean isDisplayed = true;
        try {
            isDisplayed = hotelsMenu.isDisplayed();
        } catch (NoSuchElementException e) {
            isDisplayed = false;
        }
        return isDisplayed;
    }
    public void openHotelsManagementPage() {

        clickButton(hotelsMenu);
        //Instantiating Actions class
        Actions actions = new Actions(driver);

        //Hovering on main menu
        actions.moveToElement(HotelsSubMenu);

        //To mouseover on sub menu
        actions.moveToElement(HotelsItem);
        sleep(5);
        //build()- used to compile all the actions into a single step
        actions.click().build().perform();
    }

    public void openAddHotelPage() {
        clickButton(addHotelBtn);
    }



}
