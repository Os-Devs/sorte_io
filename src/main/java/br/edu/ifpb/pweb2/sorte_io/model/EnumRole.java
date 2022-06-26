package br.edu.ifpb.pweb2.sorte_io.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EnumRole {
    CONTROLADOR("ROLE_CONTROLLER"),
    APOSTADOR("ROLE_USER");

    private String value;
}
