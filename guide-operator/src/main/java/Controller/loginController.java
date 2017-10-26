package Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {
    @RequestMapping("/")
    public String index(){return "index";}
    @RequestMapping("/login")
    public String login(){return "login";}
}
