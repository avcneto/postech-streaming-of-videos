package com.postechvideostreaming.videostreaming.util;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.dto.video.UpdateVideoDTO;
import org.junit.jupiter.api.Test;

import static com.postechvideostreaming.videostreaming.util.Validators.isNullOrEmptyOrBlank;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorsTest {

  @Test
  void shouldReturnTrueWhenObjectIsNull() {
    assertTrue(isNullOrEmptyOrBlank(null));
  }

  @Test
  void shouldReturnTrueWhenObjectIsEmpty() {
    assertTrue(isNullOrEmptyOrBlank(""));
  }

  @Test
  void shouldReturnTrueWhenObjectIsBlank() {
    assertTrue(isNullOrEmptyOrBlank(" "));
  }

  @Test
  void shouldReturnFalseWhenHasObject() {
    assertFalse(isNullOrEmptyOrBlank(new UpdateVideoDTO("title", "description", Category.DOCUMENTARY)));
  }
}
