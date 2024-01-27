package com.postechvideostreaming.videostreaming.domain.video;

import static com.postechvideostreaming.videostreaming.util.Constants.ZONED_DATETIME_FORMAT;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.postechvideostreaming.videostreaming.dto.video.VideoDTO;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document
@NoArgsConstructor
@EqualsAndHashCode
public class Video {

  @Id
  @Setter(AccessLevel.NONE)
  private String id;
  private String title;
  private String url;
  private String description;
  private Category category;
  @JsonFormat(pattern = ZONED_DATETIME_FORMAT)
  private ZonedDateTime creationDate;
  @JsonFormat(pattern = ZONED_DATETIME_FORMAT)
  private ZonedDateTime lastUpdate;
  @Version
  private Long version;

  public Video(VideoDTO videoDTO) {
    this.id = videoDTO.id();
    this.title = videoDTO.title();
    this.url = videoDTO.url();
    this.description = videoDTO.description();
    this.category = videoDTO.category();
    this.creationDate = videoDTO.creationDate();
    this.lastUpdate = videoDTO.lastUpdate();
  }
}
