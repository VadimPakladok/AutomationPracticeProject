package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderHistoryPage extends BasePage {
    private final static String URL = "http://automationpractice.com/index.php?controller=history";

    public OrderHistoryPage openPage() {
        open(URL);
        isPageOpened();
        return this;
    }

    public OrderHistoryPage isPageOpened() {
        $(PAGE_VALIDATION).waitUntil(Condition.visible, 30000);
        return this;
    }
}