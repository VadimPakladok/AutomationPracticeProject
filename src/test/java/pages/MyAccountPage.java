package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MyAccountPage extends BasePage {
    private final static String URL = "http://automationpractice.com/index.php?controller=my-account";
    private final static By ORDER_HISTORY_AND_DETAILS = By.className("icon-list-ol");
    private final static By MY_WISHLIST = By.className("icon-heart");
    private final static String LOG_OUT = ".logout";

    public MyAccountPage openPage() {
        open(URL);
        isPageOpened();
        return this;
    }

    public MyAccountPage isPageOpened() {
        $(PAGE_VALIDATION).waitUntil(Condition.visible, 30000);
        return this;
    }

    public OrderHistoryPage goToOrderHistoryAndDetails() {
        isPageOpened();
        $(ORDER_HISTORY_AND_DETAILS).shouldBe(Condition.visible).click();
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage();
        orderHistoryPage.isPageOpened();
        return orderHistoryPage;
    }

    public MyWishlistPage goToMyWishlistPage() {
        isPageOpened();
        $(MY_WISHLIST).shouldBe(Condition.visible).click();
        MyWishlistPage myWishlistPage = new MyWishlistPage();
        myWishlistPage.isPageOpened();
        return myWishlistPage;
    }

    public LoginPage logoutFromAccount() {
        isPageOpened();
        $(LOG_OUT).click();
        LoginPage loginPage = new LoginPage();
        loginPage.isPageOpened();
        return loginPage;
    }
}