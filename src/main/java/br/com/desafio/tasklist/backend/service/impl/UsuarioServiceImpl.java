/**
 * 
 */
package br.com.desafio.tasklist.backend.service.impl;

import java.util.List;
import java.util.Optional;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.desafio.tasklist.backend.persistence.dao.UsuarioDao;
import br.com.desafio.tasklist.backend.persistence.dto.http.RespostaGenerica;
import br.com.desafio.tasklist.backend.persistence.dto.usuario.UsuarioDto;
import br.com.desafio.tasklist.backend.persistence.dto.usuario.UsuarioUpdateDto;
import br.com.desafio.tasklist.backend.persistence.enums.MensagemEnum;
import br.com.desafio.tasklist.backend.persistence.model.Usuario;
import br.com.desafio.tasklist.backend.service.UsuarioService;
import br.com.desafio.tasklist.backend.service.mappers.UsuarioMapper;
import br.com.desafio.tasklist.backend.service.util.MensagemUtil;

/**
 * @author jose-nery
 *
 */
@Service
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Inject
	private UsuarioMapper usuarioMapper;

	@Override
	public RespostaGenerica<UsuarioDto> pesquisarPorCpf(String cpf) {

		RespostaGenerica<UsuarioDto> resposta = new RespostaGenerica<>();

		if (this.usuarioDao.existsById(cpf)) {

			resposta.setBody(this.usuarioMapper.mapperEntity(this.usuarioDao.getOne(cpf)));
			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG002));

		} else {

			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_004));

		}

		return resposta;

	}

	@Override
	public RespostaGenerica<List<UsuarioDto>> listarTodos() {

		RespostaGenerica<List<UsuarioDto>> resposta = new RespostaGenerica<>();

		if (!this.usuarioDao.findAll().isEmpty()) {

			resposta.setBody(this.usuarioMapper.mapperListEntity(this.usuarioDao.findAllByOrderByIdAsc()));
			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG002));

		} else {

			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_004));

		}

		return resposta;

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RespostaGenerica<UsuarioDto> cadastrar(UsuarioDto usuarioDto) {

		RespostaGenerica<UsuarioDto> resposta = new RespostaGenerica<>();

		if (!this.usuarioDao.existsById(usuarioDto.getId())) {

			Usuario saved = this.usuarioDao.save(this.usuarioMapper.mapperDtoToCreate(usuarioDto));
			resposta.setBody(this.usuarioMapper.mapperEntity(saved));
			resposta.setStatus(HttpStatus.CREATED.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_001));

		} else {

			resposta.setBody(usuarioDto);
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_006));

		}

		return resposta;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RespostaGenerica<UsuarioDto> editar(String cpf, UsuarioUpdateDto usuarioUpdateDto) {

		RespostaGenerica<UsuarioDto> resposta = new RespostaGenerica<>();

		if (usuarioUpdateDto == null) {

			resposta.setBody(null);
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_005));

		} else if (this.usuarioDao.existsById(cpf)) {

			Usuario persisted = this.usuarioDao.getOne(cpf);
			Usuario update = this.usuarioMapper.mapperDtoToUpdate(cpf, usuarioUpdateDto, persisted);

			resposta.setBody(this.usuarioMapper.mapperEntity(this.usuarioDao.save(update)));
			resposta.setStatus(HttpStatus.CREATED.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_002));

		}

		return resposta;

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RespostaGenerica<UsuarioDto> excluir(String cpf) {
		
		RespostaGenerica<UsuarioDto> resposta = new RespostaGenerica<>();

		if (this.usuarioDao.existsById(cpf)) {

			Usuario persisted = this.usuarioDao.getOne(cpf);
			resposta.setBody(this.usuarioMapper.mapperEntity(persisted));
			this.usuarioDao.deleteById(persisted.getId());
			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_003));

		}
		else {
			
			resposta.setBody(null);
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_007));
			
		}

		return resposta;
	}

	@Override
	public Optional<Usuario> recuperarEntidade(String cpf) {
		return this.usuarioDao.findById(cpf);
	}

}
