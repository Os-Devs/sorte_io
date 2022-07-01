package br.edu.ifpb.pweb2.sorte_io.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;
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

import br.edu.ifpb.pweb2.sorte_io.model.Controlador;
import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;
import br.edu.ifpb.pweb2.sorte_io.repository.ControladoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.SorteiosRepository;

@Controller
@RequestMapping("/sorteios")
public class SorteioController {

	@Autowired
	SorteiosRepository sorteiosRepository;

	@Autowired
	ControladoresRepository controladoresRepository;

	@RequestMapping("/sorteio")
	public ModelAndView sorteio(ModelAndView model, Principal auth) {
		model.addObject("meusSorteios", sorteiosRepository.findBySorteiosForUser(auth.getName()).get());
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
	@Transactional
	public ModelAndView save(@Valid Sorteio sorteio, BindingResult validation, @RequestParam(name = "checkboxes", required = false) String value, 
							 ModelAndView model, Principal auth, RedirectAttributes flash) {

		if(validation.hasErrors()) {
			model.setViewName("sorteios/cadastro");

			return model;
		}
		else {
			if(value != null) {
				Set<String> values = new HashSet<>(Arrays.asList(value.split(",")));

				if(values.size() < 6 || values.size() > 6) {
					model.setViewName("redirect:sorteios");
					flash.addFlashAttribute("mensagem", "Só poderar cadastrar 6 números no sorteio");

					return model;
				}
				else {
					sorteio.setNumSorteados(values);

					if(controladoresRepository.findByUser(auth.getName()).isPresent()) {
						Controlador criador = controladoresRepository.findByUser(auth.getName()).get();

						sorteio.setCriadoPor(criador);
						criador.add(sorteio);

						sorteiosRepository.save(sorteio);
						controladoresRepository.save(criador);
					}

					model.setViewName("redirect:home");

					return model;
				}
			}

			model.setViewName("redirect:sorteios");
			flash.addFlashAttribute("mensagem", "Campo é obrigatório");

			return model;
			
		}
		
	}

}
