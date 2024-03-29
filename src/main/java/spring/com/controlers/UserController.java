package spring.com.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.com.model.User;
import spring.com.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping()
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUserData(Principal principal, Model model) {
        model.addAttribute("user",
                userService.getUserByUsername(principal.getName()));
        return "showUser";
    }


    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "login";
    }


    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }
}
