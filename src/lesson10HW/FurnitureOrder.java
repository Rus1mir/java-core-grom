package lesson10HW;

import java.util.Date;

public class FurnitureOrder extends Order {
    private String furnitureCode;

    public FurnitureOrder(String itemName, Date dateCreated, String shipFromCity, String shipToCity, int basePrice, Customer customerOwned, String furnitureCode) {
        super(itemName, dateCreated, shipFromCity, shipToCity, basePrice, customerOwned);
        this.furnitureCode = furnitureCode;
    }

    @Override
    public void validateOrder() {
        if (isCityValidFrom() && (getTotalPrice() >= 500) &&
                (!getCustomerOwned().getName().equals("Test"))) {
            setDateConfirmed(new Date());
        }
    }

    private boolean isCityValidFrom() {
        String[] validCities = {"Киев", "Львов"};
        return isCityValid(getShipFromCity(), validCities);
    }

    private boolean isCityValid(String city, String[] validCities) {
        for (String validCity : validCities) {
            if ((city != null) && (validCity.equals(city))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void calculatePrice() {
        double priceWithShipping = calcShippingCost() + (double) getBasePrice();
        setTotalPrice(priceWithShipping);
    }

    private double calcShippingCost() {
        if (getBasePrice() < 5000) {
            return (double) getBasePrice() * 0.05;
        }
        return (double) getBasePrice() * 0.02;
    }
}
