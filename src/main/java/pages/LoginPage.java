package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends pages.InitialPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "email")
    WebElement email;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath ="//button[@type='submit']")
    WebElement loginbutton;



    public void userLogin(String mail, String passwd) {

        setElementText(email, mail);
        setElementText(password, passwd);
        clickButton(loginbutton);

    }
}
