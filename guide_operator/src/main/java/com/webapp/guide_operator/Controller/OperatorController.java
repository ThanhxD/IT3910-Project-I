package com.webapp.guide_operator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.guide_operator.Entities.Operator;
import com.webapp.guide_operator.Service.OperatorService;

@Controller
public class OperatorController {
	@Autowired
	private OperatorService operatorService;
	@RequestMapping(value="/operator/id/{id}", method= RequestMethod.GET)
	public String getOperatorbyId(@PathVariable("id") int id,Model model) {
		Operator operator= operatorService.findOne(id);
		model.addAttribute("operator",operator);
		return "operat";
		
	}
}
