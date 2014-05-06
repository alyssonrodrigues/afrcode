package br.com.afrcode.arquitetura.is.util.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNaoPrevistaRemota;
import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNegocioRemota;

/**
 * Componente respons�vel por encapsular exce��es internas a um Sistema.
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
			// Houve lan�amento expl�cito de ExcecaoNegocioRemota, seja por
			// convers�o de ExceptionS de neg�cio ou
			// desnecessariamente. Este tratamento/re-throw ocorre apenas para
			// efeitos de legibilidade de c�digo.
			// throw new ExcecaoNegocioRemota(e);
			throw new ExcecaoNegocioRemota(e.getMensagem(), e);
		} catch (Exception e) {
			throw new ExcecaoNaoPrevistaRemota(e);
		}

		return retVal;
	}

}
