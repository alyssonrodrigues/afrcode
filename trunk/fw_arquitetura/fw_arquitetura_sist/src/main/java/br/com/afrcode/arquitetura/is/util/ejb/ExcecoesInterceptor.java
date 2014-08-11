package br.com.afrcode.arquitetura.is.util.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;

import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNaoPrevistaRemota;
import br.com.afrcode.arquitetura.is.util.excecao.ExcecaoNegocioRemota;
import br.com.afrcode.arquitetura.util.excecao.ExcecaoNegocio;

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
        } catch (ExcecaoNegocio e) {
            // Exce��es de neg�cio lan�adas explicitamente.
            throw new ExcecaoNegocioRemota(e.getMessage(), e);
        } catch (ConstraintViolationException e) {
            // Exce��es oriundas do Hibernate Validator.
            throw new ExcecaoNegocioRemota(e.getMessage(), e);
        } catch (ExcecaoNegocioRemota e) {
            // Houve lan�amento expl�cito de ExcecaoNegocioRemota, seja por
            // convers�o de ExceptionS (checked ExceptionS) de neg�cio ou
            // desnecessariamente. Este tratamento/re-throw ocorre apenas para
            // efeitos de legibilidade de c�digo.
            throw new ExcecaoNegocioRemota(e.getMensagem(), e);
        } catch (Exception e) {
            throw new ExcecaoNaoPrevistaRemota(e);
        }

        return retVal;
    }

}
