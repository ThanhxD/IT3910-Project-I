package com.webapp.guide_operator.Controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.webapp.guide_operator.Entities.Guide;
import com.webapp.guide_operator.Entities.Operator;
import com.webapp.guide_operator.Entities.Tour;
import com.webapp.guide_operator.Entities.Tour_Guide_Xref;
import com.webapp.guide_operator.Entities.User;
import com.webapp.guide_operator.Repository.UserRepository;
import com.webapp.guide_operator.Service.GuideService;
import com.webapp.guide_operator.Service.OperatorService;
import com.webapp.guide_operator.Service.TourService;
import com.webapp.guide_operator.Service.Tour_Guide_Xref_Service;

@Controller
public class TourController {
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private GuideService guideService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TourService tourService;
	@Autowired
	private Tour_Guide_Xref_Service tour_Guide_Xref_Service;

	@PostMapping("/tour/accept/{tourxrefid}")
	public String accept(@PathVariable("tourxrefid") int tourxrefid, HttpServletRequest request, Model model) {
		Tour_Guide_Xref txref = tour_Guide_Xref_Service.findOne(tourxrefid);
		txref.setStatus(1);
		tour_Guide_Xref_Service.save(txref);
		if (request.isUserInRole("ROLE_OPERATOR")) {
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Operator operator = operatorService.findByUserId(user.getId());
			model.addAttribute("operator", operator);
			return "redirect:/operator";
		} else if (request.isUserInRole("ROLE_GUIDE")) {
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Guide guide = guideService.findByUserId(user.getId());
			model.addAttribute("guide", guide);
			return "redirect:/guide";
		} else
			return "index";
	}

	@PostMapping("/tour/delete/{tourid}")
	public String deleteTour(@PathVariable("tourid") int tourid, HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_OPERATOR")) {
			tourService.delete(tourid);
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Operator operator = operatorService.findByUserId(user.getId());
			model.addAttribute("operator", operator);
			return "redirect:/operator";
		} else
			return "index";
	}

	@PostMapping("/tour/cancel/{tourxrefid}")
	public String cancel(@PathVariable("tourxrefid") int tourxrefid, HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_OPERATOR")) {
			tour_Guide_Xref_Service.delete(tourxrefid);
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Operator operator = operatorService.findByUserId(user.getId());
			model.addAttribute("operator", operator);
			return "redirect:/operator";
		} else if (request.isUserInRole("ROLE_GUIDE")) {
			tour_Guide_Xref_Service.delete(tourxrefid);
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Guide guide = guideService.findByUserId(user.getId());
			model.addAttribute("guide", guide);
			return "redirect:/guide";
		} else
			return "index";
	}

	@PostMapping("/tour/guiderequest/{id}")
	public String guideSendRequest(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_GUIDE")) {
			Tour_Guide_Xref tgxref = tour_Guide_Xref_Service.findOne(id);
			tgxref.setStatus(0);
			tour_Guide_Xref_Service.save(tgxref);
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Guide guide = guideService.findByUserId(user.getId());
			model.addAttribute("guide", guide);
			return "redirect:/guide";

		} else
			return "index";
	}

	@PostMapping("/tour/guidefindrequest/{id}")
	public String requestTour(@PathVariable("id") int tourid, HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_GUIDE")) {
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Guide guide = guideService.findByUserId(user.getId());
			tour_Guide_Xref_Service.save(new Tour_Guide_Xref(guide, tourService.findOne(tourid), 0));
			model.addAttribute("guide", guide);
			return "find-tour";

		} else
			return "index";
	}

	@GetMapping("/danhsachtour")
	public String adminConTour(HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			Page<Tour> tours = tourService.findAll(new PageRequest(0, 5));
			model.addAttribute("tours", tours);
			model.addAttribute("page", 1);
			model.addAttribute("previouspage", 0);
			model.addAttribute("nextpage", 2);
			return "danhsachtour";
		}
		if (request.isUserInRole("ROLE_GUIDE")) {
			return "ERROR";
		}
		return "ERROR";
	}

	@GetMapping("/danhsachtour/page/{id}")
	public String adminConTour1(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			if (id > 0) {
				Page<Tour> tours = tourService.findAll(new PageRequest(id - 1, 5));
				model.addAttribute("tours", tours);
				model.addAttribute("page", id);
				model.addAttribute("previouspage", id - 1);
				model.addAttribute("nextpage", id + 1);
				return "danhsachtour";
			} else {
				Page<Tour> tours = tourService.findAll(new PageRequest(0, 5));
				model.addAttribute("tours", tours);
				model.addAttribute("page", 1);
				model.addAttribute("previouspage", 0);
				model.addAttribute("nextpage", 2);
				return "danhsachtour";
			}
		}
		if (request.isUserInRole("ROLE_GUIDE")) {
			return "ERROR";
		}
		return "ERROR";
	}

	@GetMapping(value = "/tour/delete/{page}/{id}")
	public String deleteTour(@PathVariable("page") int page, @PathVariable("id") int id, Model model) {
		tourService.delete(id);
		Page<Tour> tours = tourService.findAll(new PageRequest(page - 1, 5));
		model.addAttribute("tours", tours);
		model.addAttribute("page", page);
		model.addAttribute("previouspage", page - 1);
		model.addAttribute("nextpage", page + 1);
		return "danhsachtour";
	}

	@PostMapping(value = "/tour/operatorrequest/{tourid}/{guideid}")
	public String requestTour(@PathVariable("tourid") int tourid, @PathVariable("guideid") int guideid,
			HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_OPERATOR")) {
			tour_Guide_Xref_Service.operatorRequest(tourid, guideid);
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Operator operator = operatorService.findByUserId(user.getId());
			model.addAttribute("operator", operator);
			return "find-guider";

		} else
			return "index";
	}
}
