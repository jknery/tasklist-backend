/**
 * 
 */
package br.com.desafio.tasklist.backend.service;

import java.util.List;
import java.util.Optional;

import br.com.desafio.tasklist.backend.persistence.dto.http.RespostaGenerica;
import br.com.desafio.tasklist.backend.persistence.dto.tarefa.TarefaDto;
import br.com.desafio.tasklist.backend.persistence.model.Tarefa;

/**
 * @author jose-nery
 *
 */
public interface TarefaService {

	RespostaGenerica<List<TarefaDto>> listarTodas();

	RespostaGenerica<TarefaDto> pesquisarPorCodigo(String codigo);
	
	Optional<Tarefa> recuperarEntidade(String codigo);
		
	RespostaGenerica<List<TarefaDto>> listarTodasTarefasComTag(String tag);

	RespostaGenerica<TarefaDto> cadastrar(TarefaDto tarefaDto);

	RespostaGenerica<TarefaDto> editar(String codigo, TarefaDto tarefaUpdateDto);

	RespostaGenerica<TarefaDto> excluir(String codigo);
	
}
