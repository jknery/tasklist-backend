/**
 * 
 */
package br.com.desafio.tasklist.backend.service;

import java.util.List;
import java.util.Optional;

import br.com.desafio.tasklist.backend.persistence.dto.http.RespostaGenerica;
import br.com.desafio.tasklist.backend.persistence.dto.usuario.UsuarioDto;
import br.com.desafio.tasklist.backend.persistence.dto.usuario.UsuarioUpdateDto;
import br.com.desafio.tasklist.backend.persistence.model.Usuario;

/**
 * @author jose-nery
 *
 */
public interface UsuarioService {
	
	RespostaGenerica<UsuarioDto> pesquisarPorCpf(String cpf);
	
	Optional<Usuario> recuperarEntidade(String cpf);
	
	RespostaGenerica<List<UsuarioDto>> listarTodos();
	
	RespostaGenerica<UsuarioDto> cadastrar(UsuarioDto usuarioDto);
	
	RespostaGenerica<UsuarioDto> editar(String cpf, UsuarioUpdateDto usuarioUpdateDto);
	
	RespostaGenerica<UsuarioDto> excluir(String cpf);

}
