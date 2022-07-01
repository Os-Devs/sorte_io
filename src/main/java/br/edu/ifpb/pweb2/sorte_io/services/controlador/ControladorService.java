package br.edu.ifpb.pweb2.sorte_io.services.controlador;

import br.edu.ifpb.pweb2.sorte_io.model.Controlador;

public interface ControladorService {
    void saveControlador(Controlador controlador, String username, String senha);
    Controlador getControladorForUser(String username);
}
