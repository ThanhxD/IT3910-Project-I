package com.webapp.guide_operator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.guide_operator.Entities.GuideLanguageXref;
import com.webapp.guide_operator.Service.GuideLanguageXrefService;

@Controller
public class GuideLanguageXrefController {
	@Autowired
	private GuideLanguageXrefService guideLanguageXrefService;
	
	@RequestMapping(value="/guidelanguage/id/{id}", method= RequestMethod.GET)
	public String getGuideLanguagebyId(@PathVariable("id") int id,Model model) {
		GuideLanguageXref glanguage= guideLanguageXrefService.findOne(id);
		model.addAttribute("glanguage",glanguage);
		return "glanguage";
		
	}
}
