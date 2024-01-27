package com.postechvideostreaming.videostreaming.controller;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import com.postechvideostreaming.videostreaming.dto.video.VideoDTO;
import com.postechvideostreaming.videostreaming.service.video.VideoService;
import io.minio.MinioClient;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/video")
public record VideoController(
    VideoService videoService,
    MinioClient minioClient
) implements Controller {

  private static final String VIDEOS_UPLOAD_PATH_ID = "/upload/%s";
  private static final String UPLOAD_PATH = "/upload";

  @PostMapping(path = UPLOAD_PATH, consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<VideoDTO>> uploadVideo(Mono<FilePart> video,
                                                    @RequestPart String title,
                                                    @RequestPart String description) {
    return videoService.uploadVideo(video, title, description)
        .flatMap(videoDto -> {
          var uri = URI.create(format(VIDEOS_UPLOAD_PATH_ID, videoDto.id()));

          return Mono.just(ResponseEntity.created(uri)
              .body(videoDto));
        });
  }

}
