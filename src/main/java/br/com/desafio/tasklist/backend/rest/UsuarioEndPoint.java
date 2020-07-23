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
import br.com.desafio.tasklist.backend.persistence.dto.usuario.UsuarioDto;
import br.com.desafio.tasklist.backend.persistence.dto.usuario.UsuarioUpdateDto;
import br.com.desafio.tasklist.backend.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author jose-nery
 *
 */
@CrossOrigin()
@Api(tags = "Usuário", description = "Recursos para testes da aplicação")
@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioEndPoint {
	
	@Autowired
	private UsuarioService usuarioService;
	

	@ApiOperation(value = "Retorna todos os Usuários")
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<List<UsuarioDto>>> listarTodos() {
		RespostaGenerica<List<UsuarioDto>> response = this.usuarioService.listarTodos();
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Pesquisa Usuário pelo Cpf")
	@GetMapping(produces = MediaType.APPLICATION_JSON, path = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<UsuarioDto>> pesquisarPorCpf(@PathVariable(value = "cpf") @NotEmpty String cpf) {
		RespostaGenerica<UsuarioDto> response = this.usuarioService.pesquisarPorCpf(cpf);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Cadastrar Usuário")
	@PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RespostaGenerica<UsuarioDto>> cadastrar(@Valid @RequestBody UsuarioDto usuarioDto) {
		RespostaGenerica<UsuarioDto> response = this.usuarioService.cadastrar(usuarioDto);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Editar Usuário")
	@PutMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, path = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<UsuarioDto>> editar(@PathVariable(value = "cpf") @NotEmpty String cpf, @Valid @RequestBody UsuarioUpdateDto usuarioUpdateDto) {
		RespostaGenerica<UsuarioDto> response = this.usuarioService.editar(cpf, usuarioUpdateDto);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	@ApiOperation(value = "Excluir Usuário")
	@DeleteMapping(produces = MediaType.APPLICATION_JSON, path = "/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<RespostaGenerica<UsuarioDto>> excluir(@PathVariable(value = "cpf") @NotEmpty String cpf) {
		RespostaGenerica<UsuarioDto> response = this.usuarioService.excluir(cpf);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
	}
	
	

}