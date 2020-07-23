/**
 * 
 */
package br.com.desafio.tasklist.backend.service;

import java.util.List;

import br.com.desafio.tasklist.backend.persistence.dto.card.EditCardDto;
import br.com.desafio.tasklist.backend.persistence.dto.card.KanbanCardDto;
import br.com.desafio.tasklist.backend.persistence.dto.http.RespostaGenerica;
import br.com.desafio.tasklist.backend.persistence.model.KanbanCard;

/**
 * @author jose-nery
 *
 */
public interface KanbanCardService {
	
	RespostaGenerica<List<KanbanCardDto>> listarTodosCardsPorEstado(Integer state);
			
	RespostaGenerica<List<KanbanCardDto>> concluirCard(EditCardDto editCardDto);
	
	RespostaGenerica<KanbanCardDto> adicionarUsuarioATarefa(EditCardDto editCardDto);
	
	RespostaGenerica<KanbanCardDto> excluirUsuarioDaTarefa(EditCardDto editCardDto);
	
	List<KanbanCard> recuperarEntidade(String codigo);
	
	void excluirCardsConcluidos(List<KanbanCard> cards);

}
