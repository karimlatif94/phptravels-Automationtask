package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class InitialTest {
    public static WebDriver driver;

    @BeforeSuite
    @Parameters({ "browser" })

    public void startDriver(@Optional("chrome") String browserName) {
        if(browserName.equalsIgnoreCase("chrome")){ //currently chrome only used, add other browsers with if conditions if needed

            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("resolution", "1024x768");
        driver.manage().deleteAllCookies();
        driver.navigate().to("https://phptravels.net/api/admin");

        if (driver.getTitle().contains("Unavailable")) {
            System.out.println("PHPTRAVELS website is down");
            stopDriver();
        }
        else
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void stopDriver() {
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        driver.quit();
    }
}
