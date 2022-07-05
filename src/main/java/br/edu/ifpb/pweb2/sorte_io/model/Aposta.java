package br.edu.ifpb.pweb2.sorte_io.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.edu.ifpb.pweb2.sorte_io.model.builder.BuilderAposta;
import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.Setter;

@Getter
// @Setter
@NoArgsConstructor
@Entity
@Table(name = "apostas")
public class Aposta implements BuilderAposta {
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	@Column(
		name = "id_aposta"
	)
	private Integer id;

	@ElementCollection
	private Set<String> numSelecionados;

	private Boolean favorito;

	@ManyToOne
	private Apostador apostador;

	@ManyToOne
	private Sorteio sorteio;

	@Transient
	private Integer numSorteio;

	public void add(String value) {
		this.numSelecionados.add(value);
	}

	// BUILDER

	@Override
	public Aposta setApostador(Apostador apostador) {
		this.apostador = apostador;
		return this;
	}

	@Override
	public Aposta setFavorito(Boolean favorito) {
		this.favorito = favorito;
		return this;
	}

	@Override
	public Aposta setId(Integer id) {
		this.id = id;
		return this;
	}

	@Override
	public Aposta setNumSelecionados(Set<String> numSelecionados) {
		this.numSelecionados = numSelecionados;
		return this;
	}

	@Override
	public Aposta setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
		return this;
	}

	@Override
	public BuilderAposta setNumSorteio(Integer numSorteio) {
		this.numSorteio = numSorteio;
		return this;
	}
}
