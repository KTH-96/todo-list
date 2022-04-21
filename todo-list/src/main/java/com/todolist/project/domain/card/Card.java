package com.todolist.project.domain.card;

import com.todolist.project.domain.CardStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Card {

	private Long id;
	private int index;
	private String title;
	private String contents;
	private String writer;
	private LocalDateTime createdTime;
	private CardStatus cardStatus;
}
