package br.com.afrcode.arquitetura.util.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import br.com.afrcode.arquitetura.spring.anotacoes.Componente;

@Componente
public class ApplicationMailSender {

	@Autowired
	private MailSender mailSender;

	public void enviarEmail(String from, String to, String[] cc,
			String subject, String text) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setCc(cc);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);
		mailSender.send(simpleMailMessage);
	}

}
