//wybierze 5 sztuk według parametru podanego w teście
// (opcja dodatkowa: zrób tak żeby można było sparametryzować liczbę sztuk),
//dodaj produkt do koszyka,
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Locale;

public class ProductPage {
    public ProductPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@class='form-control form-control-select']")
    WebElement sizeSelector;

    @FindBy(xpath = "//button[@class='btn btn-primary add-to-cart']")
    WebElement addToCartBtn;

    @FindBy(id = "quantity_wanted")
    WebElement itemQuantity;

    @FindBy(xpath = "//a[@class='btn btn-primary'][contains(text(),'Proceed to checkout')]")
    WebElement proceedToCheckoutBtn;

    @FindBy(xpath = "//i[@class='material-icons touchspin-up']")
    WebElement quantityUpBtn;

    public void addMore(int howMany){
        for(int i = 0; i < howMany -1;i++){
            quantityUpBtn.click();
        }
    }
    //dodaj produkt do koszyka,
    public void addToCart(){
        addToCartBtn.click();
    }
    ////przejdzie do opcji - checkout,
    public void proceedToCheckout(){
        proceedToCheckoutBtn.click();
    }

    //wybierze 5 sztuk według parametru podanego w teście (opcja dodatkowa: zrób tak żeby można było sparametryzować liczbę sztuk),
    public void selectQuantityAndAddToCart(int howMany){
        addMore(howMany);
        addToCartBtn.click();
    }
    //wybierze rozmiar M (opcja dodatkowa: zrób tak żeby można było sparametryzować rozmiar i wybrać S,M,L,XL),
    public void selectSize(String size){
        Select select = new Select(sizeSelector);
        switch (size.toUpperCase()){
            case "S":
            select.selectByValue("1");
                break;
            case "M":
                select.selectByValue("2");
                break;
            case "L":
                select.selectByValue("3");
                break;
            case "XL":
                select.selectByValue("4");
                break;
            default:
                throw new IllegalArgumentException("Available size - S, M, L, XL");
                }

        }

        public void selectSizeQuantityAndCheckout(String size, int quantityToAdd){
        selectSize(size);
        selectQuantityAndAddToCart(quantityToAdd);
        proceedToCheckout();
        }

    }

