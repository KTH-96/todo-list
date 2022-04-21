package com.todolist.project.web.dto;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.domain.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardAddDto {

	private int cardIndex;
	private String title;
	private String contents;
	private String writer;
	private String cardStatus;

	public Card toEntity() {
		CardStatus card_status = CardStatus.valueOf(this.cardStatus);
		return Card.builder().index(cardIndex)
			.title(title)
			.contents(contents)
			.writer(writer)
			.cardStatus(CardStatus.valueOf(cardStatus)).build();
	}
}
