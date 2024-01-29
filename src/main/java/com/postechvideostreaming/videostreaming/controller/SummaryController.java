package com.postechvideostreaming.videostreaming.controller;

import com.postechvideostreaming.videostreaming.dto.summary.SummaryDTO;
import com.postechvideostreaming.videostreaming.service.summary.SummaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.postechvideostreaming.videostreaming.util.Constants.X_API_VERSION_1;

@Slf4j
@RestController
@RequestMapping("/summary")
public record SummaryController(
        SummaryService summaryService
) {

  @GetMapping(headers = X_API_VERSION_1)
  public Mono<ResponseEntity<SummaryDTO>> getSummary() {
    return summaryService.getSummary().map(ResponseEntity::ok);
  }
}
