package com.todolist.project.domain.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.project.domain.ActionStatus;
import com.todolist.project.domain.CardStatus;
import com.todolist.project.domain.SqlCommand;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final RowMapper<Log> rowMapper;

	public LogRepository(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.rowMapper = (rs, rowNum) -> {
			String prev_status = rs.getString("prev_status");
			String current_status = rs.getString("current_status");
			String action_status = rs.getString("action_status");
			return new Log(
				rs.getLong("id"),
				rs.getString("title"),
				Enum.valueOf(CardStatus.class, prev_status),
				Enum.valueOf(CardStatus.class, current_status),
				Enum.valueOf(ActionStatus.class, action_status),
				rs.getTimestamp("action_time").toLocalDateTime()
			);
		};
	}

	public List<Log> findAll() {
		return namedParameterJdbcTemplate.query(SqlCommand.LOGS_ALL_SELECT_ORDER_BY_ASC, rowMapper);
	}

	public int save(Log log) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		ObjectMapper objectMapper = new ObjectMapper();
		mapSqlParameterSource.addValues(objectMapper.convertValue(log, Map.class));
		return namedParameterJdbcTemplate.update(SqlCommand.LOG_INSERT, mapSqlParameterSource);
	}

}
