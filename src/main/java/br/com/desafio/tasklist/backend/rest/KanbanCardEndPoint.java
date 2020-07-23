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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.tasklist.backend.persistence.constantes.EstadoUtil;
import br.com.desafio.tasklist.backend.persistence.dto.card.EditCardDto;
import br.com.desafio.tasklist.backend.persistence.dto.card.KanbanCardDto;
import br.com.desafio.tasklist.backend.persistence.dto.http.RespostaGenerica;
import br.com.desafio.tasklist.backend.service.KanbanCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author jose-nery
 *
 */
@CrossOrigin()
@Api(tags = "Kanban Card", description = "Recursos para testes da aplicação")
@RestController
@RequestMapping(path = "/cards")
public class KanbanCardEndPoint {
	
	@Autowired
	private KanbanCardService kanbanCardService;
	
	@ApiOperation(value = "Retorna todas os Cards Tasks")
	@GetMapping(produces = MediaType.APPLICATION_JSON, path = "/all")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<List<KanbanCardDto>>> listarTodosCards() {
		RespostaGenerica<List<KanbanCardDto>> response = this.kanbanCardService.listarTodosCardsPorEstado(EstadoUtil.ALL);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Retorna todas os Cards Tasks Completos")
	@GetMapping(produces = MediaType.APPLICATION_JSON, path = "/completos")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<List<KanbanCardDto>>> listarTodosCardsCompletos() {
		RespostaGenerica<List<KanbanCardDto>> response = this.kanbanCardService.listarTodosCardsPorEstado(EstadoUtil.COMPLETE);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Retorna todas os Cards Tasks Incompletos")
	@GetMapping(produces = MediaType.APPLICATION_JSON, path = "/incompletos")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<List<KanbanCardDto>>> listarTodosCardsIncompletos() {
		RespostaGenerica<List<KanbanCardDto>> response = this.kanbanCardService.listarTodosCardsPorEstado(EstadoUtil.NO_COMPLETE);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Concluir Card")
	@PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, path = "/conclui-task")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<List<KanbanCardDto>>> concluirCard(@Valid @RequestBody EditCardDto editCardDto) {
		RespostaGenerica<List<KanbanCardDto>> response = this.kanbanCardService.concluirCard(editCardDto);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Relacionar Usuário a Task")
	@PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, path = "/usuarios-card")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RespostaGenerica<KanbanCardDto>> adicionarUsuarioACard(@Valid @RequestBody EditCardDto editCardDto) {
		RespostaGenerica<KanbanCardDto> response = this.kanbanCardService.adicionarUsuarioATarefa(editCardDto);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Excluir Usuário da Task")
	@PutMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, path = "/usuarios-card")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<KanbanCardDto>> excluirUsuarioDoCard(@Valid @RequestBody EditCardDto editCardDto) {
		RespostaGenerica<KanbanCardDto> response = this.kanbanCardService.excluirUsuarioDaTarefa(editCardDto);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
}
