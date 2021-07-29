package pages;

import com.codeborne.selenide.Condition;
import models.Account;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {
    private final static String URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private final static By EMAIL_ADDRESS = By.id("email_create");
    private final static By CREATE_ACCOUNT_BUTTON = By.id("SubmitCreate");
    private final static By LOGIN = By.id("email");
    private final static By PASSWORD = By.id("passwd");
    private final static By SIGN_IN = By.id("SubmitLogin");

    public LoginPage openPage() {
        open(URL);
        isPageOpened();
        return this;
    }

    public LoginPage isPageOpened() {
        $(PAGE_VALIDATION).waitUntil(Condition.visible, 30000);
        return this;
    }

    public RegistrationPage openRegistrationPage(String email) {
        isPageOpened();
        $(EMAIL_ADDRESS).sendKeys(email);
        $(CREATE_ACCOUNT_BUTTON).click();
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isPageOpened();
        return registrationPage;
    }

    public MyAccountPage loggingToAccount(Account account) {
        isPageOpened();
        $(LOGIN).sendKeys(account.getEmail());
        $(PASSWORD).sendKeys(account.getPassword());
        $(SIGN_IN).click();
        MyAccountPage myAccountPage = new MyAccountPage();
        myAccountPage.isPageOpened();
        return myAccountPage;
    }
}