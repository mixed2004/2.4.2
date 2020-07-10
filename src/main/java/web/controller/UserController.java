package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String userList(ModelMap model) {
        List<User> users = userService.listUsers();
        model.addAttribute("user", users);
        return "admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/newUser")
    public String addUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "newUser";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        Set<Role> roles = user.getRoles();
        roles.add(roleService.roleUser());
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/editUser")
    public ModelAndView editUserForm(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView("editUser");
        User user = userService.getUserById(Long.parseLong(id));
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/editUser")
    public String editUser(@ModelAttribute("user") User user) {
        userService.add(user);
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