package com.brq.agenda.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brq.agenda.models.Contato;
import com.brq.agenda.service.ContatoService;

@Controller
public class ContatoController {

	@Autowired
	private ContatoService service;

	@GetMapping("/")
	public String findAll(@RequestParam(name = "search", required = false) String search, Model model) {
		model.addAttribute("contatos",
				search == null ? service.findAll() : service.findSearchAll(search.toLowerCase()));
		model.addAttribute("search", search);
		return "index";
	}

	@GetMapping("/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		return new ModelAndView("view", "contato", service.findOne(id));
	}

	@GetMapping("/add")
	public ModelAndView add(Contato contato) {
		return new ModelAndView("/add", "contato", contato);
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		return add(service.findOne(id));
	}

	@PostMapping("/save")
	public String save(@Valid Contato contato, BindingResult result, RedirectAttributes attrs) {
		try {
			if (result.hasErrors())
				return "/add";
			if (contato.getAvatar() == null)
				contato.setAvatar("https://image.flaticon.com/icons/png/128/149/149072.png");
			boolean inserted = contato.getId() == null;
			service.save(contato);
			attrs.addFlashAttribute("messages", "Contato " + (inserted ? "Registrado" : "Alterado") + " com sucesso!");
			return "redirect:/edit/" + contato.getId();
		} catch (ApplicationContextException e) {
			attrs.addFlashAttribute("messages", e.getMessage());
		}
		return "redirect:/";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attrs) {
		try {
			service.delete(id);
			attrs.addFlashAttribute("messages", "Contato removido com sucesso!");
		} catch (ApplicationContextException e) {
			attrs.addFlashAttribute("messages", e.getMessage());
		}
		return "redirect:/";
	}
}
