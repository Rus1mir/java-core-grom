package lesson17HW.HW4;

public class Solution {
    public static void main(String[] args) {
        String test = "https://gromcode.com/lesson5";
        System.out.println(validate(test));
    }

    //Напишите метод для упрощенной валидации интернет адресса. Название класса не важно
    //Для подобных задач на практике используют regex (регулярные выражения), но это задание необходимо решить используя
    //стандартные возможности класса String. Сигнатура метода: validate(String address)
    //Требования: адресс должен начинаться с названия протокола, допустимые - http:// или https://+, www не обязательно+
    //доменная зона должна разделяться точкой, допустимые - com, org, net
    //другие точки в названии адреса а так же спецчимволы не допускаются. Название класса - Solution

    public static boolean validate(String address) {
        address.replace(".www", "");
        String[] parts = address.split("://|\\.|\\/");
        if (parts.length < 3)
            return false;
        boolean res = true;
        res &= validateCond(parts[0], new String[]{"http", "https"});
        res &= validateStr(parts[1]);
        res &= validateCond(parts[2], new String[]{"com", "org", "net"});
        return res;
    }

    private static boolean validateCond(String input, String[] conditions) {
        for (String cond : conditions) {
            if (input.contains(cond))
                return true;
        }
        return false;
    }

    private static boolean validateStr(String input) {
        for (char symb : input.toCharArray()) {
            if (!Character.isDigit(symb) &&
                    !Character.isLetter(symb))
                return false;
        }
        return true;
    }
}
