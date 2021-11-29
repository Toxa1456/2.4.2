package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.Objects;
import java.util.Optional;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getUsers(@RequestParam("firstName") Optional<String> firstName,
                           @RequestParam("lastName") Optional<String> lastName,
                           @RequestParam("email") Optional<String> email, ModelMap model) {
        String name = firstName.orElse(null);
        String lName = lastName.orElse(null);
        String em = email.orElse(null);
        if (name == null || lName == null || em == null) {
            model.addAttribute("Users", userService.listUsers());
            return "User/users";
        }
        userService.add(new User(name, lName, em));
        model.addAttribute("Users", userService.listUsers());
        return "User/users";
    }

    @GetMapping("/user/delete")
    public String addUsers(@RequestParam("id") Long id, ModelMap model) {
        userService.removedUserById(id);
        model.addAttribute("Users", userService.listUsers());
        return "User/users";
    }

    @GetMapping("/user/rename")
    public String renameUser(@RequestParam("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/User/changeUser";
    }

    @GetMapping("/user/change")
    private String changeUser(@RequestParam("firstName") Optional<String> firstName,
                              @RequestParam("lastName") Optional<String> lastName,
                              @RequestParam("email") Optional<String> email, @RequestParam Long id, ModelMap model) {
        User user = userService.getUserById(id);
        if (!Objects.equals(firstName.orElse(null), "")){
            user.setFirstName(firstName.orElse(null));
        }
        if (!Objects.equals(lastName.orElse(null), "")) {
            user.setLastName(lastName.orElse(null));
        }
        if (!Objects.equals(email.orElse(null), "")) {
            user.setEmail(email.orElse(null));
        }
        userService.changeUser(user);
        model.addAttribute("Users", userService.listUsers());
        return "/User/users";
    }
}
