package lesson35.service;

import lesson35.exception.AccessDeniedExeption;
import lesson35.model.Filter;
import lesson35.model.Room;
import lesson35.repository.RoomRepository;
import lesson35.repository.UserRepository;

import java.util.ArrayList;

public class RoomService {

    private RoomRepository roomRepository = new RoomRepository();

    public ArrayList<Room> findRooms(Filter filter) throws Exception{

        if (!UserRepository.isLogined())
            throw new AccessDeniedExeption("Action not permitted for unknown users, please login");

        return roomRepository.findRoomsByFilter(filter);
    }

    public Room addRoom (Room room) throws Exception{

        if (!UserRepository.isAdmin())
            throw new AccessDeniedExeption("Action not permitted for users without admins writes, please login as Admin");

        return roomRepository.addObjectToDb(room);
    }

    public void deleteRoom (long roomId) throws Exception{

        if (!UserRepository.isAdmin())
            throw new AccessDeniedExeption("Action not permitted for users without admins writes, please login as Admin");

        roomRepository.deleteObjectById(roomId);
    }
}
