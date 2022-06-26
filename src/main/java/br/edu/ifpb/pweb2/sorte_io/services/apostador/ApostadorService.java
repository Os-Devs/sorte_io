package br.edu.ifpb.pweb2.sorte_io.services.apostador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.pweb2.sorte_io.repository.ApostadoresRepository;

@RestController
@RequestMapping(value = "/api")
public class ApostadorService {
    
    @Autowired
    ApostadoresRepository apostadoresRepository;

    

}
