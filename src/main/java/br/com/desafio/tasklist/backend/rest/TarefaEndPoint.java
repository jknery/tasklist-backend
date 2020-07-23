/**
 * 
 */
package br.com.desafio.tasklist.backend.rest;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.tasklist.backend.persistence.anotations.NotEmpty;
import br.com.desafio.tasklist.backend.persistence.dto.http.RespostaGenerica;
import br.com.desafio.tasklist.backend.persistence.dto.tarefa.TarefaDto;
import br.com.desafio.tasklist.backend.service.TarefaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author jose-nery
 *
 */
@CrossOrigin()
@Api(tags = "Tarefa", description = "Recursos para testes da aplicação")
@RestController
@RequestMapping(path = "/tarefas")
public class TarefaEndPoint {

	@Autowired
	private TarefaService tarefaService;
	

	@ApiOperation(value = "Retorna todas as Tarefas")
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<List<TarefaDto>>> listarTodos() {
		RespostaGenerica<List<TarefaDto>> response = this.tarefaService.listarTodas();
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Pesquisa Tarefa pelo Código")
	@GetMapping(produces = MediaType.APPLICATION_JSON, path = "/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<TarefaDto>> pesquisarPorcodigo(@PathVariable(value = "codigo") @NotEmpty String codigo) {
		RespostaGenerica<TarefaDto> response = this.tarefaService.pesquisarPorCodigo(codigo);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Cadastrar Tarefa")
	@PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RespostaGenerica<TarefaDto>> cadastrar(@Valid @RequestBody TarefaDto tarefaDto) {
		RespostaGenerica<TarefaDto> response = this.tarefaService.cadastrar(tarefaDto);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Editar Tarefa")
	@PutMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, path = "/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<TarefaDto>> editar(@PathVariable(value = "codigo") @NotEmpty String codigo, @Valid @RequestBody TarefaDto tarefaUpdateDto) {
		RespostaGenerica<TarefaDto> response = this.tarefaService.editar(codigo, tarefaUpdateDto);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Excluir Tarefa")
	@DeleteMapping(produces = MediaType.APPLICATION_JSON, path = "/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<TarefaDto>> excluir(@PathVariable(value = "codigo") @NotEmpty String codigo) {
		RespostaGenerica<TarefaDto> response = this.tarefaService.excluir(codigo);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}

}
