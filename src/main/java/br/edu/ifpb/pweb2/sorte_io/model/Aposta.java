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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apostas")
public class Aposta {
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

	private boolean favorito;

	@ManyToOne
	private Apostador apostador;

	@ManyToOne
	private Sorteio sorteio;

	@Transient
	private Integer numSorteio;

	public void add(String value) {
		this.numSelecionados.add(value);
	}
}
