package br.com.afrcode.arquitetura.spring.config.util.verificadores;

public interface ConstantesPadroes {

	static final String BASE_PACKAGE = "br.com.afrcode";

	static final String PREFIXO_TESTE = "Teste";

	static final String SUFIXO_TESTE = "Test";

	static final String[] REGS_EXP_TESTES_SERVICO = new String[] { ".*\\.Servico.*Test$" };

	static final String[] REGS_EXP_TESTES_DAO = new String[] { ".*\\.Dao.*Test$" };

	static final String[] REGS_EXP_TESTES_COMPONENTE = new String[] { ".*\\..*Test$" };

	static final String[] REGS_EXP_TESTES_MBEAN = new String[] { ".*\\.MBean.*Test$" };
}
