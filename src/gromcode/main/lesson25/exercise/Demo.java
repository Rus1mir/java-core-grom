package gromcode.main.lesson25.exercise;

public class Demo {
    public static void main(String[] args) throws Exception{
        GeneralDao generalDao = new GeneralDao() ;

        Order order = new Order(1);
        generalDao.validate(order);

        TestClass<String, Order, Long> testClass = new TestClass<>();
        System.out.println(testClass.doSomething("rrr"));

        int test = 100;
        long variable = 111;
        Long variable2 = new Long(222);
        generalDao.validate(test);
    }
}
