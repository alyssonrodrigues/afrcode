package br.com.afrcode.apps.egos.spring.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource({"classpath:mailsender.properties"})
public class BeanMailSenderConfig {
	
	@Value("${mail.host}")
	private String host;
	@Value("${mail.port}")
	private String port;
	@Value("${mail.username}")
	private String username;
	@Value("${mail.password}")
	private String password;
	@Value("${mail.transport.protocol}")
	private String mailTransportProtocol;
	@Value("${mail.smtp.auth}")
	private String mailSmtpAuth;
	@Value("${mail.smtp.starttls.enable}")
	private String mailSmtpStartTtlsEnable;
	@Value("${mail.debug}")
	private String mailDebug;

	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(Integer.valueOf(port));
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.transport.protocol", 
				mailTransportProtocol);
		javaMailProperties.put("mail.smtp.auth", 
				mailSmtpAuth);
		javaMailProperties.put("mail.smtp.starttls.enable", 
				mailSmtpStartTtlsEnable);
		javaMailProperties.put("mail.debug", 
				mailDebug);
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
}
