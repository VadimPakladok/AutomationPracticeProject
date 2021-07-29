package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import models.WishlistComponent;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class MyWishlistPage extends BasePage {
    private final static String URL = "http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist";
    private final static String WISHLIST_VALIDATION_AFTER_VIEW = ".wishlistLinkTop";
    private final static String SEARCH = "#name";
    private final static String SAVE_BUTTON = "#submitWishlist";
    private final static By ALL_ITEM_WISHLIST = By.xpath("//table[@class='table table-bordered']//tbody/tr");
    private final static String WISHLIST_NAME = "//td[1]/a";
    private final static String WISHLIST_QUANTITY = "//td[2]";
    private final static String WISHLIST_CREATED = "//td[4]";
    private final static By WISHLIST_DIRECT_LINK = By.partialLinkText("View");
    private final static By WISHLIST_DELETE = By.xpath("//td[@class='wishlist_delete']/a");
    private final static By VALIDATE_NUMBER_OF_PRODUCTS = By.xpath("//ul[@class='row wlp_bought_list']//div[@class='row']");
    private final static Map<String, WishlistComponent> wishlistComponent = new HashMap<>();

    public MyWishlistPage openPage() {
        open(URL);
        isPageOpened();
        initializeAllProductsInWishlist();
        return this;
    }

    public MyWishlistPage isPageOpened() {
        $(PAGE_VALIDATION).waitUntil(Condition.visible, 30000);
        return this;
    }

    public MyWishlistPage initializeAllProductsInWishlist() {
        List<SelenideElement> wishlistName = $$(By.xpath(WISHLIST_NAME));
        List<SelenideElement> wishlistQuantity = $$(By.xpath(WISHLIST_QUANTITY));
        List<SelenideElement> wishlistCreated = $$(By.xpath(WISHLIST_CREATED));
        List<SelenideElement> wishlistDirectLink = $$(WISHLIST_DIRECT_LINK);
        List<SelenideElement> wishlistDelete = $$(WISHLIST_DELETE);
        for (int i = 0; i < $$(ALL_ITEM_WISHLIST).size(); i++) {
            wishlistComponent.put(wishlistName.get(i).getText(),
                    new WishlistComponent(
                            wishlistName.get(i),
                            wishlistQuantity.get(i),
                            wishlistCreated.get(i),
                            wishlistDirectLink.get(i),
                            wishlistDelete.get(i)));
        }
        return this;
    }

    public MyWishlistPage deleteWishlist(String wishlistName) {
        wishlistComponent.get(wishlistName).delete.click();
        confirm("Do you really want to delete this wishlist ?");
        initializeAllProductsInWishlist();
        return this;
    }

    public MyWishlistPage addNewWishlist(String addNewWishlist) {
        $(SEARCH).sendKeys(addNewWishlist);
        $(SAVE_BUTTON).click();
        isPageOpened();
        initializeAllProductsInWishlist();
        return this;
    }

    public MyWishlistPage clickOnView(String wishlistName) {
        wishlistComponent.get(wishlistName).direct_link.click();
        $(WISHLIST_VALIDATION_AFTER_VIEW).waitUntil(Condition.visible, 30000);
        return this;
    }

    public MyWishlistPage validateNumberOfProductsInTheView(int number) {
        Assert.assertEquals($$(VALIDATE_NUMBER_OF_PRODUCTS).size(),
                number, "Number of products is invalid");
        return this;
    }
}