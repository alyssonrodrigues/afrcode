package afrcode.fwarquitetura.spring.config.security;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService extends ShaPasswordEncoder {

    public PasswordEncoderService() {
        super(1);
    }

    @Override
    public String encodePassword(String rawPass, Object salt) {
        rawPass = rawPass.toUpperCase().trim();
        super.setEncodeHashAsBase64(true);
        String pwdEncoded = super.encodePassword(rawPass, salt);
        return pwdEncoded;
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        boolean isPwdValid = super.isPasswordValid(encPass, rawPass, salt);
        return isPwdValid;
    }

}