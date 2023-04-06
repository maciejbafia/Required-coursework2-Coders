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

        homePage.clickSignInBtn(); 
        logInPage.loginToAccount("takpachniestozlotych@mail.pl","testtest");
        homePage.findItem("Hummingbird Printed Sweater");
        Assert.assertTrue(searchResultPage.isDiscountDisplayed());
        Assert.assertEquals(searchResultPage.getDiscountBtn(),"-20%");
        searchResultPage.clickSweater();
        productPage.selectSizeQuantityAndCheckout("s",5);
        shoppingCartPage.proceedToCheckout();
        Assert.assertEquals(yourAddressesPage.getAliasInformation(),"Dominik");
        Assert.assertEquals(yourAddressesPage.getUsersInformation(),"Domino Jachas\n" + "Dywizjonu 303\n" + "Krakow\n" + "90210\n" + "United Kingdom\n" + "7777");
    }

    @AfterTest()
    public void tearDown(){
        driver.quit();
    }
}


//będzie logować się na tego stworzonego użytkownika,
//wejdzie klikając w kafelek Addresses po zalogowaniu (adres, na którym powinniśmy się znaleźć to: https://mystore-testlab.coderslab.pl/index.php?controller=addresses ),
//kliknie w + Create new address,
//wypełni formularz New address - dane powinny być pobierane z tabeli Examples w Gherkinie (alias, address, city, zip/postal code, country, phone),
//sprawdzi czy dane w dodanym adresie są poprawne.
//usunie powyższy adres klikając w "delete",
//sprawdzi czy adres został usunięty.
