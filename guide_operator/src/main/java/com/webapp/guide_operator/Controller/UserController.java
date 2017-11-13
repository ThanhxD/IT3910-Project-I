package com.webapp.guide_operator.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.guide_operator.Entities.User;
import com.webapp.guide_operator.Repository.GuideRepository;
import com.webapp.guide_operator.Repository.UserRepository;
import com.webapp.guide_operator.Service.GuideService;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GuideService guideService;
	@RequestMapping(value="/user/id/{id}", method= RequestMethod.GET)
	public String getUserbyID(@PathVariable("id") int id,Model model) {
		User user= userRepository.findOne(id);
		model.addAttribute("user",user);
		return "user";
		
	}
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
    public String admin(Model model){
    	model.addAttribute("guides",guideService.findAll());
        return "adminnew";
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
