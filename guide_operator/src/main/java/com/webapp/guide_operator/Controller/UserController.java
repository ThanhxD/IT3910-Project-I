package com.webapp.guide_operator.Controller;

import com.webapp.guide_operator.Entities.Role;
import com.webapp.guide_operator.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {

        if (request.isUserInRole("ROLE_ADMIN")){
            return "redirect:/admin";
        }
        if (request.isUserInRole("ROLE_GUIDE")){
            return "redirect:/guide";
        }
        return "redirect:/operator";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/guide")
    public String guide(){
        return "guide";
    }

    @GetMapping("/operator")
    public String operator(){
        return "operator";
    }
}
