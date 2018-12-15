import java.util.ArrayList;

public class UserService {
    public static User getUser(String username) {
        return UserDao.getUser(username);
    }

    public static boolean addUser(String username, String password){
        User user = new User(username,password);
        if(UserDao.addUser(user))
            return true;
        else
            return false;
    }

    public static boolean changePhone(User user,String phone){
        User thisUser = user;
        thisUser.setPhone(phone);
        if(UserDao.updatePhone(thisUser))
            return true;
        else
            return false;
    }

    public static boolean changeAddress(User user,String address){
        User thisUser = user;
        thisUser.setAddress(address);
        if(UserDao.updatePhone(thisUser))
            return true;
        else
            return false;
    }

}