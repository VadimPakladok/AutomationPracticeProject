package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import models.ProductComponent;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InventoryPage extends BasePage {
    private final static String URL = "http://automationpractice.com/index.php?id_category=3&controller=category";
    private final static By SORT_LIST = By.className("icon-th-list");
    private final static By CART_LAYER = By.id("layer_cart");
    private final static String ITEM_COMPONENT = ".product-container";
    private final static String ITEM_ADD_TO_CART = ".ajax_add_to_cart_button";
    private final static String ITEM_NAME = "//ul[@class='product_list row list']//a[@class='product-name']";
    private final static String ITEM_PRICE = "//div[@class='right-block-content row']//span[@class='price product-price']";
    private final static String ITEM_MORE = ".lnk_view";
    private final static String ITEM_WISHLIST = ".wishlist";
    private final static String DELETE_ITEM_FROM_CART = ".ajax_cart_block_remove_link";
    private final static String CLOSE_WISHLIST_POP_UP = ".fancybox-close";
    private final static By PRODUCT_VALIDATION_BY_TEXT = By.xpath("//div[@class='clearfix']//i[@class='icon-ok']/..");
    private final static By PRODUCT_VALIDATION_BY_ICON = By.xpath("//div[@class='clearfix']//i[@class='icon-ok']");
    Map<String, ProductComponent> productComponent = new HashMap<>();

    public InventoryPage openPage() {
        open(URL);
        isPageOpened();
        return this;
    }

    public InventoryPage isPageOpened() {
        $(PAGE_VALIDATION).waitUntil(Condition.visible, 30000);
        return this;
    }

    public InventoryPage initializeAllProducts() {
        List<SelenideElement> productsName = $$(By.xpath(ITEM_NAME));
        List<SelenideElement> productsPrices = $$(By.xpath(ITEM_PRICE));
        List<SelenideElement> productAddToCart = $$(ITEM_ADD_TO_CART);
        List<SelenideElement> productMore = $$(ITEM_MORE);
        List<SelenideElement> productWishlist = $$(ITEM_WISHLIST);
        for (int i = 0; i < $$(ITEM_COMPONENT).size(); i++) {
            productComponent.put(productsName.get(i).getText() + " " + productsPrices.get(i).getText(),
                    new ProductComponent(
                            productsPrices.get(i),
                            productsName.get(i),
                            productAddToCart.get(i),
                            productMore.get(i),
                            productWishlist.get(i)));
        }
        return this;
    }

    public InventoryPage addToCart(String productNameAndPrice) {
        productComponent.get(productNameAndPrice).addToCart.click();
        return this;
    }

    public InventoryPage addToWishlist(String productPrice) {
        productComponent.get(productPrice).addToWishlist.click();
        $(CLOSE_WISHLIST_POP_UP).click();
        return this;
    }

    public InventoryPage clickMore(String productPrice) {
        productComponent.get(productPrice).more.click();
        return this;
    }

    public InventoryPage continueShopping() {
        $(CART_LAYER).waitUntil(Condition.visible, 30000);
        $(CONTINUE_SHOPPING).click();
        isPageOpened();
        return this;
    }

    public CartPage proceedToCheckout() {
        $(CART_LAYER).waitUntil(Condition.visible, 30000);
        $(PROCEED_TO_CHECKOUT).click();
        CartPage cartPage = new CartPage();
        cartPage.isPageOpened();
        return cartPage;
    }

    public InventoryPage listViewOfProducts() {
        isPageOpened();
        $(SORT_LIST).click();
        return this;
    }
    public InventoryPage verifyProductAddedToCart() {
        assertTrue($(PRODUCT_VALIDATION_BY_ICON).waitUntil(Condition.visible, 20000).isDisplayed(), "No icon is displayed");
        assertTrue($(PRODUCT_VALIDATION_BY_TEXT).waitUntil(Condition.visible, 20000).isDisplayed(), "No text is displayed");
        assertEquals($(PRODUCT_VALIDATION_BY_TEXT).getText(), "Product successfully added to your shopping cart", "Message is not correct");
        return this;
    }
}