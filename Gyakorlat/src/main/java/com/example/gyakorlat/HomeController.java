package com.example.gyakorlat;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private UserRepo userRepository;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        model.addAttribute("pageTitle", "Főoldal - Cég");
        model.addAttribute("viewName", "index");
        model.addAttribute("user", session.getAttribute("loggedInUser"));
        model.addAttribute("page", "home");
        return "layout";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageTitle", "Belépés");
        model.addAttribute("viewName", "login");
        model.addAttribute("user", null);
        model.addAttribute("page", "login");
        return "layout";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("pageTitle", "Regisztráció");
        model.addAttribute("viewName", "register");
        model.addAttribute("user", null);
        model.addAttribute("page", "register");
        return "layout";
    }

    @PostMapping("/register")
    public String handleRegistration(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String name,
            Model model) {
        model.addAttribute("pageTitle", "Regisztráció");
        model.addAttribute("viewName", "register");
        model.addAttribute("user", null);
        model.addAttribute("page", "register");

        try {
            if (userRepository.findByUsername(username).isPresent()) {
                model.addAttribute("error", "A felhasználónév már foglalt!");
                return "layout";
            }

            if (!username.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
                var passwordEncoder = new BCryptPasswordEncoder();

                User user = new User();
                user.setUsername(username);
                user.setPassword(passwordEncoder.encode(password));
                user.setName(name);
                user.setRole("VISITOR");

                userRepository.save(user);
            }

            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Hiba történt a regisztráció során!");
            return "layout";
        }
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("pageTitle", "Kapcsolat");
        model.addAttribute("viewName", "contact");
        model.addAttribute("user", null);
        model.addAttribute("page", "contact");
        return "layout";
    }
}
