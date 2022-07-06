package br.edu.ifpb.pweb2.sorte_io.model.builder;

import java.util.List;

import br.edu.ifpb.pweb2.sorte_io.model.Authority;

public interface BuilderUser {
    BuilderUser setUsername(String username);
    BuilderUser setPassword(String password);
    BuilderUser setEnabled(Boolean enabled);
    BuilderUser setAuthorities(List<Authority> authorities);
}
