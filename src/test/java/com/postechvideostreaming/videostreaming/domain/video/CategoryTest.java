package com.postechvideostreaming.videostreaming.domain.video;

import com.postechvideostreaming.videostreaming.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import static com.postechvideostreaming.videostreaming.domain.video.Category.convertStringToCategory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CategoryTest {

  @Test
  public void shouldConvertStringToCategory() {
    assertEquals(Category.ADVENTURE, convertStringToCategory("adventure"));
    assertEquals(Category.COMEDY, convertStringToCategory("comedy"));

  }

  @Test
  public void shouldConvertInvalidCategory() {

    try {
      Category.convertStringToCategory("invalid");
      fail("Expected BadRequestException to be thrown");
    } catch (BadRequestException ex) {
      assertEquals("400 BAD_REQUEST \"invalid category, the valid categories are: [ACTION, ADVENTURE, COMEDY, DRAMA, SCIENCE_FICTION, HORROR, ANIMATION, DOCUMENTARY, ROMANCE, SUSPENSE, MUSICAL, FANTASY, CRIME, HISTORICAL, OTHER]\"", ex.getMessage());
    }
  }

}
