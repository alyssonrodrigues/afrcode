package br.com.afrcode.apps.egos.dominio.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import br.com.afrcode.apps.egos.dominio.OrdemServico;

@Repository
public class DaoJdbcOrdemServico implements DaoOrdemServico {

	private static final String CREATE_SQL = 
			"insert into ORDEM_SERVICO (descricao, valor, "
			+ "dataEntregaEmContrato, concluida) "
			+ "values (:descricao, :valor, "
			+ ":dataEntregaEmContrato, :concluida)";
	
	private static final String UPDATE_SQL =
			"update ORDEM_SERVICO "
			+ "set descricao = :descricao, "
			+ "valor = :valor, "
			+ "dataEntregaEmContrato = :dataEntregaEmContrato, "
			+ "concluida = :concluida "
			+ "where id = :id";
	
	private static final String DELETE_SQL =
			"delete from ORDEM_SERVICO "
			+ "where id = :id";

	private static final String FIND_BY_ID_SQL =
			"select id, descricao, valor, dataEntregaEmContrato, "
			+ "concluida from ORDEM_SERVICO "
			+ "where id = :id";
	
	private static final String FIND_ALL_SQL =
			"select id, descricao, valor, dataEntregaEmContrato, "
			+ "concluida from ORDEM_SERVICO";
	
	@Autowired
	private NamedParameterJdbcOperations jdbcTemplate;

	@Override
	public void salvar(OrdemServico ordemServico) {
		BeanPropertySqlParameterSource paramSource = 
				new BeanPropertySqlParameterSource(
						ordemServico);
		int r = jdbcTemplate.update(UPDATE_SQL, 
				paramSource);
		if (r == 0) {
			jdbcTemplate.update(CREATE_SQL,
					paramSource);
		}
	}

	@Override
	public void excluir(OrdemServico ordemServico) {
		jdbcTemplate.update(DELETE_SQL, 
				new MapSqlParameterSource("id", 
						ordemServico.getId()));
    }

	@Override
	public OrdemServico recuperarOrdemServico(Long id) {
		return jdbcTemplate.queryForObject(FIND_BY_ID_SQL, 
				new MapSqlParameterSource("id", id),
				BeanPropertyRowMapper.newInstance(
						OrdemServico.class));
	}

	@Override
	public Collection<OrdemServico> recuperarTodos() {
		return jdbcTemplate.query(FIND_ALL_SQL,
				BeanPropertyRowMapper.newInstance(
						OrdemServico.class));
	}
}
