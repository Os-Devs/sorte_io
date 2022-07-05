
package br.edu.ifpb.pweb2.sorte_io.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifpb.pweb2.sorte_io.model.builder.BuilderAuthority;
import br.edu.ifpb.pweb2.sorte_io.model.builder.BuilderAuthorityId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements BuilderAuthority {

    @EmbeddedId
    private AuthorityId id;

    @ManyToOne
    @JoinColumn(
        name = "username", 
        referencedColumnName = "username",
        insertable = false,
        updatable = false
    )
    private User username;

    @Column(
        name = "authority",
        insertable = false,
        updatable = false
    )
    private String authority;

    // BUILDER

    @Override
    public Authority setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    @Override
    public Authority setId(AuthorityId id) {
        this.id = id;
        return this;
    }

    @Override
    public Authority setUsername(User username) {
        this.username = username;
        return this;
    }

    @Embeddable
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorityId implements Serializable, BuilderAuthorityId {
        private String username;
        private String authority;

        //BUILDER

        @Override
        public BuilderAuthorityId setAuthority(String authority) {
            this.authority = authority;
            return this;
        }
        @Override
        public BuilderAuthorityId setUsername(String username) {
            this.username = username;
            return this;
        }
    }

}
