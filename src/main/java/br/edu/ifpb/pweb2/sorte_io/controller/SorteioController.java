package br.edu.ifpb.pweb2.sorte_io.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;

@Controller
@RequestMapping("/sorteios")
public class SorteioController {

	@RequestMapping("/sorteio")
	public ModelAndView sorteio(ModelAndView model) {
		model.setViewName("sorteios/sorteio");

		return model;
	}

	@RequestMapping("/cadastro")
	public ModelAndView cadastroSorteio(Sorteio sorteio,ModelAndView model) {
		model.addObject("sorteio", sorteio);
		model.setViewName("sorteios/cadastro");

		return model;
	}

}
