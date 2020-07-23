/**
 * 
 */
package br.com.desafio.tasklist.backend.persistence.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.tasklist.backend.persistence.model.KanbanCard;

/**
 * @author jose-nery
 *
 */
@Repository
public interface KanbanCardDao extends JpaRepository<KanbanCard, Long> {
	
	List<KanbanCard> findAllByOrderByIdAsc(); 

	List<KanbanCard> findByDataFimIsNotNullOrderByIdAsc(); 

	List<KanbanCard> findByDataFimIsNullOrderByIdAsc();
	
	Optional<KanbanCard> findByUsuarioAndTarefa(String cpf, Long idTarefa);
	
	List<KanbanCard> findByTarefa(Long idTarefa);

}
