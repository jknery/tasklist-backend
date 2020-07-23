/**
 * 
 */
package br.com.desafio.tasklist.backend.service.mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.desafio.tasklist.backend.persistence.dto.card.KanbanCardDto;
import br.com.desafio.tasklist.backend.persistence.model.KanbanCard;
import br.com.desafio.tasklist.backend.persistence.model.Tarefa;
import br.com.desafio.tasklist.backend.persistence.model.Usuario;

/**
 * @author jose-nery
 *
 */
@Service
public class KanbanCardMapper extends GenericMapper<KanbanCard, KanbanCardDto>{

	
	public KanbanCardMapper() {
		super();
	}
	
	@Override
	public KanbanCardDto mapperEntity(KanbanCard entidade) {
		return dozer.map(entidade, KanbanCardDto.class);
	}
	
	@Override
	public List<KanbanCardDto> mapperListEntity(List<KanbanCard> entidades) {

		return entidades.stream().map(item -> this.mapperEntity(item)).collect(Collectors.toList());

	}
	
	public KanbanCard mapperCreateCard(Usuario usuario, Tarefa tarefa) {
		
		KanbanCard card = new KanbanCard();
		
		card.setDataInicio(LocalDate.now());
			
		card.setUsuario(usuario);
		
		card.setTarefa(tarefa);
		
		card.setUsuarioFinal(usuario);
		
		return card;
	}
	
	public KanbanCard mapperExcluirUsuarioCard(Usuario usuario, KanbanCard card) {
				
		card.setUsuario(null);
				
		card.setUsuarioFinal(usuario);
		
		return card;
	}
}
