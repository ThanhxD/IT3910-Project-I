package com.webapp.guide_operator.Controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.webapp.guide_operator.Entities.Operator;
import com.webapp.guide_operator.Entities.User;
import com.webapp.guide_operator.Repository.UserRepository;
import com.webapp.guide_operator.Service.OperatorService;

@Controller
public class OperatorController {
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/operator")
    public String operator(HttpServletRequest request,Model model) {
        Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Operator operator = operatorService.findByUserId(user.getId());
		model.addAttribute("operator", operator);
        return "TourOperator";
    }
}
