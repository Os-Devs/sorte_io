package br.edu.ifpb.pweb2.sorte_io.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sorteios")
public class Sorteio {
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	private Integer id;

	@DateTimeFormat(
		pattern = "dd/MM/yyyy HH:mm"
	)
	@Future(
		message = "A realização precisa ser numa data futura"
	)
	private LocalDateTime dtRealizacao;
	
	@ElementCollection
	private Set<String> numSorteados;

	@NumberFormat(
		pattern = "###.###,##"
	)
	private BigDecimal valPremiacao;

	@OneToOne
	@JoinColumn(
		name = "id_controlador"
	)
	private Controlador criadoPor;

	@OneToMany(
		mappedBy = "sorteio",
		cascade = CascadeType.ALL
	)
	@JoinColumn(
		name = "id_aposta"
	)
	@ToString.Exclude
	private List<Aposta> apostas;

	@Transient
	private List<Aposta> vencedores;

	@Transient
	private Map<Integer, List<Aposta>> acertos;

	@Transient
	private Set<Integer> auxSet;

	
	public void sortear() {
		Random gerador = new Random();

		while(this.numSorteados.size() < 6) {
			Integer nSorteado = gerador.nextInt(1, 61);

			this.numSorteados.add(nSorteado.toString());
		}
	}

	public void sortear(Set<String> manual) {
		this.numSorteados = manual;
	}
	
	public void testarVencedores() {
		List<String> transform = new ArrayList<>();
		transform.addAll(this.numSorteados);

		for(Aposta aposta : this.apostas) {
			int countAcertos = 0;

			for(int i = 0; i < transform.size(); i++) {
				if(aposta.getNumSelecionados().contains(transform.get(i))) {
					countAcertos++;
				}
			}

			if(this.acertos.containsKey(countAcertos)) {
				this.acertos.get(countAcertos).add(aposta);
			}
			else {
				this.acertos.put(countAcertos, new ArrayList<Aposta>());
				this.acertos.get(countAcertos).add(aposta);
			}
		}
	}

	public void vencedores() {
		this.testarVencedores();

		for(int i = 6; i >= 0; i--) {
			if(this.acertos.containsKey(i)) {
				this.vencedores.addAll(this.acertos.get(i));
				break;
			}
		}
	}

	public void distribuirPremiacao() {
		BigDecimal valor = this.valPremiacao.divide(BigDecimal.valueOf(this.vencedores.size()));

		for(Aposta aposta : this.vencedores) {
			aposta.getApostador().setSaldo(aposta.getApostador().getSaldo().add(valor));
		}
	}
	
}
