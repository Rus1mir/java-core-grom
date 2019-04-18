package lesson35.controller;

import lesson35.service.OrderService;

public class OrderController {

    private OrderService orderService = new OrderService();

    public void bookRoom(long roomId, long userId, long hotelId) throws Exception {

        orderService.bookRoom(roomId, userId, hotelId);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {

        orderService.cancelReservation(roomId, userId);
    }
}
