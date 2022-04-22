package br.edu.ifpb.pweb2.sorte_io.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "controladores")
public class Controlador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name             = "id_controlador")
    private Integer          id;

    @NotEmpty(message        = "Campo é obrigatório!")
    private String           nome;

    @NotEmpty(message        = "Campo é obrigatório!")
    @CPF
    private String           cpf;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name            = "dt_nascimento")
    private Date            dtNascimento;

    @NotEmpty(message        = "Campo é obrigatório!")
    @Email
    private String           email;

    @NotEmpty(message        = "Campo é obrigatório!")
    @Min(value               = 8
        ,message             = "A senha precisa conter mais que 8 caracteres")
    private String           senha;
}
