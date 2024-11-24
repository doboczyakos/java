package com.example.gyakorlat;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/messages")
    public String index(HttpSession session, Model model) {
        model.addAttribute("pageTitle", "Üzenetek - Cég");
        model.addAttribute("viewName", "messages");
        model.addAttribute("user", session.getAttribute("loggedInUser"));
        model.addAttribute("page", "messages");
        return "layout";
    }

}
