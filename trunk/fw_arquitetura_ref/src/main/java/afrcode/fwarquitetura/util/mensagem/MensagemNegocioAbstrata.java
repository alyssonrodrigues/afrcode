package afrcode.fwarquitetura.util.mensagem;

/**
 * Superclasse para mensagens de negócio de diferentes tipos (erro, alerta, informação, etc.).
 * 
 * @author alyssonfr
 * 
 */
public abstract class MensagemNegocioAbstrata implements IMensagem {
    private String codMensagem;

    private String mensagem;

    @Override
    public String getCodMensagem() {
        return codMensagem;
    }

    @Override
    public void setCodMensagem(String codMensagem) {
        this.codMensagem = codMensagem;
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }

    @Override
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
