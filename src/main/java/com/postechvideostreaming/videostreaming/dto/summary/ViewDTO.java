package com.postechvideostreaming.videostreaming.dto.summary;

import jakarta.validation.constraints.NotNull;

public record ViewDTO(
        @NotNull
        Long total
) {

  public Long quantityView() {
    return total == null ? 0L : total;
  }
}
