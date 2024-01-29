package com.postechvideostreaming.videostreaming.dto.summary;

public record ViewDTO(
        Long total
) {

  public Long quantityView() {
    return total == null ? 0L : total;
  }
}
