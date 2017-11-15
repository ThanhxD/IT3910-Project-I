package com.webapp.guide_operator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.guide_operator.Entities.GuideLocationXref;
import com.webapp.guide_operator.Service.GuideLocationXrefService;

@Controller
public class GuideLocationXrefController {
	@Autowired
	private GuideLocationXrefService guideLocationXrefService;
	@RequestMapping(value="/gLocation/id/{id}", method= RequestMethod.GET)
	public String getContractrbyId(@PathVariable("id") int id,Model model) {
		GuideLocationXref glocation= guideLocationXrefService.findOne(id);
		model.addAttribute("glocation",glocation);
		return "glocation";
		
	}
}
