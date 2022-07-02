package br.edu.ifpb.pweb2.sorte_io.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.pweb2.sorte_io.services.aposta.imp.ApostaImp;
import br.edu.ifpb.pweb2.sorte_io.services.sorteio.imp.SorteioImp;
import br.edu.ifpb.pweb2.sorte_io.model.Aposta;
import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;

@Controller
@RequestMapping("/apostas")
public class ApostaController {

	@Autowired
	ApostaImp apostaService;

	@Autowired
	SorteioImp sorteioService;

	@RequestMapping("/aposta")
	public ModelAndView aposta(ModelAndView model, Principal auth) {
		model.addObject("minhasApostas", apostaService.findByUser(auth.getName()));

		model.setViewName("/apostas/aposta");

		return model;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView cadastroAposta(Aposta aposta, ModelAndView model, Principal auth) {
		model.addObject("defaultAposta", new Aposta());
		model.addObject("defaultSorteio", new Sorteio());
		model.addObject("apostasFavoritas", apostaService.findFavoritos(auth.getName()));
		model.addObject("sorteiosAbertos", sorteioService.sorteiosAbertos());
		model.addObject("aposta", aposta);
		model.setViewName("/apostas/cadastro");

		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ModelAndView save(Aposta aposta, @RequestParam(name = "checkboxes", required = false) String value, 
							 ModelAndView model, Principal auth, RedirectAttributes flash) {

		boolean valid = apostaService.createAposta(value, aposta, auth.getName());

		if(valid) {
			model.setViewName("redirect:/apostas/aposta");
			flash.addFlashAttribute("sucesso", "Aposta cadastrada!");
		}
		else {
			model.setViewName("redirect:/apostas");
			flash.addFlashAttribute("alerta", "Especifique 6 valores no mínimo e 10 no máximo!");
		}

		return model;
	}

}
