package br.edu.ifpb.pweb2.sorte_io.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "apostas")
public class Aposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name             = "id_aposta")
    private Integer          id;

    private List<Integer>   numSelecionados;
    private int             quantUso;

    @ManyToOne
    @JoinColumn(name        = "id_apostador")
    private Apostador       apostador;

    @ManyToOne
    @JoinColumn(name        = "id_sorteio")
    private Sorteio         sorteio;
}