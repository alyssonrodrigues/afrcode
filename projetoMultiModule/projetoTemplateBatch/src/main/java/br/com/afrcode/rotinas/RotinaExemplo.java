package br.com.afrcode.rotinas;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.afrcode.arquitetura.spring.anotacoes.Componente;

@Componente
public class RotinaExemplo {
	private static Logger LOG = Logger.getLogger(RotinaExemplo.class);

	/**
	 * Execução de 15 em 15 segundos.
	 */
	@Scheduled(cron = "0,15,30,45 * * * * ?")
	public void executar() {
		LOG.info("Hello World!");
	}

}
