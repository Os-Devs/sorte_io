package br.edu.ifpb.pweb2.sorte_io.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/apostas")
public class ApostaController {

	@RequestMapping("/aposta")
	public ModelAndView aposta(ModelAndView model) {
		List<Integer> numeros = new ArrayList<>();

		for (int i = 0; i < 60; i++) {
			numeros.add(i + 1);
		}

		model.addObject("numerosSort", numeros);
		model.setViewName("/aposta");

		return model;
	}

	@RequestMapping("/aposta-cadastro")
	public ModelAndView cadastroAposta(ModelAndView model) {
		model.setViewName("/aposta-cadastro");

		return model;
	}

}
