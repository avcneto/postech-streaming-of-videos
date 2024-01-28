package com.postechvideostreaming.videostreaming.controller;

import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.domain.video.VideoSearchParams;
import com.postechvideostreaming.videostreaming.dto.video.UpdateVideoDTO;
import com.postechvideostreaming.videostreaming.dto.video.VideoDTO;
import com.postechvideostreaming.videostreaming.service.video.VideoService;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import static com.postechvideostreaming.videostreaming.util.Constants.X_API_VERSION_1;
import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequestMapping("/video")
public record VideoController(
        VideoService videoService,
        MinioClient minioClient
) {

  private static final String VIDEOS_UPLOAD_PATH_ID = "/upload/%s";
  private static final String UPLOAD_PATH = "/upload";

  @PostMapping(path = UPLOAD_PATH, consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE, headers = X_API_VERSION_1)
  public Mono<ResponseEntity<VideoDTO>> uploadVideo(Mono<FilePart> video,
                                                    @RequestPart String title,
                                                    @RequestPart String description,
                                                    @RequestPart String category
  ) {
    return videoService.uploadVideo(video, title, description, category)
            .flatMap(videoDto -> Mono.just(ResponseEntity
                    .created(URI.create(format(VIDEOS_UPLOAD_PATH_ID, videoDto.id())))
                    .body(videoDto))
            );
  }

  @PatchMapping(headers = X_API_VERSION_1)
  public Mono<ResponseEntity<VideoDTO>> updateVideById(
          @RequestBody UpdateVideoDTO updateVideoDTO,
          @RequestParam String videoId
  ) {
    return videoService.updateVideoById(updateVideoDTO, videoId)
            .flatMap(videoDTO -> Mono.just(ResponseEntity.ok(videoDTO)))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @GetMapping(headers = X_API_VERSION_1)
  public Mono<ResponseEntity<List<Video>>> getVideoByParam(@ModelAttribute Mono<VideoSearchParams> searchParams) {
    return videoService.getVideoByParam(searchParams)
            .collectList()
            .map(ResponseEntity::ok);
  }
}
