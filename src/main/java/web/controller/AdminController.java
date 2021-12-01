package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/")
    public String getUsers(ModelMap model) {
        model.addAttribute("Users", userService.listUsers());
        return "/Admin/admin";
    }

    @GetMapping("/change")
    private String changeUser(@RequestParam("name") Optional<String> name,
                              @RequestParam("password") Optional<String> password,
                              @RequestParam("email") Optional<String> email,
                              @RequestParam Long id, ModelMap model) {
        if (!name.isPresent() && !password.isPresent() && !email.isPresent()) {
            model.addAttribute("user", userService.getUserById(id));
            return "/Admin/changeUser";
        }
        User user = userService.getUserById(id);
        if (!name.get().equals("")){
            user.setName(name.get());
        }
        if (!password.get().equals("")){
            user.setPassword(password.get());
        }
        email.ifPresent(user::setEmail);
        userService.changeUser(user);
        model.addAttribute("Users", userService.listUsers());
        return "/Admin/admin";
    }

    @GetMapping("/delete")
    public String removeUsers(@RequestParam("id") Long id, ModelMap model) {
        userService.removedUserById(id);
        model.addAttribute("Users", userService.listUsers());
        return "/Admin/admin";
    }

    @GetMapping("/addUser")
    public String addUser(@RequestParam("name") Optional<String> name,
                          @RequestParam("password") Optional<String> password,
                          @RequestParam("email") Optional<String> email,
                          @RequestParam("role") Optional<String> role, ModelMap model) {
        if (!name.isPresent() || !password.isPresent() || !email.isPresent() || !role.isPresent()) {
            return "/Admin/addUser";
        }
        User user = new User();
        if (name.get().equals("")){
            return "/Admin/addUser";
        }
        if (password.get().equals("")){
            return "/Admin/addUser";
        }
        user.setName(name.get());
        user.setPassword(password.get());
        user.setEmail(email.get());
        if (role.get().equalsIgnoreCase("yes")){
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleById(1L));
            roles.add(roleService.getRoleById(2L));
            user.setRoles(roles);
        } else {
            user.setRoles(Collections.singleton(roleService.getRoleById(1L)));
        }
        userService.add(user);
        model.addAttribute("Users", userService.listUsers());
        return "/Admin/admin";
    }

}
