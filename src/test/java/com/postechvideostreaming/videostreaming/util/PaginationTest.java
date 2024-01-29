package com.postechvideostreaming.videostreaming.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaginationTest {

  @Test
  void shouldReturnPagination() {
    var ten = 10;
    var five = 0;
    var total = 5L;
    var list = Arrays.asList("test", "test2");
    var pagination = new Pagination(list, ten, five, total);

    assertEquals(pagination.getLimit(), ten);
    assertEquals(pagination.getOffset(), five);
    assertEquals(pagination.getTotal(), total);
  }
}
