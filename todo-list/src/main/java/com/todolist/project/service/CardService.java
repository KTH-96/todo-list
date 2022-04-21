package com.todolist.project.service;

import com.todolist.project.domain.card.Card;
import com.todolist.project.domain.card.CardRepository;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardListRequestDto;
import com.todolist.project.web.dto.CardListResponseDto;
import com.todolist.project.web.dto.CardUpdateDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CardService {

	private final CardRepository cardRepository;

	public int addCard(CardAddDto cardAddDto) {
		return cardRepository.add(cardAddDto.toEntity());
	}

	public int removeCard(Long id) {
		return cardRepository.remove(id);
	}

	public List<CardListResponseDto> findAll() {
		return cardRepository.findAll().stream().map(CardListResponseDto::toDto)
			.collect(Collectors.toList());
	}

	public List<CardListResponseDto> findByStatus(String cardStatus) {
		return cardRepository.findCardsByStatus(cardStatus).stream().map(CardListResponseDto::toDto)
			.collect(Collectors.toList());
	}

	public int updateCard(Long id, CardUpdateDto cardUpdateDto) {
		return cardRepository.update(id, cardUpdateDto.toEntity());
	}

	public CardListRequestDto findById(Long id) {
		Card card = cardRepository.findCardById(id);
		return new CardListRequestDto(card.getId(), card.getIndex(), card.getTitle(),
			card.getContents(), card.getWriter(), String.valueOf(card.getCardStatus()),
			card.getCreatedTime());
	}
}
