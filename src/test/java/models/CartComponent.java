package models;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CartComponent {
    public SelenideElement name;
    public SelenideElement price;
    public SelenideElement description;
    public SelenideElement quantity;
    public SelenideElement delete;
}