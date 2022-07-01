package br.edu.ifpb.pweb2.sorte_io.controller;

import java.security.Principal;

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

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.pweb2.sorte_io.repository.ApostadoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.ApostasRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.SorteiosRepository;
import br.edu.ifpb.pweb2.sorte_io.model.Aposta;
import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;

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
	public ModelAndView aposta(ModelAndView model, Principal auth) {
		model.addObject("minhasApostas", apostasRepository.findByForUser(auth.getName()).get());
		model.setViewName("apostas/aposta");

		return model;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView cadastroAposta(Aposta aposta, ModelAndView model, Principal auth) {
		model.addObject("defaultAposta", new Aposta());
		model.addObject("defaultSorteio", new Sorteio());
		model.addObject("apostasFavoritas", apostasRepository.findByFavoritoTrueForUser(auth.getName()).get());
		model.addObject("sorteiosAbertos", sorteiosRepository.findAll()); // findBySorteiosNaoRealizados().get()
		model.addObject("aposta", aposta);
		model.setViewName("apostas/cadastro");

		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ModelAndView save(Aposta aposta, @RequestParam(name = "checkboxes", required = false) String value, 
							 ModelAndView model, Principal auth, RedirectAttributes flash) {

		if(aposta.getNumSelecionados().isEmpty() || aposta.getNumSelecionados() == null) {
			try {
				Set<String> values = new HashSet<>(Arrays.asList(value.split(",")));

				if(values.size() < 6 || values.size() > 10) {
					model.setViewName("redirect:apostas");
					flash.addFlashAttribute("alerta", "Especifique 6 valores no mínimo e 10 no máximo!");
				}
				else {
					if(apostadoresRepository.findByUser(auth.getName()).isPresent()) {
						Apostador apostador = apostadoresRepository.findByUser(auth.getName()).get();
						Sorteio sorteio = sorteiosRepository.getById(aposta.getNumSorteio());
		
						aposta.setApostador(apostador);
						aposta.setNumSelecionados(values);
						aposta.setSorteio(sorteio);
		
						sorteio.add(aposta);
		
						apostasRepository.save(aposta);
						sorteiosRepository.save(sorteio);
						
						model.setViewName("redirect:apostas/aposta");
						flash.addFlashAttribute("sucesso", "Aposta cadastrada!");
					}
					
				}
			}
			catch(Exception e) {
				model.setViewName("redirect:apostas");
				flash.addFlashAttribute("alerta", "Especifique 6 valores no mínimo e 10 no máximo!");
			}
		}
		else {
			if(apostadoresRepository.findByUser(auth.getName()).isPresent()) {
				Apostador apostador = apostadoresRepository.findByUser(auth.getName()).get();
				Sorteio sorteio = sorteiosRepository.getById(aposta.getNumSorteio());
				Set<String> auxFormat = new HashSet<>();

				for (String element : aposta.getNumSelecionados()) {
					if(element.contains("[")) {
						auxFormat.add(element.replace("[", ""));
					}
					else if(element.contains("]")) {
						auxFormat.add(element.replace("]", ""));
					}
					else {
						auxFormat.add(element);
					}
				}

				aposta.setNumSelecionados(auxFormat);
				aposta.setApostador(apostador);
				aposta.setSorteio(sorteio);

				sorteio.add(aposta);

				apostasRepository.save(aposta);
				sorteiosRepository.save(sorteio);
				
				model.setViewName("redirect:apostas/aposta");
				flash.addFlashAttribute("sucesso", "Aposta cadastrada!");
			}
			
		}

		return model;
	}

}
