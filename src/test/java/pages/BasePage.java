package pages;

import org.openqa.selenium.By;

public abstract class BasePage {
    By PAGE_VALIDATION = By.className("icon-map-marker");
    By PROCEED_TO_CHECKOUT = By.className("button-medium");
    By CONTINUE_SHOPPING = By.className("exclusive-medium");

    public abstract BasePage openPage();

    public abstract BasePage isPageOpened();
}
