/**
 * 
 */
package br.com.desafio.tasklist.backend.persistence.dto.http;

import br.com.desafio.tasklist.backend.persistence.dto.BaseDto;

/**
 * @author jose-nery
 * @param <T>
 *
 */
public class RespostaGenerica<T> extends BaseDto {

	private static final long serialVersionUID = 7191738526534662124L;
	
	private String mensagem;
	
	private Integer status;
	
	private T body;

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the body
	 */
	public T getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(T body) {
		this.body = body;
	}
	
}
