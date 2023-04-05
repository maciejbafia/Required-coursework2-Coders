import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//wybierze do zakupu Hummingbird Printed Sweater
public class HomePage {

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//a[contains(@href,'controller=my-account')]")
    WebElement signInBtn;
    @FindBy(xpath = "//input[@name='s']")
    WebElement searchBar;

    public void clickSignInBtn(){
        signInBtn.click();
    }

    public void findItem(String itemName){
        searchBar.sendKeys(itemName);
        searchBar.submit();
    }

}
