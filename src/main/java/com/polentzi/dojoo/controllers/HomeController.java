package com.polentzi.dojoo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.polentzi.dojoo.models.Answer;
import com.polentzi.dojoo.services.MainService;
import com.polentzi.dojoo.models.Question;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	private final MainService mainService;
	public HomeController(MainService mainService) {
		this.mainService=mainService;
	}
	
	@GetMapping("/questions")
	public String HomePage(Model model) {
		
		model.addAttribute("questions",mainService.allQuestions());
		return "index.jsp";
	}
	@GetMapping("/questions/new")
	public String questionForm(@ModelAttribute("question") Question question) {
		return "index1.jsp";
	}
	
	@PostMapping("/questions/new")
	public String createQuestion(@Valid @ModelAttribute("question") com.polentzi.dojoo.models.Question question, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "redirect:/questions/new";
		}else {
			mainService.createQuestion(question);
			return "redirect:/questions/"+question.getId();
		}
	}
	@GetMapping("/questions/{id}")
	public String questionAnswers(@ModelAttribute("answer") Answer answer,@PathVariable("id") Long id,Model model) {
		com.polentzi.dojoo.models.Question question=mainService.findQuestion(id);
		model.addAttribute("question",question);
		model.addAttribute("answers",question.getAnswers());
		return "question_info.jsp";
	}
	@PostMapping("/questions/{id}")
	public String addAnswer(@Valid @ModelAttribute("answer") Answer answer,HttpSession session, BindingResult result,@PathVariable("id") Long id) {
		com.polentzi.dojoo.models.Question question=mainService.findQuestion(id);
		if(result.hasErrors()) {
			return "redirect:/questions/"+id;
		}else {
			mainService.createAnswer(answer);
			mainService.addAnswerToQuestion(answer,question);
		return "redirect:/questions/"+id;
		}
	}
}