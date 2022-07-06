package br.edu.ifpb.pweb2.sorte_io.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @Setter
@NoArgsConstructor
@Entity
@Table(name = "controladores")
public class Controlador extends Usuario {

    @OneToMany(
        mappedBy = "criadoPor",
        cascade = CascadeType.ALL
    )
    private List<Sorteio> sorteios;

    public void add(Sorteio sorteio) {
        this.sorteios.add(sorteio);
    }

    public Controlador setSorteios(List<Sorteio> sorteios) {
        this.sorteios = sorteios;
        return this;
    }
}
