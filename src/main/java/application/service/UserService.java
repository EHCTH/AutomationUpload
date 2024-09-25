package application.service;

import domain.user.User;
import util.DotenvUtil;

public class UserService {
    public static User createUser() {
        String id = DotenvUtil.getID();
        String pass = DotenvUtil.getPass();
        return new User(id, pass);
    }
}
