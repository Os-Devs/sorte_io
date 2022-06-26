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
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Usuario implements Serializable {
	@Id
	@GeneratedValue(
		strategy = GenerationType.TABLE
	)
	private Integer id;

	@NotEmpty(
		message	= "Campo é obrigatório!"
	)
	private String nome;

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

	@NotEmpty(message = "Campo é obrigatório")
	@Transient
	private String nick;

	@NotEmpty(
		message = "Campo é obrigatório!"
	)
	@Size(
		min = 8,
		message = "A senha precisa ter no mínimo 8 caracteres"
	)
	@Transient
	private String senha;

    @OneToOne
    @JoinColumn(
		name = "username",
		referencedColumnName = "username"
	)
    private User user;
   
}
