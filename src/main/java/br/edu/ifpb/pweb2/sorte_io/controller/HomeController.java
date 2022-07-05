package br.edu.ifpb.pweb2.sorte_io.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sorte_io.services.proxy.ProxySorteio;
import br.edu.ifpb.pweb2.sorte_io.services.sorteio.imp.SorteioImp;

@Controller
public class HomeController {

	@Autowired
	SorteioImp sorteioService;

	@Autowired
	ProxySorteio proxy;
	
	@RequestMapping("/home")
	public ModelAndView home(ModelAndView model) {

		model.addObject("sorteiosAbertos", proxy.getAbertos());
		model.addObject("sorteiosFechados", proxy.getFechados());
		
		model.setViewName("/home");
        return model;
    }

}
