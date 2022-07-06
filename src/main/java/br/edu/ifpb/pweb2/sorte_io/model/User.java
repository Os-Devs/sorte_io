
package br.edu.ifpb.pweb2.sorte_io.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.ifpb.pweb2.sorte_io.model.builder.BuilderUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements BuilderUser {

    @Id
    private String username;

    private String password;

    private Boolean enabled;

    @OneToMany(
        mappedBy = "username"
    )
    List<Authority> authorities;

    // BUILDER

    @Override
    public User setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
        return this;
    }

    @Override
    public User setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Override
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public User setUsername(String username) {
        this.username = username;
        return this;
    }
}
