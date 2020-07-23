/**
 * 
 */
package br.com.desafio.tasklist.backend.service.mappers;

import org.springframework.stereotype.Service;

import br.com.desafio.tasklist.backend.persistence.dto.tarefa.TarefaDto;
import br.com.desafio.tasklist.backend.persistence.model.Tarefa;

/**
 * @author jose-nery
 *
 */
@Service
public class TarefaMapper extends GenericMapper<Tarefa, TarefaDto>{

	public TarefaMapper() {
		super();
	}
	
	public Tarefa mapperDtoToUpdate(Long id, TarefaDto dto, Tarefa tarefa) {

		tarefa = super.dozer.map(dto, Tarefa.class);
		tarefa.setId(id);	
		return tarefa;

	}

}
