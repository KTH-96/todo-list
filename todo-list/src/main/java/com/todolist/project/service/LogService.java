package com.todolist.project.service;

import com.todolist.project.domain.log.LogRepository;
import com.todolist.project.web.dto.LogResponseListDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogService {

	private final LogRepository logRepository;

	public List<LogResponseListDto> findAll() {
		return logRepository.findAll().stream().map(LogResponseListDto::toDto)
			.collect(Collectors.toList());
	}


	public int addLog(LogResponseListDto dto) {
		return logRepository.save(dto.toEntity());
	}
}
