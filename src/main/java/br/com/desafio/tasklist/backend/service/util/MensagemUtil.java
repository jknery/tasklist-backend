package br.com.desafio.tasklist.backend.service.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import br.com.desafio.tasklist.backend.persistence.enums.MensagemEnum;


public final class MensagemUtil {

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");

	private MensagemUtil() {
		super();
	}

	public static String getMessage(MensagemEnum mensagemEnum) {
		return new String(RESOURCE_BUNDLE.getString(mensagemEnum.toString()).getBytes());

	}

	public static String getMessage(MensagemEnum mensagemEnum, Object... args) {
		String mensagem = getMessage(mensagemEnum);
		if (args.length > 0) {
			mensagem = MessageFormat.format(mensagem, args);
		}
		return mensagem;
	}
}
