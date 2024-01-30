package com.postechvideostreaming.videostreaming.domain.video;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VideoTest {

    @Test
    void testNoArgsConstructor() {
        Video video = new Video();
        assertNull(video.getId());
        assertNull(video.getTitle());
        assertNull(video.getUrl());
        assertNull(video.getDescription());
        assertNull(video.getCategory());
        assertNull(video.getCreationDate());
        assertNull(video.getLastUpdate());
        assertNull(video.getVersion());
        assertEquals(0L, video.getQuantityView());
    }

    @Test
    void testAllArgsConstructor() {
        ZonedDateTime now = ZonedDateTime.now();
        Video video = new Video("1", "Title", "http://example.com", "Description", Category.ACTION,
                now, now, 1L, 100L);

        assertEquals("1", video.getId());
        assertEquals("Title", video.getTitle());
        assertEquals("http://example.com", video.getUrl());
        assertEquals("Description", video.getDescription());
        assertEquals(Category.ACTION, video.getCategory());
        assertEquals(now, video.getCreationDate());
        assertEquals(now, video.getLastUpdate());
        assertEquals(1L, video.getVersion());
        assertEquals(100L, video.getQuantityView());
    }

    @Test
    void testEqualsAndHashCode() {
        Video video1 = new Video("1", "Title", "http://example.com", "Description", Category.ACTION,
                ZonedDateTime.now(), ZonedDateTime.now(), 1L, 100L);
        Video video2 = new Video("1", "Title", "http://example.com", "Description",
                Category.ACTION, ZonedDateTime.now(), ZonedDateTime.now(), 1L, 100L);
        assertEquals(video1, video2);
        assertEquals(video1.hashCode(), video2.hashCode());
        video2.setTitle("New Title");
        assertNotEquals(video1, video2);
        assertNotEquals(video1.hashCode(), video2.hashCode());
    }
}
