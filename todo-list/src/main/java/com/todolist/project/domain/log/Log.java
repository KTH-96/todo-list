package com.todolist.project.domain.log;

import com.todolist.project.domain.ActionStatus;
import com.todolist.project.domain.CardStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Log {

	private Long id;
	private String title;
	private CardStatus prevStatus;
	private CardStatus currentStatus;
	private ActionStatus actionStatus;
	private LocalDateTime actionTime;


}
