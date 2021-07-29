package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import models.CartComponent;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage {
    private final static String URL = "http://automationpractice.com/index.php?controller=order";
    private final static String CART_ITEM_COMPONENT = ".cart_item";
    private final static String CART_ITEM_NAME = "//td[@class='cart_product']//img";
    private final static String CART_ITEM_PRICE = "//span[@class='price']/span[@class='price'] | //span[@class='price']/span[@class='price special-price']";
    private final static String CART_ITEM_DESCRIPTION = "//td[@class='cart_description']//p[@class='product-name']";
    private final static String CART_ITEM_QUANTITY = ".cart_quantity_input";
    private final static String CART_ITEM_DELETE = ".icon-trash";
    private final static String TERMS_OF_SERVICE = "#cgv";
    private final static String PAY_BY_BANK_WIRE = ".bankwire";
    private final static String PAY_BY_CHEQUE = ".cheque";
    private final static String ORDER_CONFIRMATION = ".alert alert-success";
    private final static String PROCEED_TO_CHECKOUT_SUMMARY = ".standard-checkout";
    private final static By PROCEED_TO_CHECKOUT_ADDRESS = By.name("processAddress");
    private final static By PROCEED_TO_CHECKOUT_SHIPPING = By.name("processCarrier");
    private final static By PROCEED_TO_CHECKOUT_PAYMENT = By.xpath("//*[@class='button btn btn-default button-medium']");
    Map<String, CartComponent> cartComponent = new HashMap<>();

    public CartPage openPage() {
        open(URL);
        isPageOpened();
        return this;
    }

    public CartPage isPageOpened() {
        $(PAGE_VALIDATION).waitUntil(Condition.visible, 30000);
        return this;
    }

    public CartPage initializeAllProductsInCart() {
        List<SelenideElement> cartProductsName = $$(By.xpath(CART_ITEM_NAME));
        List<SelenideElement> cartProductPrices = $$(By.xpath(CART_ITEM_PRICE));
        List<SelenideElement> cartDescriptions = $$(By.xpath(CART_ITEM_DESCRIPTION));
        List<SelenideElement> cartQuantities = $$(CART_ITEM_QUANTITY);
        List<SelenideElement> cartDeletes = $$(CART_ITEM_DELETE);
        for (int i = 0; i < $$(CART_ITEM_COMPONENT).size(); i++) {
            cartComponent.put(cartDescriptions.get(i).getText() + " " + cartProductPrices.get(i).getText(),
                    new CartComponent(
                            cartDescriptions.get(i),
                            cartProductsName.get(i),
                            cartProductPrices.get(i),
                            cartQuantities.get(i),
                            cartDeletes.get(i)));
        }
        return this;
    }

    public CartPage setQuantityValue(String productNameAndPrice, String setQuantityValue) {
        cartComponent.get(productNameAndPrice).quantity.setValue(setQuantityValue);
        return this;
    }

    public CartPage deleteFromCart(String productNameAndPrice) {
        cartComponent.get(productNameAndPrice).delete.click();
        initializeAllProductsInCart();
        return this;
    }

    public CartPage checkoutAndConfirm() {
        isPageOpened();
        $(PROCEED_TO_CHECKOUT_SUMMARY).click();
        $(PROCEED_TO_CHECKOUT_ADDRESS).click();
        $(TERMS_OF_SERVICE).click();
        $(TERMS_OF_SERVICE).shouldBe(checked);
        $(PROCEED_TO_CHECKOUT_SHIPPING).click();
        $(PAY_BY_BANK_WIRE).click();
        $(PROCEED_TO_CHECKOUT_PAYMENT).click();
        return this;
    }

    public CartPage validateNumberOfProducts(int number) {
        Assert.assertEquals($$(CART_ITEM_COMPONENT).size(),
                number, "Number of products is invalid");
        return this;
    }
}