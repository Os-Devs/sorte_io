package br.edu.ifpb.pweb2.sorte_io.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView pagamentos(ModelAndView model) {
        model.setViewName("/pagamentos/pagamento");
        return model;
    }
}
