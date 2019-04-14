package lesson35.repository;

import lesson35.exception.UserAlreadyExistException;
import lesson35.exception.DataFormatErrorException;
import lesson35.model.User;

import java.util.ArrayList;
import java.util.Random;

public class UserRepository extends GeneralRepo<User>{

    public UserRepository() {
        super.path = "C:/javaExercises/project/UserDb.txt";
    }

    public User addUser(User user) throws Exception {

        if (getUserByNameAndPass(user.getUserName(), user.getPassword()) != null)
            throw new UserAlreadyExistException("UserRepository addUser method returns exception. User this name: " +
                    user.getUserName() + " and pass: " + user.getPassword() + " already exist, save is not possible.");

        user.setId(Math.abs(new Random().nextLong()));
        return save(user);
    }

    public User getUserByNameAndPass(String name, String password) throws Exception {

        String userFilter[] = {null, name, password, null, null};
        ArrayList<User> res = getObjectsByFilter(userFilter);

        if (res.size() > 0) {
            return res.get(0);
        }

        return null;
    }

    @Override
    protected User createObjFromFields(String[] fields) throws Exception {
        //validateFields(fields);
        try {
            return new User(Long.parseLong(fields[0]), fields[1], fields[2], fields[3], User.UserType.valueOf(fields[4]));
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
