package com.todolist.project.web;

import com.todolist.project.service.LogService;
import com.todolist.project.web.dto.LogResponseListDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/logs")
@RestController
public class LogController {

	private final LogService logService;

	@GetMapping
	public List<LogResponseListDto> getLogList() {
		return logService.findAll();
	}

	@PostMapping
	public int add(@RequestBody LogResponseListDto dto) {
		return logService.addLog(dto);
	}
}
