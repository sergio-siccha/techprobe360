package com.prueba.tecnica.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USUARIO")
public class UsuarioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USU_ID")
    private Integer id;

    @Column(name = "USU_NOMBRE", nullable = false)
    private String username; // Nombre de usuario

    @Column(name = "USU_APE_PAT", nullable = false)
    private String apellidoPaterno;

    @Column(name = "USU_APE_MAT", nullable = false)
    private String apellidoMaterno;

    @Column(name = "USU_CLAVE", nullable = false)
    private String clave;

    @ManyToMany
    @JoinTable(
            name = "USUARIO_ROL",
            joinColumns = @JoinColumn(name = "USU_ID_REF"),
            inverseJoinColumns = @JoinColumn(name = "ROL_ID_REF")
    )
    private Set<RolDTO> rol = new HashSet<>();
}
