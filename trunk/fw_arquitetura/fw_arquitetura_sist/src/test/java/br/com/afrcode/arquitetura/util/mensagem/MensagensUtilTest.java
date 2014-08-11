package br.com.afrcode.arquitetura.util.mensagem;

import org.junit.Assert;
import org.junit.Test;

import br.com.afrcode.arquitetura.teste.unitario.util.junit.AbstractCasoTesteEmMemoria;

public class MensagensUtilTest extends AbstractCasoTesteEmMemoria {

    @Test
    public void testarRecuperarMensagem() {
        final String chaveMsg = "msg";
        Assert.assertNotNull("Deveria ter recuperado uma msg!", MensagensUtil.recuperarMensagem(chaveMsg));
    }

}
