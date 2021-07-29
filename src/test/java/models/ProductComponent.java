package models;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductComponent {
    public SelenideElement name;
    public SelenideElement price;
    public SelenideElement addToCart;
    public SelenideElement more;
    public SelenideElement addToWishlist;
}
