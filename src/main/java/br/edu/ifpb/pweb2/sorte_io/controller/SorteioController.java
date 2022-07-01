package br.edu.ifpb.pweb2.sorte_io.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;
import br.edu.ifpb.pweb2.sorte_io.services.sorteio.imp.SorteioImp;

@Controller
@RequestMapping("/sorteios")
public class SorteioController {

	@Autowired
	SorteioImp sorteioService;

	@RequestMapping("/sorteio")
	public ModelAndView sorteio(ModelAndView model, Principal auth) {
		model.addObject("meusSorteios", sorteioService.sorteiosForUser(auth.getName()));
		model.setViewName("sorteios/sorteio");

		return model;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView cadastroSorteio(Sorteio sorteio, ModelAndView model) {
		model.addObject("sorteio", sorteio);
		model.setViewName("sorteios/cadastro");

		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView save(@Valid Sorteio sorteio, BindingResult validation, @RequestParam(name = "checkboxes", required = false) String value, 
							 ModelAndView model, Principal auth, RedirectAttributes flash) {

		if(validation.hasErrors()) {
			model.setViewName("sorteios/cadastro");

			return model;
		}
		else {
			boolean valid = this.sorteioService.createSorteio(value, sorteio, auth.getName());

			if(valid) {
				model.setViewName("redirect:sorteios/sorteio");
				flash.addFlashAttribute("sucesso", "Sorteio cadastrado com sucesso!");
			}
			else {
				model.setViewName("redirect:sorteios");
				flash.addFlashAttribute("alerta", "Só poderar cadastrar 6 números no sorteio");
			}

			return model;
			
		}
		
	}

}
