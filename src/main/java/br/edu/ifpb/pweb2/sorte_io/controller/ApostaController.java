package br.edu.ifpb.pweb2.sorte_io.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.ifpb.pweb2.sorte_io.model.Aposta;

@Controller
@RequestMapping("/apostas")
public class ApostaController {

	/* @Autowired
	ApostasRepository apostasRepository; */

	@RequestMapping("/aposta")
	public ModelAndView aposta(ModelAndView model) {
		List<Integer> numeros = new ArrayList<>();

		for (int i = 0; i < 60; i++) {
			numeros.add(i + 1);
		}

		model.addObject("numerosSort", numeros);
		model.setViewName("apostas/aposta");

		return model;
	}

	@RequestMapping("/cadastro")
	public ModelAndView cadastroAposta(Aposta aposta, ModelAndView model) {
		List<Integer> numeros = new ArrayList<>();

		for (int i = 0; i < 60; i++) {
			numeros.add(i + 1);
		}

		model.addObject("numerosSort", numeros);
		model.addObject("aposta", aposta);
		model.setViewName("apostas/cadastro");

		return model;
	}

	/* @RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView save(Aposta aposta, @RequestParam(name = "checkboxes", required = true) String value,ModelAndView model) {
		List<String> aux = Arrays.asList(value.split(";"));
		Set<String> values = new HashSet<>();

		values.addAll(aux);
		values.remove("");
		
		if(values.size() < 6 || values.size() > 10) {
			model.addObject("alerta", "Precisa ser no mínimo 6 número e no máximo 10 números");
		}
		else {
			aposta.setNumSelecionados(values);
		}

		
		return null;
	} */

}
