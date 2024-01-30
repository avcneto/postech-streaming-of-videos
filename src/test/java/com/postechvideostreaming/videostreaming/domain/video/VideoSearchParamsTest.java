package com.postechvideostreaming.videostreaming.domain.video;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VideoSearchParamsTest {

    @Test
    void testDefaultValues() {
        VideoSearchParams videoSearchParams = new VideoSearchParams();

        assertEquals(null, videoSearchParams.getVideoId());
        assertEquals(null, videoSearchParams.getTitle());
        assertEquals(null, videoSearchParams.getDescription());
        assertEquals(LocalDate.now().minusDays(365L), videoSearchParams.getCreationDate());
        assertEquals(LocalDate.now(), videoSearchParams.getEndCreationDate());
        assertEquals(null, videoSearchParams.getCategory());
        assertEquals(Order.DESC, videoSearchParams.getSort());
        assertEquals(0, videoSearchParams.getOffset());
        assertEquals(10, videoSearchParams.getLimit());
    }

    @Test
    void testSetSort() {
        VideoSearchParams videoSearchParams = new VideoSearchParams();
        videoSearchParams.setSort("ASC");

        assertEquals(Order.ASC, videoSearchParams.getSort());
    }

    @Test
    void testGetOffsetMultiplyLimit() {
        VideoSearchParams videoSearchParams = new VideoSearchParams();
        videoSearchParams.setOffset(2);
        videoSearchParams.setLimit(5);

        assertEquals(10L, videoSearchParams.getOffsetMultiplyLimit());
    }
}