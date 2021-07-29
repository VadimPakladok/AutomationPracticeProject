package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    MyWishlistPage myWishlistPage;
    RegistrationPage registrationPage;
    MyAccountPage myAccountPage;

    @BeforeMethod(description = "Opening Chrome browser")
    public void setupBrowser() {
        Configuration.headless = true;
        Configuration.startMaximized = true;
        Configuration.timeout = 20000;
        Configuration.browser = "chrome";
        Configuration.clickViaJs = false;
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        myWishlistPage = new MyWishlistPage();
        registrationPage = new RegistrationPage();
        myAccountPage = new MyAccountPage();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        getWebDriver().quit();
    }
}