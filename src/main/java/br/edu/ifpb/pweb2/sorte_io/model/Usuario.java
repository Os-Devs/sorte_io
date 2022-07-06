package br.edu.ifpb.pweb2.sorte_io.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import br.edu.ifpb.pweb2.sorte_io.model.builder.BuilderUsuario;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;

import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
// @Setter
public abstract class Usuario implements Serializable, BuilderUsuario {
	@Id
	@GeneratedValue(
		strategy = GenerationType.TABLE
	)
	private Integer id;

	@NotEmpty(
		message	= "Campo é obrigatório!"
	)
	private String nome;

	@CPF
	private String cpf;

	@DateTimeFormat(
		pattern = "yyyy-MM-dd"
	)
	@NotNull(
		message = "Campo é obrigatório"
	)
	@Past(
		message = "Não pode ser uma data futura"
	)
	private Date dtNascimento;

    @OneToOne
	@JoinColumn(
		name = "username"
	)
	@ToString.Exclude
    private User user;

	// BUILDER

	@Override
	public Usuario setId(Integer id) {
		this.id = id;
		return this;
	}

	@Override
	public Usuario setNome(String nome) {
		this.nome = nome;
		return this;
	}

	@Override
	public Usuario setCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}

	@Override
	public Usuario setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
		return this;
	}

	@Override
	public Usuario setUser(User user) {
		this.user = user;
		return this;
	}
   
}
