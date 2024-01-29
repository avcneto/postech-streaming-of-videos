package com.postechvideostreaming.videostreaming.dto.favorite;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryCountTest {

  @Test
  public void shouldReturnCategoryCount() {
    Category category = Category.ACTION;
    Long count = 10L;
    CategoryCount categoryCount = new CategoryCount(category, count);

    assertEquals(category, categoryCount._id());
    assertEquals(count, categoryCount.count());
  }

}
