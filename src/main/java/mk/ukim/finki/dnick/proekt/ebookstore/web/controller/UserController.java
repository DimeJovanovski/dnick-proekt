package mk.ukim.finki.dnick.proekt.ebookstore.web.controller;

import mk.ukim.finki.dnick.proekt.ebookstore.model.User;
import mk.ukim.finki.dnick.proekt.ebookstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfilePage(HttpServletRequest req, Model model) {
        String username = req.getRemoteUser();
        User user = this.userService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("bodyContent","profile");
        return "master-template";
    }
}
