package br.edu.ifpb.pweb2.sorte_io.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.repository.ApostadoresRepository;
import br.edu.ifpb.pweb2.sorte_io.services.apostador.imp.ApostadorImpl;

import java.math.BigDecimal;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
    ApostadoresRepository apostadoresRepository;

	@Autowired
	ApostadorImpl apostadorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pagamentos(ModelAndView model) {
		model.setViewName("/pagamentos/pagamento");
		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView save(ModelAndView model, Principal auth, RedirectAttributes flash, @RequestParam(value = "saldo") BigDecimal saldo, @RequestParam(value = "radio") String radio) {
		Boolean valid = apostadorService.attSaldo(auth.getName(), saldo, radio);
		
		if(!valid) {
			model.setViewName("redirect:/pagamento");
			flash.addFlashAttribute("alerta", "Valor inválido!");
			
		} else {
			model.setViewName("redirect:/apostas/aposta");
			flash.addFlashAttribute("sucesso", "Saldo Adicionado!");
		}

		return model;
	}
}
