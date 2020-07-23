/**
 * 
 */
package br.com.desafio.tasklist.backend.persistence.dto.card;

import br.com.desafio.tasklist.backend.persistence.anotations.NotEmpty;
import br.com.desafio.tasklist.backend.persistence.dto.BaseDto;

/**
 * @author jose-nery
 *
 */
public class EditCardDto extends BaseDto {

	private static final long serialVersionUID = 4328787543986817058L;

	@NotEmpty(message = "O Campo CPF é obrigatorio")
	private String cpf;
	
	@NotEmpty(message = "O Campo Cógido Tarefa é obrigatorio")
	private String codigoTarefa;
	
	@NotEmpty(message = "O Campo Id Card é obrigatorio")
	private Long idCard;

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the codigoTarefa
	 */
	public String getCodigoTarefa() {
		return codigoTarefa;
	}

	/**
	 * @param codigoTarefa the codigoTarefa to set
	 */
	public void setCodigoTarefa(String codigoTarefa) {
		this.codigoTarefa = codigoTarefa;
	}

	/**
	 * @return the idCard
	 */
	public Long getIdCard() {
		return idCard;
	}

	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(Long idCard) {
		this.idCard = idCard;
	}
		
}
