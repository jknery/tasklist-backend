/**
 * 
 */
package br.com.desafio.tasklist.backend.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.desafio.tasklist.backend.persistence.dao.TarefaDao;
import br.com.desafio.tasklist.backend.persistence.dto.http.RespostaGenerica;
import br.com.desafio.tasklist.backend.persistence.dto.tarefa.TarefaDto;
import br.com.desafio.tasklist.backend.persistence.enums.MensagemEnum;
import br.com.desafio.tasklist.backend.persistence.model.KanbanCard;
import br.com.desafio.tasklist.backend.persistence.model.Tarefa;
import br.com.desafio.tasklist.backend.service.KanbanCardService;
import br.com.desafio.tasklist.backend.service.TarefaService;
import br.com.desafio.tasklist.backend.service.mappers.TarefaMapper;
import br.com.desafio.tasklist.backend.service.util.MensagemUtil;

/**
 * @author jose-nery
 *
 */
@Service
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TarefaServiceImpl implements TarefaService {

	private static final int QUANTIDADE_ZERO = 0;

	@Autowired
	private TarefaDao tarefaDao;
	
	@Autowired
	private KanbanCardService kanbanCardService;

	@Inject
	private TarefaMapper tarefaMapper;

	@Override
	public RespostaGenerica<List<TarefaDto>> listarTodas() {
		RespostaGenerica<List<TarefaDto>> resposta = new RespostaGenerica<>();

		if (!this.tarefaDao.findAll().isEmpty()) {

			resposta.setBody(this.tarefaMapper.mapperListEntity(this.tarefaDao.findAllByOrderByIdAsc()));
			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG002));

		} else {

			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_TAREFA_004));

		}

		return resposta;
	}

	@Override
	public RespostaGenerica<List<TarefaDto>> listarTodasTarefasComTag(String tag) {
		RespostaGenerica<List<TarefaDto>> resposta = new RespostaGenerica<>();

		List<Tarefa> tarefas = this.tarefaDao.findByTag(tag);

		if (!tarefas.isEmpty()) {

			resposta.setBody(this.tarefaMapper.mapperListEntity(tarefas));
			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG002));

		} else {

			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_TAREFA_004));

		}

		return resposta;
	}

	@Override
	public RespostaGenerica<TarefaDto> pesquisarPorCodigo(String codigo) {
		RespostaGenerica<TarefaDto> resposta = new RespostaGenerica<>();

		Tarefa tarefa = this.tarefaDao.findByCodigo(codigo).get();

		if (tarefa != null) {

			resposta.setBody(this.tarefaMapper.mapperEntity(tarefa));
			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG002));

		} else {

			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_TAREFA_004));

		}

		return resposta;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RespostaGenerica<TarefaDto> cadastrar(TarefaDto tarefaDto) {

		RespostaGenerica<TarefaDto> resposta = new RespostaGenerica<>();

		if (this.tarefaDao.findByCodigo(tarefaDto.getCodigo()) == null) {

			Tarefa tarefa = this.tarefaMapper.mapperDtoToCreate(tarefaDto);
			tarefa.setDataCriacao(LocalDate.now());

			Tarefa saved = this.tarefaDao.save(tarefa);
			resposta.setBody(this.tarefaMapper.mapperEntity(saved));
			resposta.setStatus(HttpStatus.CREATED.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_TAREFA_001));

		} else {

			resposta.setBody(tarefaDto);
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_TAREFA_006));

		}

		return resposta;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RespostaGenerica<TarefaDto> editar(String codigo, TarefaDto tarefaUpdateDto) {
		RespostaGenerica<TarefaDto> resposta = new RespostaGenerica<>();

		Tarefa tarefa = this.tarefaDao.findByCodigo(codigo).get();

		if (tarefaUpdateDto == null) {

			resposta.setBody(null);
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_TAREFA_005));

		} else if (tarefa != null) {

			Tarefa update = this.tarefaMapper.mapperDtoToUpdate(tarefa.getId(), tarefaUpdateDto, tarefa);

			resposta.setBody(this.tarefaMapper.mapperEntity(this.tarefaDao.save(update)));
			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_002));

		}

		return resposta;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RespostaGenerica<TarefaDto> excluir(String codigo) {
		RespostaGenerica<TarefaDto> resposta = new RespostaGenerica<>();

		Tarefa tarefa = this.tarefaDao.findByCodigo(codigo).get();

		if (this.validarExclusao(tarefa)) {

			resposta.setBody(this.tarefaMapper.mapperEntity(tarefa));
			this.tarefaDao.deleteById(tarefa.getId());
			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_003));

		} else {

			resposta.setBody(null);
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_007));

		}

		return resposta;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private Boolean validarExclusao(Tarefa tarefa) {
		
		List<KanbanCard> cards = this.kanbanCardService.recuperarEntidade(tarefa.getCodigo());
		
		int qntd = cards.stream().filter(card -> card.getDataFim() == null).collect(Collectors.toList()).size();
		
		if(qntd > QUANTIDADE_ZERO) {
			return false;
		}
		else {
			this.kanbanCardService.excluirCardsConcluidos(cards);
			return true;
		}
	}

	@Override
	public Optional<Tarefa> recuperarEntidade(String codigo) {
		return this.tarefaDao.findByCodigo(codigo);
	}

}
