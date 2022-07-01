package br.edu.ifpb.pweb2.sorte_io.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sorte_io.repository.SorteiosRepository;

@Controller
public class HomeController {

	@Autowired
	SorteiosRepository sorteiosRepository;
	
	@RequestMapping("/home")
	public ModelAndView home(ModelAndView model) {
		if(sorteiosRepository.findBySorteiosNaoRealizados().isPresent()) {
			model.addObject("sorteiosAbertos", sorteiosRepository.findBySorteiosNaoRealizados().get());
		}

		if(sorteiosRepository.findBySorteiosRealizados().isPresent()) {
			model.addObject("sorteiosFechados", sorteiosRepository.findBySorteiosRealizados().get());
		}
		
		model.setViewName("/home");
        return model;
    }

}
