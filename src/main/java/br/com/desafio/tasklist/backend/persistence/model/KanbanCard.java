/**
 * 
 */
package br.com.desafio.tasklist.backend.persistence.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author jose-nery
 *
 */
@Entity
@Table(name = "kanban_card")
public class KanbanCard extends EntidadeBase<Long> {

	private static final long serialVersionUID = -450222541143879857L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cpf")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "cpf_final")
	private Usuario usuarioFinal;

	@ManyToOne
	@JoinColumn(name = "codigo")
	private Tarefa tarefa;
	
	@Column(name = "data_inicio")
	private LocalDate dataInicio;
	
	@Column(name = "data_fim")
	private LocalDate dataFim;

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
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the tarefa
	 */
	public Tarefa getTarefa() {
		return tarefa;
	}

	/**
	 * @param tarefa the tarefa to set
	 */
	public void setTarefa(Tarefa tarefa) {
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

	/**
	 * @return the usuarioFinal
	 */
	public Usuario getUsuarioFinal() {
		return usuarioFinal;
	}

	/**
	 * @param usuarioFinal the usuarioFinal to set
	 */
	public void setUsuarioFinal(Usuario usuarioFinal) {
		this.usuarioFinal = usuarioFinal;
	}

	
	
	
}
