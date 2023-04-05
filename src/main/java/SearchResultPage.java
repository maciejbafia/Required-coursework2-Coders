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
    public boolean isDiscountDisplayed(){
        return discountBtn.isDisplayed();
    }
    public String getDiscountBtn(){
       return discountBtn.getText();

    }
    public void clickSweater(){
        hummingbirdPrintedSweater.click();
    }



}

