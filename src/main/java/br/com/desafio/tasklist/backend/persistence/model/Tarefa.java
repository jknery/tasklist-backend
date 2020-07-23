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
import javax.persistence.Table;

/**
 * @author jose-nery
 *
 */
@Entity
@Table(name = "tarefas")
public class Tarefa extends EntidadeBase<Long>{
	
	private static final long serialVersionUID = 3696754677380707716L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "codigo", unique = true, nullable = false)
	private String codigo;
	
	@Column(name = "tag", length = 20)
	private String tag;
	
	@Column(name = "descricao", length = 1000, nullable = false)
	private String descricao;
	
	@Column(name = "prioridade", nullable = false)
	private String prioridade;
	
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;
	
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
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the prioridade
	 */
	public String getPrioridade() {
		return prioridade;
	}

	/**
	 * @param prioridade the prioridade to set
	 */
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	/**
	 * @return the dataCriacao
	 */
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	

}
