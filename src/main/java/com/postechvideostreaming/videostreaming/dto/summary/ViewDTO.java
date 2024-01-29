package com.postechvideostreaming.videostreaming.dto.summary;

public record ViewDTO(
        Long quantityView
) {

  public Long quantityView() {
    return quantityView == null ? 0L : quantityView;
  }
}
