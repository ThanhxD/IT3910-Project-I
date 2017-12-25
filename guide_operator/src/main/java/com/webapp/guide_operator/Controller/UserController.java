package com.webapp.guide_operator.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.guide_operator.Entities.Guide;
import com.webapp.guide_operator.Entities.Operator;
import com.webapp.guide_operator.Entities.Role;
import com.webapp.guide_operator.Entities.User;
import com.webapp.guide_operator.Repository.RoleRepository;
import com.webapp.guide_operator.Service.GuideService;
import com.webapp.guide_operator.Service.OperatorService;
import com.webapp.guide_operator.Service.UserDetailsServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private GuideService guideService;

	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/default")
	public String defaultAfterLogin(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin";
		}
		if (request.isUserInRole("ROLE_GUIDE")) {
			return "redirect:/guide";
		}
		return "redirect:/operator";
	}

	@GetMapping("/home")
	public String home(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin";
		}
		if (request.isUserInRole("ROLE_GUIDE")) {
			return "redirect:/guide";
		}
		if (request.isUserInRole("ROLE_OPERATOR"))
			return "redirect:/operator";
		return "index";
	}

	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}

	@GetMapping("/login")
	public String as() {
		return "index";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		return "admin";
	}

	/**
	 * @description: go logout method
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login?logout";
	}

	/**
	 * @description: signup method, first check username if user not exist then
	 *               check password and repasword, if true then update in table.
	 * @issue: this method not dry code, need repair.
	 * @param request
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {
		String status;
		String username = request.getParameter("username");
		System.out.println(username);
		if ((userDetailsServiceImpl.findByUsername(username)) != null) {
			status = "exist username!";
			model.addAttribute("status", status);

		} else if (!request.getParameter("password").equals(request.getParameter("repassword"))) {

			status = "password not matched!";
			model.addAttribute("status", status);
		} else {
			if ("operator".equals(request.getParameter("org_ind"))) {
				Operator operator = new Operator();
				User user = new User();
				Role role = roleRepository.findByName("ROLE_OPERATOR");
				user.setUsername(username);
				user.setPassword(passwordEncoder.encode(request.getParameter("password")));
				user.setEmail(request.getParameter("email"));
				user.setRoles(role);
				userDetailsServiceImpl.save(user);
				user = userDetailsServiceImpl.findByUsername(username);
				operator.setUser(user);
				operatorService.save(operator);
			}
			if ("guide".equals(request.getParameter("org_ind"))) {
				Guide guide = new Guide();
				User user = new User();
				Role role = roleRepository.findByName("ROLE_GUIDE");
				boolean isMale, isFeMale;
				if (request.getParameter("male") != null && !request.getParameter("male").equals("")) {
					isMale = true;
				} else {
					isMale = false;
				}
				if (request.getParameter("female") != null && !request.getParameter("female").equals("")) {
					isFeMale = true;
				} else {
					isFeMale = false;
				}
				if(isMale) {
					guide.setGender("Male");
				}
				if(isFeMale) {
					guide.setGender("Female");
				}
				user.setUsername(username);
				user.setPassword(passwordEncoder.encode(request.getParameter("password")));
				user.setEmail(request.getParameter("email"));
				user.setRoles(role);
				userDetailsServiceImpl.save(user);
				user = userDetailsServiceImpl.findByUsername(username);
				guide.setUser(user);
				guideService.save(guide);
			}
		}
		return "index";

	}

}
