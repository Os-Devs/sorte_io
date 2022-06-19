package br.edu.ifpb.pweb2.sorte_io.model;

/* import java.util.Date;

import javax.persistence.Column; */
import javax.persistence.Entity;
/* import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; */
import javax.persistence.Table;
/* import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat; */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "controladores")
public class Controlador extends Usuario {
    /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name             = "id_apostador")
    private Integer          id;

    @NotEmpty(message        = "Campo é obrigatório!")
    private String           nome;

    @CPF(message             = "Digite um CPF válido")
    private String           cpf;

    @Column(name             = "dt_nascimento")
    @DateTimeFormat(pattern  = "dd/MM/yyyy")
    @NotNull(message         = "Campo é obrigatório")
    @Past(message            = "Não pode ser uma data futura")
    private Date             dtNascimento;

    @NotEmpty(message        = "Campo é obrigatório!")
    @Email(message           = "Digite um e-mail válido")
    private String           email;

    @NotEmpty(message        = "Campo é obrigatório!")
    @Min(value               = 8 
        ,message             = "A senha precisa conter mais que 8 caracteres")
    private String           senha; */
}
