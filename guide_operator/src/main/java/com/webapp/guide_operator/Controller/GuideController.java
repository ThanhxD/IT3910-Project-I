package com.webapp.guide_operator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.guide_operator.Entities.Guide;
import com.webapp.guide_operator.Repository.GuideRepository;

@Controller
public class GuideController {
	
	@Autowired
	private GuideRepository guideRepository;
	
	@RequestMapping(value="/guide/{page}", method= RequestMethod.GET)
	public String getTourbyId(@PathVariable("page") int page,Model model) {
		Page<Guide> guides = guideRepository.findAll(new PageRequest(page, 20));
		model.addAttribute("guides",guides);
		return "guidetest";
		
	}
}
