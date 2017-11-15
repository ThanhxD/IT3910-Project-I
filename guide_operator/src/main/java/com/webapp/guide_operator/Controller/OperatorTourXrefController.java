package com.webapp.guide_operator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.guide_operator.Entities.OperatorTourXref;
import com.webapp.guide_operator.Service.OperatorTourXrefService;

@Controller
public class OperatorTourXrefController {
	@Autowired
	private OperatorTourXrefService operatorTourXrefService;
	
	@RequestMapping(value="/operatortour/id/{id}", method= RequestMethod.GET)
	public String getOperatorTourbyId(@PathVariable("id") int id,Model model) {
		OperatorTourXref operatortour= operatorTourXrefService.findOne(id);
		model.addAttribute("operatortour",operatortour);
		return "operatortour";
		
	}
}
