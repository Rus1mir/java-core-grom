package lesson35.service;

import lesson35.exception.AccessDeniedExeption;
import lesson35.repository.OrderRepository;
import lesson35.repository.UserRepository;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public void bookRoom(long roomId, long userId, long hotelId) throws Exception{

        if (!UserRepository.isLogined())
            throw new AccessDeniedExeption("Action not permitted for unknown users, please login");

        //TODO create logic
    }

    public void cancelReservation(long roomId, long userId) throws Exception{

        if (!UserRepository.isLogined())
            throw new AccessDeniedExeption("Action not permitted for unknown users, please login");

        //TODO create logic
    }
}
