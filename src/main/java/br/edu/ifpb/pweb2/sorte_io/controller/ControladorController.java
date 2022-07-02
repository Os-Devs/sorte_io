package br.edu.ifpb.pweb2.sorte_io.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sorte_io.model.Controlador;

import br.edu.ifpb.pweb2.sorte_io.services.controlador.imp.ControladorImp;

@Controller
@RequestMapping("/controladores")
public class ControladorController {

	@Autowired
	ControladorImp controladorService;

	@RequestMapping("/form")
	public ModelAndView getForm(Controlador controlador, ModelAndView model) {
		model.addObject("controlador", controlador);
		model.setViewName("/controlador/formControlador");

		return model;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid Controlador controlador, BindingResult validation, ModelAndView model, RedirectAttributes flash,
							@RequestParam("nick") String username, @RequestParam("senha") String senha) {
		if (validation.hasErrors()) {
			model.setViewName("/controlador/formControlador");
			return model;
		}
		else {
			this.controladorService.saveControlador(controlador, username, senha);

			model.setViewName("redirect:/login");
			flash.addFlashAttribute("mensagem", "Controlador cadastrado com sucesso!");

			return model;
		}
	}

}
