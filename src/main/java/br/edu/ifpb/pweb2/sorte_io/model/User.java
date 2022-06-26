
package br.edu.ifpb.pweb2.sorte_io.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @NotEmpty(
        message = "Campo é obrigatório!"
    )
    private String username;

    @NotEmpty(
        message = "Campo é obrigatório!"
    )
    @Min(
        value = 8,
        message = "A senha precisa conter mais que 8 caracteres"
    )
    private String password;

    private Boolean enabled;

    @OneToMany(
        mappedBy = "username"
    )
    @ToString.Exclude
    List<Authority> authorities;
}
