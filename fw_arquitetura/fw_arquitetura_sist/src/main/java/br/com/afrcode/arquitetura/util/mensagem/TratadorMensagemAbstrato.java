package br.com.afrcode.arquitetura.util.mensagem;

import org.apache.log4j.Logger;

import br.com.afrcode.arquitetura.util.mensagem.erro.TratadorMensagemErroNegocio;

/**
 * Superclasse para implementações de ITratadorMensagem.
 * 
 * 
 * @param <T>
 *            Subtipo de IMensagem
 */
public abstract class TratadorMensagemAbstrato<T extends IMensagem> implements
		ITratadorMensagem<T> {
	protected static final Logger LOG = Logger
			.getLogger(TratadorMensagemErroNegocio.class);

}
