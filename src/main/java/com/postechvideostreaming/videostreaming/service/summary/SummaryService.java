package com.postechvideostreaming.videostreaming.service.summary;

import com.postechvideostreaming.videostreaming.dto.summary.SummaryDTO;
import com.postechvideostreaming.videostreaming.repository.favorite.FavoriteVideoRepository;
import com.postechvideostreaming.videostreaming.repository.video.VideoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public record SummaryService(
        VideoRepository videoRepository,
        FavoriteVideoRepository favoriteVideoRepository
) {

  public Mono<SummaryDTO> getSummary() {
    return Mono.zip(videoRepository.count(),
                    favoriteVideoRepository.count(),
                    videoRepository.sumQuantityView())
            .map(it -> new SummaryDTO(it.getT1(), it.getT2(), getViewAverage(it.getT3().quantityView(), it.getT1())));
  }

  private Long getViewAverage(Long quantityView, Long total) {
    return total == null || total == 0L ? 0 : quantityView / total;
  }
}
