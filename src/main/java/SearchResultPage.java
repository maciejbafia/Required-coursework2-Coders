import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
    public SearchResultPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//li[@class='product-flag discount'][contains(text(),'')]")
    WebElement discountBtn;
    @FindBy(xpath = "//a[@class='thumbnail product-thumbnail']")
    WebElement hummingbirdPrintedSweater;

    //(opcja dodatkowa: sprawdzi czy rabat na niego wynosi 20%)
    public boolean isDiscountDisplayed(String value){
        boolean isDisplayed = false;
        if(discountBtn.isDisplayed() && discountBtn.getText().contains(value)){
            isDisplayed = true;
        }
        return isDisplayed;
    }
    public void clickSweater(){
        hummingbirdPrintedSweater.click();
    }

    public void checkDiscountAndContinue(String value){
        isDiscountDisplayed(value);
        clickSweater();
    }

}

