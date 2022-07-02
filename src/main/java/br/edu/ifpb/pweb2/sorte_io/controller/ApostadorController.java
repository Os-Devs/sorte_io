package br.edu.ifpb.pweb2.sorte_io.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.services.apostador.imp.ApostadorImpl;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/apostadores")
public class ApostadorController {

	@Autowired
	ApostadorImpl apostadorService;

	@RequestMapping("/form")
	public ModelAndView getForm(Apostador apostador, ModelAndView model) {
		model.addObject("apostador", apostador);
		model.setViewName("/apostador/formApostador");

		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid Apostador apostador, BindingResult validation, ModelAndView model, RedirectAttributes flash,
							@RequestParam(value = "nick") String nick, @RequestParam(value = "senha") String senha) {
		if (validation.hasErrors()) {
			model.setViewName("/apostador/formApostador");
			return model;
		}
		else {
			apostadorService.saveApostador(apostador, nick, senha);

			model.setViewName("redirect:/login");
			flash.addFlashAttribute("mensagem", "Apostador cadastrado com sucesso!");

			return model;
		}
	}
}
