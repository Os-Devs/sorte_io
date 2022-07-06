package br.edu.ifpb.pweb2.sorte_io.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
// import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.Setter;

@Getter
// @Setter
@NoArgsConstructor
@Entity
@Table(name = "apostadores")
public class Apostador extends Usuario {
	
	private BigDecimal saldo = BigDecimal.ZERO;

	@OneToMany(
		mappedBy = "apostador",
		cascade = CascadeType.ALL
	)
	private List<Aposta> apostas;

	public Apostador setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
		return this;
	}
}
