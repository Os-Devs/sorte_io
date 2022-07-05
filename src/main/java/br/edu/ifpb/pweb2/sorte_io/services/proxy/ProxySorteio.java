package br.edu.ifpb.pweb2.sorte_io.services.proxy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;
import br.edu.ifpb.pweb2.sorte_io.services.sorteio.imp.SorteioImp;

@Service
@Scope("singleton")
public class ProxySorteio {
    
    @Autowired
    private SorteioImp sorteioService;

    private List<Sorteio> sorteiosAbertos = new ArrayList<>();
    private List<Sorteio> sorteiosFechados = new ArrayList<>();

    public List<Sorteio> getAbertos() {
        if(this.sorteiosAbertos.isEmpty()) {
            this.sorteiosAbertos = sorteioService.sorteiosAbertos();
        }

        return this.sorteiosAbertos;
    }

    public List<Sorteio> getFechados() {
        if(this.sorteiosFechados.isEmpty()) {
            this.sorteiosFechados = sorteioService.sorteiosFechados();
        }

        return this.sorteiosFechados;
    }

    public void AttProxy() {
        this.sorteiosAbertos = this.sorteioService.sorteiosAbertos();
        this.sorteiosFechados = this.sorteioService.sorteiosFechados();
    }
}
