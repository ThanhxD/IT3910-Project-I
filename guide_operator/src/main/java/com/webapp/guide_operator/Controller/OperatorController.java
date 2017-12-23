package com.webapp.guide_operator.Controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.webapp.guide_operator.Entities.Guide;
import com.webapp.guide_operator.Entities.Language;
import com.webapp.guide_operator.Entities.Location;
import com.webapp.guide_operator.Entities.Operator;
import com.webapp.guide_operator.Entities.Tour;
import com.webapp.guide_operator.Entities.User;
import com.webapp.guide_operator.Repository.UserRepository;
import com.webapp.guide_operator.Service.GuideService;
import com.webapp.guide_operator.Service.LanguageService;
import com.webapp.guide_operator.Service.LocationService;
import com.webapp.guide_operator.Service.OperatorService;
import com.webapp.guide_operator.Service.TourService;
import com.webapp.guide_operator.Service.Tour_Guide_Xref_Service;

@Controller
public class OperatorController {
	@Autowired
	private Tour_Guide_Xref_Service tour_Guide_Xref_Service;
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private LanguageService languageService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TourService tourService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private GuideService guideService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@GetMapping("/operator")
	public String operator(HttpServletRequest request, Model model) {
		Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Operator operator = operatorService.findByUserId(user.getId());
		model.addAttribute("operator", operator);
		return "TourOperator";
	}

	@GetMapping("/danhsachcongtyluhanh")
	public String adminCon(HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			Page<Operator> operators = operatorService.findAll(new PageRequest(0, 5));
			model.addAttribute("operators", operators);
			model.addAttribute("page", 1);
			model.addAttribute("previouspage", 0);
			model.addAttribute("nextpage", 2);
			return "danhsachcongtyluhanh";
		}
		if (request.isUserInRole("ROLE_GUIDE")) {
			return "ERROR";
		}
		return "ERROR";
	}

	@GetMapping("/danhsachcongtyluhanh/page/{id}")
	public String adminCompany(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			if (id > 0) {
				Page<Operator> operators = operatorService.findAll(new PageRequest(id - 1, 5));
				model.addAttribute("operators", operators);
				model.addAttribute("page", id);
				model.addAttribute("previouspage", id - 1);
				model.addAttribute("nextpage", id + 1);
			}
			else {
				Page<Operator> operators = operatorService.findAll(new PageRequest(0, 5));
				model.addAttribute("operators", operators);
				model.addAttribute("page", 1);
				model.addAttribute("previouspage", 0);
				model.addAttribute("nextpage", 2);
				return "danhsachcongtyluhanh";
			}
			return "danhsachcongtyluhanh";
		}
		if (request.isUserInRole("ROLE_GUIDE")) {
			return "ERROR";
		}
		return "ERROR";
	}

	@GetMapping(value = "/operator/delete/{page}/{id}")
	public String delete(@PathVariable("page") int page, @PathVariable("id") int id, Model model) {
		operatorService.delete(id);
		Page<Operator> operators = operatorService.findAll(new PageRequest(page - 1, 5));
		model.addAttribute("operators", operators);
		model.addAttribute("page", page);
		model.addAttribute("previouspage", page - 1);
		model.addAttribute("nextpage", page + 1);
		return "danhsachcongtyluhanh";
	}
	@GetMapping("/operator/info")
	public String operatorInfo(HttpServletRequest request,Model model) {
		if(request.isUserInRole("ROLE_OPERATOR")) {
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Operator operator = operatorService.findByUserId(user.getId());
			model.addAttribute("operator", operator);
			return "operator-info";
		}
		else {
			return "index";
		}
	}
	@PostMapping("/operator/update/{id}")
	public String updateTour(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		Tour tour = tourService.findOne(id);
		String tourName = request.getParameter("tourName");
		String tourPrice = request.getParameter("tourPrice");
		String day = request.getParameter("day");
		String night = request.getParameter("night");
		tour.setTourName(tourName);
		tour.setTourPrice(Integer.parseInt(tourPrice));
		tour.setTourTime(day + " ngày " + night + " đêm");
		tourService.save(tour);
		Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Operator operator = operatorService.findByUserId(user.getId());
		model.addAttribute("operator", operator);
		return "TourOperator";
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/operator/posttour")
	public String posttour(HttpServletRequest request, Model model) {
		if (!request.isUserInRole("ROLE_OPERATOR"))
			return "index";
		Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Operator operator = operatorService.findByUserId(user.getId());
		String tourname = request.getParameter("tourname");
		String language = request.getParameter("language");
		String day = request.getParameter("day");
		String night = request.getParameter("night");
		String date = request.getParameter("date");
		String tourprice = request.getParameter("tourprice");
		int count = Integer.parseInt(request.getParameter("count-location"));
		Set<Location> setl = new HashSet<>();
		Language lang = languageService.findByLanguage(language);
		Tour tour = new Tour(tourname, day + " ngày " + night + " đêm", Integer.parseInt(tourprice));
		Set<Operator> set = new HashSet<>();
		set.add(operator);
		tourService.save(tour);
		for (int i = 1; i <= count; i++) {
			String location = request.getParameter("location" + i);
			Location loc = locationService.findByLocationName(location);
			setl.add(loc);
			tour_Guide_Xref_Service.recommendByLocation(tour.getId(), loc.getId(), lang.getId());
		}

		tour.setOperators(set);
		tour.setLocations(setl);
		tourService.save(tour);
		model.addAttribute("operator", operator);
		return "TourOperator";
	}

	@GetMapping("/operator/searchguide")
	public String findGuide(HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_OPERATOR")) {
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Operator operator = operatorService.findByUserId(user.getId());
			model.addAttribute("operator", operator);
			return "find-guider";
		} else
			return "ERROR";
	}

	@PostMapping("/operator/search")
	public String searchGuide(HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_OPERATOR")) {
			String location = request.getParameter("location");
			String name = request.getParameter("guidename");
			boolean isMale, isFeMale, isNoExpGuide, isExpGuide;
			if (request.getParameter("male") != null)
				isMale = true;
			else
				isMale = false;
			if (request.getParameter("exp") != null)
				isExpGuide = true;
			else
				isExpGuide = false;
			if (request.getParameter("female") != null)
				isFeMale = true;
			else
				isFeMale = false;
			if (request.getParameter("noexp") != null)
				isNoExpGuide = true;
			else
				isNoExpGuide = false;
			Tour tour = tourService.findByTourName(request.getParameter("tourname")).get(0);
			List<Guide> guides = guideService.findGuide(name, tour, location, isMale, isFeMale, isNoExpGuide,
					isExpGuide);
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Operator operator = operatorService.findByUserId(user.getId());
			model.addAttribute("operator", operator);
			model.addAttribute("guides", guides);
			model.addAttribute("tour", tour);
			model.addAttribute("location", location);
			return "find-guider";
		} else
			return "ERROR";
	}
	@PostMapping("/operator/changeinfo")
	public String changeInfo(HttpServletRequest request,Model model) {
		if(request.isUserInRole("ROLE_OPERATOR")) {
			String operatorName = request.getParameter("operatorname");
			String address = request.getParameter("address");
			String pnumber = request.getParameter("phonenumber");
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Operator operator = operatorService.findByUserId(user.getId());
			operator.getUser().setAddress(address);
			operator.getUser().setFullname(operatorName);
			operator.setCompanyNameViet(operatorName);
			operator.getUser().setPhonenumber(pnumber);
			operatorService.save(operator);
			model.addAttribute("operator", operator);
			return "operator-info";
		}
		else {
			return "index";
		}
	}
	@PostMapping("/operator/changepassword")
	public String changePass(HttpServletRequest request,Model model) {
		if(request.isUserInRole("ROLE_OPERATOR")) {
			String oldpassword = request.getParameter("old-passwd");
			String newpassword = request.getParameter("new-passwd");
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Operator operator = operatorService.findByUserId(user.getId());
			if(BCrypt.checkpw(oldpassword, user.getPassword())) {
				operator.getUser().setPassword(passwordEncoder.encode(newpassword));
				model.addAttribute("status","Đổi password thành công");
			}
			else {
				model.addAttribute("status","Đổi password thất bại do sai mật khẩu cũ");
			}
			operatorService.save(operator);
			model.addAttribute("operator", operator);
			return "operator-info";
		}
		else {
			return "index";
		}
	}
	@GetMapping("/operator/{guideid}")
	public String showGuideInfo(@PathVariable("guideid") int guideid,HttpServletRequest request, Model model) {
		if(request.isUserInRole("ROLE_OPERATOR")) {
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Operator operator = operatorService.findByUserId(user.getId());
			model.addAttribute("guide",guideService.findOne(guideid));
			model.addAttribute("operator", operator);
			return "showing-info";
		}
		else return "index";
	}
}
