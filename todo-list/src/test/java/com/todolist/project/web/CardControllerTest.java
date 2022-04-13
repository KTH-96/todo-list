package com.todolist.project.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.project.domain.CardStatus;
import com.todolist.project.service.CardService;
import com.todolist.project.web.dto.CardAddDto;
import com.todolist.project.web.dto.CardListDto;
import com.todolist.project.web.dto.CardUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
class CardControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CardService service;

    CardAddDto addDto;
    CardUpdateDto updateDto;
    CardListDto listDto;

    @BeforeEach
    void setUp() {
        updateDto = new CardUpdateDto(1, "title1", "content1", CardStatus.TODO);
        listDto = new CardListDto(1L, 1, "title1", "content1", "writer", "TODO", LocalDateTime.now());
        addDto = new CardAddDto(1, "title1", "content1", "writer", "TODO");
    }

    @Test
    void list() throws Exception {
        List<CardListDto> list = List.of(this.listDto);
        given(service.findAll())
                .willReturn(list);

        ResultActions actions = mockMvc.perform(get("/cards").accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk());
        //TODO: content().contentType(MediaType.APPLICATION_JSON) -> cast하면 에러나서 해결을 해야한다.
    }

    @Test
    void listByStatus() {
        //TODO: 테스트 작성하기
    }

    @Test
    void add() throws Exception {
        ResultActions actions = mockMvc.perform(post("/cards")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(addDto)));

        actions.andExpect(status().is2xxSuccessful());

        then(service).should(times(1)).addCard(any(CardAddDto.class));
    }

    @Test
    void remove() {
        //TODO: 테스트 작성하기
    }

    @Test
    void update() {
        //TODO: 테스트 작성하기
    }
}
