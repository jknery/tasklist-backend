/**
 * 
 */
package br.com.desafio.tasklist.backend.service.mappers;

import org.springframework.stereotype.Service;

import br.com.desafio.tasklist.backend.persistence.dto.usuario.UsuarioDto;
import br.com.desafio.tasklist.backend.persistence.dto.usuario.UsuarioUpdateDto;
import br.com.desafio.tasklist.backend.persistence.model.Usuario;

/**
 * @author jose-nery
 *
 */
@Service
public final class UsuarioMapper extends GenericMapper<Usuario, UsuarioDto>{
	public UsuarioMapper() {
		super();
	}
	
	public Usuario mapperDtoToUpdate(String id, UsuarioUpdateDto dto, Usuario usuario) {

		usuario = super.dozer.map(dto, Usuario.class);
		usuario.setId(id);	
		return usuario;

	}
}
