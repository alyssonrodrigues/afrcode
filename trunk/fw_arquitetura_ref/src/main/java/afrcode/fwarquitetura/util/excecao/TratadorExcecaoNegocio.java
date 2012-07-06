package afrcode.fwarquitetura.util.excecao;

import org.apache.commons.lang.Validate;

/**
 * Tratador de exece��es para o tipo espec�fico de exce��o {@link ExcecaoNegocio}.
 * 
 * @author alyssonfr
 * 
 */
public class TratadorExcecaoNegocio implements ITratadorExcecao<ExcecaoNegocio> {

    @Override
    public void tratarExcecao(ExcecaoNegocio excecao) {
        Validate.notNull(excecao, "Uma inst�ncia de ExcecaoNegocio deve ser informada!");
        excecao.getMensagem().tratarMensagem();
    }

}
