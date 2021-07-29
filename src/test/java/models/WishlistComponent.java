package models;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WishlistComponent {
    public SelenideElement name;
    public SelenideElement quantity;
    public SelenideElement created;
    public SelenideElement direct_link;
    public SelenideElement delete;
}