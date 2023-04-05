import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

    public ShoppingCartPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='btn btn-primary'][contains(text(),'Proceed to checkout')]")
    WebElement proceedToCheckoutBtn;

    public void proceedToCheckout(){
        proceedToCheckoutBtn.click();
    }

}
