package com.todolist.project.domain.card;

import com.todolist.project.domain.CardStatus;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Card {

	private Long id;
	private int cardIndex;
	private String title;
	private String contents;
	private String writer;
	private LocalDateTime createdTime;
	private CardStatus cardStatus;

	public LocalDateTime createTime() {
		return LocalDateTime.now();
	}

	public Card(Long id, int cardIndex, String title, String contents, String writer,
		LocalDateTime createdTime, CardStatus cardStatus) {
		this.id = id;
		this.cardIndex = cardIndex;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.createdTime = createdTime;
		this.cardStatus = cardStatus;
	}

	public Card(int cardIndex, String title, String contents, String writer,
		CardStatus cardStatus) {
		this.cardIndex = cardIndex;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.cardStatus = cardStatus;
	}

	public Card(int cardIndex, String title, String contents, CardStatus cardStatus) {
		this.cardIndex = cardIndex;
		this.title = title;
		this.contents = contents;
		this.cardStatus = cardStatus;
	}
}
