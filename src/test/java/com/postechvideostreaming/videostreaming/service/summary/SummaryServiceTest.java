package com.postechvideostreaming.videostreaming.service.summary;

import com.postechvideostreaming.videostreaming.dto.summary.SummaryDTO;
import com.postechvideostreaming.videostreaming.dto.summary.ViewDTO;
import com.postechvideostreaming.videostreaming.repository.favorite.FavoriteVideoRepository;
import com.postechvideostreaming.videostreaming.repository.video.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


class SummaryServiceTest {

    @InjectMocks
    private SummaryService summaryService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private FavoriteVideoRepository favoriteVideoRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testGetSummary() {
        when(videoRepository.count()).thenReturn(Mono.just(10L));
        when(favoriteVideoRepository.count()).thenReturn(Mono.just(5L));
        when(videoRepository.sumQuantityView()).thenReturn(Mono.just(new ViewDTO(50L)));
        Mono<SummaryDTO> resultMono = summaryService.getSummary();
        StepVerifier.create(resultMono)
                .expectNextMatches(summaryDTO ->
                        summaryDTO.total() == 10L &&
                                summaryDTO.QuantityOfFavoriteVideos() == 5L &&
                                summaryDTO.viewingMedia() == 5L)  // 50L / 10L = 5L
                .verifyComplete();
    }
}