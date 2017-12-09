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

import com.webapp.guide_operator.Entities.Guide;
import com.webapp.guide_operator.Entities.Operator;
import com.webapp.guide_operator.Entities.User;
import com.webapp.guide_operator.Repository.UserRepository;
import com.webapp.guide_operator.Service.GuideService;
import com.webapp.guide_operator.Service.OperatorService;

@Controller
public class GuideController {
	
	@Autowired
	private GuideService guideService;
	@Autowired
	private UserRepository userRepository;

	
	
	@GetMapping("/guide/info")
	public String info(HttpServletRequest request,Model model) {
		Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Guide guide = guideService.findByUserId(user.getId());
		model.addAttribute("guide", guide);
		return "imformation";
	}
	@GetMapping("/guide")
	public String getTourbyId(HttpServletRequest request,Model model) {
		Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Guide guide = guideService.findByUserId(user.getId());
		model.addAttribute("guide", guide);
		return "TourGuide";
	}
	@GetMapping("/danhsachhuongdanvien")
    public String adminCon(HttpServletRequest request,Model model) {
        if (request.isUserInRole("ROLE_ADMIN")){
        	Page<Guide> guides = guideService.findAll(new PageRequest(0, 5));
        	model.addAttribute("guides", guides);
        	model.addAttribute("page", 1);
        	model.addAttribute("previouspage", 0);
        	model.addAttribute("nextpage", 2);
            return "danhsachhuongdanvien";
        }
        if (request.isUserInRole("ROLE_GUIDE")){
            return "ERROR";
        }
        return "ERROR";
    }
	 @GetMapping("/danhsachhuongdanvien/page/{id}")
	    public String adminCon1(@PathVariable("id") int id,HttpServletRequest request,Model model) {
	        if (request.isUserInRole("ROLE_ADMIN")){
	        	Page<Guide> guides = guideService.findAll(new PageRequest(id-1, 5));
	        	model.addAttribute("guides", guides);
	        	model.addAttribute("page", id);
	        	model.addAttribute("previouspage", id-1);
	        	model.addAttribute("nextpage", id+1);
	            return "danhsachhuongdanvien";
	        }
	        if (request.isUserInRole("ROLE_GUIDE")){
	            return "ERROR";
	        }
	        return "ERROR";
	 }
	 @GetMapping(value="/guide/delete/{page}/{id}")
		public String delete(@PathVariable("page") int page,@PathVariable("id") int id,Model model) {
			guideService.delete(id);
			Page<Guide> guides = guideService.findAll(new PageRequest(page-1, 5));
	    	model.addAttribute("guides", guides);
	    	model.addAttribute("page", page);
	    	model.addAttribute("previouspage", page-1);
	    	model.addAttribute("nextpage", page+1);
	        return "danhsachhuongdanvien";
		}
}
