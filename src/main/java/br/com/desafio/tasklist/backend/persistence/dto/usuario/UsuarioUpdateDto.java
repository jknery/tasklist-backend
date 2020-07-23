/**
 * 
 */
package br.com.desafio.tasklist.backend.persistence.dto.usuario;

import br.com.desafio.tasklist.backend.persistence.anotations.NotEmpty;
import br.com.desafio.tasklist.backend.persistence.dto.BaseDto;

/**
 * @author jose-nery
 *
 */
public class UsuarioUpdateDto extends BaseDto{
	
	private static final long serialVersionUID = -4376392041748252019L;

	@NotEmpty(message = "O Campo NOME é obrigatorio")
	private String nome;

	@NotEmpty(message = "O Campo EMAIL é obrigatorio")
	private String email;

	private String telefone;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
