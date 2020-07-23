/**
 * 
 */
package br.com.desafio.tasklist.backend.persistence.dto.tarefa;

import br.com.desafio.tasklist.backend.persistence.anotations.NotEmpty;
import br.com.desafio.tasklist.backend.persistence.dto.BaseDto;

/**
 * @author jose-nery
 *
 */
public class TarefaDto extends BaseDto{

	private static final long serialVersionUID = -7404389874598809825L;
	
	@NotEmpty(message = "O Campo Código é obrigatorio")
	private String codigo;
	
	@NotEmpty(message = "O Campo Descrição é obrigatorio")
	private String descricao;
	
	@NotEmpty(message = "O Campo Prioridade é obrigatorio")
	private String prioridade;

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
	
	

}
