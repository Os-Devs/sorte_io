package br.edu.ifpb.pweb2.sorte_io.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.edu.ifpb.pweb2.sorte_io.repository.ApostadoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.ApostasRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.SorteiosRepository;
import br.edu.ifpb.pweb2.sorte_io.model.Aposta;
import br.edu.ifpb.pweb2.sorte_io.model.Apostador;

@Controller
@RequestMapping("/apostas")
public class ApostaController {

	@Autowired
	ApostasRepository apostasRepository;

	@Autowired
	SorteiosRepository sorteiosRepository;

	@Autowired
	ApostadoresRepository apostadoresRepository;

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

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView save(Aposta aposta, @RequestParam(name = "checkboxes") String value, ModelAndView model, RedirectAttributes flash, Principal auth) {
		Set<String> values = new HashSet<>(Arrays.asList(value.split(",")));

		if(values.size() < 6 || values.size() > 10) {
			//ERROR
		}
		else {
			if(apostadoresRepository.findByUser(auth.getName()).isPresent()) {
				Apostador apostador = apostadoresRepository.findByUser(auth.getName()).get();
			}
			
		}

		
		
		flash.addFlashAttribute("Teste", values);

		model.setViewName("redirect:apostas/cadastro");

		return model;
	}

}
