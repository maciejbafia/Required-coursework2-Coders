import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;


public class OrderTest {

    private static WebDriver driver;
    String url ="https://mystore-testlab.coderslab.pl/index.php";



    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void orderTest(){
        HomePage homePage = new HomePage(driver);
        LogInPage logInPage = new LogInPage(driver);
        ProductPage productPage = new ProductPage(driver);
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        YourAddressesPage yourAddressesPage = new YourAddressesPage(driver);

        homePage.clickSignInBtn(); //zaloguje się na tego samego użytkownika z zadania 1,
        logInPage.loginToAccount("takpachniestozlotych@mail.pl","testtest");
        homePage.findItem("Hummingbird Printed Sweater");
        Assert.assertTrue(searchResultPage.isDiscountDisplayed());
        Assert.assertEquals(searchResultPage.getDiscountBtn(),"-20%");
        searchResultPage.clickSweater();
        productPage.selectSizeQuantityAndCheckout("s",5);//wybierze rozmiar M (opcja dodatkowa: zrób tak żeby można było sparametryzować rozmiar i wybrać S,M,L,XL),//wybierze 5 sztuk według parametru podanego w teście (opcja dodatkowa: zrób tak żeby można było sparametryzować liczbę sztuk),//dodaj produkt do koszyka,//przejdzie do opcji - checkout,
        shoppingCartPage.proceedToCheckout();
        Assert.assertEquals(yourAddressesPage.getAliasInformation(),"Dominik");
        Assert.assertEquals(yourAddressesPage.getUsersInformation(),"Domino Jachas\n" + "Dywizjonu 303\n" + "Krakow\n" + "90210\n" + "United Kingdom\n" + "7777");
    }

    @AfterTest()
    public void tearDown(){
        driver.quit();
    }
}





//potwierdzi address (możesz go dodać wcześniej ręcznie),
//wybierze metodę odbioru - PrestaShop "pick up in store",
//wybierze opcję płatności - Pay by Check,
//kliknie na "order with an obligation to pay",
//zrobi screenshot z potwierdzeniem zamówienia i kwotą.