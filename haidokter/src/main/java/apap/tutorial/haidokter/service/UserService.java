package apap.tutorial.haidokter.service;

import apap.tutorial.haidokter.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    void updatePass(UserModel user, String newPassword);
    UserModel findUserByUsername(String username);
}
