package com.todolist.project.web.dto;

import com.todolist.project.domain.card.Card;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardListResponseDto {

	private Long id;
	private int cardIndex;
	private String title;
	private String contents;
	private String writer;
	private String cardStatus;
	private LocalDateTime createdTime;

	public static CardListResponseDto toDto(Card card) {
		return CardListResponseDto.builder()
			.id(card.getId())
			.cardIndex(card.getIndex())
			.title(card.getTitle())
			.contents(card.getContents())
			.writer(card.getWriter())
			.cardStatus(card.getCardStatus().name()).build();
	}
}
