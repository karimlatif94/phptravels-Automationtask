package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RemoveHotelPage extends pages.InitialPage {
    public RemoveHotelPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "(//a[@title='Remove'])[1]")
    WebElement removeBtn;


    @FindBy(xpath = "//table[@class='xcrud-list table table-striped table-hover']/tbody/tr")
    WebElement hotelsDBRows;

    public void removeLatestHotel(){
        clickButton(removeBtn);
        sleep(5);
        confirmRemoveAlertbox();
        sleep(5);

    }


    private void confirmRemoveAlertbox(){
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
    }

    public boolean isHotelFoundinDB(String hotelname){
        boolean found=false;
        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='xcrud-list table table-striped table-hover']/tbody/tr"));

        for(int i=0; i<rows.size(); i++) {
            WebElement currentRow = rows.get(i);

            if(currentRow.getText().contains(hotelname)) {
                found=true;
                break;
            }
        }
        return found;
    }


}
