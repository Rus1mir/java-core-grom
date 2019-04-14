package lesson35.repository;

import lesson35.exception.DataFormatErrorException;
import lesson35.model.Order;

import java.util.Random;

public class OrderRepository extends GeneralRepo<Order> {

    private UserRepository userRepo = new UserRepository();
    private RoomRepository roomRepo = new RoomRepository();

    public OrderRepository() {
        super.path = "C:/javaExercises/project/OrderDb.txt";
    }

    private final String PATH = "D:/OrderDb.txt";

    public Order addOrder(Order order) throws Exception {

        order.setId(Math.abs(new Random().nextLong()));
        return save(order);
    }

    public void deleteOrder(Long orderId) throws Exception{
        deleteRecordById(orderId);
    }

    @Override
    protected Order createObjFromFields(String[] fields) throws Exception {
        //validateFields(fields);
        try {
            return new Order(Long.parseLong(fields[0]),
                    userRepo.getObjectById(Long.parseLong(fields[1])),
                    roomRepo.getObjectById(Long.parseLong(fields[2])),
                    DATE_FORMAT.parse(fields[3]),
                    DATE_FORMAT.parse(fields[4]),
                    Double.parseDouble(fields[5]));

        } catch (NumberFormatException e) {
            throw new DataFormatErrorException("Wrong Id field format detected");
        }
    }

    @Override
    protected void validateFields(String[] fields) throws Exception {

        for(String f : fields) {
            if (f.trim().equals(""))
                throw new DataFormatErrorException("Empty fields detected");
        }
    }
}
