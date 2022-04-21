package com.todolist.project.domain;

public class SqlCommand {

	public final static String DELETE_CARD_BY_ID
		= "DELETE FROM card WHERE id = :id";
	public final static String FIND_ALL_CARDS_ORDER_BY_ASC
		= "SELECT id, card_index, title, contents, writer, card_status, created_date FROM card ORDER BY card_index ASC";
	public final static String FIND_CARD_BY_ID
		= "SELECT id, card_index, title, contents, writer, card_status, created_date FROM card WHERE id = :id";
	public final static String UPDATE_CARD_BY_ID
		= "UPDATE card SET card_index = :index, title = :title, contents = :contents, card_status = :cardStatus, created_date = :createdTime WHERE id = :id";
	public final static String INSERT_CARD
		= "INSERT INTO card(card_index, title, contents, writer, card_status) VALUES (:index,:title,:contents,:writer,:cardStatus)";
	public final static String FIND_CARD_BY_STATUS
		= "SELECT id, card_index, title, contents, writer, card_status, created_date FROM card WHERE card_status = :cardStatus";

	public static final String LOGS_ALL_SELECT_ORDER_BY_ASC
		= "SELECT title, current_status, prev_status, action_status, action_time FROM LOG ORDER BY action_time ASC";
	public static final String LOG_INSERT
		= "INSERT INTO LOG (title, current_status, prev_status, action_status, action_time) VALUES (:title, :currentStatus, :prevStatus, :actionStatus, :actionTime)";
}
