package br.com.afrcode.arquitetura.modelo.entidade.povoador;

import br.com.afrcode.arquitetura.spring.anotacoes.Povoador;

/**
 * Classe cuja responsabilidade é iniciar o banco de dados com um schema inicial
 * sem dados.
 * 
 * Via configurações de TESTES presentes em
 * projeto/src/main/resources/hibernate-jpaProperties.properties é executado um
 * schema drop-create via Hibernate tools com base nas entidades encontradas em
 * classpath.
 * 
 * 
 */
@Povoador
public class IniciadorBancoDados extends PovoadorAbstrato {

	public IniciadorBancoDados() {
		super(IniciadorBancoDados.class);
	}

	@Override
	public void povoar() {
		// Nenhum dado é criado.
	}

	public static void main(String[] args) {
		new IniciadorBancoDados().executar();
	}

}
