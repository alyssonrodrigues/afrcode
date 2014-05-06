package br.com.afrcode.arquitetura.apresentacao.managedbean;

import br.com.afrcode.arquitetura.util.mensagem.IMensagem;

/**
 * Interface de defini��o de JSF managed beans.
 * 
 * 
 */
public interface IManagedBean {

    /**
     * M�todo central de adi��o de mensagens cujo tratamento depende de seu tipo.
     * 
     * Ver IMensagem e ITratadorMensagem.
     * 
     * @param mensagem
     */
    void adicionarMensagem(IMensagem mensagem);

    /**
     * M�todo de adi��o de objetos em sess�o HTTP.
     * 
     * N�o � permitida a inclus�o de Entidades (objetos IEntidade) em sess�o HTTP - problemas relacionados: detached
     * object, stalled data, lazy initialization exceptions, resources leak, etc.
     * 
     * @param chave chave �nica do objeto em sess�o
     * @param objetoEmSessao um objeto para adi��o em sess�o
     * 
     * @throws IllegalArgumentException quando o objeto � na verdade uma IEntidade
     */
    void adicionarObjetoEmSessaoHttp(String chave, Object objetoEmSessao);

    /**
     * M�todo de remo��o de objetos em sess�o HTTP atrav�s de chave �nica identificadora.
     * 
     * @param chave
     */
    void removerObjetoEmSessaoHttp(String chave);

    /**
     * M�todo de obten��o de objetos em sess�o HTTP atrav�s de chave �nica identificadora.
     * 
     * @param chave
     * @return null ou o objeto em sess�o encontrado
     */
    Object getObjetoEmSessao(String chave);

}
