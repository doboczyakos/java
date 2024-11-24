package com.example.gyakorlat;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/data")
    public String index(HttpSession session, Model model) {
        model.addAttribute("pageTitle", "Adatok - Cég");
        model.addAttribute("viewName", "data");
        model.addAttribute("user", session.getAttribute("loggedInUser"));
        model.addAttribute("page", "data");
        return "layout";
    }

}
