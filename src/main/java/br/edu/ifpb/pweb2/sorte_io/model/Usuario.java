package br.edu.ifpb.pweb2.sorte_io.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Usuario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer          id;

	@NotEmpty(message        = "Campo é obrigatório!")
	private String           nome;

	@CPF(message             = "Digite um CPF válido")
	private String           cpf;

	@DateTimeFormat(pattern  = "dd/MM/yyyy")
	@NotNull(message         = "Campo é obrigatório")
	@Past(message            = "Não pode ser uma data futura")
	private Date             dtNascimento;

   /*  @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user; */
   
}
