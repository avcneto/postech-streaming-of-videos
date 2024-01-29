package com.postechvideostreaming.videostreaming.domain.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

import static com.postechvideostreaming.videostreaming.domain.video.Order.ASC;
import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoSearchParams {
  private static final Long DAYS_TO_SUBTRACT = 365L;
  private static final Integer ZERO = 0;
  private static final Integer TEN = 10;

  private String videoId;
  private String title;
  private String description;
  private LocalDate creationDate = LocalDate.now().minusDays(DAYS_TO_SUBTRACT);
  private LocalDate endCreationDate = LocalDate.now();
  private Category category;
  private Order sort = Order.DESC;
  private Integer offset = ZERO;
  private Integer limit = TEN;

  public VideoSearchParams setSort(String sort) {
    this.sort = Order.convertStringToOrder(sort);
    return this;
  }

  public long getOffsetMultiplyLimit() {
    return (long) this.offset * this.limit;
  }

}
