package com.afrcode.dominio.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByNomeContainingIgnoreCase(String nome);

	Usuario findByLoginIgnoreCase(String login);

}
