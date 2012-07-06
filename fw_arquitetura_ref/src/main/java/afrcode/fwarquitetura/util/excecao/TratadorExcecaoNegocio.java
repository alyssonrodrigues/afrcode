package afrcode.fwarquitetura.util.excecao;

import org.apache.commons.lang.Validate;

/**
 * Tratador de execeções para o tipo específico de exceção {@link ExcecaoNegocio}.
 * 
 * @author alyssonfr
 * 
 */
public class TratadorExcecaoNegocio implements ITratadorExcecao<ExcecaoNegocio> {

    @Override
    public void tratarExcecao(ExcecaoNegocio excecao) {
        Validate.notNull(excecao, "Uma instância de ExcecaoNegocio deve ser informada!");
        excecao.getMensagem().tratarMensagem();
    }

}
