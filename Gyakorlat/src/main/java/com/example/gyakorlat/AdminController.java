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
        String str="";
        for(Message message: messageRepo.findByOrderBySendDateTimeDesc()){
            if(message.getSender().isEmpty()){
                str+="Vendég";
            }else{
                str+=message.getSender();
            }
            //str+=" ; "+message.getText()+" ; "+message.getSendDateTime().toLocalDate()+" "+message.getSendDateTime().toLocalTime()+"<br>";
            str+=" ; "+message.getText()+" ; "+message.getSendDateTime().format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss" ))+"<br>";
        }
        return str;
    }
}
