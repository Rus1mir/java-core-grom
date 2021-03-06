package lesson35.service;

import lesson35.exception.AccessDeniedExeption;
import lesson35.exception.BadRequestException;
import lesson35.model.*;
import lesson35.repository.*;

import java.util.*;
;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    public void bookRoom(long roomId, long userId, long hotelId) throws Exception {

        User user = UserRepository.getLoginUser();

        RoomRepository roomRepository = new RoomRepository();

        if (user == null || user.getId() != userId)
            throw new AccessDeniedExeption("Action not permitted for user id " + userId + ", cause this user is not login");

        Room room = roomRepository.getObjectByID(roomId);

        if (room == null)
            throw new BadRequestException("Room id: " + roomId + " was not found in database, booking filed");

        if (room.getHotel().getId() != hotelId)
            throw new BadRequestException("In request of booking the id of hotel id: " + hotelId +
                    " and field 'hotel' in requested room id: " + roomId + "is not match, booking filed");

        Date dateBookingFrom = new Date();
        int daysAmount = 1;
        //For example one day order)

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateBookingFrom);
        calendar.add(Calendar.DAY_OF_MONTH, daysAmount);
        Date dateBookingTo = calendar.getTime();

        if (room.getDateAvailableFrom().after(dateBookingFrom))
            throw new BadRequestException("Requested room id: " + roomId + " is already booked for requested date!");

        Order order = new Order(-1, user, room,
                dateBookingFrom, dateBookingTo, room.getPrice() * daysAmount);

        roomRepository.changeAvailableDate(roomId, dateBookingTo);

        orderRepository.addObjectToDb(order);
    }

    public void cancelReservation(long roomId, long userId) throws Exception {

        User user = UserRepository.getLoginUser();

        RoomRepository roomRepository = new RoomRepository();

        if (user == null || user.getId() != userId)
            throw new AccessDeniedExeption("Action not permitted for user id " + userId + ", cause this user is not login");

        Room room = roomRepository.getObjectByID(roomId);

        if (room == null)
            throw new BadRequestException("Room id: " + roomId + " was not found in database, cancel reservation filed");

        for (Order ord : orderRepository.getAllObjectsFromDb()) {

            if (ord.getUser().getId() == userId && ord.getRoom().getId() == roomId) {

                roomRepository.changeAvailableDate(roomId, new Date());

                orderRepository.deleteObjectById(ord.getId());
                return;
            }
        }
        throw new BadRequestException("Order with requested room id: " + roomId + " or (and) with requested user id:" +
                userId + " was not found, cancel reservation filed");
    }
}
