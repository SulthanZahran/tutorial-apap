package apap.tutorial.haidokter.service;

import apap.tutorial.haidokter.model.UserModel;
import apap.tutorial.haidokter.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;

    @Override
    public Boolean addUser(UserModel user){
        if(findUserByUsername(user.getUsername()) == null ){
            String pass = encrypt(user.getPassword());
            user.setPassword(pass);
            userDb.save(user);
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public void updatePass(UserModel user, String newPassword){
        UserModel userGanti = userDb.findByUsername(user.getUsername());
        userGanti.setPassword(encrypt(newPassword));
        userDb.save(userGanti);
    }

    @Override
    public UserModel findUserByUsername(String username){
        UserModel user = userDb.findByUsername(username);
        return user;
    }
}
