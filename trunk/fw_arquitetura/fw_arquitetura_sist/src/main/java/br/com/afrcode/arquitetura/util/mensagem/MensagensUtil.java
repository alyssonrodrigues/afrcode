package br.com.afrcode.arquitetura.util.mensagem;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.Validate;

import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;

public class MensagensUtil {
	private static Properties msgsResourceBundle = null;

	static {
		InputStream isMsgsResourceBundle = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("Mensagens.properties");
		if (isMsgsResourceBundle != null) {
			msgsResourceBundle = new Properties();
			try {
				msgsResourceBundle.load(isMsgsResourceBundle);
			} catch (IOException e) {
				throw new ExcecaoNaoPrevista(e);
			}
		}
	}

	public static String recuperarMensagem(String codMensagem) {
		String mensagem = msgsResourceBundle.getProperty(codMensagem);
		Validate.notNull(mensagem, "mensagem " + codMensagem
				+ " não deve ser nula!");
		return mensagem;
	}

}
