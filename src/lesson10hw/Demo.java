package lesson10hw;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        testCalcPriceElectronicsOrder();
        testValidateOrderElectronicsOrder();
        testCalcPriceFurnitureOrder();
        testValidateOrderFurnitureOrder();
        try {
            testConfirmShippingOrder();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void processOrder(Order order) {
        order.calculatePrice();
        order.validateOrder();
        order.confirmShipping();
        System.out.println(order.getCustomerOwned().getName());
        System.out.println(order.getItemName());
        System.out.println(order.getBasePrice());
        System.out.println(order.getTotalPrice());
        System.out.println(order.getDateConfirmed());
        System.out.println(order.getDateShipped());
        System.out.println();
    }

    private static void oldDemo() {

        System.out.println();

        Customer customer = new Customer("Тест", "Киев", "Мужской");
        Customer customer1 = new Customer("Натали", "Киев", "Женский");

        Order[] orders = new Order[4];

        orders[0] = new ElectronicsOrder("Something", new Date(),
                "Киев", "Киев", 200, customer, 12);
        orders[1] = new ElectronicsOrder("Something else", new Date(),
                "Киев", "Днепр", 100, customer1, 12);

        orders[2] = new FurnitureOrder("Something furniture", new Date(),
                "Киев", "Винница", 1000, customer, "2");
        orders[3] = new FurnitureOrder("Something else details", new Date(),
                "Киев", "Ташкент", 800, customer1, "12");

        for (Order order : orders) {
            processOrder(order);
        }
    }

    private static void testCalcPriceElectronicsOrder() {
        //Цена может состоит из цены товара и цены за доставку. Так же могут быть скидки.
        //Если доставка производится в любой город, кроме Киева и Одессы, то цена за доставку - 15% от суммы заказа.
        //В других случаях 10%. Так же если цена больше 1000 то скидка на заказ 5% после оплаты комиссии за доставку
        //-----------------
        //посчитать цену, если город доставки и отправки Киев и цена < 1000+
        //посчитать цену, если город доставки и отправки Киев и цена > 1000+
        //посчитать цену, если город доставки не Киев и не Одесса и цена < 1000+
        //посчитать цену, если город отправки не "Киев", "Одесса", "Днепр", "Харьков" и цена < 1000+
        Order[] orders = new Order[4];
        Customer customer = new Customer("Иван", "Киев", "Мужской");
        orders[0] = new ElectronicsOrder("one", new Date(), "Киев", "Киев", 100, customer, 12);
        orders[1] = new ElectronicsOrder("one", new Date(), "Киев", "Киев", 2000, customer, 12);
        orders[2] = new ElectronicsOrder("one", new Date(), "Киев", "Харьков", 200, customer, 12);
        orders[3] = new ElectronicsOrder("one", new Date(), "Винница", "Харьков", 200, customer, 12);

        for (Order order : orders) {
            order.calculatePrice();
            System.out.println(order.getTotalPrice());
        }
    }

    private static void testValidateOrderElectronicsOrder() {
        //заказ возможен с городов: Киев, Одесса, Днепр, Харьков и в один из этих городов.
        //Минимальная цена заказа 100. Так же имя клиента который делает заказ может быть любым, а пол только женским
        //--------------------
        //Валидировать заказ из Киева в Одессу стоимостью больше 100 заказчик женщина+
        //Валидировать заказ из Киева в Одессу стоимостью больше 100 заказчик мужчина+
        //Валидировать заказ из неправильного города в Киев стоимостью больше 100 заказчик женщина+
        //Валидировать заказ из Киева в неправильный город стоимостью больше 100 заказчик женщина+
        //Валидировать заказ из Киева в Днепр стоимостью менее 100 заказчик женщина+
        Order[] orders = new Order[5];
        Customer customerFemale = new Customer("Натали", "Одесса", "Женский");
        Customer customerMale = new Customer("Иван", "Одесса", "Мужской");
        orders[0] = new ElectronicsOrder("one", new Date(), "Киев", "Одесса", 200, customerFemale, 12);
        orders[1] = new ElectronicsOrder("one", new Date(), "Киев", "Одесса", 200, customerMale, 12);
        orders[2] = new ElectronicsOrder("one", new Date(), "Винница", "Одесса", 200, customerFemale, 12);
        orders[3] = new ElectronicsOrder("one", new Date(), "Одесса", "Винница", 200, customerFemale, 12);
        orders[4] = new ElectronicsOrder("one", new Date(), "Киев", "Днепр", 20, customerFemale, 12);

        for (Order order : orders) {
            order.validateOrder();
            System.out.println(order.getDateConfirmed());
        }
    }

    private static void testCalcPriceFurnitureOrder() {
        //Коммисия за доставку - 5% от суммы заказа если сумма меньше 5000 и 2% в других случаях
        //--------------------
        //Посчитать цену при базовой цене меньше 5000+
        //Посчитать цену при базовой цене больше 5000+
        //Посчитать цену при базовой цене 0+
        Order[] orders = new Order[3];
        Customer customer = new Customer("Петр", "Киев", "Мужской");
        orders[0] = new FurnitureOrder("one", new Date(), "Киев", "Киев", 100, customer, "АВС133");
        orders[1] = new FurnitureOrder("one", new Date(), "Киев", "Киев", 8000, customer, "АВС133");
        orders[2] = new FurnitureOrder("one", new Date(), "Киев", "Киев", 0, customer, "АВС133");

        for (Order order : orders) {
            order.calculatePrice();
            System.out.println(order.getTotalPrice());
        }
    }

    private static void testValidateOrderFurnitureOrder() {
        //Заказ возможен с городов: Киев, Львов и в любой город. Минимальная цена заказа 500.
        //Так же имя клиента который делает заказ не может быть "Тест", а пол может быть любым
        //----------------
        //Валидировать заказ из Киева стоимостью больше 500+
        //Валидировать заказ из Киева стоимостью меньше 500+
        //Валидировать заказ из неправильного города стоимостью больше 500+
        //Валидировать заказ из Киева стоимостью больше 500+ имя клиента "Тест"+
        Order[] orders = new Order[4];
        Customer customer = new Customer("Натали", "Одесса", "Женский");
        Customer customerTest = new Customer("Тест", "Одесса", "Мужской");
        orders[0] = new FurnitureOrder("one", new Date(), "Киев", "Одесса", 600, customer, "ff12");
        orders[1] = new FurnitureOrder("one", new Date(), "Киев", "Одесса", 100, customer, "ff12");
        orders[2] = new FurnitureOrder("one", new Date(), "Винница", "Одесса", 600, customer, "ff12");
        orders[3] = new FurnitureOrder("one", new Date(), "Одесса", "Винница", 200, customerTest, "ff12");

        for (Order order : orders) {
            order.validateOrder();
            System.out.println(order.getDateConfirmed());
        }
    }

    private static void testConfirmShippingOrder() throws ParseException {
        //Подтвердить доставку+
        //Подтвердить доставку повторно+

        Customer customer = new Customer("Натали", "Одесса", "Женский");
        Order order = new ElectronicsOrder("one", new Date(), "Киев", "Киев", 100, customer, 12);
        order.confirmShipping();
        System.out.println(order.getDateShipped());
        Date date = new SimpleDateFormat("dd,MM,yy").parse("12,12,2017");
        order.setDateShipped(date);
        System.out.println(order.getDateShipped());
        order.confirmShipping();
        System.out.println(order.getDateShipped());
    }
}
