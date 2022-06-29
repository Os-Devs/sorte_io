package br.edu.ifpb.pweb2.sorte_io.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "controladores")
public class Controlador extends Usuario {
    
}
