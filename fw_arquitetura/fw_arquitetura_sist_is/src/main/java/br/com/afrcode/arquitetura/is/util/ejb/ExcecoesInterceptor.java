package br.com.afrcode.arquitetura.is.util.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNaoPrevistaRemota;
import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNegocioRemota;

/**
 * Componente responsável por encapsular exceções internas a um Sistema.
 * 
 * 
 */
public class ExcecoesInterceptor {

	@AroundInvoke
	public Object encapsularExcecoes(InvocationContext ctx) throws Exception {
		Object retVal = null;

		try {
			retVal = ctx.proceed();
		} catch (ExcecaoNegocioRemota e) {
			// Houve lançamento explícito de ExcecaoNegocioRemota, seja por
			// conversão de ExceptionS de negócio ou
			// desnecessariamente. Este tratamento/re-throw ocorre apenas para
			// efeitos de legibilidade de código.
			// throw new ExcecaoNegocioRemota(e);
			throw new ExcecaoNegocioRemota(e.getMensagem(), e);
		} catch (Exception e) {
			throw new ExcecaoNaoPrevistaRemota(e);
		}

		return retVal;
	}

}
