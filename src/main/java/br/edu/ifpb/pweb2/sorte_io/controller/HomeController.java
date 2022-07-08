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
	
	@RequestMapping("/home")
	public ModelAndView home(ModelAndView model) {

		model.addObject("sorteiosAbertos", ProxySorteio.getInstance(this.sorteioService).getAbertos());
		model.addObject("sorteiosFechados", ProxySorteio.getInstance(this.sorteioService).getFechados());
		
		model.setViewName("/home");
        return model;
    }

}
