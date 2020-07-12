package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String userList(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("user", users);
        return "admin";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/newUser")
    public String addUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "newUser";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") User user) {
        user.setRoles(Collections.singleton(Role.USER));
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/editUser")
    public String editUserForm(@RequestParam("id") String id, Model model) {
        User user = userService.getUserById(Long.parseLong(id));
        model.addAttribute("user", user);
        model.addAttribute("userRoles", Role.values());
        return "editUser";
    }

    @PostMapping(value = "/editUser")
    public String editUser(@ModelAttribute("user") User user, @RequestParam Map<String, String> form) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(user.toString());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") String id) {
        userService.deletUserById(Long.parseLong(id));
        return "redirect:/admin";
    }

    @GetMapping(value = "/user")
    public String getUser(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "user";

    }
}