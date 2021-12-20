package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends pages.InitialPage { //Dashboard page

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='btn btn-default btn-block'][1]")
    WebElement adminsBtn;

    @FindBy(xpath = "//*[@class='btn btn-success']")
    WebElement addAdminBtn;


    public void openAdminsManagementPage() {
        clickButton(adminsBtn);
    }

    public void openAddAdminPage() {
        clickButton(addAdminBtn);
    }
}
