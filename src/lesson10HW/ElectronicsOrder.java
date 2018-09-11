package lesson10HW;

import java.util.Date;

public class ElectronicsOrder extends Order {
    private int guaranteeMonths;

    public ElectronicsOrder(String itemName, Date dateCreated, String shipFromCity, String shipToCity, int basePrice, Customer customerOwned, int guaranteeMonths) {
        super(itemName, dateCreated, shipFromCity, shipToCity, basePrice, customerOwned);
        this.guaranteeMonths = guaranteeMonths;
    }

    @Override
    public void validateOrder() {
        if (isCityValidFrom() && isCityValidTo() &&
                (getBasePrice() + calcShippingCost()  >= 100) &&
                (getCustomerOwned().getName() != null) &&
                (getCustomerOwned().getGender().equals("Женский"))) {
            setDateConfirmed(new Date());
        }
    }

    private boolean isCityValidTo() {
        String[] validCities = {"Киев", "Одесса", "Днепр", "Харьков"};
        return isCityValid(getShipToCity(), validCities);
    }

    private boolean isCityValidFrom() {
        String[] validCities = {"Киев", "Одесса", "Днепр", "Харьков"};
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
        double priceWithShipping = calcShippingCost() + getBasePrice();
        if (priceWithShipping > 1000) {
            priceWithShipping -= priceWithShipping * 0.05;
        }
        setTotalPrice(priceWithShipping);
    }

    private double calcShippingCost() {
        if (getShipToCity().equals("Киев") || getShipToCity().equals("Одесса")) {
            return (double) getBasePrice() * 0.1;
        }
        return (double) getBasePrice() * 0.15;
    }
}
