package afrcode.fwarquitetura.apresentacao.managedbean;

import afrcode.fwarquitetura.util.mensagem.IMensagem;

/**
 * Superclasse de JSF managed beans.
 * 
 * Pretende-se que esta seja a superclasse de todos os managed beans JSF.
 * 
 * @author alyssonfr
 * 
 */
public class ManagedBeanAbstrato implements IManagedBean {

    @Override
    public void adicionarMensagem(IMensagem mensagem) {
        mensagem.tratarMensagem();
    }

}
