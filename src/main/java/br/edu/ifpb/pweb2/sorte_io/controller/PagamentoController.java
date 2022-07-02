package br.edu.ifpb.pweb2.sorte_io.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sorte_io.repository.SorteiosRepository;

@Controller
public class PagamentoController {
    

    @RequestMapping("/pagamento")
    public ModelAndView pagamentos(ModelAndView model) {
        model.setViewName("/pagamentos/pagamento");
        return model;
    }
}
