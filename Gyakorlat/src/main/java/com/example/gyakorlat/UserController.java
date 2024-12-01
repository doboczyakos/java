package com.example.gyakorlat;

import com.example.gyakorlat.hulladekSzallitas.LakigRepo;
import com.example.gyakorlat.hulladekSzallitas.NaptarRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private NaptarRepo naptarRepository;
    @Autowired
    private LakigRepo lakigRepository;

    @GetMapping("/user/data")
    public String index(HttpSession session, Model model) {
        model.addAttribute("pageTitle", "Adatok - Cég");
        model.addAttribute("viewName", "data");
        model.addAttribute("user", session.getAttribute("loggedInUser"));
        model.addAttribute("page", "data");
        model.addAttribute("naptarData", naptarRepository.findAll());
        model.addAttribute("lakigData", lakigRepository.findAll());
        return "layout";
    }

}
