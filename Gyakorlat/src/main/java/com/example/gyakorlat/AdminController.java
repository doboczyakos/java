package com.example.gyakorlat;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AdminController {

    @Autowired private MessageRepo messageRepo;
    @GetMapping("/admin/messages")
    public String index(HttpSession session, Model model) {
        String str = getAllMessages();
        model.addAttribute("pageTitle", "Üzenetek - Cég");
        model.addAttribute("viewName", "messages");
        model.addAttribute("user", session.getAttribute("loggedInUser"));
        model.addAttribute("page", "messages");
        model.addAttribute("str", str);

        return "layout";
    }

    String getAllMessages(){
        String str="<table style='border-spacing: 5px;'><tr><th>Felhasználó</th><th>Üzenet</th><th>Dátum és idő</th></tr>";
        for(Message message: messageRepo.findByOrderBySendDateTimeDesc()){
            str+="<tr><td class='card-text'>";
            if(message.getSender().isEmpty()){
                str+="Vendég";
            }else{
                str+=message.getSender();
            }
            str+="</td><td>"+message.getText()+"</td><td>"+message.getSendDateTime().format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss" ))+"</td></tr>";
        }
        str+="</table>";
        return str;
    }
}
