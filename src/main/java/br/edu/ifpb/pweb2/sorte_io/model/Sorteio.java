package br.edu.ifpb.pweb2.sorte_io.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;

import br.edu.ifpb.pweb2.sorte_io.model.builder.BuilderSorteio;
import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.Setter;
import lombok.ToString;


@Getter
// @Setter
@NoArgsConstructor
@Entity
@Table(name = "sorteios")
public class Sorteio implements BuilderSorteio {
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	private Integer id;

	@DateTimeFormat(
		pattern = "yyyy-MM-dd'T'HH:mm"
	)
	@NotNull(
		message = "Campo é obrigatório!"
	)
	@Future(
		message = "A realização precisa ser numa data futura"
	)
	private Date dtRealizacao;
	
	@ElementCollection
	private Set<String> numSorteados;

	@NotNull(
		message = "Campo é obrigatório!"
	)
	private BigDecimal valPremiacao;

	@ManyToOne
	private Controlador criadoPor;

	@OneToMany(
		mappedBy = "sorteio",
		cascade = CascadeType.ALL
	)
	@ToString.Exclude
	private List<Aposta> apostas;

	private Boolean realizado;

	@Transient
	private List<Aposta> vencedores = new ArrayList<>();

	@Transient
	private Map<Integer, List<Aposta>> acertos = new HashMap<>();


	public void add(Aposta aposta) {
		this.apostas.add(aposta);
	}
	
	public void sortear() {
		Random gerador = new Random();

		while(this.numSorteados.size() < 6) {
			Integer nSorteado = gerador.nextInt(1, 61);

			this.numSorteados.add(nSorteado.toString());
		}
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

	public List<Apostador> distribuirPremiacao() {
		BigDecimal valor = this.valPremiacao.divide(BigDecimal.valueOf(this.vencedores.size()));
		List<Apostador> apostadores = new ArrayList<>();

		for(Aposta aposta : this.vencedores) {
			aposta.getApostador().setGanhos(aposta.getApostador().getGanhos().add(valor));
			apostadores.add(aposta.getApostador());
		}

		return apostadores;
	}

	// BUILDER

	@Override
	public Sorteio setApostas(List<Aposta> apostas) {
		this.apostas = apostas;

		return this;
	}

	@Override
	public Sorteio setCriadoPor(Controlador criadoPor) {
		this.criadoPor = criadoPor;
		
		return this;
	}

	@Override
	public Sorteio setDtRealizacao(Date dtRealizacao) {
		this.dtRealizacao = dtRealizacao;

		return null;
	}

	@Override
	public Sorteio setId(Integer id) {
		this.id = id;

		return this;
	}

	@Override
	public Sorteio setNumSorteados(Set<String> numSorteados) {
		this.numSorteados = numSorteados;

		return this;
	}

	@Override
	public Sorteio setRealizado(Boolean realizado) {
		this.realizado = realizado;

		return this;
	}

	@Override
	public Sorteio setValPremiacao(BigDecimal valPremiacao) {
		this.valPremiacao = valPremiacao;

		return this;
	}
	
}
