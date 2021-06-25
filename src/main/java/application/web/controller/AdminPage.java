package application.web.controller;

import application.entity.Role;
import application.entity.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AdminPage {

    @Autowired
    private UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getByUserName(auth.getName());
        Set<Role> userRoles = new HashSet<>(authUser.getRoles());
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
//        model.addAttribute("userRoles", userRoles);


        return "admin-page-ViewAllUsers";
    }


    @GetMapping("/admin/create")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createNewUser";
    }

    @PatchMapping()
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createNewUser";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/update")
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "update-user";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult , @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "update-user";
        }
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/delete")
    public String deleteUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "delete-user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String showInfoUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUserName(auth.getName());
        model.addAttribute("user", user);
        return "user-info";
    }
}
