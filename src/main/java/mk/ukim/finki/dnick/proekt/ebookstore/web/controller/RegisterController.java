package mk.ukim.finki.dnick.proekt.ebookstore.web.controller;

import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.Role;
import mk.ukim.finki.dnick.proekt.ebookstore.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.dnick.proekt.ebookstore.service.AuthService;
import mk.ukim.finki.dnick.proekt.ebookstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String phone_number,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam Role role) {
        try{
            this.userService.register(username, phone_number, email, password, role);
            return "redirect:/login";
        } catch (InvalidArgumentsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}
