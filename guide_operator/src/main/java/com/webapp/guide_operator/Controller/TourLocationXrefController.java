package com.webapp.guide_operator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.guide_operator.Entities.TourLocationXref;
import com.webapp.guide_operator.Service.TourLocationXrefService;

@Controller
public class TourLocationXrefController {
	@Autowired
	private TourLocationXrefService tourLocationXrefService;
	
	@RequestMapping(value="/tlocation/id/{id}", method= RequestMethod.GET)
	public String getLocationbyId(@PathVariable("id") int id,Model model) {
		TourLocationXref tlocation= tourLocationXrefService.findOne(id);
		model.addAttribute("tlocation",tlocation);
		return "tlocation";
		
	}
}
