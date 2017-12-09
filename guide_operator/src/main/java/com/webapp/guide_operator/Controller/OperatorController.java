package com.webapp.guide_operator.Controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.webapp.guide_operator.Entities.Location;
import com.webapp.guide_operator.Entities.Operator;
import com.webapp.guide_operator.Entities.Tour;
import com.webapp.guide_operator.Entities.Tour_Guide_Xref;
import com.webapp.guide_operator.Entities.User;
import com.webapp.guide_operator.Repository.UserRepository;
import com.webapp.guide_operator.Service.LocationService;
import com.webapp.guide_operator.Service.OperatorService;
import com.webapp.guide_operator.Service.TourService;

@Controller
public class OperatorController {
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TourService tourService;
	@Autowired
	private LocationService locationService;

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
			Page<Operator> operators = operatorService.findAll(new PageRequest(id - 1, 5));
			model.addAttribute("operators", operators);
			model.addAttribute("page", id);
			model.addAttribute("previouspage", id - 1);
			model.addAttribute("nextpage", id + 1);
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

	@PostMapping("/operator/update/{id}")
	public String updateTour(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		Tour tour = tourService.findOne(id);
		String tourName = request.getParameter("tourName");
		String tourPrice = request.getParameter("tourPrice");
		String day = request.getParameter("day");
		String night = request.getParameter("night");
		tour.setTourName(tourName);
		tour.setTourPrice(Integer.parseInt(tourPrice));
		tour.setTourTime(day+" ngày "+night+" đêm");
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
		String locaton = request.getParameter("location");
		String language = request.getParameter("language");
		String day = request.getParameter("day");
		String night = request.getParameter("night");
		String date = request.getParameter("date");
		String tourprice = request.getParameter("tourprice");
		System.out.println(tourname + " " + tourprice + " " + locaton + " " + language + " " + day + night + date);
		Tour tour = new Tour(tourname, day + " ngày " + night + " đêm", Integer.parseInt(tourprice));
		Set<Operator> set = new HashSet<>();
		set.add(operator);
		Location location = locationService.findByLocationName(locaton);
		Set<Location> setl = new HashSet<>();
		setl.add(location);
		tour.setOperators(set);
		tour.setLocations(setl);
		tourService.save(tour);
		operator = operatorService.findByUserId(user.getId());
		model.addAttribute("operator", operator);
		return "TourOperator";
	}
}
