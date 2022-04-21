package com.todolist.project.web.dto;

import com.todolist.project.domain.ActionStatus;
import com.todolist.project.domain.CardStatus;
import com.todolist.project.domain.log.Log;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LogResponseListDto {

	private String title;
	private String prevStatus;
	private String currentStatus;
	private String actionStatus;
	private LocalDateTime actionTime;

	public Log toEntity() {
		CardStatus prevStatus = CardStatus.valueOf(this.prevStatus);
		CardStatus currentStatus = CardStatus.valueOf(this.currentStatus);
		ActionStatus actionStatus = ActionStatus.valueOf(this.actionStatus);
		return Log.builder()
			.title(title)
			.prevStatus(prevStatus)
			.currentStatus(currentStatus)
			.actionStatus(actionStatus).build();
	}

	public static LogResponseListDto toDto(Log log) {
		return LogResponseListDto.builder()
			.title(log.getTitle())
			.prevStatus(log.getPrevStatus().name())
			.currentStatus(log.getCurrentStatus().name())
			.actionStatus(log.getActionStatus().name())
			.actionTime(log.getActionTime())
			.build();
	}
}
