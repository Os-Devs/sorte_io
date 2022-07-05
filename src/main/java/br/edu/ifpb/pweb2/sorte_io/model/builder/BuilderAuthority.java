package br.edu.ifpb.pweb2.sorte_io.model.builder;

import br.edu.ifpb.pweb2.sorte_io.model.User;
import br.edu.ifpb.pweb2.sorte_io.model.Authority.AuthorityId;

public interface BuilderAuthority {
    BuilderAuthority setId(AuthorityId id);
    BuilderAuthority setUsername(User username);
    BuilderAuthority setAuthority(String authority);
}
