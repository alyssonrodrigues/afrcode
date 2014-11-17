package br.com.afrcode.arquitetura.util.mensagem.info;

import br.com.afrcode.arquitetura.util.mensagem.AbstractMensagemNegocio;

/**
 * Classe representativa de uma mensagem de informação de negócio.
 * 
 * 
 */
public class MensagemInfoNegocio extends AbstractMensagemNegocio {
	private TratadorMensagemInfoNegocio tratador;

	public MensagemInfoNegocio() {
		super();
	}

	public MensagemInfoNegocio(String mensagem) {
		this.setMensagem(mensagem);
	}

	private TratadorMensagemInfoNegocio getTratador() {
		if (tratador == null) {
			tratador = new TratadorMensagemInfoNegocio();
		}
		return tratador;
	}

	@Override
	public void tratarMensagem() {
		getTratador().tratarMensagem(this);
	}

}
