package br.edu.ifpb.pweb2.sorte_io.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView cadastroSorteio(Sorteio sorteio, ModelAndView model) {
		List<Integer> numeros = new ArrayList<>();

		for (int i = 0; i < 60; i++) {
			numeros.add(i + 1);
		}
		
		model.addObject("numerosSort", numeros);
		model.addObject("sorteio", sorteio);
		model.setViewName("sorteios/cadastro");

		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView save(Sorteio sorteio, @RequestParam(name = "checkboxes") String value, ModelAndView model, RedirectAttributes flash) {
		Set<String> values = new HashSet<>(Arrays.asList(value.split(",")));
		
		flash.addFlashAttribute("Teste", values);

		model.setViewName("redirect:sorteios/cadastro");

		return model;
	}

}
