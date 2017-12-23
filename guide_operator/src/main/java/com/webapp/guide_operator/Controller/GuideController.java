package com.webapp.guide_operator.Controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.webapp.guide_operator.Entities.Guide;
import com.webapp.guide_operator.Entities.Tour;
import com.webapp.guide_operator.Entities.User;
import com.webapp.guide_operator.Repository.UserRepository;
import com.webapp.guide_operator.Service.GuideService;
import com.webapp.guide_operator.Service.OperatorService;
import com.webapp.guide_operator.Service.TourService;

@Controller
public class GuideController {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private GuideService guideService;
	@Autowired
	private TourService tourService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OperatorService operatorService;
	@PostMapping("/guide/changeinfo")
	public String updateInfo(HttpServletRequest request, Model model) {
		if(request.isUserInRole("ROLE_GUIDE")) {
			String guidename = request.getParameter("guidename");
			String address = request.getParameter("address");
			String phonenumber = request.getParameter("phonenumber");
			String cardnumber = request.getParameter("cardnumber");
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Guide guide = guideService.findByUserId(user.getId());
			guide.getUser().setFullname(guidename);
			guide.getUser().setAddress(address);
			guide.getUser().setPhonenumber(phonenumber);
			guide.setCardnumber(cardnumber);
			model.addAttribute("guide", guide);
			return "guide-info";
			
		}
		else {
			return "index";
		}

	}

	@GetMapping("/guide/findtour")
	public String searchTour(HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_GUIDE")) {
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Guide guide = guideService.findByUserId(user.getId());
			model.addAttribute("guide", guide);
			return "find-tour";
		} else
			return "index";
	}

	@PostMapping("/guide/search")
	public String requestGuide(HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_GUIDE")) {
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Guide guide = guideService.findByUserId(user.getId());
			model.addAttribute("guide", guide);

			String location = request.getParameter("location");
			String tourName = request.getParameter("tourname");
			String operatorName = request.getParameter("operator");
			String day = request.getParameter("day");

			List<Tour> tours = tourService.findTour(guide, tourName, operatorName, location, day);
			model.addAttribute("guide", guide);
			model.addAttribute("tours", tours);
			return "find-tour";
		} else
			return "index";
	}

	@GetMapping("/guide/info")
	public String info(HttpServletRequest request, Model model) {
		Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Guide guide = guideService.findByUserId(user.getId());
		model.addAttribute("guide", guide);
		return "guide-info";
	}

	@GetMapping("/guide")
	public String getTourbyId(HttpServletRequest request, Model model) {
		Principal principal = request.getUserPrincipal();
		User user = userRepository.findByUsername(principal.getName());
		Guide guide = guideService.findByUserId(user.getId());
		model.addAttribute("guide", guide);
		return "TourGuide";
	}

	@GetMapping("/danhsachhuongdanvien")
	public String adminCon(HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			Page<Guide> guides = guideService.findAll(new PageRequest(0, 5));
			model.addAttribute("guides", guides);
			model.addAttribute("page", 1);
			model.addAttribute("previouspage", 0);
			model.addAttribute("nextpage", 2);
			return "danhsachhuongdanvien";
		}
		if (request.isUserInRole("ROLE_GUIDE")) {
			return "ERROR";
		}
		return "ERROR";
	}

	@GetMapping("/danhsachhuongdanvien/page/{id}")
	public String adminCon1(@PathVariable("id") int id, HttpServletRequest request, Model model) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			if (id > 0) {
				Page<Guide> guides = guideService.findAll(new PageRequest(id - 1, 5));
				model.addAttribute("guides", guides);
				model.addAttribute("page", id);
				model.addAttribute("previouspage", id - 1);
				model.addAttribute("nextpage", id + 1);
				return "danhsachhuongdanvien";
			} else {
				Page<Guide> guides = guideService.findAll(new PageRequest(0, 5));
				model.addAttribute("guides", guides);
				model.addAttribute("page", 1);
				model.addAttribute("previouspage", 0);
				model.addAttribute("nextpage", 2);
				return "danhsachhuongdanvien";
			}
		}
		if (request.isUserInRole("ROLE_GUIDE")) {
			return "ERROR";
		}
		return "ERROR";
	}

	@GetMapping(value = "/guide/delete/{page}/{id}")
	public String delete(@PathVariable("page") int page, @PathVariable("id") int id, Model model) {
		guideService.delete(id);
		Page<Guide> guides = guideService.findAll(new PageRequest(page - 1, 5));
		model.addAttribute("guides", guides);
		model.addAttribute("page", page);
		model.addAttribute("previouspage", page - 1);
		model.addAttribute("nextpage", page + 1);
		return "danhsachhuongdanvien";
	}
	@PostMapping("/guide/changepassword")
	public String changePass(HttpServletRequest request,Model model) {
		if(request.isUserInRole("ROLE_GUIDE")) {
			String oldpassword = request.getParameter("old-passwd");
			String newpassword = request.getParameter("new-passwd");
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Guide guide = guideService.findByUserId(user.getId());
			if(BCrypt.checkpw(oldpassword, user.getPassword())) {
				guide.getUser().setPassword(passwordEncoder.encode(newpassword));
				model.addAttribute("status","Đổi password thành công");
			}
			else {
				model.addAttribute("status","Đổi password thất bại do sai mật khẩu cũ");
			}
			guideService.save(guide);
			model.addAttribute("guide", guide);
			return "guide-info";
		}
		else {
			return "index";
		}
	}
	@GetMapping("/guide/{operatorid}")
	public String showOperatorInfo(@PathVariable("operatorid") int operatorid,HttpServletRequest request, Model model) {
		if(request.isUserInRole("ROLE_GUIDE")) {
			Principal principal = request.getUserPrincipal();
			User user = userRepository.findByUsername(principal.getName());
			Guide guide = guideService.findByUserId(user.getId());
			model.addAttribute("operator",operatorService.findOne(operatorid));
			model.addAttribute("guide", guide);
			return "showingoperator-info";
		}
		else return "index";
	}
}
