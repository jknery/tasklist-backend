/**
 * 
 */
package br.com.desafio.tasklist.backend.persistence.dto.card;

import java.time.LocalDate;

import br.com.desafio.tasklist.backend.persistence.dto.BaseDto;
import br.com.desafio.tasklist.backend.persistence.dto.tarefa.TarefaDto;
import br.com.desafio.tasklist.backend.persistence.dto.usuario.UsuarioDto;

/**
 * @author jose-nery
 *
 */
public class KanbanCardDto extends BaseDto {

	private static final long serialVersionUID = -4135103229833494540L;
	
	private Long id;
	
	private UsuarioDto usuario = new UsuarioDto();
	
	private UsuarioDto usuarioFinal = new UsuarioDto();

	private TarefaDto tarefa = new TarefaDto();
	
	private LocalDate dataInicio = LocalDate.now();
	
	private LocalDate dataFim = LocalDate.now();
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioDto getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the usuarioFinal
	 */
	public UsuarioDto getUsuarioFinal() {
		return usuarioFinal;
	}

	/**
	 * @param usuarioFinal the usuarioFinal to set
	 */
	public void setUsuarioFinal(UsuarioDto usuarioFinal) {
		this.usuarioFinal = usuarioFinal;
	}

	/**
	 * @return the tarefa
	 */
	public TarefaDto getTarefa() {
		return tarefa;
	}

	/**
	 * @param tarefa the tarefa to set
	 */
	public void setTarefa(TarefaDto tarefa) {
		this.tarefa = tarefa;
	}

	/**
	 * @return the dataInicio
	 */
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	/**
	 * @param dataInicio the dataInicio to set
	 */
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	public LocalDate getDataFim() {
		return dataFim;
	}

	/**
	 * @param dataFim the dataFim to set
	 */
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	

}
