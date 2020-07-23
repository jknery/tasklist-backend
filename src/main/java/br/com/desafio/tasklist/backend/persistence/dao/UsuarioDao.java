package br.com.desafio.tasklist.backend.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.tasklist.backend.persistence.model.Usuario;


@Repository
public interface UsuarioDao extends JpaRepository<Usuario, String>{
		
	List<Usuario> findAllByOrderByIdAsc();

}
