package com.postechvideostreaming.videostreaming.controller;

import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.domain.video.VideoSearchParams;
import com.postechvideostreaming.videostreaming.dto.video.UpdateVideoDTO;
import com.postechvideostreaming.videostreaming.dto.video.VideoDTO;
import com.postechvideostreaming.videostreaming.exception.FailedDependencyException;
import com.postechvideostreaming.videostreaming.service.video.VideoService;
import com.postechvideostreaming.videostreaming.util.Pagination;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

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

  private static final String VIDEO_ID = "videoId";
  private static final String ID = "id";
  private static final String ERROR_READING_VIDEO = "Error reading the video's InputStream";
  private static final String STREAMING = "/streaming";
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
  public Mono<ResponseEntity<Pagination<Video>>> getVideoByParam(@ModelAttribute Mono<VideoSearchParams> searchParams) {
    return videoService.getVideoByParam(searchParams)
            .flatMap(it -> Mono.just(ResponseEntity.ok(it)));
  }

  @DeleteMapping(headers = X_API_VERSION_1, params = VIDEO_ID)
  public Mono<ResponseEntity<Void>> deleteByVideoId(String videoId) {
    return videoService.deleteByVideoId(videoId)
            .then(Mono.fromCallable(() -> ResponseEntity.noContent().build()));
  }

  @GetMapping(path = STREAMING, params = ID)
  public Mono<ResponseEntity<byte[]>> getVideo(String id) {
    return videoService.getStreamingVideo(id)
            .flatMap(inputStream -> {
              try {
                byte[] bytes = IOUtils.toByteArray(inputStream);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.valueOf("video/mp4"));
                headers.setContentLength(bytes.length);
                headers.setCacheControl(CacheControl.noCache());

                return Mono.just(new ResponseEntity<>(bytes, headers, HttpStatus.OK));
              } catch (IOException ex) {
                log.error(ERROR_READING_VIDEO, ex);
                return Mono.error(new FailedDependencyException(ERROR_READING_VIDEO, ex));
              }
            });
  }
}
