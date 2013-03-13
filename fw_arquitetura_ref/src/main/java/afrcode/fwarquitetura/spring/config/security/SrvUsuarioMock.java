package afrcode.fwarquitetura.spring.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SrvUsuarioMock {

    public List<String> recuperarRolesPorUsuario(String login) {

        ArrayList<String> roles = new ArrayList<String>();
        roles.add(0, "ROLE_TESTE");
        roles.add(1, "ROLE_USER");
        return roles;
    }

}
