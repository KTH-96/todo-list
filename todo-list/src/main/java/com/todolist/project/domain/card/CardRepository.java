package com.todolist.project.domain.card;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CardRepository {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final RowMapper<Card> rowMapper;

	public CardRepository(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.rowMapper = (rs, rowNum) -> {
			String status = rs.getString("card_status");
			return new Card(
				rs.getLong("id"),
				rs.getInt("card_index"),
				rs.getString("title"),
				rs.getString("contents"),
				rs.getString("writer"),
				rs.getTimestamp("created_date").toLocalDateTime(),
				Enum.valueOf(CardStatus.class, status)
			);
		};
	}

	public Card findCardById(Long id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		ObjectMapper objectMapper = new ObjectMapper();
		mapSqlParameterSource.addValues(
			objectMapper.convertValue(Card.builder().id(id), Map.class));
		return namedParameterJdbcTemplate.queryForObject(SqlCommand.FIND_CARD_BY_ID,
			mapSqlParameterSource,
			rowMapper);
	}

	public List<Card> findCardsByStatus(String cardStatus) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		ObjectMapper objectMapper = new ObjectMapper();
		mapSqlParameterSource.addValues(
			objectMapper.convertValue(Card.builder().cardStatus(CardStatus.valueOf(cardStatus)),
				Map.class));
		return namedParameterJdbcTemplate.query(SqlCommand.FIND_CARD_BY_STATUS,
			mapSqlParameterSource,
			rowMapper);
	}

	public List<Card> findAll() {
		return namedParameterJdbcTemplate.query(SqlCommand.FIND_ALL_CARDS_ORDER_BY_ASC, rowMapper);

	}

	public int add(Card card) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		ObjectMapper objectMapper = new ObjectMapper();
		mapSqlParameterSource.addValues(objectMapper.convertValue(card, Map.class));
		return namedParameterJdbcTemplate.update(SqlCommand.INSERT_CARD, mapSqlParameterSource);
	}

	public int remove(Long id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		ObjectMapper objectMapper = new ObjectMapper();
		mapSqlParameterSource.addValues(
			objectMapper.convertValue(Card.builder().id(id), Map.class));
		return namedParameterJdbcTemplate.update(SqlCommand.DELETE_CARD_BY_ID,
			mapSqlParameterSource);
	}

	public int update(Long id, Card card) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		ObjectMapper objectMapper = new ObjectMapper();
		mapSqlParameterSource.addValues(objectMapper.convertValue(card, Map.class));
		return namedParameterJdbcTemplate.update(SqlCommand.UPDATE_CARD_BY_ID,
			mapSqlParameterSource);
	}
}
