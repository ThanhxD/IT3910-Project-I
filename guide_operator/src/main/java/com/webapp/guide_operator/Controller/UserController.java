package com.webapp.guide_operator.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.guide_operator.Entities.Operator;
import com.webapp.guide_operator.Entities.User;
import com.webapp.guide_operator.Repository.UserRepository;
import com.webapp.guide_operator.Service.OperatorService;

@Controller
public class UserController {	

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OperatorService operatorService;
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
    @GetMapping("/danhsachcongtyluhanh")
    public String adminCon(HttpServletRequest request,Model model) {
        if (request.isUserInRole("ROLE_ADMIN")){
        	Page<Operator> operators = operatorService.findAll(new PageRequest(0, 5));
        	model.addAttribute("operators", operators);
            return "danhsachcongtyluhanh";
        }
        if (request.isUserInRole("ROLE_GUIDE")){
            return "ERROR";
        }
        return "ERROR";
    }
    @GetMapping("/danhsachcongtyluhanh/page/{id}")
    public String adminCompany(@PathVariable("id") int id,HttpServletRequest request,Model model) {
        if (request.isUserInRole("ROLE_ADMIN")){
        	Page<Operator> operators = operatorService.findAll(new PageRequest(id-1, 5));
        	model.addAttribute("operators", operators);
            return "danhsachcongtyluhanh";
        }
        if (request.isUserInRole("ROLE_GUIDE")){
            return "ERROR";
        }
        return "ERROR";
    }
    @GetMapping("/danhsachhuongdanvien")
    public String adminCon1(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")){
            return "danhsachhuongdanvien";
        }
        if (request.isUserInRole("ROLE_GUIDE")){
            return "ERROR";
        }
        return "ERROR";
    }@GetMapping("/danhsachtour")
    public String adminCon2(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")){
            return "danhsachtour";
        }
        if (request.isUserInRole("ROLE_GUIDE")){
            return "ERROR";
        }
        return "ERROR";
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
        return "admin";
    }

    @GetMapping("/guide")
    public String guide(){
        return "guide";
    }

    @GetMapping("/pay")
    public String pay(){
        return "payment";
    }
}
