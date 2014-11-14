package br.com.afrcode.arquitetura.is.util.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;

import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNaoPrevistaRemota;
import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNegocioRemota;
import br.com.afrcode.arquitetura.util.excecao.ExcecaoNegocio;

/**
 * Componente responsävel por encapsular exceções internas a um Sistema.
 * 
 * 
 */
public class ExcecoesInterceptor {

	@AroundInvoke
	public Object encapsularExcecoes(InvocationContext ctx) throws Exception {
		Object retVal = null;

		try {
			retVal = ctx.proceed();
		} catch (ExcecaoNegocio e) {
			// Exceções de negócio lançadas explicitamente.
			throw new ExcecaoNegocioRemota(e.getMessage(), e);
		} catch (ConstraintViolationException e) {
			// Exceções oriundas do Hibernate Validator.
			throw new ExcecaoNegocioRemota(e.getMessage(), e);
		} catch (ExcecaoNegocioRemota e) {
			// Houve lançamento explícito de ExcecaoNegocioRemota, seja por
			// conversão de ExceptionS (checked ExceptionS) de negócio ou
			// desnecessariamente. Este tratamento/re-throw ocorre apenas para
			// efeitos de legibilidade de código.
			throw new ExcecaoNegocioRemota(e.getMensagem(), e);
		} catch (Exception e) {
			throw new ExcecaoNaoPrevistaRemota(e);
		}

		return retVal;
	}

}
