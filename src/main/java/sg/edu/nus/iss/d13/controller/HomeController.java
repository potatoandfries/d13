package sg.edu.nus.iss.d13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/pagea")
    public String PageA(Model model,HttpSession session) {
        return "pagea";
    }

    @PostMapping("/pagea")
    public String PageAPost(@RequestBody MultiValueMap<String,String> form,Model model,HttpSession session){
        
        String myFullName = form.getFirst("fullname");
        System.out.println("My fullname is " + myFullName);

        session.setAttribute("myFullName", myFullName);

        model.addAttribute("myName", session.getAttribute("myFullName").toString());

        return "pageb";
    }
}
