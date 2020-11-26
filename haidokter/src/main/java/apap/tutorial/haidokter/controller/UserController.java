package apap.tutorial.haidokter.controller;

import apap.tutorial.haidokter.model.UserModel;
import apap.tutorial.haidokter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserModel user){
        userService.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public String changePassForm(Model model) {
        model.addAttribute("text", "");
        return "form-update-password";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    private String updatePasswordUserFormSubmit(@RequestParam String passLama, String passBaru, String passKonfirmasi, String username, Model model){
        UserModel user = userService.findUserByUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(passLama, user.getPassword())){
            if(passBaru.equals(passKonfirmasi)){
                userService.updatePass(user, passBaru);
            }
            else{ }
        }
        else{
        }
        return "form-update-password";
    }
}
