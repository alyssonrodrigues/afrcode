package br.com.afrcode.arquitetura.util.mensagem;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;

/**
 * Superclasse para mensagens de negócio de diferentes tipos (erro, alerta,
 * informação, etc.).
 * 
 * 
 */
public abstract class MensagemNegocioAbstrata implements IMensagem {
	private static Properties msgsResourceBundle = null;

	private String codMensagem;

	private String mensagem;

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

	@Override
	public String getCodMensagem() {
		return codMensagem;
	}

	@Override
	public void setCodMensagem(String codMensagem) {
		this.codMensagem = codMensagem;
	}

	@Override
	public String getMensagem() {
		if (StringUtils.isBlank(mensagem)) {
			mensagem = MensagensUtil.recuperarMensagem(getCodMensagem());
		}
		return mensagem;
	}

	@Override
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
