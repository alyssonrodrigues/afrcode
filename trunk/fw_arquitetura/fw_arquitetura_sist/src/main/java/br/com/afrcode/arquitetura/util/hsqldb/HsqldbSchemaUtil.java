package br.com.afrcode.arquitetura.util.hsqldb;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class HsqldbSchemaUtil {
	private Logger LOG = Logger.getLogger(HsqldbSchemaUtil.class);

	private static final String HIBERNATE_DEFAULT_SCHEMA =
			"hibernate.default_schema";
	private static final String HIBERNATE_DEFAULT_SCHEMA_TU =
			"hibernate.default_schema_tu";

	private static final String DDL_CREATE_SCHEMA = "create schema %s";

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private HsqldbUtil hsqldbUtil;

	private void criarDefaultSchemaSeNecessario(String nomePropriedade) {
		String nome =
				applicationContext.getEnvironment()
						.getProperty(nomePropriedade);
		if (StringUtils.isNotBlank(nome)) {
			criarSchema(nome);
		}
	}

	private void criarSchema(String nome) {
		LOG.info("Criando schema " + nome
				+ " para uso por testes unitários ou aplicação com HSQLDB...");
		try {
			jdbcTemplate.execute(String.format(DDL_CREATE_SCHEMA, nome));
		} catch (BadSqlGrammarException e) {
			LOG.error(e);
		}
	}

	@PostConstruct
	protected void criarDefaultSchemaSeTUOuAplicacaoComHsqldb() {
		if (!hsqldbUtil.isEjbSpringApplicationContext()
				&& !hsqldbUtil.isExecutandoPovoadorStandAlone()
				&& !hsqldbUtil.isExecutandoTesteFuncional()
				&& !hsqldbUtil.isExecutandoTesteIntegracao()
				&& (hsqldbUtil.isHsqldbEmUsoParaTU() || hsqldbUtil
						.isHsqldbEmUsoParaAplicacao())) {

			String defaultSchema =
					hsqldbUtil.isHsqldbEmUsoParaAplicacao() ? HIBERNATE_DEFAULT_SCHEMA
							: HIBERNATE_DEFAULT_SCHEMA_TU;
			criarDefaultSchemaSeNecessario(defaultSchema);
		}
	}

}
