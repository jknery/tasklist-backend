package br.com.desafio.tasklist.backend.persistence.mensagens;

public class MensagemSucesso extends Mensagem {
	
    public MensagemSucesso(String conteudo) {
        super("success", conteudo);
    }

}
