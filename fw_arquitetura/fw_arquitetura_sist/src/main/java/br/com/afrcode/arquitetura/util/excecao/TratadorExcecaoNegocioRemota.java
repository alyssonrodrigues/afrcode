package br.com.afrcode.arquitetura.util.excecao;

import org.apache.commons.lang.Validate;

import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNegocioRemota;
import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNegocioRemota.Severidade;
import br.com.afrcode.arquitetura.util.mensagem.MensagemNegocioAbstrata;
import br.com.afrcode.arquitetura.util.mensagem.alerta.MensagemAlertaNegocio;
import br.com.afrcode.arquitetura.util.mensagem.erro.MensagemErroNegocio;
import br.com.afrcode.arquitetura.util.mensagem.info.MensagemInfoNegocio;

/**
 * Tratador de exceções ExcecaoNegocioRemota originadas em serviços remotos.
 * 
 * Ver ExcecoesInterceptor.
 * 
 * 
 */
public class TratadorExcecaoNegocioRemota implements
		ITratadorExcecao<ExcecaoNegocioRemota> {

	@Override
	public void tratarExcecao(ExcecaoNegocioRemota excecao) {
		Validate.notNull(excecao,
				"Uma instância de ExcecaoNegocio deve ser informada!");
		String msg = excecao.getMensagem();

		MensagemNegocioAbstrata mensagem = null;
		if (Severidade.ERRO.equals(excecao.getSeveridade())) {
			mensagem = new MensagemErroNegocio(msg);
		} else if (Severidade.ALERTA.equals(excecao.getSeveridade())) {
			mensagem = new MensagemAlertaNegocio(msg);
		} else if (Severidade.INFO.equals(excecao.getSeveridade())) {
			mensagem = new MensagemInfoNegocio(msg);
		} else {
			throw new IllegalArgumentException(
					"Tipo de mensagem não reconhecido!");
		}

		mensagem.tratarMensagem();
	}
}
