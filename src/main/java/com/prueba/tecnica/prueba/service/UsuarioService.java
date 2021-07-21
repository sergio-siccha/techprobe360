package com.prueba.tecnica.prueba.service;

import com.prueba.tecnica.prueba.constant.HttpResponseCte;
import com.prueba.tecnica.prueba.dao.RolDAO;
import com.prueba.tecnica.prueba.dao.UsuarioDAO;
import com.prueba.tecnica.prueba.dto.RolDTO;
import com.prueba.tecnica.prueba.dto.UsuarioDTO;
import com.prueba.tecnica.prueba.models.RequestCrearUsuario;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Service
public class UsuarioService {

    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;

    @Autowired
    public UsuarioService (UsuarioDAO usuarioDAO, RolDAO rolDAO) {
        this.usuarioDAO = usuarioDAO;
        this.rolDAO = rolDAO;
    }

    @Transactional
    public Object getAll() {
        try {
            log.info("Buscando todos los/las usuarios");

            return this.usuarioDAO.findAll();
        } catch(Exception e) {
            log.error("Ocurrió un error al realizar la busqueda de usuarios.");
            return HttpResponseCte.ERROR_GENERAL.getCodigo();
        }
    }

    @Transactional
    public Object setOne(RequestCrearUsuario request) {
        try {
            log.info("Creando el nuevo usuario [{}]", request.getNombre());

            if (this.usuarioDAO.existsByUsername(request.getNombre())) {
                log.error("Ya existe un usuario con el nombre proporcionado.");
                throw new RuntimeException();
            }

            UsuarioDTO userTmp = new UsuarioDTO();
            userTmp.setUsername(request.getNombre());
            userTmp.setApellidoPaterno(request.getApellidoPaterno());
            userTmp.setApellidoMaterno(request.getApellidoMaterno());
            userTmp.setClave(request.getClave());

            RolDTO rol = (request.isAdmin())
                    ? this.rolDAO.findByNombre("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("No existe ROLE_ADMIN."))
                    : this.rolDAO.findByNombre("ROLE_USER").orElseThrow(() -> new RuntimeException("No existe ROLE_USER."));

            userTmp.getRol().add(rol);

            this.usuarioDAO.save(userTmp);

            log.info("Usuario [{}] creado exitosamente.", request.getNombre());

            return userTmp;
        } catch (Exception e) {
            log.error("Ocurrió un error al registrar el nuevo usuario.");
            return HttpResponseCte.ERROR_GENERAL.getCodigo();
        }
    }

    @Transactional
    public Object updateOne(RequestCrearUsuario request) {
        try {
            log.info("Actualizando el usuario con ID [{}]", request.getId());

            if (!this.usuarioDAO.existsById(request.getId())) {
                log.error("No existe ningun usuario con el ID proporcionado.");
                throw new RuntimeException();
            }

            if (this.usuarioDAO.existsByUsername(request.getNombre())) {
                log.error("Ya existe un usuario con el nombre proporcionado.");
                throw new RuntimeException();
            }

            UsuarioDTO actualUser = this.usuarioDAO.findById(request.getId()).orElseThrow(() -> new RuntimeException("No existe el usuario."));
            actualUser.setUsername(request.getNombre());
            actualUser.setApellidoPaterno(request.getApellidoPaterno());
            actualUser.setApellidoMaterno(request.getApellidoMaterno());
            actualUser.setClave(request.getClave());

            RolDTO rol = (request.isAdmin())
                    ? this.rolDAO.findByNombre("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("No existe ROLE_ADMIN."))
                    : this.rolDAO.findByNombre("ROLE_USER").orElseThrow(() -> new RuntimeException("No existe ROLE_USER."));

            actualUser.getRol().clear();
            actualUser.getRol().add(rol);

            this.usuarioDAO.save(actualUser);

            log.info("Usuario [{}] actualizado exitosamente.", request.getId());

            return actualUser;
        } catch (Exception e) {
            log.error("Ocurrió un error al registrar el nuevo usuario.");
            return HttpResponseCte.ERROR_GENERAL.getCodigo();
        }
    }

    @Transactional
    public Object deleteById(Integer id) {
        try {
            log.info("Eliminando el usuario con ID [{}]", id);

            if (!this.usuarioDAO.existsById(id)) {
                log.error("No existe ningún usuario con el ID proporcionado.");
                throw new RuntimeException();
            }

            this.usuarioDAO.deleteById(id);

            log.error("El usuario con ID [{}] se elimino exitosamente.", id);

            return true;
        } catch (Exception e) {
            log.error("Ocurrió un error al registrar el nuevo usuario.");
            return HttpResponseCte.ERROR_GENERAL.getCodigo();
        }
    }
}
