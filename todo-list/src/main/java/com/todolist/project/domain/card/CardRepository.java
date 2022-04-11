package com.todolist.project.domain.card;

import com.todolist.project.domain.CardStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class CardRepository {

    private final static String DELETE_CARD_SQL = "DELETE FROM card WHERE id = :id";
    private final static String FIND_CARD_SQL = "SELECT id, card_index, title, contents, writer, card_Status, created_date FROM card ORDER BY card_index ASC";
    private final static String FIND_ID_SQL = "SELECT id, card_index, title, contents, writer, card_status, created_date FROM card WHERE id = :id";
    private final static String UPDATE_CARD_SQL
            = "UPDATE card SET card_index = :index, title = :title, contents = :contents, card_status = :card_status, created_date = :createTime WHERE id = :id";
    private final static String INSERT_CARD_SQL
            = "INSERT INTO card(card_index, title, contents, writer, created_date, card_status) VALUES (:index,:title,:contents,:writer,:createTime,:card_status)";
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Card> rowMapper;

    public CardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.rowMapper = (rs, rowNum) -> {
                String status = rs.getString("card_Status");
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
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.query(FIND_ID_SQL, mapSqlParameterSource, rowMapper).stream()
                .findAny()
                .orElseThrow(
                        IllegalAccessError::new
                );
    }

    public List<Card> findAll() {
        return jdbcTemplate.query(FIND_CARD_SQL, rowMapper);
    }

    public int add(Card card, int size){
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("index", ++size);
        mapSqlParameterSource.addValue("title", card.getTitle());
        mapSqlParameterSource.addValue("contents", card.getContents());
        mapSqlParameterSource.addValue("writer", card.getWriter());
        mapSqlParameterSource.addValue("createTime", card.createTime());
        mapSqlParameterSource.addValue("card_status", CardStatus.TODO.name());
        return namedParameterJdbcTemplate.update(INSERT_CARD_SQL, mapSqlParameterSource);
    }

    public int remove(Long id){
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.update(DELETE_CARD_SQL, mapSqlParameterSource);
    }

    public int update(Long id, Card card) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("index", card.getCardIndex());
        mapSqlParameterSource.addValue("title", card.getTitle());
        mapSqlParameterSource.addValue("contents", card.getContents());
        mapSqlParameterSource.addValue("card_status", card.getCardStatus().name());
        mapSqlParameterSource.addValue("createTime", card.createTime());
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.update(UPDATE_CARD_SQL, mapSqlParameterSource);
    }
}
