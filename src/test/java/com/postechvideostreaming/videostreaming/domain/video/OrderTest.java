package com.postechvideostreaming.videostreaming.domain.video;

import com.postechvideostreaming.videostreaming.exception.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
  @Test
  public void testConvertStringToOrderValid() {
    assertEquals(Order.ASC, Order.convertStringToOrder("ASC"));
    assertEquals(Order.DESC, Order.convertStringToOrder("Desc"));
  }

  @Test
  public void testConvertStringToOrderInvalid() {
    assertThrows(BadRequestException.class, () -> Order.convertStringToOrder("InvalidOrder"));
    assertThrows(BadRequestException.class, () -> Order.convertStringToOrder("123"));
  }

}
