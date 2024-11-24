package com.example.gyakorlat;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitorController {

    @GetMapping("/visitor/home")
    public String index(HttpSession session, Model model) {
        model.addAttribute("pageTitle", "Látogatók - Cég");
        model.addAttribute("viewName", "visitors");
        model.addAttribute("user", session.getAttribute("loggedInUser"));
        model.addAttribute("page", "visitors");
        return "layout";
    }

}
