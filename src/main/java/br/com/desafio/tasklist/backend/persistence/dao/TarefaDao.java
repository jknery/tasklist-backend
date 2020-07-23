/**
 * 
 */
package br.com.desafio.tasklist.backend.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.tasklist.backend.persistence.model.Tarefa;

/**
 * @author jose-nery
 *
 */
@Repository
public interface TarefaDao extends JpaRepository<Tarefa, Long>{

	List<Tarefa> findAllByOrderByIdAsc(); 
	
	Optional<Tarefa> findByCodigo(String codigo);
	
	List<Tarefa> findByTag(String tag);

}
