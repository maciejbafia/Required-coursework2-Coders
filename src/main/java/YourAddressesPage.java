import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;

////wybierze metodę odbioru - PrestaShop "pick up in store",
////wybierze opcję płatności - Pay by Check,
////kliknie na "order with an obligation to pay",
public class YourAddressesPage {
    public YourAddressesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@name='confirm-addresses'][contains(text(),'Continue')]")
    WebElement continueBtn;

    @FindBy(xpath = "//span[@class='address-alias h4']")
    WebElement aliasInformation;

    @FindBy(xpath = "//div[@class='address']")
    WebElement usersInformation;

    public String getUsersInformation(){
        return usersInformation.getText();
    }

    ////potwierdzi address (możesz go dodać wcześniej ręcznie),
    public String getAliasInformation() {
        return aliasInformation.getText();
    }

    public void acceptAddress() {
        continueBtn.click();
    }



    public boolean check(String alias, String name, String address, String city, String postalCode, String country, String phoneNumber) {
        String[] providedInformation = {alias, name, address, city, postalCode, country, phoneNumber};
        String[] pageInformation = {getAliasInformation()};
        return Arrays.equals(providedInformation, pageInformation);

    }

}


















