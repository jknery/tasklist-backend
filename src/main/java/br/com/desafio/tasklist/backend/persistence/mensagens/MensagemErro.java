package br.com.desafio.tasklist.backend.persistence.mensagens;

/**
 * Mensagem do tipo erro.
 *
 * @author jose.nery
 */
public class MensagemErro extends Mensagem {

    /**
     * Construtor recebendo o conteúdo da mensagem do tipo erro.
     * 
     * @param conteudo
     *            conteúdo da mensagem
     */
    public MensagemErro(String conteudo) {
        super("error", conteudo);
    }
    
}
