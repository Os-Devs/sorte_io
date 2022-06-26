package br.edu.ifpb.pweb2.sorte_io.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sorteios")
public class SorteioController {

	@RequestMapping("/sorteio")
	public ModelAndView sorteio(ModelAndView model) {
		model.setViewName("/sorteio");

		return model;
	}

	@RequestMapping("/sorteio-cadastro")
	public ModelAndView cadastroSorteio(ModelAndView model) {
		model.setViewName("/sorteio-cadastro");

		return model;
	}

}
