package br.com.afrcode.arquitetura.spring.config.security;

import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextInputCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * Classe responsável por prover autenticação de usuário no profile de Batch.
 * Por padrão os batchES serão executados com um usuário fixo definido abaixo.
 * 
 * {usuário, senha, principal, role} = {SEGURANCA, teste, SEGURANCA_PRINCIPAL,
 * ROLE_USER}.
 * 
 * 
 */
public class LoginModuleParaBatch implements LoginModule {
    public static final String USER = "SEGURANCA";
    public static final String PASSWD = "teste";
    public static final String PRINCIPAL = "SEGURANCA_PRINCIPAL";

    private String password;

    private String user;

    private Subject subject;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
            Map<String, ?> options) {
        this.subject = subject;

        try {
            TextInputCallback textCallback = new TextInputCallback("prompt");
            NameCallback nameCallback = new NameCallback("prompt");
            PasswordCallback passwordCallback = new PasswordCallback("prompt", false);

            callbackHandler.handle(new Callback[] { textCallback, nameCallback, passwordCallback });

            password = new String(passwordCallback.getPassword());

            user = nameCallback.getName();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean login() throws LoginException {

        if (!user.equals(USER)) {
            throw new LoginException("Usuário inválido. Use: \"" + USER + "\".");
        }

        if (!password.equals(PASSWD)) {
            throw new LoginException("Senha inválida. Use: \"" + PASSWD + "\".");
        }

        subject.getPrincipals().add(new Principal() {
            public String getName() {
                return PRINCIPAL;
            }
        });

        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }

}
