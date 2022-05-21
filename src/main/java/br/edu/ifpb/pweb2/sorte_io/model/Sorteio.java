package br.edu.ifpb.pweb2.sorte_io.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/* import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType; */
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
/* @Entity
@Table(name = "sorteios") */
public class Sorteio {
    /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) */
    private Integer          id;

    /* @Temporal(TemporalType.TIMESTAMP) */
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Future(message         = "A realização precisa ser numa data futura")
    private Date            dtRealizacao;

    private List<Integer>   numSorteados;

    @NumberFormat(pattern   = "###.#00,00")
    private BigDecimal      valPremiacao;

    /* @OneToOne
    @JoinColumn(name        = "id_controlador") */
    private Controlador     criadoPor;

    /* @OneToOne
    @JoinColumn(name        = "id_apostador") */
    private Apostador       vencedor;

    /* @ManyToMany
    @JoinColumn(name        = "id_apostador") */
    private List<Apostador> participantes;

    
    public void sortear() {
        int i = 0;

        while (i < 10) {
            if (this.numSorteados.size() < 10) {
                this.testeDistinto();
                i++;
            }
        }
    }

    public void sortear(List<Integer> manual) {
        this.numSorteados = manual;
    }

    public int sortearN() {
        Random gerador = new Random();
        int    nSorte  = gerador.nextInt(1, 61);

        return nSorte;
    }

    public void testeDistinto() {
        boolean teste    = false;
        int     sorteado = this.sortearN();

        for (int i = 0; i < this.numSorteados.size(); i++) {
            if (this.numSorteados.get(i) == sorteado) {
                teste = true;
            }
        }

        if (!teste) {
            this.numSorteados.add(sorteado);
        }
    }
}
