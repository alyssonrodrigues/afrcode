package afrcode.fwarquitetura.is.test.modelo.ejb.objetoemmemoria.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * TODO: Mover esta classe para src/test/java assim que for possível usar o jboss as embedded!
 * 
 * @author alyssonfr
 * 
 */
public class UmObjetoEmMemoria implements Serializable {
    public static final int NUM_OBJS_ASSOCIADOS = 10;
    private static final long serialVersionUID = 1L;
    private Long id;
    private String descricao;
    private UmObjetoEmMemoria umObjetoEmMemoriaAssociado;
    private Collection<UmObjetoEmMemoria> objetosEmMemoriaAssociados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void preencherComValoresPersistiveis() {
        setDescricao("Uma descrição");
        UmObjetoEmMemoria umObjAssociado = new UmObjetoEmMemoria();
        umObjAssociado.setDescricao("Um objeto associado");
        setUmObjetoEmMemoriaAssociado(umObjAssociado);
        for (int i = 0; i < NUM_OBJS_ASSOCIADOS; i++) {
            UmObjetoEmMemoria umObj = new UmObjetoEmMemoria();
            umObj.setDescricao("Um objeto associado em coleção");
            getObjetosEmMemoriaAssociados().add(umObj);
        }
    }

    public void validarSalvamento() {

    }

    public void validarExclusao() {

    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricacao) {
        this.descricao = descricacao;
    }

    /**
     * @return the umObjetoEmMemoriaAssociado
     */
    public UmObjetoEmMemoria getUmObjetoEmMemoriaAssociado() {
        return umObjetoEmMemoriaAssociado;
    }

    /**
     * @param umObjetoEmMemoriaAssociado the umObjetoEmMemoriaAssociado to set
     */
    public void setUmObjetoEmMemoriaAssociado(UmObjetoEmMemoria umObjetoEmMemoriaAssociado) {
        this.umObjetoEmMemoriaAssociado = umObjetoEmMemoriaAssociado;
    }

    /**
     * @return the objetosEmMemoriaAssociados
     */
    public Collection<UmObjetoEmMemoria> getObjetosEmMemoriaAssociados() {
        if (objetosEmMemoriaAssociados == null) {
            objetosEmMemoriaAssociados = new ArrayList<UmObjetoEmMemoria>();
        }
        return objetosEmMemoriaAssociados;
    }

    /**
     * @param objetosEmMemoriaAssociados the objetosEmMemoriaAssociados to set
     */
    public void setObjetosEmMemoriaAssociados(Collection<UmObjetoEmMemoria> objetosEmMemoriaAssociados) {
        this.objetosEmMemoriaAssociados = objetosEmMemoriaAssociados;
    }

}
