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

import com.webapp.guide_operator.Entities.Operator;
import com.webapp.guide_operator.Entities.Tour;
import com.webapp.guide_operator.Entities.Tour_Guide_Xref;
import com.webapp.guide_operator.Entities.User;
import com.webapp.guide_operator.Repository.UserRepository;
import com.webapp.guide_operator.Service.OperatorService;
import com.webapp.guide_operator.Service.TourService;
import com.webapp.guide_operator.Service.Tour_Guide_Xref_Service;

@Controller
public class TourController {
	@Autowired
	private OperatorService operatorService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TourService tourService;
	@Autowired
	private Tour_Guide_Xref_Service tour_Guide_Xref_Service;
	@PostMapping("/tour/accept/{tourxrefid}")
	public String accept(@PathVariable("tourxrefid") int tourxrefid,HttpServletRequest request,Model model) {
		Tour_Guide_Xref txref = tour_Guide_Xref_Service.findOne(tourxrefid);
		txref.setStatus(1);
		tour_Guide_Xref_Service.save(txref);
		Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Operator operator = operatorService.findByUserId(user.getId());
		model.addAttribute("operator", operator);
		return "TourOperator";
	}
	@PostMapping("/tour/cancel/{tourxrefid}")
	public String cancel(@PathVariable("tourxrefid") int tourxrefid,HttpServletRequest request,Model model) {
		tour_Guide_Xref_Service.delete(tourxrefid);
		Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Operator operator = operatorService.findByUserId(user.getId());
		model.addAttribute("operator", operator);
		return "TourOperator";
	}
	
	@GetMapping("/danhsachtour")
    public String adminConTour(HttpServletRequest request,Model model) {
        if (request.isUserInRole("ROLE_ADMIN")){
        	Page<Tour> tours = tourService.findAll(new PageRequest(0, 5));
        	model.addAttribute("tours", tours);
        	model.addAttribute("page", 1);
        	model.addAttribute("previouspage", 0);
        	model.addAttribute("nextpage", 2);
            return "danhsachtour";
        }
        if (request.isUserInRole("ROLE_GUIDE")){
            return "ERROR";
        }
        return "ERROR";
    }
	 @GetMapping("/danhsachtour/page/{id}")
	    public String adminConTour1(@PathVariable("id") int id,HttpServletRequest request,Model model) {
	        if (request.isUserInRole("ROLE_ADMIN")){
	        	Page<Tour> tours = tourService.findAll(new PageRequest(id-1, 5));
	        	model.addAttribute("tours", tours);
	        	model.addAttribute("page", id);
	        	model.addAttribute("previouspage", id-1);
	        	model.addAttribute("nextpage", id+1);
	            return "danhsachtour";
	        }
	        if (request.isUserInRole("ROLE_GUIDE")){
	            return "ERROR";
	        }
	        return "ERROR";
	 }
	 @GetMapping(value="/tour/delete/{page}/{id}")
		public String deleteTour(@PathVariable("page") int page,@PathVariable("id") int id,Model model) {
		 tourService.delete(id);
			Page<Tour> tours = tourService.findAll(new PageRequest(page-1, 5));
	    	model.addAttribute("tours", tours);
	    	model.addAttribute("page", page);
	    	model.addAttribute("previouspage", page-1);
	    	model.addAttribute("nextpage", page+1);
	        return "danhsachtour";
		}

}
