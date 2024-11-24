package com.example.gyakorlat;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

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
}
