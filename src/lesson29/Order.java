package lesson29;

import java.util.Objects;

public class Order {
    private int price;
    private String currency;
    private String itemName;
    private String shopIdentificator;

    public Order(int price, String currency, String itemName, String shopIdentificator) {
        this.price = price;
        this.currency = currency;
        this.itemName = itemName;
        this.shopIdentificator = shopIdentificator;
    }

    public int getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getItemName() {
        return itemName;
    }

    public String getShopIdentificator() {
        return shopIdentificator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return price == order.price &&
                Objects.equals(currency, order.currency) &&
                Objects.equals(itemName, order.itemName) &&
                Objects.equals(shopIdentificator, order.shopIdentificator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, currency, itemName, shopIdentificator);
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", currency='" + currency + '\'' +
                ", itemName='" + itemName + '\'' +
                ", shopIdentificator='" + shopIdentificator + '\'' +
                '}';
    }
}
