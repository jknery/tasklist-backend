/**
 * 
 */
package br.com.desafio.tasklist.backend.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.desafio.tasklist.backend.persistence.constantes.EstadoUtil;
import br.com.desafio.tasklist.backend.persistence.dao.KanbanCardDao;
import br.com.desafio.tasklist.backend.persistence.dto.card.EditCardDto;
import br.com.desafio.tasklist.backend.persistence.dto.card.KanbanCardDto;
import br.com.desafio.tasklist.backend.persistence.dto.http.RespostaGenerica;
import br.com.desafio.tasklist.backend.persistence.enums.MensagemEnum;
import br.com.desafio.tasklist.backend.persistence.model.KanbanCard;
import br.com.desafio.tasklist.backend.persistence.model.Tarefa;
import br.com.desafio.tasklist.backend.persistence.model.Usuario;
import br.com.desafio.tasklist.backend.service.KanbanCardService;
import br.com.desafio.tasklist.backend.service.TarefaService;
import br.com.desafio.tasklist.backend.service.UsuarioService;
import br.com.desafio.tasklist.backend.service.mappers.KanbanCardMapper;
import br.com.desafio.tasklist.backend.service.util.MensagemUtil;

/**
 * @author jose-nery
 *
 */
@Service
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class KanbanCardServiceImpl implements KanbanCardService {

	@Autowired
	private KanbanCardDao kanbanCardDao;

	@Autowired
	private TarefaService tarefaService;

	@Autowired
	private UsuarioService usuarioService;

	@Inject
	private KanbanCardMapper kanbanCardMapper;

	@Override
	public RespostaGenerica<List<KanbanCardDto>> listarTodosCardsPorEstado(Integer state) {

		RespostaGenerica<List<KanbanCardDto>> resposta = new RespostaGenerica<>();

		List<KanbanCard> cards = null;

		if (state == EstadoUtil.ALL) {
			cards = this.kanbanCardDao.findAllByOrderByIdAsc();
		} else if (state == EstadoUtil.COMPLETE) {
			cards = this.kanbanCardDao.findByDataFimIsNotNullOrderByIdAsc();
		} else if (state == EstadoUtil.NO_COMPLETE) {
			cards = this.kanbanCardDao.findByDataFimIsNullOrderByIdAsc();
		}

		if (!cards.isEmpty()) {
			List<KanbanCardDto> cardsDto = this.kanbanCardMapper.mapperListEntity(cards);
			resposta.setBody(cardsDto);
			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG002));
		} else {
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_CARDS_004));
		}

		return resposta;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RespostaGenerica<List<KanbanCardDto>> concluirCard(EditCardDto editCardDto) {

		RespostaGenerica<List<KanbanCardDto>> resposta = new RespostaGenerica<>();

		Optional<Usuario> usuario = this.usuarioService.recuperarEntidade(editCardDto.getCpf());

		Optional<Tarefa> tarefa = this.tarefaService.recuperarEntidade(editCardDto.getCodigoTarefa());
		
		List<KanbanCard> cards = this.kanbanCardDao.findByTarefa(tarefa.get().getId());

		if (tarefa.isPresent() && usuario.isPresent() && !cards.isEmpty()) {
			
			this.realizarExclusaoTarefa(cards, usuario.get());			
			resposta.setBody(this.kanbanCardMapper.mapperListEntity(this.kanbanCardDao.saveAll(cards)));
			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_CARDS_003));

		} else if (cards.isEmpty() && tarefa.isPresent()) {

			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_004));
		} else {

			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_CARDS_004));

		}

		return resposta;
	}

	private void realizarExclusaoTarefa(List<KanbanCard> cards, Usuario usuario) {
		cards.stream().forEach(card -> {
			card.setDataFim(LocalDate.now());
			card.setUsuarioFinal(usuario);
		});
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RespostaGenerica<KanbanCardDto> adicionarUsuarioATarefa(EditCardDto editCardDto) {

		RespostaGenerica<KanbanCardDto> resposta = new RespostaGenerica<>();

		Optional<Usuario> usuario = this.usuarioService.recuperarEntidade(editCardDto.getCpf());

		Optional<Tarefa> tarefa = this.tarefaService.recuperarEntidade(editCardDto.getCodigoTarefa());

		if (tarefa.isPresent() && usuario.isPresent()) {

			resposta.setBody(this.kanbanCardMapper.mapperEntity(
					this.kanbanCardDao.save(this.kanbanCardMapper.mapperCreateCard(usuario.get(), tarefa.get()))));
			resposta.setStatus(HttpStatus.CREATED.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_CARDS_003));

		} else if (tarefa.isPresent()) {
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_004));
		} else {
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_TAREFA_004));

		}

		return resposta;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public RespostaGenerica<KanbanCardDto> excluirUsuarioDaTarefa(EditCardDto editCardDto) {
		RespostaGenerica<KanbanCardDto> resposta = new RespostaGenerica<>();

		Optional<Usuario> usuario = this.usuarioService.recuperarEntidade(editCardDto.getCpf());

		Optional<Tarefa> tarefa = this.tarefaService.recuperarEntidade(editCardDto.getCodigoTarefa());

		if (tarefa.isPresent() && usuario.isPresent()) {

			Optional<KanbanCard> card = this.kanbanCardDao.findByUsuarioAndTarefa(usuario.get().getId(),
					tarefa.get().getId());

			resposta.setBody(this.kanbanCardMapper.mapperEntity(this.kanbanCardDao
					.save(this.kanbanCardMapper.mapperExcluirUsuarioCard(usuario.get(), card.get()))));

			resposta.setStatus(HttpStatus.OK.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_CARDS_003));

		} else if (tarefa.isPresent()) {
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_USUARIO_004));
		} else {
			resposta.setStatus(HttpStatus.BAD_REQUEST.value());
			resposta.setMensagem(MensagemUtil.getMessage(MensagemEnum.MSG_TAREFA_004));

		}

		return resposta;
	}

	@Override
	public List<KanbanCard> recuperarEntidade(String codigo) {
		
		Optional<Tarefa> tarefa = this.tarefaService.recuperarEntidade(codigo);
		
		List<KanbanCard> cards = this.kanbanCardDao.findByTarefa(tarefa.get().getId());
		
		return !cards.isEmpty() ? null : cards;		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluirCardsConcluidos(List<KanbanCard> cards) {
		// TODO Auto-generated method stub
		
	}

}
