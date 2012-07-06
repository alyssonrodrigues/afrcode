package afrcode.fwarquitetura.spring.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("srvUsuarioMock")
public class SrvUsuarioMock {

    public List<String> recuperaFuncoesComputacionaisLiberadasParaUsuario(String login) {

        ArrayList<String> funcoesLiberadas = new ArrayList<String>();
        funcoesLiberadas.add(0, "ROLE_TESTE");
        funcoesLiberadas.add(1, "ROLE_USER");
        return funcoesLiberadas;
    }

}
