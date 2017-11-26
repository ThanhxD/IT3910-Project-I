package com.webapp.guide_operator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.guide_operator.Entities.Contract;

import com.webapp.guide_operator.Service.ContractService;

@Controller
public class ContractController {
	@Autowired
	private ContractService contractService;
	
	@RequestMapping(value="/contract/id/{id}", method= RequestMethod.GET)
	public String getContractrbyId(@PathVariable("id") int id,Model model) {
		Contract contract= contractService.findOne(id);
		model.addAttribute("contract",contract);
		return "contract";
		
	}
}
